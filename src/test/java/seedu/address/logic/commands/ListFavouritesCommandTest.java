package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonIsFavouriteContactPredicate;
import seedu.address.testutil.PersonBuilder;

class ListFavouritesCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonIsFavouriteContactPredicate predicate = new PersonIsFavouriteContactPredicate();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ListFavouritesCommand(), model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_noPersonFound() {
        // unfavourite all favourite persons
        List<Person> typicalPersons = model.getFilteredPersonList();
        for (Person originalPerson : typicalPersons) {
            if (originalPerson.getFavouriteStatus().isFavourite()) {
                Person unfavouritedPerson = new PersonBuilder(originalPerson).withFavourite("false").build();
                model.setPerson(originalPerson, unfavouritedPerson);
            }
        }
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonIsFavouriteContactPredicate predicate = new PersonIsFavouriteContactPredicate();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ListFavouritesCommand(), model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
}

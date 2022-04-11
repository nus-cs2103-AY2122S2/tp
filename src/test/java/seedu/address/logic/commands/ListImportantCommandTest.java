package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.ELLE;
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
import seedu.address.model.person.PersonHasHighImportancePredicate;
import seedu.address.testutil.PersonBuilder;

class ListImportantCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    void execute_multiplePersonsListed() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonHasHighImportancePredicate predicate = new PersonHasHighImportancePredicate();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ListImportantCommand(), model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, ELLE), model.getFilteredPersonList());
    }

    @Test
    void execute_noPersonListed() {
        // Remove high importance status for all contacts
        List<Person> typicalPersons = model.getFilteredPersonList();
        for (Person originalPerson : typicalPersons) {
            if (originalPerson.getHighImportanceStatus().hasHighImportance()) {
                Person highImportancePerson = new PersonBuilder(originalPerson).withHighImportance("false").build();
                model.setPerson(originalPerson, highImportancePerson);
            }
        }
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasHighImportancePredicate predicate = new PersonHasHighImportancePredicate();
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new ListImportantCommand(), model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }
}

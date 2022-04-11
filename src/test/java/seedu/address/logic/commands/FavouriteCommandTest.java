package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandUnExecuteSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class FavouriteCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withTelegram("@AliceInBorderland9393").withEmail("alice@example.com")
                .withFaculty("Business").withRole("Professor").withFavourite(true).withPhone("94351253")
                .withTags("friends").build();


        FavouriteCommand favouriteCommand = new FavouriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(FavouriteCommand.MESSAGE_FAVOURITE_PERSON_SUCCESS, alice);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), alice);

        assertCommandSuccess(favouriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_personAlreadyFavourite_failure() {
        Person person = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        assertTrue(person.getFavourite().isFavourite == true);

        FavouriteCommand favouriteCommand = new FavouriteCommand(INDEX_SECOND_PERSON);
        assertCommandFailure(favouriteCommand, model, favouriteCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void unExecute_favourite_successful() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        FavouriteCommand command = new FavouriteCommand(INDEX_FIRST_PERSON);

        assertCommandUnExecuteSuccess(command, model, expectedModel);
    }
}

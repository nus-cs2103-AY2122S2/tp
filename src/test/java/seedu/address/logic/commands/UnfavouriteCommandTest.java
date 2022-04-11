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

public class UnfavouriteCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person benson = new PersonBuilder().withName("Benson Meier")
                .withEmail("johnd@example.com").withTelegram("@bendsons332")
                .withPhone("98765432").withFaculty("Computing").withRole("TA")
                .withFavourite(false).withTags("owesMoney", "friends").build();

        UnfavouriteCommand unfavouriteCommand =
                new UnfavouriteCommand(INDEX_SECOND_PERSON);

        String expectedMessage =
                String.format(UnfavouriteCommand.MESSAGE_UNFAVOURITE_PERSON_SUCCESS,
                        benson);

        ModelManager expectedModel =
                new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), benson);

        assertCommandSuccess(unfavouriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_personAlreadyUnfavourite_failure() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        assertTrue(person.getFavourite().isFavourite == false);

        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(INDEX_FIRST_PERSON);
        assertCommandFailure(unfavouriteCommand, model, UnfavouriteCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void unExecute_unfavourite_successful() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        UnfavouriteCommand command =
                new UnfavouriteCommand(INDEX_SECOND_PERSON);

        assertCommandUnExecuteSuccess(command, model, expectedModel);
    }
}

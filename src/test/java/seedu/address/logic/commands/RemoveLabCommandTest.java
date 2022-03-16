package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithoutLabs;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.lab.Lab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemoveLabCommand}.
 */
public class RemoveLabCommandTest {

    @Test
    public void execute_validLab_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // Lab with lab_number 1 does exist in the TypicalAddressBook.
        RemoveLabCommand command = new RemoveLabCommand(new Lab("1"));

        String expectedMessage = RemoveLabCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(getTypicalAddressBookWithoutLabs(), new UserPrefs());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidLab_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // Lab with lab_number 3 does not exist in the TypicalAddressBook.
        RemoveLabCommand command = new RemoveLabCommand(new Lab("3"));

        String expectedMessage = RemoveLabCommand.MESSAGE_LAB_NOT_FOUND;

        assertCommandFailure(command, model, expectedMessage);
    }
}

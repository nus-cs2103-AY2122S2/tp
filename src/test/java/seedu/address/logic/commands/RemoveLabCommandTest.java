package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBookWithoutLabs;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lab.Lab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemoveLabCommand}.
 */
public class RemoveLabCommandTest {

    @Test
    public void constructor_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveLabCommand(null));
    }

    @Test
    public void constructor_validLab_success() {
        assertDoesNotThrow(() -> new RemoveLabCommand(new Lab("1")));
    }

    @Test
    public void execute_validLab_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // Lab with lab_number 1 does exist in the TypicalAddressBook.
        RemoveLabCommand command = new RemoveLabCommand(new Lab("1"));

        String expectedMessage = String.format(RemoveLabCommand.MESSAGE_SUCCESS, "1");

        Model expectedModel = new ModelManager(getTypicalAddressBookWithoutLabs(), new UserPrefs());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidLab_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // Lab with lab_number 3 does not exist in the TypicalAddressBook.
        RemoveLabCommand command = new RemoveLabCommand(new Lab("3"));

        String expectedMessage = String.format(RemoveLabCommand.MESSAGE_LAB_NOT_FOUND, "3");

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals_success() {
        RemoveLabCommand command1 = new RemoveLabCommand(new Lab("1"));
        RemoveLabCommand command2 = new RemoveLabCommand(new Lab("1"));
        assertEquals(command1, command2);
    }

    @Test
    public void equals_failure() {
        RemoveLabCommand command1 = new RemoveLabCommand(new Lab("1"));
        RemoveLabCommand command2 = new RemoveLabCommand(new Lab("2"));
        assertNotEquals(command1, command2);
    }
}

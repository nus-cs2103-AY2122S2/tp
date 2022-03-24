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

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lab.Lab;

class AddLabCommandTest {

    @Test
    public void constructor_nullLab_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddLabCommand(null));
    }

    @Test
    public void constructor_validLab_success() {
        assertDoesNotThrow(() -> new AddLabCommand(new Lab("1")));
    }

    @Test
    public void execute_validLab_success() {
        Model model = new ModelManager(getTypicalAddressBookWithoutLabs(), new UserPrefs());

        AddLabCommand command = new AddLabCommand(new Lab("1"));

        String expectedMessage = String.format(AddLabCommand.MESSAGE_SUCCESS, "1");

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateLab_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        // TypicalAddressBook already has Lab 1
        AddLabCommand command = new AddLabCommand(new Lab("1"));

        String expectedMessage = String.format(AddLabCommand.MESSAGE_DUPLICATE_LAB, "1");

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_emptyStudentList_failure() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        // TypicalAddressBook already has Lab 1
        AddLabCommand command = new AddLabCommand(new Lab("1"));

        String expectedMessage = AddLabCommand.MESSAGE_EMPTY_STUDENT_LIST;

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals_success() {
        AddLabCommand command1 = new AddLabCommand(new Lab("1"));
        AddLabCommand command2 = new AddLabCommand(new Lab("1"));
        assertEquals(command1, command2);
    }

    @Test
    public void equals_failure() {
        AddLabCommand command1 = new AddLabCommand(new Lab("1"));
        AddLabCommand command2 = new AddLabCommand(new Lab("2"));
        assertNotEquals(command1, command2);
    }

}

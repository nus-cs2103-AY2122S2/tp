package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        // Initialise models without commandHistory and addressBookHistory
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_onlyCommandHistoryIsEmpty_commandExceptionThrown() {
        // Ensure AddressBookHistory is not empty
        model.saveCurrentAddressBookToHistory();

        UndoCommand undoCommand = new UndoCommand();
        assertThrows(CommandException.class, () -> undoCommand.execute(model));
    }

    @Test
    public void execute_onlyCommandHistoryIsEmpty_commandExceptionMessageMatchesNoCommandToUndoMessage() {
        // Ensure AddressBookHistory is not empty
        model.saveCurrentAddressBookToHistory();

        UndoCommand undoCommand = new UndoCommand();
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_NO_COMMAND_TO_UNDO);
    }

    @Test
    public void execute_onlyAddressBookHistoryIsEmpty_commandExceptionThrown() {
        // Ensure CommandHistory is not empty
        String testCommandWord = ListCommand.COMMAND_WORD;
        model.addToCommandHistory(testCommandWord);

        // Ensure AddressBookHistory is empty
        model.getAddressBookHistory().getAddressBooks().clear();

        UndoCommand undoCommand = new UndoCommand();
        assertThrows(CommandException.class, () -> undoCommand.execute(model));
    }

    @Test
    public void execute_onlyAddressBookHistoryIsEmpty_commandExceptionMessageMatchesNoPreviousAddressBookMessage() {
        // Ensure CommandHistory is not empty
        String testCommandWord = ListCommand.COMMAND_WORD;
        model.addToCommandHistory(testCommandWord);

        // Ensure AddressBookHistory is empty
        model.getAddressBookHistory().getAddressBooks().clear();

        UndoCommand undoCommand = new UndoCommand();
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_NO_COMMAND_TO_UNDO);
    }

    @Test
    public void execute_commandHistoryAndAddressBookHistoryAreNotEmpty_success() {
        // Using List as a test command to be undone
        ListCommand listCommand = new ListCommand();
        listCommand.execute(model);

        // Update CommandHistory
        String testCommandWord = ListCommand.COMMAND_WORD;
        model.addToCommandHistory(testCommandWord);

        // Update AddressBookHistory
        model.saveCurrentAddressBookToHistory();

        UndoCommand undoCommand = new UndoCommand();
        String previousCommand = testCommandWord;
        String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS, previousCommand);
        CommandResult expectedCommandResult =
                new CommandResult(expectedMessage, false, false, false, true);
        assertCommandSuccess(undoCommand, model, expectedCommandResult, expectedModel);
    }
}

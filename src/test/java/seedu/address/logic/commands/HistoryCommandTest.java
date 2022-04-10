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

public class HistoryCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_commandHistoryIsEmpty_commandExceptionThrown() {
        HistoryCommand historyCommand = new HistoryCommand();
        assertThrows(CommandException.class, () -> historyCommand.execute(model));
    }

    @Test
    public void execute_commandHistoryIsEmpty_commandExceptionMessageMatchesNoPreviousHistoryMessage() {
        HistoryCommand historyCommand = new HistoryCommand();
        assertCommandFailure(historyCommand, model, HistoryCommand.MESSAGE_NO_PREVIOUS_HISTORY);
    }

    @Test
    public void execute_commandHistoryIsNotEmpty_success() {
        // Add the same command to commandHistory in both model and expectedModel
        String testCommandWord = ListCommand.COMMAND_WORD;
        model.addToCommandHistory(testCommandWord);
        expectedModel.addToCommandHistory(testCommandWord);

        String expectedCommandHistoryAsString = expectedModel.getCommandHistory().display();
        String expectedMessage = String.format(HistoryCommand.MESSAGE_SUCCESS, expectedCommandHistoryAsString);

        HistoryCommand historyCommand = new HistoryCommand();
        assertCommandSuccess(historyCommand, model, expectedMessage, expectedModel);
    }
}

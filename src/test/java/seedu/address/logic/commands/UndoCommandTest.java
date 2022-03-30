package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {


    @Test
    public void execute_undoOnEmptyStack_failed() {

        //Create new model
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // Undo Command
        UndoCommand undoCommand = new UndoCommand();

        //execute undo command, expects a command failure
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_USAGE_FAILURE);

    }

    @Test
    public void execute_undoOnNonEmptyStack_success() throws CommandException {

        //Create new model
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        UndoCommand undoCommand = new UndoCommand();

        StackUndoRedo undoRedoStack = new StackUndoRedo();

        //Expected result
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        //Execute a command
        RedoableCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        CommandResult commandResult = deleteCommand.execute(model, undoRedoStack);
        deleteCommand.saveSuccessMessage(commandResult.getFeedbackToUser());

        undoRedoStack.push(deleteCommand);
        assertCommandSuccess(new UndoCommand(), model, undoRedoStack,
                String.format(UndoCommand.MESSAGE_USAGE_SUCCESS, commandResult.getFeedbackToUser()), expectedModel);

    }
}

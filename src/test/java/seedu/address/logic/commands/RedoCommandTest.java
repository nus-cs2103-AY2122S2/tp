package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RedoCommandTest {

    private static final StackUndoRedo EMPTY_STACK = new StackUndoRedo();

    //Create new model
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_undoOnEmptyStack_failed() {

        // Undo Command
        RedoCommand redoCommand = new RedoCommand();

        //execute undo command, expects a command failure
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_USAGE_FAILURE);

    }

    @Test
    public void execute_undoOnNonEmptyStack_success() throws CommandException {

        //Create new model
        StackUndoRedo undoRedoStack = new StackUndoRedo();

        //Expected result
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());


        //Execute a command
        RedoableCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        CommandResult commandResult = deleteCommand.execute(model, new CommandHistory(), undoRedoStack);
        deleteCommand.saveSuccessMessage(commandResult.getFeedbackToUser());
        undoRedoStack.push(deleteCommand);
        undoRedoStack.popUndo();


        assertCommandSuccess(new RedoCommand(), model, undoRedoStack,
                String.format(RedoCommand.MESSAGE_USAGE_SUCCESS, commandResult.getFeedbackToUser()), expectedModel);

    }


}

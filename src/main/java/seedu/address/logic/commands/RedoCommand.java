package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Redo the previously undone command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo Command : ";
    public static final String MESSAGE_FAILURE = "No more commands to redo!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model, undoRedoStack);

        if (!undoRedoStack.canRedo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        StackUndoRedo.ModelStack modelStack = undoRedoStack.popRedo();
        if (modelStack.getRedoableCommand() instanceof SwitchCommand) {
            return new CommandResult(MESSAGE_SUCCESS, false, false, false, false, false, true);
        }
        modelStack.getRedoableCommand().redo(model);
        return new CommandResult(MESSAGE_SUCCESS + modelStack.getCommandText());
    }

    @Override
    public void setData(StackUndoRedo undoRedoStack) {
        this.undoRedoStack = undoRedoStack;
    }
}

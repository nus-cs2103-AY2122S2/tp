package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;

/**
 * Represents a command that when executed undo most recent changes.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_UNDO_SUCCESS = "Undone previous command.";
    public static final String MESSAGE_OLDEST_STATE = "Already at the oldest state.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoIBook()) {
            throw new CommandException(MESSAGE_OLDEST_STATE);
        }

        model.undoIBook();
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }
}

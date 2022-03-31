package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;

/**
 * Represents a command that when executed redo most recent undone changes.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_REDO_SUCCESS = "Redone previously undone command.";
    public static final String MESSAGE_LATEST_STATE = "Already at the latest state.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoIBook()) {
            throw new CommandException(MESSAGE_LATEST_STATE);
        }

        model.redoIBook();
        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }
}

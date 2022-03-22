package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.exceptions.AtLatestStateException;
import seedu.ibook.model.exceptions.AtOldestStateException;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_REDO_SUCCESS = "Redone previously undone command.";
    public static final String MESSAGE_LATEST_STATE = "Already at the latest state.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.redoIBook();
        } catch (AtLatestStateException exception) {
            throw new CommandException(MESSAGE_LATEST_STATE);
        }

        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }
}

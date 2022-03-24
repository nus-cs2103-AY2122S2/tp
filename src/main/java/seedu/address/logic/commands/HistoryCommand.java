package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CommandHistory;
import seedu.address.model.Model;

/**
 * Displays previously used commands.
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";
    public static final String MESSAGE_NO_PREVIOUS_HISTORY = "No commands have been used yet.";
    public static final String MESSAGE_SUCCESS = "Displaying previously used commands "
            + "(from earliest to most recent):\n%1$s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.isCommandHistoryEmpty() || model.getCommandHistory() == null) {
            throw new CommandException(MESSAGE_NO_PREVIOUS_HISTORY);
        }
        CommandHistory commandHistory = model.getCommandHistory();
        return new CommandResult(String.format(MESSAGE_SUCCESS, commandHistory.display()));
    }
}

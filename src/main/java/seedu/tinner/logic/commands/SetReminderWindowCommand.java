package seedu.tinner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tinner.commons.core.Messages.MESSAGE_INVALID_REMINDER_WINDOW;

import seedu.tinner.logic.commands.exceptions.CommandException;
import seedu.tinner.model.Model;


/**
 * Sets the reminder window of the application to a given input value.
 */
public class SetReminderWindowCommand extends Command {

    public static final String COMMAND_WORD = "setWindow";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "\n"
            + "Function: Sets the reminder window to the given argument (in days)."
            + "\n"
            + "Format: " + COMMAND_WORD + " "
            + "REMINDER_WINDOW (must be a positive integer that is between 1 and 30 inclusive) "
            + "\n"
            + "Example: " + COMMAND_WORD + " 7";

    public static final String MESSAGE_SET_REMINDER_WINDOW_SUCCESS = "Set reminder window to %d days";


    private final int reminderWindow;

    public SetReminderWindowCommand(int reminderWindow) {
        this.reminderWindow = reminderWindow;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (reminderWindow < 1 || reminderWindow > 30) {
            throw new CommandException(MESSAGE_INVALID_REMINDER_WINDOW);
        }

        model.setReminderWindow(reminderWindow);

        return new CommandResult(String.format(MESSAGE_SET_REMINDER_WINDOW_SUCCESS, reminderWindow));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetReminderWindowCommand // instanceof handles nulls
                && this.reminderWindow == ((SetReminderWindowCommand) other).reminderWindow); // state check
    }
}

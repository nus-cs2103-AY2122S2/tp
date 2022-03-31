package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * A Window containing the list of Persons & their corresponding Reminders.
 */
public class ReminderWindowCommand extends Command {
    public static final String COMMAND_WORD = "rm";
    public static final String MESSAGE_REMINDERS_WINDOW_SUCCESS =
            "Successfully opened Reminders window!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens up the Reminders window "
            + "that lists reminders set for clients.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_REMINDERS_WINDOW_SUCCESS, false, false, false, false, false, true, false);
    }
}

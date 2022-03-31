package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Gather all the emails in current list and opens up email window.
 */
public class EmailCommand extends Command {

    public static final String COMMAND_WORD = "email";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows email list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_EMAIL_MESSAGE = "Opened email window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_EMAIL_MESSAGE, false, false,
                false, true, false);
    }
}

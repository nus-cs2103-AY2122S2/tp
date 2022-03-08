package seedu.address.logic.commands;

import seedu.address.model.ModelTrackermon;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommandTrackermon extends CommandTrackermon {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows Trackermon usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResultTrackermon execute(ModelTrackermon model) {
        return new CommandResultTrackermon(SHOWING_HELP_MESSAGE, true, false);
    }
}

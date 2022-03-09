package seedu.address.logic.commands;

import seedu.address.model.ModelTrackermon;

/**
 * Terminates Trackermon.
 */
public class ExitCommandTrackermon extends CommandTrackermon {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Trackermon as requested ...";

    @Override
    public CommandResultTrackermon execute(ModelTrackermon model) {
        return new CommandResultTrackermon(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}

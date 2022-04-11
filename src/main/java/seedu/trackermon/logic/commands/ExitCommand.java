package seedu.trackermon.logic.commands;

import seedu.trackermon.model.Model;

/**
 * Terminates Trackermon.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Trackermon as requested ...";

    /**
     * Executes a {@code Model} object.
     * @param model {@code Model} which the command should operate on.
     * @return a {@code CommandResult} object.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}

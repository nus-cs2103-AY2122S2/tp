package seedu.trackbeau.logic.commands;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting TrackBeau as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false,
                true, false, false,
                false, false, false, false, Panel.NO_CHANGE);
    }

}

package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full tutorial instructions for every command for display.
 */
public class TutorialCommand extends Command {

    public static final String COMMAND_WORD = "tutorial";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_TUTORIAL_MESSAGE = "Opened tutorial window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_TUTORIAL_MESSAGE, false, true, false, false, false, null, null);
    }
}

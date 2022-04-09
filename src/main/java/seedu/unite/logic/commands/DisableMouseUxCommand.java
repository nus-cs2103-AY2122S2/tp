package seedu.unite.logic.commands;

import seedu.unite.model.Model;

/**
 * Disables mouse interaction
 */
public class DisableMouseUxCommand extends Command {

    public static final String COMMAND_WORD = "disable_mouseUX";
    public static final String MESSAGE_SUCCESS = "mouse interaction disabled";

    @Override
    public CommandResult execute(Model model) {
        model.disableMouseUX();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package seedu.unite.logic.commands;

import seedu.unite.model.Model;

/**
 * Enables mouse interaction
 */
public class EnableMouseUxCommand extends Command {

    public static final String COMMAND_WORD = "enable_mouseUX";
    public static final String MESSAGE_SUCCESS = "mouse interaction enabled";

    @Override
    public CommandResult execute(Model model) {
        model.enableMouseUX();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private Class<? extends Command> commandClass;

    public HelpCommand() {
        this.commandClass = null;
    }

    public HelpCommand(Class<? extends Command> commandClass) {
        this.commandClass = commandClass;
    }

    @Override
    public CommandResult execute(Model model) {
        if (commandClass == null) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        } else {
            return new CommandResult(commandClass.)
        }
    }
}

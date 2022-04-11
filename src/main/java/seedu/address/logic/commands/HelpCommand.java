package seedu.address.logic.commands;

import seedu.address.model.DisplayType;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public final String commandWordInquiry;

    public HelpCommand() {
        this.commandWordInquiry = "DEFAULT";
    }

    public HelpCommand(String inquiredWord) {
        this.commandWordInquiry = inquiredWord;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, DisplayType.PERSON, commandWordInquiry);
    }
}

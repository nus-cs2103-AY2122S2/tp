package seedu.address.logic.commands;

import seedu.address.commons.core.DataType;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting HireLah as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, getCommandDataType(), false, true);
    }

    @Override
    public DataType getCommandDataType() {
        return null;
    }
}

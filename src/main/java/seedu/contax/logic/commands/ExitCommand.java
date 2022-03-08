package seedu.contax.logic.commands;

import seedu.contax.model.Model;
import seedu.contax.ui.ListContentType;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, ListContentType.UNCHANGED, false, true);
    }

}

package seedu.address.logic.commands;

import java.util.HashMap;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        HashMap<String, Boolean> settings = new HashMap<>();
        settings.put("exit", true);
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, settings);
    }

}

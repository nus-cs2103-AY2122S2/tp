package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Terminates McKitchen upon user request.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting McKitchen as requested...";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }
}

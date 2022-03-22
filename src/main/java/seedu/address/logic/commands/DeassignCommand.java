package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deassigns a student from a group in ArchDuke
 */
public class DeassignCommand extends Command {

    public static final String COMMAND_WORD = "deassign";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("hello from deassign");
    }
}

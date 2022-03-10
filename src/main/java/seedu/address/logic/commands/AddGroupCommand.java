package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ACADEMIC_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a student group to ArchDuke.
 */
public class AddGroupCommand extends Command {

    public static final String COMMAND_WORD = "addgroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student group to ArchDuke. "
            + "Parameters: "
            + "g/" + "GROUP_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + "g/" + "CS2103-W16-3";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Addgroup command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }

}

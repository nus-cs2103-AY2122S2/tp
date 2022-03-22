package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Deassigns a student from a group in ArchDuke.
 */
public class DeassignCommand extends Command {

    public static final String COMMAND_WORD = "deassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deassigns a student contact from an "
            + "existing group in ArchDuke. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_GROUP_NAME + "GROUP_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GROUP_NAME + "CS2103-W16-3";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Group: %2$s";

    private final Index index;
    private final Group group;

    /**
     * Creates a DeassignCommand to deassign the specified {@code Person}
     * at the specified {@code Index} from an existing {@code Group}.
     *
     * @param index Index of the student contact.
     * @param group An existing group to deassign the student contact.
     */
    public DeassignCommand(Index index, Group group) {
        requireAllNonNull(index, group);

        this.index = index;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), group));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeassignCommand)) {
            return false;
        }

        // state check
        DeassignCommand e = (DeassignCommand) other;
        return index.equals(e.index)
                && group.equals(e.group);
    }
}

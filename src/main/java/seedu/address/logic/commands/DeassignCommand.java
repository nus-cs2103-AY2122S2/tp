package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * Deassigns a student from an existing group in ArchDuke.
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

    public static final String MESSAGE_PERSON_DOES_NOT_EXIST = "%1$s does not exist in %2$s.";

    public static final String MESSAGE_DEASSIGN_SUCCESS = "Student %1$s deassigned from %2$s";

    public static final String MESSAGE_GROUP_DOES_NOT_EXIST = "This group does not exist in ArchDuke";

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
        requireNonNull(model);
        List<Person> persons = model.getFilteredPersonList();

        if (index.getZeroBased() >= persons.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!model.hasGroup(group)) {
            throw new CommandException(MESSAGE_GROUP_DOES_NOT_EXIST);
        }

        ObservableList<Group> groups = model.getFilteredGroupList();

        Group deassignedGroup = group;
        for (Group gp : groups) {
            if (gp.equals(group)) {
                deassignedGroup = gp;
                break;
            }
        }

        Person personToDeassign = persons.get(index.getZeroBased());

        if (!deassignedGroup.personExists(personToDeassign)) {
            throw new CommandException(String.format(
                    MESSAGE_PERSON_DOES_NOT_EXIST, personToDeassign.getName(), group));
        }

        model.deassignPerson(personToDeassign, deassignedGroup);
        return new CommandResult(String.format(MESSAGE_DEASSIGN_SUCCESS, personToDeassign.getName(), deassignedGroup));
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

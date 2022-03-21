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
 * Assigns a student to a group in ArchDuke
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a student contact to ArchDuke. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_GROUP_NAME + "GROUP_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GROUP_NAME + "CS2103-W16-3";

    public static final String MESSAGE_ASSIGN_SUCCESS = "Student %1$s assigned to %2$s";
    public static final String MESSAGE_GROUP_DOES_NOT_EXIST = "This group does not exist in ArchDuke";
    public static final String MESSAGE_PERSON_ALREADY_EXISTS = "%1$s already exists in %2$s.";

    private final Index index;
    private final Group group;

    /**
     * Creates an AssignCommand to add the specified {@code Person}.
     */
    public AssignCommand(Index index, Group group) {
        requireAllNonNull(index, group);
        this.index = index;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!model.hasGroup(group)) {
            throw new CommandException(MESSAGE_GROUP_DOES_NOT_EXIST);
        }

        ObservableList<Group> groupList = model.getFilteredGroupList();

        Group assignedGroup = group;
        for (Group gp : groupList) {
            if (gp.equals(group)) {
                assignedGroup = gp;
                break;
            }
        }

        Person personToAssign = lastShownList.get(index.getZeroBased());

        if (assignedGroup.personExists(personToAssign)) {
            throw new CommandException(String.format(
                    MESSAGE_PERSON_ALREADY_EXISTS, personToAssign.getName(), group));
        }

        model.assignPerson(personToAssign, assignedGroup);
        return new CommandResult(String.format(MESSAGE_ASSIGN_SUCCESS, personToAssign.getName(), assignedGroup));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignCommand)) {
            return false;
        }

        // state check
        AssignCommand e = (AssignCommand) other;
        return index.equals(e.index) && group.equals(e.group);
    }
}

package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.Person;

/**
 * Represents a Group in ArchDuke.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Group {

    // Identity fields
    private final GroupName groupName;

    /**
     * Constructs a {@code Group}.
     *
     * @param groupName A valid group name.
     */
    public Group(GroupName groupName) {
        requireNonNull(groupName);
        this.groupName = groupName;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    /**
     * Compares as a weaker notion of identity between two groups.
     *
     * @param otherGroup Another group object.
     * @return true as a boolean value if both groups have the same group name.
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.getGroupName().equals(getGroupName());
    }

    
}

package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a Group in ArchDuke.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Group {

    // Identity fields
    private final GroupName groupName;

    // Data fields
    private final Set<Person> persons = new HashSet<>();

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

    /**
     * Checks if both groups have the same identity and data fields.
     * This defines a stronger notion of equality between two groups.
     *
     * @param other Another group object.
     * @return true as a boolean value if both groups have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return otherGroup.getGroupName().equals(getGroupName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(groupName);
    }

    /**
     * Returns a String representation of a group.
     *
     * @return String representation of a group.
     */
    @Override
    public String toString() {
        return String.valueOf(getGroupName());
    }
}

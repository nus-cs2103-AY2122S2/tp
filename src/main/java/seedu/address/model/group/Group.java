package seedu.address.model.group;

/**
 * Represents a Group in ArchDuke.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Group {

    // Identity fields
    private final GroupName groupName;

    public Group(GroupName groupName) {
        this.groupName = groupName;
    }
}

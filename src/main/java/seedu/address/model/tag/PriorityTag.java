package seedu.address.model.tag;

/**
 * Represents a Tag in InternApply that shows the application's priority to the user.
 */
public class PriorityTag extends Tag {
    public PriorityTag(String tagName) {
        super(tagName, TagType.PRIORITY);
    }
}

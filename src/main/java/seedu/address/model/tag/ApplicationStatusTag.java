package seedu.address.model.tag;

/**
 * Represents a Tag in InternApply that shows the application status.
 */
public class ApplicationStatusTag extends Tag {
    public ApplicationStatusTag(String tagName) {
        super(tagName, TagType.APPLICATION_STATUS);
    }
}

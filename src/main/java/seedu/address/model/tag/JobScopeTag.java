package seedu.address.model.tag;

/**
 * Represents a Tag in InternApply that shows the job scopes for the application.
 */
public class JobScopeTag extends Tag {
    public JobScopeTag(String tagName) {
        super(tagName, TagType.JOB_SCOPE);
    }
}

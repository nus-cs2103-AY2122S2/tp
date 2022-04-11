package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in InternApply.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {
    public static final String MESSAGE_CONSTRAINTS =
            "Tag names should be alphanumeric, without spaces and not be empty";
    public static final String TAG_NAME_CONSTRAINTS = "Tag name should not be a priority or application status type";
    public static final String VALIDATION_REGEX = "^[A-Za-z0-9_]+$";

    public final String tagName;
    public final TagType tagType;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        String tagNameUpper = tagName.toUpperCase();
        if (PriorityTagType.contains(tagNameUpper)) {
            this.tagName = tagName;
            this.tagType = TagType.PRIORITY;
        } else if (ApplicationStatusTagType.contains(tagNameUpper)) {
            this.tagName = tagName;
            this.tagType = TagType.APPLICATION_STATUS;
        } else {
            this.tagName = tagName;
            this.tagType = TagType.JOB_SCOPE;
        }
    }

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     * @param tagType A valid tag type.
     */
    public Tag(String tagName, TagType tagType) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        this.tagType = tagType;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string matches priority or application status tag name
     */
    public static boolean isPriorityApplicationStatus(String test) {
        return (PriorityTagType.contains(test.toUpperCase()) || ApplicationStatusTagType.contains(test.toUpperCase()));
    }

    /**
     * Returns type of tag.
     */
    public TagType getTagType() {
        return tagType;
    }

    /**
     * Returns tag name.
     */
    public String getTagName() {
        return tagName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)
                && tagType.equals(((Tag) other).tagType)
                ); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return tagName;
    }

}

package seedu.unite.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.unite.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the unite.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;
    private Remark remark;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        remark = new Remark("");
    }

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     * @param tagRemark A tag remark
     */
    public Tag(String tagName, String tagRemark) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
        remark = new Remark(tagRemark);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getTagName() {
        return tagName;
    }

    /**
     * Returns true if both tags have the same description.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTag(Tag otherTag) {
        if (otherTag == this) {
            return true;
        }

        return otherTag != null
                && otherTag.getTagName().equals(getTagName());
    }

    public Remark getRemark() {
        return remark;
    }

    public void changeRemark(Remark remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}

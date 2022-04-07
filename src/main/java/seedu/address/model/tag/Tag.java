package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Tag in the address book.
 */
public abstract class Tag {
    public static final String CCA = "cca";
    public static final String EDUCATION = "education";
    public static final String INTERNSHIP = "internship";
    public static final String MODULE = "module";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        this.tagName = tagName;
    }

    public abstract String getTagString();

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return tagName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Tag
                && this.tagName.equals(((Tag) other).tagName));
    }

}

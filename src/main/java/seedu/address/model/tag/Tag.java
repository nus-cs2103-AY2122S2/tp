package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValid(String)}
 */
public class Tag implements Serializable {
    public static final Prefix PREFIX = new Prefix("t/", false);
    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String value;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        value = tagName.trim();
        checkArgument(isValid(value), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValid(String str) {
        return str.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && value.equals(((Tag) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + value + ']';
    }

    /**
     * Create a set of tags using a list of tag names.
     * @param tagNames the list of tag names.
     * @return a set of tags
     */
    public static Set<Tag> createSet(Collection<String> tagNames) {
        Set<Tag> tags = new HashSet<>();
        for (String str : tagNames) {
            tags.add(new Tag(str));
        }
        return tags;
    }

    /**
     * Create a set of tags using a list of tag names.
     * @param tagNames the list of tag names.
     * @return a set of tags
     */
    public static Set<Tag> createSet(String... tagNames) {
        return createSet(List.of(tagNames));
    }
}

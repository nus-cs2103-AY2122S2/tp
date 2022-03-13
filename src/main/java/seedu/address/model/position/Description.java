package seedu.address.model.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a description of a {@code Position}. Description is a text that briefly describes the nature of the
 * position that is available for application.
 * Guarantees: immutable; description is valid as declared in {@link #isValidDescriptionText(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Description text should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String descriptionText;

    /**
     * Constructs a {@code Description}.
     *
     * @param text A valid description text.
     */
    public Description(String text) {
        requireNonNull(text);
        checkArgument(isValidDescriptionText(text), MESSAGE_CONSTRAINTS);
        descriptionText = text;
    }

    /**
     * Returns true if a given string is a valid description text.
     */
    public static boolean isValidDescriptionText(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && descriptionText.equals(((Description) other).descriptionText)); // state check
    }

    @Override
    public int hashCode() {
        return descriptionText.hashCode();
    }

    @Override
    public String toString() {
        return descriptionText;
    }
}

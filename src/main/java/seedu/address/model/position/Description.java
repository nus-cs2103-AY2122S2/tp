package seedu.address.model.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a description of a {@code Position}. Description is a text that briefly describes the nature of the
 * position that is available for application.
 * Guarantees: immutable; description is valid as declared in {@link #isValidDescriptionText(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Description can contain any characters and spaces, and it should not be blank.\n"
                    + "Description should contain at least one alphanumeric character (e.g. \"1\" or \"a\")\n"
                    + "Length of description is restricted to a maximum of 200 characters.";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * After the first non-whitespace character, all characters are valid, including newline character.
     */
    public static final String VALIDATION_REGEX = "[^\\s][\\s\\S]{0,199}";

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

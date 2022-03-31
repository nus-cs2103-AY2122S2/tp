package seedu.address.model.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Requirement in the address book. Requirement can denote a skill/experience that a candidate needs for
 * a job position.
 * Guarantees: immutable; requirement is valid as declared in {@link #isValidRequirementText(String)}
 */
public class Requirement {

    public static final String MESSAGE_CONSTRAINTS =
            "Requirement text can contain any characters and spaces, and it should not be blank.\n"
                    + "Requirement text should contain at least one alphanumeric character (e.g. \"1\" or \"a\")\n"
                    + "Length of requirement text is restricted to a maximum of 30 characters.\n"
                    + "If you need to fit more information, consider splitting information over multiple requirements.";

    public static final String VALIDATION_REGEX = "(?=.*[\\p{Alnum}].*)([^\\s].{0,29})";

    public final String requirementText;

    /**
     * Constructs a {@code Requirement}.
     *
     * @param requirementText A valid requirement text.
     */
    public Requirement(String requirementText) {
        requireNonNull(requirementText);
        checkArgument(isValidRequirementText(requirementText), MESSAGE_CONSTRAINTS);
        this.requirementText = requirementText;
    }

    /**
     * Returns true if a given string is a valid requirement text.
     */
    public static boolean isValidRequirementText(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Requirement // instanceof handles nulls
            && requirementText.equals(((Requirement) other).requirementText)); // state check
    }

    @Override
    public int hashCode() {
        return requirementText.hashCode();
    }

    /**
     * Format requirement as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + requirementText + ']';
    }
}

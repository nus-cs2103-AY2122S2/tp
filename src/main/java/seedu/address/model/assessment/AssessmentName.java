package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Assessment's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAssessmentName(String)}
 */
public class AssessmentName {
    public static final String MESSAGE_CONSTRAINTS =
            "Assessment names should only contain alphanumeric characters and spaces, and should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String name;

    /**
     * Constructs a {@code AssessmentName}.
     *
     * @param value an assessment name.
     */
    public AssessmentName(String value) {
        requireNonNull(value);
        checkArgument(isValidAssessmentName(value), MESSAGE_CONSTRAINTS);
        name = value;
    }

    /**
     * Returns true if a given String is a valid assessment name.
     */
    public static boolean isValidAssessmentName(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssessmentName // instanceof handles nulls
                && name.equals(((AssessmentName) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

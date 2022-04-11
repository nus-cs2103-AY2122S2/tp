package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Assessment's name in the TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidAssessmentName(String)}
 */
public class AssessmentName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the Assessment Name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code AssessmentName}.
     *
     * @param assessmentName A valid assessment name.
     */
    public AssessmentName(String assessmentName) {
        requireNonNull(assessmentName);
        checkArgument(isValidAssessmentName(assessmentName), MESSAGE_CONSTRAINTS);
        value = assessmentName;
    }

    /**
     * Returns true if a given string is a valid assessment name.
     */
    public static boolean isValidAssessmentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssessmentName // instanceof handles nulls
                && value.equals(((AssessmentName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

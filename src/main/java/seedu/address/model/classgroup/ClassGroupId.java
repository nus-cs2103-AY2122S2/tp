package seedu.address.model.classgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a ClassGroup's ID in the TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassGroupId(String)}
 */
public class ClassGroupId {
    public static final String MESSAGE_CONSTRAINTS =
            "CLass Group ID should start with a letter followed by 2 numbers. Letter at the back is optional.";
    public static final String VALIDATION_REGEX = "^[A-Z]{1}\\d{2}[A-Z]?";
    public final String value;

    /**
     * Constructs a {@code ClassGroupId}.
     *
     * @param classGroupId A valid class group ID.
     */
    public ClassGroupId(String classGroupId) {
        requireNonNull(classGroupId);
        checkArgument(isValidClassGroupId(classGroupId.toUpperCase()), MESSAGE_CONSTRAINTS);
        value = classGroupId.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid class group ID.
     */
    public static boolean isValidClassGroupId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ClassGroupId
                && value.equals(((ClassGroupId) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

package seedu.address.model.classgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a ClassGroup's type in TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassGroupType(String)}
 */
public class ClassGroupType {
    public static final String MESSAGE_CONSTRAINTS =
            "CLass Group Type should only contain alphabets, and it should be 3 characters long";
    // regex is case-sensitive
    public static final String VALIDATION_REGEX = "[A-Z]{3}";
    public final String value;

    /**
     * Constructs a {@code ClassGroupType}.
     *
     * @param classGroupType A valid class group type.
     */
    public ClassGroupType(String classGroupType) {
        requireNonNull(classGroupType);
        checkArgument(isValidClassGroupType(classGroupType), MESSAGE_CONSTRAINTS);
        value = classGroupType;
    }

    /**
     * Returns true if a given string is a valid class group type.
     */
    public static boolean isValidClassGroupType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ClassGroupType
                && value.equals(((ClassGroupType) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

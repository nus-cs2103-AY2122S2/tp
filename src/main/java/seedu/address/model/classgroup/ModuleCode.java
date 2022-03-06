package seedu.address.model.classgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a ClassGroup's module code in TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {
    public static final String MESSAGE_CONSTRAINTS =
            "Module Code should only contain alphabets and numbers, and it should be 6 characters long";
    // regex currently supports CS-coded modules only, is case-sensitive
    public static final String VALIDATION_REGEX = "CS\\d{4}[A-Z]{0,1}";
    public final String value;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param moduleCode A valid module code.
     */
    public ModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        value = moduleCode;
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ModuleCode
                && value.equals(((ModuleCode) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

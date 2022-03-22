package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a module (in the form of a code) that a student is currently taking in TAPA.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {
    public static final String MESSAGE_CONSTRAINTS = "Module code should only contain alphanumeric "
            + "characters and should not be blank";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]+$";

    public final String moduleCode;

    /**
     * Constructs a {@code ModuleCode}
     * @param moduleCode A valid moduleCode that represents the student's matriculation number.
     */
    public ModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        this.moduleCode = moduleCode;
    }

    /**
     * Returns true if a given string is a valid module code.
     *
     * @param test the string that is being tested.
     * @return a boolean value (true/false) depending if the module code is valid.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return moduleCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && moduleCode.equals(((ModuleCode) other).moduleCode)); // state check
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }
}

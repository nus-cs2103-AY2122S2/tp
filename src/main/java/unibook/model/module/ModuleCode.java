package unibook.model.module;

import static java.util.Objects.requireNonNull;
import static unibook.commons.util.AppUtil.checkArgument;


/**
 * Represents a Module Code.
 */
public class ModuleCode {
    public static final String MESSAGE_CONSTRAINTS =
        "Module codes should only contain alphanumeric characters and cannot contain spaces.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]*$";

    public final String moduleCode;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param moduleCode A valid ModuleCode.
     */
    public ModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        this.moduleCode = moduleCode.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.moduleCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ModuleCode)// instanceof handles nulls
            && moduleCode.equals(((ModuleCode) other).moduleCode); // state check
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }
}

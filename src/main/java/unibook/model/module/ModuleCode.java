package unibook.model.module;

import static java.util.Objects.requireNonNull;
import static unibook.commons.util.AppUtil.checkArgument;


/**
 * Represents a Module Code.
 */
public class ModuleCode {
    public static final String MESSAGE_CONSTRAINTS =
        "Module codes should only contain up to 10 characters, cannot be blank and cannot contain spaces";

    /*
     * A module code can contain any character that is not a whitespace.
     */
    public static final String VALIDATION_REGEX = "\\S+";

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
        return test.matches(VALIDATION_REGEX) && test.length() <= 10;
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

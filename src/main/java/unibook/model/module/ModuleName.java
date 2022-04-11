package unibook.model.module;

import static java.util.Objects.requireNonNull;
import static unibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module Name.
 */
public class ModuleName {

    public static final String MESSAGE_CONSTRAINTS = " Module names "
                    + "should only contain up to 50 alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\s\\w]+$";

    public final String moduleName;

    /**
     * Constructs a {@code ModuleName}.
     *
     * @param moduleName A valid moduleName.
     */
    public ModuleName(String moduleName) {
        requireNonNull(moduleName);
        checkArgument(isValidModuleName(moduleName), MESSAGE_CONSTRAINTS);
        this.moduleName = moduleName;
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 50;
    }


    @Override
    public String toString() {
        return this.moduleName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ModuleName)// instanceof handles nulls
            && moduleName.equals(((ModuleName) other).moduleName); // state check
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }

}

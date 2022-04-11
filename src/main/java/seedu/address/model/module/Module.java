package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module in the Module list.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class Module implements Comparable<Module> {

    public static final String MESSAGE_CONSTRAINTS =
            "Modules should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the module must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String code;

    /**
     * Constructs a {@code Module}.
     *
     * @param code A valid module.
     */
    public Module(String code) {
        requireNonNull(code);
        checkArgument(isValidModule(code), MESSAGE_CONSTRAINTS);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    /**
     * Returns true if a given string is a valid module.
     *
     * @param test The {@code String} to test.
     * @return True, if the {@code String} is a valid module.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return '[' + code + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && code.equals(((Module) other).code)); // state check
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public int compareTo(Module other) {
        return code.compareToIgnoreCase(other.code);
    }

}

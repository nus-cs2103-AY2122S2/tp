package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's insurance package in the address book.
 * Guarantees: mutable; is valid as declared in {@link #isValidInsurancePackage(String)}
 */
public class InsurancePackage {

    public static final String MESSAGE_CONSTRAINTS = "Insurance package should not be blank";

    /*
     * The first character of the insurance package must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String packageName;


    /**
     * Constructs an {@code InsurancePackage}.
     *
     * @param packageName A name for the insurance package
     */
    public InsurancePackage(String packageName) {
        requireNonNull(packageName);
        checkArgument(isValidInsurancePackage(packageName), MESSAGE_CONSTRAINTS);
        this.packageName = packageName;
    }

    /**
     * Returns true if a given string is a valid insurance package.
     */
    public static boolean isValidInsurancePackage(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return packageName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsurancePackage // instanceof handles nulls
                && packageName.equals(((InsurancePackage) other).packageName)); // state check
    }

    @Override
    public int hashCode() {
        return packageName.hashCode();
    }
}

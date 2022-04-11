package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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

    private final String packageName;
    private String packageDescription;

    /**
     * Constructs an {@code InsurancePackage}.
     *
     * @param packageName A name for the insurance package
     */
    public InsurancePackage(String packageName) {
        this(packageName, "");
    }

    /**
     * Constructs an {@code InsurancePackage} with a package description.
     *
     * @param packageName A name for the insurance package
     * @param packageDescription The description of the insurance package
     */
    public InsurancePackage(String packageName, String packageDescription) {
        requireAllNonNull(packageName);
        checkArgument(isValidInsurancePackage(packageName), MESSAGE_CONSTRAINTS);
        this.packageName = packageName;
        this.packageDescription = packageDescription;
    }

    /**
     * Returns true if a given string is a valid insurance package.
     */
    public static boolean isValidInsurancePackage(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String s) {
        packageDescription = s;
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

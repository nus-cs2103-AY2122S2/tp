package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's insurance package in the address book.
 * Guarantees: mutable; is always valid
 */
public class InsurancePackage {
    public final String packageName;

    /**
     * Constructs an {@code InsurancePackage}.
     *
     * @param packageName A name for the insurance package
     */
    public InsurancePackage(String packageName) {
        requireNonNull(packageName);
        this.packageName = packageName;
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

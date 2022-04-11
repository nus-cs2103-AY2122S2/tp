package seedu.address.model.policy;

import java.util.Objects;

import seedu.address.model.client.Name;

/**
 * Represents a Policy in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Policy {

    // Identity fields
    private final Name name;
    private final Name company;
    private final Name policyManager;
    private final Premium premium;

    /**
     * Every field must be present and not null.
     */
    public Policy(Name name, Name company, Name policyManager, Premium premium) {
        this.name = name;
        this.company = company;
        this.policyManager = policyManager;
        this.premium = premium;
    }

    public Name getName() {
        return name;
    }

    public Name getCompany() {
        return company;
    }

    public Name getPolicyManager() {
        return policyManager;
    }

    public Premium getPremium() {
        return premium;
    }

    /**
     * Returns true if both policies have the same name.
     * This defines a weaker notion of equality between two policies.
     */
    public boolean isSamePolicy(Policy otherPolicy) {
        if (otherPolicy == this) {
            return true;
        }

        return otherPolicy != null
                && otherPolicy.getName().equals(getName());
    }

    /**
     * Returns true if both Policies have the same identity fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Policy)) {
            return false;
        }

        Policy otherPolicy = (Policy) other;
        return otherPolicy.getName().equals(getName())
                && otherPolicy.getCompany().equals(getCompany())
                && otherPolicy.getPolicyManager().equals(getPolicyManager())
                && otherPolicy.getPremium().equals(getPremium());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, company, policyManager, premium);
    }

    @Override
    public String toString() {
        return getName().fullName;
    }

    public String toNumberedDisplay(int index) {
        return String.format("%s. %s", index, this);
    }

}

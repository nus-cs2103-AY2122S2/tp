package seedu.address.testutil;

import seedu.address.model.client.Name;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.Premium;

/**
 * A utility class to help with building Policy objects.
 */
public class PolicyBuilder {
    public static final String DEFAULT_POLICY_NAME = "Big Insurance Policy";
    public static final String DEFAULT_COMPANY = "Big Insurance Company";
    public static final String DEFAULT_POLICY_MANAGER = "Vijay";
    public static final String DEFAULT_PREMIUM = "100";

    private Name name;
    private Name company;
    private Name policyManager;
    private Premium premium;

    /**
     * Creates a {@code PolicyBuilder} with the default details.
     */
    public PolicyBuilder() {
        name = new Name(DEFAULT_POLICY_NAME);
        company = new Name(DEFAULT_COMPANY);
        policyManager = new Name(DEFAULT_POLICY_MANAGER);
        premium = new Premium(DEFAULT_PREMIUM);
    }

    /**
     * Initializes the PolicyBuilder with the data of {@code policyToCopy}.
     */
    public PolicyBuilder(Policy policyToCopy) {
        name = policyToCopy.getName();
        company = policyToCopy.getCompany();
        policyManager = policyToCopy.getPolicyManager();
        premium = policyToCopy.getPremium();
    }

    /**
     * Sets the {@code name} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code company} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withCompany(String company) {
        this.company = new Name(company);
        return this;
    }

    /**
     * Sets the {@code policyManager} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withPolicyManager(String policyManager) {
        this.policyManager = new Name(policyManager);
        return this;
    }

    /**
     * Sets the {@code premium} of the {@code Policy} that we are building.
     */
    public PolicyBuilder withPremium(String premium) {
        this.premium = new Premium(premium);
        return this;
    }

    public Policy build() {
        return new Policy(name, company, policyManager, premium);
    }
}

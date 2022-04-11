package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.policy.Policy;

/**
 * A utility class containing a list of {@code Policy} objects to be used in tests.
 */
public class TypicalPolicies {

    public static final Policy INSURANCE = new PolicyBuilder()
            .withName("Insurance Policy")
            .withCompany("Insurance Company")
            .withPolicyManager("Ari")
            .withPremium("1000")
            .build();
    public static final Policy INVESTMENT = new PolicyBuilder()
            .withName("Investment Policy")
            .withCompany("Investment Company")
            .withPolicyManager("Elston")
            .withPremium("2000")
            .build();
    public static final Policy RETIREMENT = new PolicyBuilder()
            .withName("Retirement Policy")
            .withCompany("Retirement Company")
            .withPolicyManager("Henry")
            .withPremium("3000")
            .build();

    // Manually added
    public static final Policy TRUST = new PolicyBuilder()
            .withName("Trust Policy")
            .withCompany("Trust Company")
            .withPolicyManager("Wui")
            .withPremium("4000")
            .build();

    public static List<Policy> getTypicalPolicies() {
        return new ArrayList<>(Arrays.asList(INSURANCE, INVESTMENT, RETIREMENT));
    }
}

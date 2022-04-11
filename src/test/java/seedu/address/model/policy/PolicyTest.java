package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_MANAGER_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NAME_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NAME_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREMIUM_INVESTMENT;
import static seedu.address.testutil.TypicalPolicies.INSURANCE;
import static seedu.address.testutil.TypicalPolicies.INVESTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PolicyBuilder;

public class PolicyTest {

    @Test
    public void isSamePolicy() {
        // same object -> returns true
        assertTrue(INSURANCE.isSamePolicy(INSURANCE));

        // null -> returns false
        assertFalse(INSURANCE.isSamePolicy(null));

        // same name, all other attributes different -> returns true
        Policy editedInsurance = new PolicyBuilder(INSURANCE)
                .withCompany(VALID_COMPANY_INVESTMENT)
                .withPolicyManager(VALID_POLICY_MANAGER_INVESTMENT)
                .withPremium(VALID_PREMIUM_INVESTMENT)
                .build();
        assertTrue(INSURANCE.isSamePolicy(editedInsurance));

        // different name, all other attributes same -> returns false
        editedInsurance = new PolicyBuilder(INSURANCE)
                .withName(VALID_POLICY_NAME_INVESTMENT)
                .build();
        assertFalse(INSURANCE.isSamePolicy(editedInsurance));

        // name differs in case, all other attributes same -> returns false
        editedInsurance = new PolicyBuilder(INSURANCE).withName(VALID_POLICY_NAME_INSURANCE.toLowerCase()).build();
        assertFalse(INSURANCE.isSamePolicy(editedInsurance));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_POLICY_NAME_INSURANCE + " ";
        editedInsurance = new PolicyBuilder(INSURANCE).withName(nameWithTrailingSpaces).build();
        assertFalse(INSURANCE.isSamePolicy(editedInsurance));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Policy insuranceCopy = new PolicyBuilder(INSURANCE).build();
        assertTrue(INSURANCE.equals(insuranceCopy));

        // same object -> returns true
        assertTrue(INSURANCE.equals(INSURANCE));

        // null -> returns false
        assertFalse(INSURANCE.equals(null));

        // different type -> returns false
        assertFalse(INSURANCE.equals(5));

        // different policy -> returns false
        assertFalse(INSURANCE.equals(INVESTMENT));

        // different name -> returns false
        Policy editedInsurance = new PolicyBuilder(INSURANCE).withName(VALID_POLICY_NAME_INVESTMENT).build();
        assertFalse(INSURANCE.equals(editedInsurance));

        // different company -> returns false
        editedInsurance = new PolicyBuilder(INSURANCE).withCompany(VALID_COMPANY_INVESTMENT).build();
        assertFalse(INSURANCE.equals(editedInsurance));

        // different policy_manager -> returns false
        editedInsurance = new PolicyBuilder(INSURANCE).withPolicyManager(VALID_COMPANY_INVESTMENT).build();
        assertFalse(INSURANCE.equals(editedInsurance));

        // different premium -> returns false
        editedInsurance = new PolicyBuilder(INSURANCE).withPremium(VALID_PREMIUM_INVESTMENT).build();
        assertFalse(INSURANCE.equals(editedInsurance));
    }

    @Test
    public void toNumberedDisplay() {
        String expectedDisplay = String.format("1. %s", INSURANCE);
        assertEquals(INSURANCE.toNumberedDisplay(1), expectedDisplay);
    }
}

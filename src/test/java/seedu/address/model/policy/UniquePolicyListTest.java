package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_INVESTMENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPolicies.INSURANCE;
import static seedu.address.testutil.TypicalPolicies.INVESTMENT;
import static seedu.address.testutil.TypicalPolicies.RETIREMENT;
import static seedu.address.testutil.TypicalPolicies.TRUST;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.policy.exceptions.DuplicatePolicyException;
import seedu.address.model.policy.exceptions.EmptyPolicyListException;
import seedu.address.model.policy.exceptions.PolicyNotEditedException;
import seedu.address.model.policy.exceptions.PolicyNotFoundException;
import seedu.address.testutil.PolicyBuilder;

public class UniquePolicyListTest {

    private final UniquePolicyList uniquePolicyList = new UniquePolicyList();

    @Test
    public void contains_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.contains(null));
    }

    @Test
    public void contains_policyNotInList_returnsFalse() {
        assertFalse(uniquePolicyList.contains(INSURANCE));
    }

    @Test
    public void contains_policyInList_returnsTrue() {
        uniquePolicyList.add(INSURANCE);
        assertTrue(uniquePolicyList.contains(INSURANCE));
    }

    @Test
    public void contains_policyWithSameIdentityFieldsInList_returnsTrue() {
        uniquePolicyList.add(INSURANCE);
        Policy editedInsurance = new PolicyBuilder(INSURANCE).withCompany(VALID_COMPANY_INVESTMENT).build();
        assertTrue(uniquePolicyList.contains(editedInsurance));
    }

    @Test
    public void add_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.add(null));
    }

    @Test
    public void add_duplicatePolicy_throwsDuplicateClientException() {
        uniquePolicyList.add(INSURANCE);
        assertThrows(DuplicatePolicyException.class, () -> uniquePolicyList.add(INSURANCE));
    }

    @Test
    public void setPolicy_nullTargetPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicy(null, INSURANCE));
    }

    @Test
    public void setPolicy_nullEditedPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicy(INSURANCE, null));
    }

    @Test
    public void setPolicy_targetPolicyNotInList_throwsAssertionError() {
        uniquePolicyList.add(RETIREMENT);
        assertThrows(AssertionError.class, () -> uniquePolicyList.setPolicy(INSURANCE, INSURANCE));
    }

    @Test
    public void setPolicy_editedPolicyIsSamePolicy_throwsPolicyNotEditedException() {
        uniquePolicyList.add(INSURANCE);
        assertThrows(PolicyNotEditedException.class, () -> uniquePolicyList.setPolicy(INSURANCE, INSURANCE));
    }

    @Test
    public void setPolicy_editedPolicyHasSameIdentity_success() {
        uniquePolicyList.add(INSURANCE);
        Policy editedInsurance = new PolicyBuilder(INSURANCE).withCompany(VALID_COMPANY_INVESTMENT).build();
        uniquePolicyList.setPolicy(INSURANCE, editedInsurance);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(editedInsurance);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPolicy_editedPolicyHasDifferentIdentity_success() {
        uniquePolicyList.add(INSURANCE);
        uniquePolicyList.setPolicy(INSURANCE, INVESTMENT);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(INVESTMENT);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPolicy_editedPolicyHasNonUniqueIdentity_throwsDuplicatePolicyException() {
        uniquePolicyList.add(INSURANCE);
        uniquePolicyList.add(INVESTMENT);
        assertThrows(DuplicatePolicyException.class, () -> uniquePolicyList.setPolicy(INSURANCE, INVESTMENT));
    }

    @Test
    public void setPolicy_emptyPolicyList_throwsAssertionListException() {
        assertThrows(AssertionError.class, () -> uniquePolicyList.setPolicy(INSURANCE, INVESTMENT));
    }

    @Test
    public void remove_nullPolicy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.remove(null));
    }

    @Test
    public void remove_policyDoesNotExist_throwsPolicyNotFoundException() {
        uniquePolicyList.add(INVESTMENT);
        assertThrows(PolicyNotFoundException.class, () -> uniquePolicyList.remove(INSURANCE));
    }

    @Test
    public void remove_existingPolicy_removesPolicy() {
        uniquePolicyList.add(INSURANCE);
        uniquePolicyList.remove(INSURANCE);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void removePolicy_emptyPolicyList_throwsEmptyPolicyListException() {
        assertThrows(EmptyPolicyListException.class, () -> uniquePolicyList.remove(INSURANCE));
    }

    @Test
    public void setPolicies_nulluniquePolicyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicies((UniquePolicyList) null));
    }

    @Test
    public void setPolicies_uniquePolicyList_replacesOwnListWithProvideduniquePolicyList() {
        uniquePolicyList.add(INSURANCE);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(INVESTMENT);
        uniquePolicyList.setPolicies(expectedUniquePolicyList);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPolicies_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePolicyList.setPolicies((List<Policy>) null));
    }

    @Test
    public void setPolicies_list_replacesOwnListWithProvidedList() {
        uniquePolicyList.add(INSURANCE);
        List<Policy> policyList = Collections.singletonList(INVESTMENT);
        uniquePolicyList.setPolicies(policyList);
        UniquePolicyList expectedUniquePolicyList = new UniquePolicyList();
        expectedUniquePolicyList.add(INVESTMENT);
        assertEquals(expectedUniquePolicyList, uniquePolicyList);
    }

    @Test
    public void setPolicies_listWithDuplicatePolicies_throwsDuplicateClientException() {
        List<Policy> listWithDuplicateClients = Arrays.asList(INSURANCE, INSURANCE);
        assertThrows(DuplicatePolicyException.class, () -> uniquePolicyList.setPolicies(listWithDuplicateClients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePolicyList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void totalPremiumSum() {
        // empty policy list
        assertEquals(uniquePolicyList.totalPremiumSum(), 0);

        // list with 1 insurance policy
        uniquePolicyList.add(INSURANCE);
        assertEquals(uniquePolicyList.totalPremiumSum(), Integer.parseInt(INSURANCE.getPremium().value));

        // list with multiple policies
        uniquePolicyList.add(INVESTMENT);
        uniquePolicyList.add(TRUST);
        int sum = Integer.parseInt(INSURANCE.getPremium().value)
                        + Integer.parseInt(INVESTMENT.getPremium().value)
                        + Integer.parseInt(TRUST.getPremium().value);
        assertEquals(uniquePolicyList.totalPremiumSum(), sum);
    }

    @Test
    public void hasPolicyFromCompany() {
        // empty policy list
        assertFalse(uniquePolicyList.hasPolicyFromCompany(INSURANCE.getCompany()));

        // list with 1 insurance policy
        uniquePolicyList.add(INSURANCE);
        assertTrue(uniquePolicyList.hasPolicyFromCompany(INSURANCE.getCompany()));
        assertFalse(uniquePolicyList.hasPolicyFromCompany(INVESTMENT.getCompany()));
    }
}

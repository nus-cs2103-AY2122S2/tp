package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPolicy.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPolicies.INSURANCE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Name;
import seedu.address.model.policy.Premium;


public class JsonAdaptedPolicyTest {

    private static final String VALID_POLICY_NAME = INSURANCE.getName().fullName;
    private static final String VALID_COMPANY = INSURANCE.getCompany().fullName;
    private static final String VALID_POLICY_MANAGER = INSURANCE.getPolicyManager().fullName;
    private static final String VALID_PREMIUM = INSURANCE.getPremium().value;

    private static final String INVALID_POLICY_NAME = "Insurance-Plan";
    private static final String INVALID_COMPANY = "Insurance-Company";
    private static final String INVALID_POLICY_MANAGER = "Insurance-Agent";
    private static final String INVALID_PREMIUM = "$100";

    @Test
    public void toModelType_validPolicyDetails_returnsPolicy() throws Exception {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(INSURANCE);
        assertEquals(INSURANCE, policy.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(INVALID_POLICY_NAME, VALID_COMPANY, VALID_POLICY_MANAGER,
                VALID_PREMIUM);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(null, VALID_COMPANY, VALID_POLICY_MANAGER, VALID_PREMIUM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "name");
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(VALID_POLICY_NAME, INVALID_COMPANY, VALID_POLICY_MANAGER,
                VALID_PREMIUM);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(VALID_POLICY_NAME, null, VALID_POLICY_MANAGER,
                VALID_PREMIUM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "company");
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_invalidPolicyManager_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_COMPANY, INVALID_POLICY_MANAGER,
                VALID_PREMIUM);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_nullPolicyManager_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_COMPANY, null,
                VALID_PREMIUM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "policy manager");
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_invalidPremium_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_COMPANY, VALID_POLICY_MANAGER,
                INVALID_PREMIUM);
        String expectedMessage = Premium.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }

    @Test
    public void toModelType_nullPremium_throwsIllegalValueException() {
        JsonAdaptedPolicy policy = new JsonAdaptedPolicy(VALID_POLICY_NAME, VALID_COMPANY, VALID_POLICY_MANAGER,
                null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "premium");
        assertThrows(IllegalValueException.class, expectedMessage, policy::toModelType);
    }
}

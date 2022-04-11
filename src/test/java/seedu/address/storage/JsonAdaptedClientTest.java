package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.BOSS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Plan;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = " ";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_PLAN = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BOSS.getName().toString();
    private static final String VALID_PHONE = BOSS.getPhone().toString();
    private static final String VALID_EMAIL = BOSS.getEmail().toString();
    private static final String VALID_ADDRESS = BOSS.getAddress().toString();
    private static final String VALID_PLAN = BOSS.getPlan().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BOSS.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedProcedure> VALID_PROCEDURES = BOSS.getProcedures().stream()
            .map(JsonAdaptedProcedure::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(BOSS);
        assertEquals(BOSS, client.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_PLAN,
                VALID_TAGS, VALID_PROCEDURES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_PLAN, VALID_TAGS, VALID_PROCEDURES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_PLAN, VALID_TAGS, VALID_PROCEDURES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_PLAN, VALID_TAGS, VALID_PROCEDURES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                VALID_PLAN, VALID_TAGS, VALID_PROCEDURES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_PLAN, VALID_TAGS, VALID_PROCEDURES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                VALID_PLAN, VALID_TAGS, VALID_PROCEDURES);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_PLAN,
                VALID_TAGS, VALID_PROCEDURES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPlan_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_PLAN, VALID_TAGS, VALID_PROCEDURES);
        String expectedMessage = Plan.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPlan_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_TAGS, VALID_PROCEDURES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Plan.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_PLAN, invalidTags, VALID_PROCEDURES);
        assertThrows(IllegalValueException.class, client::toModelType);
    }

}

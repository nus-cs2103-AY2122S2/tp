package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.LastContacted;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.testutil.ClientBuilder;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_BIRTHDAY = "1/1/1";
    private static final String INVALID_LAST_CONTACTED = "1/1/1 1:1";
    private static final String INVALID_PREMIUM = "ten dollars";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_BIRTHDAY = BENSON.getBirthday().toString();
    private static final String VALID_LAST_CONTACTED = BENSON.getLastContacted().toString();
    private static final List<JsonAdaptedPolicy> VALID_POLICIES =
            BENSON.getPolicies().asUnmodifiableObservableList().stream().map(JsonAdaptedPolicy::new)
                    .collect(Collectors.toList());
    private static final String VALID_NOTE = BENSON.getNote().toString();
    private static final JsonAdaptedPreferenceMap VALID_PREFERENCES =
            new JsonAdaptedPreferenceMap(BENSON.getPreferenceMap());

    @Test
    public void toModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(BENSON);
        assertEquals(BENSON, client.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_emptyEmail_returnsClient() throws Exception {
        Client noEmailClient = new ClientBuilder().withEmail("").build();
        JsonAdaptedClient client = new JsonAdaptedClient(noEmailClient);
        assertEquals(noEmailClient, client.toModelType());

    }

    @Test
    public void toModelType_emptyAddress_returnsClient() throws Exception {
        Client noAddressClient = new ClientBuilder().withAddress("").build();
        JsonAdaptedClient client = new JsonAdaptedClient(noAddressClient);
        assertEquals(noAddressClient, client.toModelType());

    }

    @Test
    public void toModelType_emptyBirthday_returnsClient() throws Exception {
        Client noBirthdayClient = new ClientBuilder().withBirthday("").build();
        JsonAdaptedClient client = new JsonAdaptedClient(noBirthdayClient);
        assertEquals(noBirthdayClient, client.toModelType());

    }

    @Test
    public void toModelType_emptyLastContacted_returnsClient() throws Exception {
        Client noLastContactedClient = new ClientBuilder().withLastContacted("").build();
        JsonAdaptedClient client = new JsonAdaptedClient(noLastContactedClient);
        assertEquals(noLastContactedClient, client.toModelType());
    }



    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                VALID_BIRTHDAY, VALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_BIRTHDAY, VALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidBirthday_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                INVALID_BIRTHDAY, VALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = Birthday.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullBirthday_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidLastContacted_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, INVALID_LAST_CONTACTED, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = LastContacted.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullLastContacted_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, null, VALID_POLICIES, VALID_NOTE, VALID_PREFERENCES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LastContacted.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPolicies_throwsIllegalValueException() {
        List<JsonAdaptedPolicy> invalidPolicies = new ArrayList<>(VALID_POLICIES);
        invalidPolicies.add(new JsonAdaptedPolicy(INVALID_NAME, INVALID_NAME, INVALID_NAME, INVALID_PREMIUM));
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_LAST_CONTACTED, invalidPolicies, VALID_NOTE, VALID_PREFERENCES);
        assertThrows(IllegalValueException.class, client::toModelType);
    }
}

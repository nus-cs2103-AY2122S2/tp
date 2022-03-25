package seedu.trackbeau.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.trackbeau.storage.JsonAdaptedCustomer.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.trackbeau.testutil.Assert.assertThrows;
import static seedu.trackbeau.testutil.TypicalCustomers.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.commons.exceptions.IllegalValueException;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.SkinType;

public class JsonAdaptedCustomerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_SKIN_TYPE = " ";
    private static final String INVALID_HAIR_TYPE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_STAFF = " ";
    private static final String INVALID_SERVICE = " ";
    private static final String INVALID_ALLERGY = " ";
    private static final String INVALID_BIRTHDAY = "30-02-2016";
    private static final String INVALID_REG_DATE = "30-02-2016";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_SKIN_TYPE = BENSON.getSkinType().toString();
    private static final String VALID_HAIR_TYPE = BENSON.getHairType().toString();
    private static final String VALID_BIRTHDATE = BENSON.getBirthdate().toString();
    private static final String VALID_REG_DATE = BENSON.getRegDate().toString();
    private static final List<JsonAdaptedTag> VALID_STAFFS = BENSON.getStaffs().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_SERVICES = BENSON.getServices().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_ALLERGIES = BENSON.getAllergies().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validCustomerDetails_returnsCustomer() throws Exception {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(BENSON);
        assertEquals(BENSON, customer.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                        VALID_SERVICES, VALID_ALLERGIES, VALID_BIRTHDATE, VALID_REG_DATE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                VALID_SERVICES, VALID_ALLERGIES,
                VALID_BIRTHDATE, VALID_REG_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, INVALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS,
                        VALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                        VALID_SERVICES, VALID_ALLERGIES,
                        VALID_BIRTHDATE, VALID_REG_DATE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_NAME, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                VALID_SERVICES, VALID_ALLERGIES,
                VALID_BIRTHDATE, VALID_REG_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_ADDRESS, VALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                        VALID_SERVICES, VALID_ALLERGIES,
                        VALID_BIRTHDATE, VALID_REG_DATE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                VALID_SERVICES, VALID_ALLERGIES,
                VALID_BIRTHDATE, VALID_REG_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                        VALID_SERVICES, VALID_ALLERGIES,
                        VALID_BIRTHDATE, VALID_REG_DATE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                VALID_SERVICES, VALID_ALLERGIES,
                VALID_BIRTHDATE, VALID_REG_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidSkinType_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, INVALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                        VALID_SERVICES, VALID_ALLERGIES,
                        VALID_BIRTHDATE, VALID_REG_DATE);
        String expectedMessage = SkinType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidHairType_throwsIllegalValueException() {
        JsonAdaptedCustomer customer =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_SKIN_TYPE, INVALID_HAIR_TYPE, VALID_STAFFS,
                        VALID_SERVICES, VALID_ALLERGIES,
                        VALID_BIRTHDATE, VALID_REG_DATE);
        String expectedMessage = HairType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidStaffs_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidStaffs = new ArrayList<>(VALID_STAFFS);
        invalidStaffs.add(new JsonAdaptedTag(INVALID_STAFF));
        JsonAdaptedCustomer person =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_SKIN_TYPE, VALID_HAIR_TYPE, invalidStaffs,
                        VALID_SERVICES, VALID_ALLERGIES,
                        VALID_BIRTHDATE, VALID_REG_DATE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidServices_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidServices = new ArrayList<>(VALID_SERVICES);
        invalidServices.add(new JsonAdaptedTag(INVALID_SERVICE));
        JsonAdaptedCustomer person =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                        invalidServices, VALID_ALLERGIES,
                        VALID_BIRTHDATE, VALID_REG_DATE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidAllergies_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidAllergies = new ArrayList<>(VALID_ALLERGIES);
        invalidAllergies.add(new JsonAdaptedTag(INVALID_ALLERGY));
        JsonAdaptedCustomer person =
                new JsonAdaptedCustomer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_SKIN_TYPE, VALID_HAIR_TYPE, VALID_STAFFS,
                        VALID_SERVICES, invalidAllergies,
                        VALID_BIRTHDATE, VALID_REG_DATE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}

package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedSeller.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSellers.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;

public class JsonAdaptedSellerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_TAG = "#friend";


    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_APPOINTMENT = BENSON.getAppointment().toString();
    private static final JsonAdaptedPropertyToSell VALID_NULL_PROPERTY =
            new JsonAdaptedPropertyToSell(BENSON.getPropertyToSell());
    @Test
    public void toModelType_validSellerDetails_returnsSeller() throws Exception {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(BENSON);
        assertEquals(BENSON, seller.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedSeller seller =
                new JsonAdaptedSeller(INVALID_NAME, VALID_PHONE, VALID_APPOINTMENT, VALID_TAGS, VALID_NULL_PROPERTY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(null, VALID_PHONE, VALID_APPOINTMENT, VALID_TAGS,
                VALID_NULL_PROPERTY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedSeller seller =
                new JsonAdaptedSeller(VALID_NAME, INVALID_PHONE, VALID_APPOINTMENT, VALID_TAGS, VALID_NULL_PROPERTY);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, null, VALID_APPOINTMENT, VALID_TAGS,
                VALID_NULL_PROPERTY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedSeller seller =
                new JsonAdaptedSeller(VALID_NAME, VALID_PHONE,
                        VALID_APPOINTMENT, invalidTags, VALID_NULL_PROPERTY);
        assertThrows(IllegalValueException.class, seller::toModelType);
    }

}

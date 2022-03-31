package woofareyou.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import woofareyou.commons.exceptions.IllegalValueException;
import woofareyou.model.pet.Address;
import woofareyou.model.pet.Name;
import woofareyou.model.pet.OwnerName;
import woofareyou.model.pet.Phone;
import woofareyou.testutil.Assert;
import woofareyou.testutil.TypicalPets;

public class JsonAdaptedPetTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_OWNER_NAME = "S@arah";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalPets.PIZZA.getName().toString();
    private static final String VALID_PHONE = TypicalPets.PIZZA.getPhone().toString();
    private static final String VALID_OWNER_NAME = TypicalPets.PIZZA.getOwnerName().toString();
    private static final String VALID_ADDRESS = TypicalPets.PIZZA.getAddress().toString();
    private static final String VALID_DIET = TypicalPets.PIZZA.getDiet().toString();
    private static final String VALID_APPOINTMENT = TypicalPets.PIZZA.getAppointment().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalPets.PIZZA.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAttendance> VALID_ATTENDANCE_MAP = TypicalPets.PIZZA.getAttendanceHashMap()
            .toCollection()
            .stream()
            .map(JsonAdaptedAttendance::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPetDetails_returnsPet() throws Exception {
        JsonAdaptedPet pet = new JsonAdaptedPet(TypicalPets.PIZZA);
        Assertions.assertEquals(TypicalPets.PIZZA, pet.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPet pet =
                new JsonAdaptedPet(INVALID_NAME, VALID_PHONE, VALID_OWNER_NAME, VALID_ADDRESS,
                        VALID_TAGS, VALID_DIET, VALID_APPOINTMENT, VALID_ATTENDANCE_MAP);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(null, VALID_PHONE, VALID_OWNER_NAME,
                VALID_ADDRESS, VALID_TAGS, VALID_DIET, VALID_APPOINTMENT, VALID_ATTENDANCE_MAP);
        String expectedMessage = String.format(JsonAdaptedPet.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPet pet =
                new JsonAdaptedPet(VALID_NAME, VALID_OWNER_NAME, INVALID_PHONE, VALID_ADDRESS,
                        VALID_TAGS, VALID_DIET, VALID_APPOINTMENT, VALID_ATTENDANCE_MAP);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_NAME, VALID_OWNER_NAME, null,
                VALID_ADDRESS, VALID_TAGS, VALID_DIET, VALID_APPOINTMENT, VALID_ATTENDANCE_MAP);
        String expectedMessage = String.format(JsonAdaptedPet.MISSING_FIELD_MESSAGE_FORMAT,
                Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidOwnerName_throwsIllegalValueException() {
        JsonAdaptedPet pet =
                new JsonAdaptedPet(VALID_NAME, INVALID_OWNER_NAME, VALID_PHONE, VALID_ADDRESS,
                        VALID_TAGS, VALID_DIET, VALID_APPOINTMENT, VALID_ATTENDANCE_MAP);
        String expectedMessage = OwnerName.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullOwnerName_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_NAME, null, VALID_PHONE, VALID_ADDRESS,
                VALID_TAGS, VALID_DIET, VALID_APPOINTMENT, VALID_ATTENDANCE_MAP);
        String expectedMessage = String.format(JsonAdaptedPet.MISSING_FIELD_MESSAGE_FORMAT,
                OwnerName.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPet pet =
                new JsonAdaptedPet(VALID_NAME, VALID_OWNER_NAME, VALID_PHONE, INVALID_ADDRESS,
                        VALID_TAGS, VALID_DIET, VALID_APPOINTMENT, VALID_ATTENDANCE_MAP);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPet pet = new JsonAdaptedPet(VALID_NAME, VALID_OWNER_NAME,
                VALID_PHONE, null, VALID_TAGS, VALID_DIET, VALID_APPOINTMENT, VALID_ATTENDANCE_MAP);
        String expectedMessage = String.format(JsonAdaptedPet.MISSING_FIELD_MESSAGE_FORMAT,
                Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, pet::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPet pet =
                new JsonAdaptedPet(VALID_NAME, VALID_OWNER_NAME, VALID_PHONE, VALID_ADDRESS,
                        invalidTags, VALID_DIET, VALID_APPOINTMENT, VALID_ATTENDANCE_MAP);
        Assert.assertThrows(IllegalValueException.class, pet::toModelType);
    }


}

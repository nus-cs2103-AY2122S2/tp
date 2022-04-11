package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedApplication.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.LAZADA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Address;
import seedu.address.model.application.Email;
import seedu.address.model.application.InterviewSlot;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.Name;
import seedu.address.model.application.Phone;

public class JsonAdaptedApplicationTest {
    private static final String INVALID_NAME = "L@z@d@";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_INTEVIEWSLOT = "32-13-1920 11:66";
    private static final String INVALID_DETAILS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_JOBTITLE = "J@v@ Developer";

    private static final String VALID_NAME = LAZADA.getName().toString();
    private static final String VALID_PHONE = LAZADA.getPhone().toString();
    private static final String VALID_EMAIL = LAZADA.getEmail().toString();
    private static final String VALID_ADDRESS = LAZADA.getAddress().toString();
    private static final String VALID_INTEVIEWSLOT = LAZADA.getInterviewSlot().toInputString();
    private static final String VALID_DETAILS = LAZADA.getDetails().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = LAZADA.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_JOBTITLE = LAZADA.getJobTitle().toString();

    @Test
    public void toModelType_validApplicationDetails_returnsApplication() throws Exception {
        JsonAdaptedApplication application = new JsonAdaptedApplication(LAZADA);
        assertEquals(LAZADA, application.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INTEVIEWSLOT,
                        VALID_DETAILS, VALID_TAGS, VALID_JOBTITLE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INTEVIEWSLOT,
                        VALID_DETAILS, VALID_TAGS, VALID_JOBTITLE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INTEVIEWSLOT,
                        VALID_DETAILS, VALID_TAGS, VALID_JOBTITLE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_INTEVIEWSLOT,
                    VALID_DETAILS, VALID_TAGS, VALID_JOBTITLE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_INTEVIEWSLOT,
                    VALID_DETAILS, VALID_TAGS, VALID_JOBTITLE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_INTEVIEWSLOT,
                    VALID_DETAILS, VALID_TAGS, VALID_JOBTITLE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_INTEVIEWSLOT,
                    VALID_DETAILS, VALID_TAGS, VALID_JOBTITLE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_INTEVIEWSLOT,
                    VALID_DETAILS, VALID_TAGS, VALID_JOBTITLE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidInterviewSlot_throwsIllegalArgumentException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_INTEVIEWSLOT,
                    VALID_DETAILS, VALID_TAGS, VALID_JOBTITLE);
        String expectedMessage = InterviewSlot.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullInterviewSlot_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null,
                    VALID_DETAILS, VALID_TAGS, VALID_JOBTITLE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewSlot.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INTEVIEWSLOT,
                    VALID_DETAILS, invalidTags, VALID_JOBTITLE);
        assertThrows(IllegalValueException.class, application::toModelType);
    }

    @Test
    public void toModelType_invalidJobTitle_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INTEVIEWSLOT,
                    VALID_DETAILS, VALID_TAGS, INVALID_JOBTITLE);
        String expectedMessage = JobTitle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullJobTitle_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INTEVIEWSLOT, VALID_DETAILS, VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

}

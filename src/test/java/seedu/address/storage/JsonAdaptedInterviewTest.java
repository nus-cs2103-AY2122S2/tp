package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedInterview.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInterviews.JR_PROJECT_MANAGER_INTERVIEW;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.interview.Status;

public class JsonAdaptedInterviewTest {
    private static final JsonAdaptedApplicant VALID_APPLICANT =
            new JsonAdaptedApplicant(JR_PROJECT_MANAGER_INTERVIEW.getApplicant());
    private static final LocalDateTime VALID_DATE = JR_PROJECT_MANAGER_INTERVIEW.getDate();
    private static final JsonAdaptedPosition VALID_POSITION =
            new JsonAdaptedPosition(JR_PROJECT_MANAGER_INTERVIEW.getPosition());
    private static final String VALID_STATUS = JR_PROJECT_MANAGER_INTERVIEW.getStatus().toString();

    @Test
    public void toModelType_nullApplicant_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(null, VALID_DATE, VALID_POSITION, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                JsonAdaptedApplicant.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_APPLICANT, null, VALID_POSITION, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                LocalDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullPosition_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_APPLICANT, VALID_DATE, null, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                JsonAdaptedPosition.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_APPLICANT, VALID_DATE, VALID_POSITION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }
}

package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedInterview.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCandidates.BENSON;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.candidate.Availability;
import seedu.address.model.candidate.Course;
import seedu.address.model.candidate.Email;
import seedu.address.model.candidate.Name;
import seedu.address.model.candidate.Phone;
import seedu.address.model.candidate.Seniority;
import seedu.address.model.candidate.StudentId;
import seedu.address.model.interview.Interview;


public class JsonAdaptedInterviewTest {
    private static final String INVALID_STUDENT_ID = "A0123456";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_COURSE = "computer Science";
    private static final String INVALID_SENIORITY = "0.0";
    private static final String INVALID_AVAILABILITY = "9";

    private static final String VALID_STUDENT_ID = BENSON.getStudentId().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_COURSE = BENSON.getCourse().toString();
    private static final String VALID_SENIORITY = BENSON.getSeniority().toString();
    private static final String VALID_APPLICATION_STATUS = BENSON.getApplicationStatus().toString();
    private static final String VALID_INTERVIEW_STATUS = BENSON.getInterviewStatus().toString();
    private static final String VALID_AVAILABILITY = BENSON.getAvailability().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final String VALID_DATE_STRING = "26/03/2022 14:40";


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final LocalDateTime VALID_DATE_TIME = LocalDateTime.parse(VALID_DATE_STRING, FORMATTER);


    @Test
    public void toModelType_validCandidateDetails_returnsCandidate() throws Exception {
        Interview interview = new Interview(BENSON, VALID_DATE_TIME);
        JsonAdaptedInterview candidate = new JsonAdaptedInterview(interview);
        assertEquals(interview, candidate.toModelType());
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(INVALID_STUDENT_ID, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_COURSE, VALID_SENIORITY,
                VALID_APPLICATION_STATUS, VALID_INTERVIEW_STATUS,
                VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);

        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }


    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(null, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_COURSE, VALID_SENIORITY,
                VALID_APPLICATION_STATUS, VALID_INTERVIEW_STATUS,
                VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, INVALID_NAME, VALID_PHONE,
                        VALID_EMAIL, VALID_COURSE, VALID_SENIORITY, VALID_APPLICATION_STATUS,
                        VALID_INTERVIEW_STATUS, VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, null, VALID_PHONE,
                VALID_EMAIL, VALID_COURSE, VALID_SENIORITY, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, VALID_NAME, INVALID_PHONE,
                VALID_EMAIL, VALID_COURSE, VALID_SENIORITY, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, VALID_NAME, null,
                VALID_EMAIL, VALID_COURSE, VALID_SENIORITY, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, VALID_NAME, VALID_PHONE,
                INVALID_EMAIL, VALID_COURSE, VALID_SENIORITY, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, VALID_NAME, VALID_PHONE,
                null, VALID_COURSE, VALID_SENIORITY, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidCourse_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, INVALID_COURSE, VALID_SENIORITY, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = Course.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullCourse_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, null, VALID_SENIORITY, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Course.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidSeniority_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_COURSE, INVALID_SENIORITY, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = Seniority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullSeniority_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_COURSE, null, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, VALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Seniority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidAvailability_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_COURSE, VALID_SENIORITY, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, INVALID_AVAILABILITY, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = Availability.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullAvailability_throwsIllegalValueException() {
        JsonAdaptedInterview interview = new JsonAdaptedInterview(VALID_STUDENT_ID, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_COURSE, VALID_SENIORITY, VALID_APPLICATION_STATUS,
                VALID_INTERVIEW_STATUS, null, VALID_DATE_STRING, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Availability.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }
}

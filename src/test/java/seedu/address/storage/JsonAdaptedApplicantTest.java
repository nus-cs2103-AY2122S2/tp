package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedApplicant.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicant.ApplicantStatus;
import seedu.address.model.applicant.DateApplied;
import seedu.address.model.applicant.InterviewDate;
import seedu.address.model.applicant.JobId;
import seedu.address.model.applicant.Nric;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.TypicalApplicants;

class JsonAdaptedApplicantTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_NRIC = "1234567";
    private static final String INVALID_INTERVIEWDATE = "12/02/2012";
    private static final String INVALID_DATEAPPLIED = "12/02/2012";
    private static final String INVALID_JOB = " ";
    private static final String INVALID_QUALIFICATION = " ";
    private static final String INVALID_APPLICATION_STATUS = "4";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_INTERVIEWDATE = BENSON.getInterviewDate().toString();
    private static final String VALID_DATEAPPLIED = BENSON.getDateApplied().toString();
    private static final String VALID_JOB = BENSON.getJobId().toString();
    private static final String VALID_QUALIFICATION = BENSON.getQualification().toString();
    private static final String VALID_APPLICATION_STATUS = BENSON.getApplicantStatus().toString();

    @Test
    public void toModelType_validApplicantDetails_returnsApplicant() throws Exception {
        System.out.println(VALID_JOB);
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(TypicalApplicants.BENSON);
        System.out.println(applicant.toModelType());
        assertEquals(TypicalApplicants.BENSON, applicant.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        assertThrows(IllegalValueException.class, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        INVALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        null, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }


    @Test
    public void toModelType_invalidDateApplied_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, INVALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = DateApplied.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullDateApplied_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, null, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateApplied.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidInterviewDate_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, INVALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = InterviewDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullInterviewDate_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, null, VALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidJob_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, INVALID_JOB, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = JobId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullJobId_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, null, VALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidQualification_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, INVALID_QUALIFICATION,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = Qualification.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullQualification_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, null,
                        VALID_APPLICATION_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Qualification.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidApplicantStatus_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        INVALID_APPLICATION_STATUS);
        String expectedMessage = ApplicantStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullApplicantStatus_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_NRIC, VALID_DATEAPPLIED, VALID_INTERVIEWDATE, VALID_JOB, VALID_QUALIFICATION,
                        null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ApplicantStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }
}

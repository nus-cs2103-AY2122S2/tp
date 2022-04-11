package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedJob.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicant.JobId;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.job.CompanyName;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobStatus;
import seedu.address.model.job.JobTitle;
import seedu.address.model.job.Position;
import seedu.address.model.job.Salary;
import seedu.address.model.person.Address;

class JsonAdaptedJobTest {
    private static final String INVALID_JOB_TITLE = "R@chel";
    private static final String INVALID_COMPANY_NAME = " ";
    private static final String INVALID_ID = "ad34";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_QUALIFICATION = " ";
    private static final String INVALID_JOBSTATUS = "12/02/2012";
    private static final String INVALID_POSITION = "12/02/2012";
    private static final String INVALID_SALARY = "4";

    private static final String VALID_JOB_TITLE = "Software Engineer";
    private static final String VALID_COMPANY_NAME = "BitDance";
    private static final String VALID_ID = "20";
    private static final String VALID_ADDRESS = "311, Clementi Ave 2";
    private static final String VALID_QUALIFICATION = "Bachelors Degree";
    private static final String VALID_JOBSTATUS = "filled";
    private static final String VALID_POSITION = "ft";
    private static final String VALID_SALARY = "3000-4000";

    @Test
    public void toModelType_validJobDetails_returnsJob() throws Exception {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS,
                VALID_QUALIFICATION, VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        Job expected = new Job(new JobTitle(VALID_JOB_TITLE), new CompanyName(VALID_COMPANY_NAME),
                new JobId(VALID_ID), new Address(VALID_ADDRESS), new Qualification(VALID_QUALIFICATION),
                new JobStatus(VALID_JOBSTATUS), new Position(VALID_POSITION), new Salary(VALID_SALARY));
        assertEquals(expected, job.toModelType());
    }

    @Test
    public void toModelType_invalidJobTitle_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(INVALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = JobTitle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullJobTitle_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(null, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, INVALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, null, VALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidJobId_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, INVALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = JobId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullJobId_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, null, VALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, INVALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, null, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidQualification_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, INVALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = Qualification.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullQualification_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, null,
                        VALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Qualification.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidJobStatus_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        INVALID_JOBSTATUS, VALID_POSITION, VALID_SALARY);
        String expectedMessage = JobStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullJobStatus_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        null, VALID_POSITION, VALID_SALARY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidPosition_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, INVALID_POSITION, VALID_SALARY);
        String expectedMessage = Position.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullPosition_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, null, VALID_SALARY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidSalary_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, INVALID_SALARY);
        String expectedMessage = Salary.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullSalary_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_ID, VALID_ADDRESS, VALID_QUALIFICATION,
                        VALID_JOBSTATUS, VALID_POSITION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }
}

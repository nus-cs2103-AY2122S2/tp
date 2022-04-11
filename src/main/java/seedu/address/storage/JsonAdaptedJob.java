package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

/**
 * Jackson-friendly version of {@link Job}.
 */
class JsonAdaptedJob {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String jobTitle;
    private final String companyName;
    private final String id;
    private final String address;
    private final String qualification;
    private final String jobStatus;
    private final String position;
    private final String salary;

    /**
     * Constructs a {@code JsonAdaptedJob} with the given job details.
     */
    @JsonCreator
    public JsonAdaptedJob(@JsonProperty("jobTitle") String jobTitle, @JsonProperty("companyName") String companyName,
                             @JsonProperty("id") String id, @JsonProperty("address") String address,
                             @JsonProperty("qualification") String qualification,
                             @JsonProperty("jobStatus") String jobStatus, @JsonProperty("position") String position,
                             @JsonProperty("salary") String salary) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.id = id;
        this.address = address;
        this.qualification = qualification;
        this.jobStatus = jobStatus;
        this.position = position;
        this.salary = salary;
    }

    /**
     * Converts a given {@code Job} into this class for Jackson use.
     */
    public JsonAdaptedJob(Job source) {
        jobTitle = source.getJobTitle().jobTitle;
        companyName = source.getCompany().companyName;
        id = Integer.toString(source.getJobId().jobId);
        address = source.getAddress().value;
        qualification = source.getQualification().highestQualification;
        jobStatus = source.getJobStatus().toString();
        position = source.getPosition().position;
        salary = source.getSalary().salaryRange;
    }

    /**
     * Converts this Jackson-friendly adapted job object into the model's {@code Job} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Job toModelType() throws IllegalValueException {

        if (jobTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobTitle.class.getSimpleName()));
        }
        if (!JobTitle.isValidJobTitle(jobTitle)) {
            throw new IllegalValueException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        final JobTitle modelJobTitle = new JobTitle(jobTitle);

        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidCompanyName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompanyName = new CompanyName(companyName);

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, JobId.class.getSimpleName()));
        }
        if (!JobId.isValidJobId(id)) {
            throw new IllegalValueException(JobId.MESSAGE_CONSTRAINTS);
        }
        final JobId modelJobId = new JobId(id);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (qualification == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Qualification.class.getSimpleName()));
        }
        if (!Qualification.isValidQualification(qualification)) {
            throw new IllegalValueException(Qualification.MESSAGE_CONSTRAINTS);
        }

        final Qualification modelQualification = new Qualification(qualification);

        if (jobStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobStatus.class.getSimpleName()));
        }
        if (!JobStatus.isValid(jobStatus)) {
            throw new IllegalValueException(JobStatus.MESSAGE_CONSTRAINTS);
        }

        final JobStatus modelJobStatus = new JobStatus(jobStatus);

        if (position == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Position.class.getSimpleName()));
        }
        if (!Position.validPosition(position)) {
            throw new IllegalValueException(Position.MESSAGE_CONSTRAINTS);
        }

        final Position modelPosition = new Position(position);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }

        final Salary modelSalary = new Salary(salary);

        return new Job(modelJobTitle, modelCompanyName, modelJobId, modelAddress, modelQualification,
                modelJobStatus, modelPosition, modelSalary);
    }

}

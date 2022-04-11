package seedu.address.testutil;

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
 * A utility class to help with building Job objects.
 */
public class JobBuilder {
    public static final String DEFAULT_JOB_TITLE = "Quantity Surveyer";
    public static final String DEFAULT_COMPANY_NAME = "Capitaland";
    public static final String DEFAULT_JOB_ID = "123456";
    public static final String DEFAULT_ADDRESS = "168 Robinson Rd, Capital Tower";
    public static final String DEFAULT_QUALIFICATION = "Degree in Project Management";
    public static final String DEFAULT_JOB_STATUS = "filled";
    public static final String DEFAULT_POSITION = "ft";
    public static final String DEFAULT_SALARY_LOW = "4000";
    public static final String DEFAULT_SALARY_HIGH = "5000";

    private JobTitle jobTitle;
    private CompanyName companyName;
    private JobId jobId;
    private Address address;
    private Qualification qualification;
    private JobStatus jobStatus;
    private Position position;
    private Salary salary;

    /**
     * Creates a {@code JobBuilder} with the default details.
     */
    public JobBuilder() {
        jobTitle = new JobTitle(DEFAULT_JOB_TITLE);
        companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        jobId = new JobId(DEFAULT_JOB_ID);
        address = new Address(DEFAULT_ADDRESS);
        qualification = new Qualification(DEFAULT_QUALIFICATION);
        jobStatus = new JobStatus(DEFAULT_JOB_STATUS);
        position = new Position(DEFAULT_POSITION);
        salary = new Salary(DEFAULT_SALARY_LOW, DEFAULT_SALARY_HIGH);
    }

    /**
     * Initializes the JobBuilder with the data of {@code jobToCopy}.
     */
    public JobBuilder(Job jobToCopy) {
        jobTitle = jobToCopy.getJobTitle();
        companyName = jobToCopy.getCompany();
        jobId = jobToCopy.getJobId();
        address = jobToCopy.getAddress();
        qualification = jobToCopy.getQualification();
        jobStatus = jobToCopy.getJobStatus();
        position = jobToCopy.getPosition();
        salary = jobToCopy.getSalary();
    }

    /**
     * Sets the {@code JobTitle} of the {@code Job} that we are building.
     */
    public JobBuilder withJobTitle(String jobTitle) {
        this.jobTitle = new JobTitle(jobTitle);
        return this;
    }

    /**
     * Sets the {@code CompanyName} of the {@code Job} that we are building.
     */
    public JobBuilder withCompanyName(String companyName) {
        this.companyName = new CompanyName(companyName);
        return this;
    }

    /**
     * Sets the {@code JobId} of the {@code Job} that we are building.
     */
    public JobBuilder withJobId(String jobId) {
        this.jobId = new JobId(jobId);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Job} that we are building.
     */
    public JobBuilder withJobAddress(String jobAddress) {
        this.address = new Address(jobAddress);
        return this;
    }

    /**
     * Sets the {@code Qualification} of the {@code Job} that we are building.
     */
    public JobBuilder withJobQualification(String jobQualification) {
        this.qualification = new Qualification(jobQualification);
        return this;
    }

    /**
     * Sets the {@code JobStatus} of the {@code Job} that we are building.
     */
    public JobBuilder withJobStatus(String jobStatus) {
        this.jobStatus = new JobStatus(jobStatus);
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Job} that we are building.
     */
    public JobBuilder withJobPosition(String jobPosition) {
        this.position = new Position(jobPosition);
        return this;
    }

    /**
     * Sets the {@code JobSalary} of the {@code Job} that we are building.
     */
    public JobBuilder withJobSalary(String jobSalaryLow, String jobSalaryHigh) {
        this.salary = new Salary(jobSalaryLow, jobSalaryHigh);
        return this;
    }

    /**
     * Builds a job object for testing
     *
     * @return the job object
     */
    public Job build() {
        return new Job(jobTitle, companyName, jobId, address,
                qualification, jobStatus, position, salary);
    }
}

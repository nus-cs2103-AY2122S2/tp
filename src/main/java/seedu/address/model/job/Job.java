package seedu.address.model.job;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.applicant.JobId;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.person.Address;

public class Job {

    // Identity fields
    private final JobTitle jobTitle;
    private final CompanyName companyName;
    private final JobId id;

    // Data fields
    private final Address address;
    private final Qualification qualification;
    private final JobStatus jobStatus;
    private final Position position;
    private final Salary salary;



    /**
     * Creates a Job object with all attributes for use by Edit method
     */
    public Job(JobTitle title, CompanyName companyName, JobId id,
               Address address, Qualification qualification, JobStatus jobStatus,
               Position position, Salary salary) {
        requireAllNonNull(title, companyName, id, address, qualification, jobStatus, position, salary);
        this.jobTitle = title;
        this.companyName = companyName;
        this.id = id;
        this.address = address;
        this.qualification = qualification;
        this.jobStatus = jobStatus;
        this.position = position;
        this.salary = salary;
    }

    /**
     * Creates a Job object with minimum required attributes for use by Add method
     */
    public Job(JobTitle title, CompanyName company,
               Address address, Qualification qualification,
               Position position, Salary salary) {
        requireAllNonNull(title, company, address, qualification, position, salary);
        this.jobTitle = title;
        this.companyName = company;
        this.id = new JobId();
        this.address = address;
        this.qualification = qualification;
        this.jobStatus = new JobStatus("vacant");
        this.position = position;
        this.salary = salary;
    }

    /**
     * Creates a Job object with specified job status for use by Mark method
     */
    public Job(Job job, JobStatus jobStatus) {
        requireAllNonNull(job, jobStatus);
        this.jobTitle = job.getJobTitle();
        this.companyName = job.getCompany();
        this.id = job.getJobId();
        this.address = job.getAddress();
        this.qualification = job.getQualification();
        this.jobStatus = jobStatus;
        this.position = job.getPosition();
        this.salary = job.getSalary();
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public CompanyName getCompany() {
        return companyName;
    }

    public JobId getJobId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public Position getPosition() {
        return position;
    }

    public Salary getSalary() {
        return salary;
    }

    /**
     * Returns true if both Jobs have the same title and company.
     * This defines a weaker notion of equality between two jobs.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
                && otherJob.getJobTitle().equals(getJobTitle())
                && otherJob.getCompany().equals(getCompany());
    }

    /**
     * Returns true if both Jobs have the same identity and data fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return otherJob.getJobTitle().equals(getJobTitle())
                && otherJob.getCompany().equals(getCompany())
                && otherJob.getJobId().equals(getJobId())
                && otherJob.getAddress().equals(getAddress())
                && otherJob.getQualification().equals(getQualification())
                && otherJob.getJobStatus().equals(getJobStatus())
                && otherJob.getPosition().equals(getPosition())
                && otherJob.getSalary().equals(getSalary());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getJobTitle())
                .append("; Company: ")
                .append(getCompany())
                .append("; Job Id: ")
                .append(getJobId())
                .append("; Address: ")
                .append(getAddress())
                .append("; Qualification: ")
                .append(getQualification())
                .append("; Job Status: ")
                .append(getJobStatus())
                .append("; Position: ")
                .append(getPosition())
                .append("; Salary: ")
                .append(getSalary());
        return builder.toString();
    }
}

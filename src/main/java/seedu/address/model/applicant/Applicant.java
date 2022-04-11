package seedu.address.model.applicant;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class Applicant {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Nric nric;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final DateApplied dateApplied;
    private final InterviewDate interviewDate;
    private final JobId job;
    private final Qualification qualification;
    private final ApplicantStatus applicantStatus;



    /**
     * Creates an Applicant object with all attributes for use by Edit method
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                     DateApplied dateApplied, Nric nric, JobId job, InterviewDate interviewDate,
                     Qualification qualification, ApplicantStatus applicantStatus) {
        requireAllNonNull(name, phone, email, address, tags, dateApplied, interviewDate, nric, job, qualification);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.nric = nric;
        this.dateApplied = dateApplied;
        this.interviewDate = interviewDate;
        this.job = job;
        this.qualification = qualification;
        this.applicantStatus = applicantStatus;
    }

    /**
     * Creates an Applicant object with minimum required attributes for use by Add method
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                     DateApplied dateApplied, Nric nric) {
        requireAllNonNull(name, phone, email, address, tags, dateApplied, nric);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.nric = nric;
        this.dateApplied = dateApplied;
        this.job = new JobId();
        this.interviewDate = new InterviewDate();
        this.qualification = new Qualification();
        this.applicantStatus = new ApplicantStatus("1");
    }

    /**
     * Creates an Applicant object with old unmarked Applicant descriptors
     * for use by Mark method
     */
    public Applicant(Applicant unmarkedApplicant, ApplicantStatus applicantStatus) {
        requireAllNonNull(unmarkedApplicant, applicantStatus);
        this.name = unmarkedApplicant.getName();
        this.phone = unmarkedApplicant.getPhone();
        this.email = unmarkedApplicant.getEmail();
        this.address = unmarkedApplicant.getAddress();
        this.tags.addAll(unmarkedApplicant.getTags());
        this.nric = unmarkedApplicant.getNric();
        this.dateApplied = unmarkedApplicant.getDateApplied();
        this.interviewDate = unmarkedApplicant.getInterviewDate();
        this.job = unmarkedApplicant.getJobId();
        this.qualification = unmarkedApplicant.getQualification();
        this.applicantStatus = applicantStatus;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Nric getNric() {
        return nric;
    }

    public DateApplied getDateApplied() {
        return dateApplied;
    }

    public InterviewDate getInterviewDate() {
        return interviewDate;
    }

    public JobId getJobId() {
        return job;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public ApplicantStatus getApplicantStatus() {
        return applicantStatus;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both applicants have the same nric, email and phone number.
     * Used to compare between applicants in the applicant list.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }
        return otherApplicant != null
                && this.isSame(otherApplicant);
    }

    private boolean isSame(Applicant otherApplicant) {
        return otherApplicant.getNric().equals(getNric())
                || otherApplicant.getEmail().equals(getEmail())
                || otherApplicant.getPhone().equals(getPhone());
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return otherApplicant.getName().equals(getName())
                && otherApplicant.getPhone().equals(getPhone())
                && otherApplicant.getEmail().equals(getEmail())
                && otherApplicant.getAddress().equals(getAddress())
                && otherApplicant.getTags().equals(getTags())
                && otherApplicant.getDateApplied().equals(getDateApplied())
                && otherApplicant.getNric().equals(getNric())
                && otherApplicant.getJobId().equals(getJobId())
                && otherApplicant.getInterviewDate().equals(getInterviewDate())
                && otherApplicant.getApplicantStatus().equals((getApplicantStatus()))
                && otherApplicant.getQualification().equals((getQualification()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Application status: ")
                .append(getApplicantStatus())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Nric: ")
                .append(getNric())
                .append("; Date applied: ")
                .append(getDateApplied())
                .append("; Date of interview: ")
                .append(getInterviewDate())
                .append("; Job: ")
                .append(getJobId())
                .append(" [")
                .append(getApplicantStatus())
                .append("]; Qualification: ")
                .append(getQualification());
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}

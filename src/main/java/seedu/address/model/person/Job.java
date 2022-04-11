package seedu.address.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the job applied by an applicant in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidJob(String)}
 */
public class Job {

    public static final String MESSAGE_CONSTRAINTS = "Job should only contain alphanumeric "
            + "characters and spaces, and it should not be blank";

    /*
     * The first character of the job must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String jobTitle;

    /**
     * Constructs a {@code Job}.
     *
     * @param jobTitle A valid job.
     */
    public Job(String jobTitle) {
        requireNonNull(jobTitle);
        checkArgument(isValidJob(jobTitle), MESSAGE_CONSTRAINTS);
        this.jobTitle = jobTitle;
    }

    /**
     * Returns true if a given string is a valid job.
     */
    public static boolean isValidJob(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getJob() {
        return this.jobTitle;
    }

    public boolean contains(String s) {
        return jobTitle.toLowerCase().contains(s.toLowerCase().strip());
    }

    @Override
    public String toString() {
        return jobTitle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Job // instanceof handles nulls
                && jobTitle.equals(((Job) other).jobTitle)); // state check
    }

    @Override
    public int hashCode() {
        return jobTitle.hashCode();
    }

}


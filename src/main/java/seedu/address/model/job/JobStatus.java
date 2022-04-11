package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Job listings' availability status: filled or vacant.
 */
public class JobStatus {
    public static final String MESSAGE_CONSTRAINTS = "Job status should be denoted as either 'filled' or 'vacant'";

    public static final String JOBSTATUS_FILLED = "filled";
    public static final String JOBSTATUS_VACANT = "vacant";

    public final String jobStatus;

    /**
     * Instantiates a new Job status.
     *
     * @param jobStatus the user input for job status
     */
    public JobStatus(String jobStatus) {
        requireNonNull(jobStatus);
        checkArgument(isValid(jobStatus), MESSAGE_CONSTRAINTS);
        this.jobStatus = jobStatus.toLowerCase();
    }

    public static boolean isValid(String jobStatus) {
        return jobStatus.toLowerCase().matches(JOBSTATUS_FILLED + "|" + JOBSTATUS_VACANT);
    }

    @Override
    public String toString() {
        return jobStatus;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobStatus
            && jobStatus.equals(((JobStatus) other).jobStatus));
    }

    @Override
    public int hashCode() {
        return jobStatus.hashCode();
    }

}

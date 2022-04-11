package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class JobId implements Comparable<JobId> {

    /**
     * The error message for invalid job ID received.
     */
    public static final String MESSAGE_CONSTRAINTS = "Job ID can take any positive integer value, "
            + "and it should not be blank";

    /**
     * The job id.
     */
    public final Integer jobId;

    private final boolean isInit;

    /**
     * Constructs a Job ID
     *
     * @param jobId ID of job applicant is applying for
     */
    public JobId(String jobId) {
        requireNonNull(jobId);
        checkArgument(isValidJobId(jobId), MESSAGE_CONSTRAINTS);
        if (jobId.equals("PENDING")) {
            isInit = false;
            this.jobId = 0;
        } else {
            isInit = true;
            this.jobId = Integer.parseInt(jobId);
        }
    }

    /**
     * Constructor to create JobId that is not initialized
     */
    public JobId() {
        isInit = false;
        this.jobId = 10;
    }

    /**
     * Returns true if the input integer is a valid Id.
     */
    public static boolean isValidJobId(String test) {
        if (test.equals("PENDING")) {
            return true;
        }
        try {
            int testing = Integer.parseInt(test);
            return testing > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        if (isInit) {
            return this.jobId.toString();
        } else {
            return "PENDING";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.JobId
                && jobId.equals(((JobId) other).jobId));
    }

    @Override
    public int hashCode() {
        return jobId.hashCode();
    }

    @Override
    public int compareTo(JobId o) {
        return !isInit && o.isInit
                ? 1
                : isInit && !o.isInit
                    ? -1
                    : jobId.compareTo(o.jobId);
    }
}

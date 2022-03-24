package seedu.address.model.candidate;

/**
 * Represents a Candidate's interview status in TAlent Assistant™.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class InterviewStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Interview Status should be Pending, Interviewing or Not Interviewed";
    public static final String NOT_SCHEDULED = "Not Scheduled";
    public static final String SCHEDULED = "Scheduled";
    public static final String COMPLETED = "Completed";

    public final String interviewStatus;

    /**
     * Constructor for Interview Status.
     *
     * @param interviewStatusType the string value of the status.
     */
    public InterviewStatus(String interviewStatusType) {
        String input = interviewStatusType.toLowerCase();
        String statusTemp = "";
        input = input.replaceAll(" ", "");

        if (input.equals(SCHEDULED.toLowerCase())) {
            statusTemp = SCHEDULED;
        } else if (input.equals(COMPLETED.toLowerCase())) {
            statusTemp = COMPLETED;
        } else if (input.equals(NOT_SCHEDULED
                .replaceAll(" ", "").toLowerCase())) {
            statusTemp = NOT_SCHEDULED;
        }

        this.interviewStatus = statusTemp;
    }

    /**
     * Check if the status is a valid status.`
     *
     * @param interviewStatusType the status type.
     * @return true if it is valid, false if not valid.
     */
    public static boolean isValidStatus(String interviewStatusType) {

        String input = interviewStatusType.toLowerCase();
        input = input.replaceAll(" ", "");

        if (input.equals(SCHEDULED.toLowerCase())
                || input.equals(COMPLETED.toLowerCase())
                || input.equals(NOT_SCHEDULED.replaceAll(" ", "")
                .toLowerCase())) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns the string value of the Status.
     *
     * @return a string of the status.
     */
    @Override
    public String toString() {
        return interviewStatus;
    }


    /**
     * Check if the statuses are equal.
     *
     * @param other the other object of the same class.
     * @return true if equal, false if not.
     */
    @Override
    public boolean equals(Object other) {
        return ((InterviewStatus) other).interviewStatus.equals(this.interviewStatus);
    }

}

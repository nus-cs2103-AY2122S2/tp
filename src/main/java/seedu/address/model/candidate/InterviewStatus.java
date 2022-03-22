package seedu.address.model.candidate;

public class InterviewStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Interview Status should be Pending, Interviewing or Not Interviewed";
    public static final String PENDING_STATUS = "Pending";
    public static final String INTERVIEWING_STATUS = "Interviewing";
    public static final String NOT_INTERVIEWED_STATUS = "Not Interviewed";

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

        if (input.equals(PENDING_STATUS.toLowerCase())) {
            statusTemp = PENDING_STATUS;
        } else if (input.equals(INTERVIEWING_STATUS.toLowerCase())) {
            statusTemp = INTERVIEWING_STATUS;
        } else if (input.equals(NOT_INTERVIEWED_STATUS
                .replaceAll(" ", "").toLowerCase())) {
            statusTemp = NOT_INTERVIEWED_STATUS;
        }

        this.interviewStatus = statusTemp;
    }

    /**
     * Check if the status is a valid status.
     *
     * @param interviewStatusType the status type.
     * @return true if it is valid, false if not valid.
     */
    public static boolean isValidStatus(String interviewStatusType) {

        String input = interviewStatusType.toLowerCase();
        input = input.replaceAll(" ", "");

        if (input.equals(PENDING_STATUS.toLowerCase())
                || input.equals(INTERVIEWING_STATUS.toLowerCase())
                || input.equals(NOT_INTERVIEWED_STATUS.replaceAll(" ", "")
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

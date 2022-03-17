package seedu.address.model.candidate;

public class ApplicationStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Application Status should be Pending, Accepted or Rejected";
    public static final String PENDING_STATUS = "Pending";
    public static final String ACCPETED_STATUS = "Accepted";
    public static final String REJECTED_STATUS = "Rejected";

    private String statusType;

    /**
     * Constructor for Application Status.
     *
     * @param statusType the string value of the status.
     */
    public ApplicationStatus(String statusType) {
        String input = statusType.toLowerCase();
        if (input.equals(PENDING_STATUS.toLowerCase())) {
            this.statusType = PENDING_STATUS;
        } else if (input.equals(ACCPETED_STATUS.toLowerCase())) {
            this.statusType = ACCPETED_STATUS;
        } else if (input.equals(REJECTED_STATUS.toLowerCase())) {
            this.statusType = REJECTED_STATUS;
        }
    }

    /**
     * Check if the status is a valid status.
     *
     * @param statusType the status type.
     * @return true if it is valid, false if not valid.
     */
    public static boolean isValidStatus(String statusType) {
        String input = statusType.toLowerCase();
        if (input.equals(PENDING_STATUS.toLowerCase())
                || input.equals(ACCPETED_STATUS.toLowerCase())
                || input.equals(REJECTED_STATUS.toLowerCase())) {
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
        return this.statusType;
    }

    /**
     * Check if the statuses are equal.
     *
     * @param other the other object of the same class.
     * @return true if equal, false if not.
     */
    @Override
    public boolean equals(Object other) {
        return ((ApplicationStatus) other).statusType.equals(this.statusType);
    }
}

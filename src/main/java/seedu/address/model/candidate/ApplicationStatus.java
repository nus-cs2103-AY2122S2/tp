package seedu.address.model.candidate;

/**
 * Represents a Candidate's application status in TAlent Assistant™.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class ApplicationStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Application Status should be Pending, Accepted or Rejected";
    public static final String PENDING_STATUS = "Pending";
    public static final String ACCEPTED_STATUS = "Accepted";
    public static final String REJECTED_STATUS = "Rejected";

    public final String statusType;

    /**
     * Constructor for Application Status.
     *
     * @param statusType the string value of the status.
     */
    public ApplicationStatus(String statusType) {
        String input = statusType.toLowerCase();
        String statusTemp = "";
        if (input.equals(PENDING_STATUS.toLowerCase())) {
            statusTemp = PENDING_STATUS;
        } else if (input.equals(ACCEPTED_STATUS.toLowerCase())) {
            statusTemp = ACCEPTED_STATUS;
        } else if (input.equals(REJECTED_STATUS.toLowerCase())) {
            statusTemp = REJECTED_STATUS;
        }

        this.statusType = statusTemp;
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
                || input.equals(ACCEPTED_STATUS.toLowerCase())
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

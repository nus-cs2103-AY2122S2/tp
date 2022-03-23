package seedu.address.model.interview;

/**
 * Represents the status of interview in HireLah.
 * Guarantees: is always initialized as "Pending" {@link #Status()}
 */
public class Status {
    public static final String MESSAGE_CONSTRAINTS =
            "Gender should be exactly one character and only M or F (case sensitive)";

    public static final String VALIDATION_REGEX = "M|F";
    public String value;

    /**
     * Constructs a Status with default value of "Pending".
     */
    public Status() {
        value = "Pending";
    }

    public void markAsPassed() {
        value = "Passed - Waiting for Applicant";
    }

    public void markAsFailed() {
        value = "Failed";
    }

    public void markAsAccepted() {
        // Defensive programming to prevent acceptance before passing interview
        if (value != "Passed - Waiting for Applicant") {
            throw new RuntimeException("The Interview should be passed before its can be accepted by candidate");
        }
        value = "Accepted";
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value.equals(((Status) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

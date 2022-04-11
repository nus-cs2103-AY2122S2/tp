package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Interview Status of an applicant in the address book.
 */
public class InterviewStatus {

    public final Boolean isInterviewed;

    /**
     * Constructs an Interview Status
     * isInterviewed is initialized to false.
     */
    public InterviewStatus() {
        isInterviewed = false;
    }

    /**
     * Constructs an Interview Status
     *
     * @param status An interview status
     */
    public InterviewStatus(Boolean status) {
        requireNonNull(status);
        isInterviewed = status;
    }

    @Override
    public String toString() {
        return isInterviewed.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.InterviewStatus
                && isInterviewed.equals(((seedu.address.model.applicant.InterviewStatus) other).isInterviewed));
    }

    @Override
    public int hashCode() {
        return isInterviewed.hashCode();
    }
}

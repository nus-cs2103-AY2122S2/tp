package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant Status of an applicant in the address book.
 */
public class ApplicantStatus {

    /**
     * The error message for invalid applicant status received.
     */
    public static final String MESSAGE_CONSTRAINTS = "Application status has to be either rejected, pending, "
            + "interviewed, or accepted";
    private static final String[] STATUSES = {"REJECTED", "PENDING", "INTERVIEWED", "ACCEPTED"};
    public final Integer applicantStatus;

    /**
     * Constructs an Applicant Status
     *
     * @param status An applicant's job application status
     */
    public ApplicantStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        applicantStatus = Integer.parseInt(status);
    }

    /**
     * Returns true if a given string is a valid state for application status
     */
    public static boolean isValidStatus(String status) {
        try {
            int test = Integer.parseInt(status);
            return test >= 0 && test <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return STATUSES[this.applicantStatus];
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.ApplicantStatus
                && applicantStatus.equals(((ApplicantStatus) other).applicantStatus));
    }

    @Override
    public int hashCode() {
        return applicantStatus.hashCode();
    }
}

package seedu.address.model.applicant;

import java.util.function.Predicate;

/**
 * Tests that an {@code Applicant}'s {@code HiredStatus} matches the status given.
 */
public class ApplicantStatusPredicate implements Predicate<Applicant> {
    private final String status;

    public ApplicantStatusPredicate(String status) {
        this.status = status;
    }

    @Override
    public boolean test(Applicant applicant) {
        assert status.equals("available") || status.equals("hired");
        return (status.equals("available") && applicant.getStatus().toString().equals("Available"))
                || (status.equals("hired") && applicant.getStatus().toString().toLowerCase().contains("hired"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicantStatusPredicate // instanceof handles nulls
                && status.equals(((ApplicantStatusPredicate) other).status)); // state check
    }
}

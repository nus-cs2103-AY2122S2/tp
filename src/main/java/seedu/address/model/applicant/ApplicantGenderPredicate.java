package seedu.address.model.applicant;

import java.util.function.Predicate;

/**
 * Tests that an {@code Applicant}'s {@code Gender} matches the gender given.
 */
public class ApplicantGenderPredicate implements Predicate<Applicant> {
    private final String gender;

    public ApplicantGenderPredicate(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean test(Applicant applicant) {
        assert gender.equals("M") || gender.equals("F");
        return applicant.getGender().value.equals(gender);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ApplicantGenderPredicate // instanceof handles nulls
                && gender.equals(((ApplicantGenderPredicate) other).gender)); // state check
    }
}

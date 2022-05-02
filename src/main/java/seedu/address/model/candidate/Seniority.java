package seedu.address.model.candidate;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Candidate's seniority in TAlent Assistant™.
 * Guarantees: immutable; is valid as declared in {@link #isValidSeniority(String)}
 */
public class Seniority {
    public static final String MESSAGE_CONSTRAINTS =
            "Seniority input should only either be 1, 2, 3 or 4, and it should not be blank";
    public static final String COM_VALUE = "COM";
    public static final String VALIDATION_REGEX = "[1|2|3|4]";
    public final String seniority;

    /**
     * Constructs a {@code Seniority}.
     *
     * @param seniority A valid seniority.
     */
    public Seniority(String seniority) {
        checkArgument(isValidSeniority(seniority), MESSAGE_CONSTRAINTS);

        this.seniority = seniority;
    }

    /**
     * Returns true if a given string is a valid seniority.
     */
    public static boolean isValidSeniority(String test) {
        if (test == null) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Formats the {@code seniority} with the appended 'COM' string as a prefix.
     * @return the formatted seniority string appended to 'COM'
     */
    public String toSearchString() {
        return COM_VALUE + seniority;
    }

    @Override
    public String toString() {
        return seniority;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Seniority // instanceof handles nulls
                && seniority.equals(((Seniority) other).seniority)); // state check
    }

    @Override
    public int hashCode() {
        return seniority.hashCode();
    }
}

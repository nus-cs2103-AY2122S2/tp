package seedu.address.model.candidate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Candidate's seniority in TAlent Assistant™.
 * Guarantees: immutable; is valid as declared in {@link #isValidSeniority(String)}
 */
public class Seniority {
    public static final String MESSAGE_CONSTRAINTS =
            "Seniority should only either be COM1, COM2, COM3 or COM4, and it should not be blank";
    public static final String VALIDATION_REGEX = "COM[1|2|3|4]";
    public final String seniority;

    /**
     * Constructs a {@code Seniority}.
     *
     * @param seniority A valid seniority.
     */
    public Seniority(String seniority) {
        requireNonNull(seniority);
        checkArgument(isValidSeniority(seniority), MESSAGE_CONSTRAINTS);
        this.seniority = seniority;
    }

    /**
     * Returns true if a given string is a valid seniority.
     */
    public static boolean isValidSeniority(String test) {
        return test.matches(VALIDATION_REGEX);
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

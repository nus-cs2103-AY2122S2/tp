package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import java.time.LocalDate;

public class DOB {
    public static final String MESSAGE_CONSTRAINTS =  "DOB should be in YYYY-MM-DD format";
    public static final String VALIDATION_REGEX = "";
    public final LocalDate dateOfBirth;

    /**
     * Constructs a {@code DOB}.
     *
     * @param dob A valid dob in YYYY-MM-DD format.
     */
    public DOB(LocalDate dob) {
        requireNonNull(dob);
        checkArgument(isValidDOB(dob), MESSAGE_CONSTRAINTS);
        dateOfBirth = dob;
    }

    /**
     * Returns true if a given string is a valid DOB.
     */
    public static boolean isValidDOB(LocalDate test) {
        return test.toString().matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return dateOfBirth.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && dateOfBirth.equals(((DOB) other).dateOfBirth)); // state check
    }

    @Override
    public int hashCode() {
        return dateOfBirth.hashCode();
    }


}

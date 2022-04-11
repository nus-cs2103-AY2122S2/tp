package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant's qualification in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidQualification(String)}
 */
public class Qualification {
    public static final String MESSAGE_CONSTRAINTS =
            "Qualifications should only contain alphanumeric characters and spaces, "
                    + "special symbols such as '/' or '#' should be avoided.\n"
                    + " It should also not be blank";

    /*
     * The first character of the qualification must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String highestQualification;

    private final boolean isInit;

    /**
     * Constructs a {@code Qualification}.
     *
     * @param qualification A valid qualification.
     */
    public Qualification(String qualification) {
        requireNonNull(qualification);
        checkArgument(isValidQualification(qualification), MESSAGE_CONSTRAINTS);

        if (qualification.equals("PENDING")) {
            isInit = false;
            highestQualification = "PENDING";
        } else {
            isInit = true;
            highestQualification = qualification;
        }
    }

    /**
     * Constructs an instance of Qualification that is not initialised
     */
    public Qualification() {
        isInit = false;
        highestQualification = "PENDING";
    }


    /**
     * Returns true if a given string is a valid qualification.
     */
    public static boolean isValidQualification(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals("PENDING");
    }

    @Override
    public String toString() {
        if (isInit) {
            return highestQualification;
        } else {
            return "PENDING";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.Qualification // instanceof handles nulls
                && highestQualification.equals(((seedu.address.model.applicant.Qualification) other)
                .highestQualification)); // state check
    }

    @Override
    public int hashCode() {
        return highestQualification.hashCode();
    }
}

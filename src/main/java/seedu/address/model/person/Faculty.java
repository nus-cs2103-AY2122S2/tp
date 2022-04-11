package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact's faculty in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidFaculty(String)}
 */
public class Faculty {


    public static final String MESSAGE_CONSTRAINTS = "Faculty should not be blank "
            + "and should only have the following values\n"
            + "1. CHS\n"
            + "2. Business\n"
            + "3. Computing\n"
            + "4. Dentistry\n"
            + "5. CDE\n"
            + "6. Law\n"
            + "7. Medicine\n"
            + "8. Nursing\n"
            + "9. Pharmacy\n"
            + "10. Music\n"
            + "11. Others\n";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[A-Za-z]+$";
    public final String value;

    /**
     * Constructs a {@code Faculty}.
     *
     * @param faculty A valid faculty.
     */
    public Faculty(String faculty) {
        requireNonNull(faculty);
        checkArgument(isValidFaculty(faculty), MESSAGE_CONSTRAINTS);
        value = faculty.substring(0, 1).toUpperCase() + faculty.substring(1).toLowerCase();
    }

    /**
     * Returns true if a given string is a valid faculty.
     */
    public static boolean isValidFaculty(String test) {
        return test.matches(VALIDATION_REGEX)
                && (test.equalsIgnoreCase("CHS")
                || test.equalsIgnoreCase("Business")
                || test.equalsIgnoreCase("Computing")
                || test.equalsIgnoreCase("CDE")
                || test.equalsIgnoreCase("Dentistry")
                || test.equalsIgnoreCase("Law")
                || test.equalsIgnoreCase("Medicine")
                || test.equalsIgnoreCase("Nursing")
                || test.equalsIgnoreCase("Pharmacy")
                || test.equalsIgnoreCase("Music")
                || test.equalsIgnoreCase("Others"));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Faculty // instanceof handles nulls
                && value.equals(((Faculty) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

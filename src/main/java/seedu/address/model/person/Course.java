package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Course in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCourse(String)}
 */
public class Course {

    public static final String MESSAGE_CONSTRAINTS =
            "Course should only contain alphabet characters and spaces.";

    /*
     * Input can be blank space, if you input blank spaces,
     * it is treated as if the course field is unfilled and blank.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";

    public final String value;

    /**
     * Constructs a {@code Course}.
     *
     * @param courseName A valid courseName.
     */
    public Course(String courseName) {
        requireNonNull(courseName);
        checkArgument(isValidCourse(courseName), MESSAGE_CONSTRAINTS);
        value = courseName;
    }

    /**
     * Returns true if a given string is a valid courseName.
     */
    public static boolean isValidCourse(String test) {
        return test.equals("") || test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Course // instanceof handles nulls
                && value.equals(((Course) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

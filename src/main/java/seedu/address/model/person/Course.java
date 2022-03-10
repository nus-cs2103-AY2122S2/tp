package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Course {
    public static final String MESSAGE_CONSTRAINTS =
            "Courses should only contain alphabets, and it should not be blank";

    /*
     * The format of Course should only contain alphabets case-insensitive.
     */
    public static final String VALIDATION_REGEX = "^\\w+( \\w+)*$";

    public final String course;

    /**
     * Constructs a {@code StudentID}.
     *
     * @param course A valid course.
     */
    public Course(String course) {
        requireNonNull(course);
        checkArgument(isValidId(course), MESSAGE_CONSTRAINTS);
        this.course = course;
    }

    /**
     * Returns true if a given string is a valid course.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return course;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Course // instanceof handles nulls
                && course.equals(((Course) other).course)); // state check
    }

    @Override
    public int hashCode() {
        return course.hashCode();
    }
}

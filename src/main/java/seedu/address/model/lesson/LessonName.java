package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Lesson's name in the lesson book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class LessonName {

    public static final String MESSAGE_CONSTRAINTS =
            "Lesson names should only contain alphanumeric characters and spaces."
                    + "\nLesson names should not be blank and is capped at 50 characters.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code LessonName}.
     *
     * @param name A valid name.
     */
    public LessonName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 50;
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonName // instanceof handles nulls
                && fullName.equals(((LessonName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}

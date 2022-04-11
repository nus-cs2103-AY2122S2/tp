package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's name in the student book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject {
    public static final String MESSAGE_CONSTRAINTS =
            "Subject should only contain alphanumeric characters and spaces."
                    + "\nSubject should not be blank and is capped at 50 characters.";

    /*
     * The first character of the subject must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final Subject EMPTY_SUBJECT = new Subject();

    public final String subjectName;

    /**
     * Constructs a {@code Subject}.
     *
     * @param name A valid subject name.
     */
    public Subject(String name) {
        requireNonNull(name);
        checkArgument(isValidSubject(name), MESSAGE_CONSTRAINTS);
        subjectName = name;
    }

    /**
     * Constructs an empty {@code Subject}
     */
    public Subject() {
        subjectName = "NO SUBJECT ASSIGNED";
    }

    /**
     * Returns true if a given string is a valid subject.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 50;
    }

    @Override
    public String toString() {
        return subjectName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Subject // instanceof handles nulls
                && subjectName.equals(((Subject) other).subjectName)); // state check
    }

    @Override
    public int hashCode() {
        return subjectName.hashCode();
    }

}

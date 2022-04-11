package seedu.address.model.lab;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;

import seedu.address.model.lab.exceptions.IllegalLabStateException;

/**
 * Represents the marks attained in a Lab assignment.
 * Guarantees: immutable; is valid as declared in {@link #isValidLabMark(String)}
 */
public class LabMark {

    public static final String MESSAGE_CONSTRAINTS =
            "Lab mark should be an integer between 0 and 100 inclusive.";

    public static final String VALIDATION_REGEX = "\\d*";

    /*
     * A placeholder value to represent that the marks have not been initialized.
     */
    public static final String MARKS_UNKNOWN = "Unknown";

    public static final String MARKS_DESCRIPTION = "Marks: %1$s";

    private final Optional<Integer> marks;

    /**
     * Constructs a {@code LabMark} with marks initialized.
     *
     * @param mark A valid lab mark.
     */
    public LabMark(String mark) {
        requireNonNull(mark);
        checkArgument(isValidLabMark(mark), MESSAGE_CONSTRAINTS);
        this.marks = Optional.of(Integer.parseInt(mark));
    }

    /**
     * Constructs a {@code LabMark} with no marks initialized.
     */
    public LabMark() {
        this.marks = Optional.empty();
    }

    /**
     * Returns true if a given string contains only digits and the Integer it parses to is between 0 and 100 inclusive.
     *
     * @param test The string to be checked and parsed.
     * @return True if the string is a valid Integer between 0 and 100 inclusive and false otherwise.
     */
    public static boolean isValidLabMark(String test) {
        return isValidString(test) && isIntegerInBounds(test);
    }

    private static boolean isValidString(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private static boolean isIntegerInBounds(String test) {
        try {
            int labMark = Integer.parseInt(test);
            return labMark >= 0 && labMark <= 100;
        } catch (NumberFormatException e) {
            // can't be parsed to Integer so return false.
            return false;
        }
    }

    /**
     * Returns true if the marks have not been initialized.
     */
    public boolean isEmpty() {
        return !marks.isPresent();
    }

    /**
     * Returns a String of the numerical value of {@code marks} if
     * it is not empty, otherwise returns a placeholder String.
     */
    @Override
    public String toString() {
        if (marks.isPresent()) {
            return String.valueOf(marks.get());
        }
        return MARKS_UNKNOWN;
    }

    /**
     * Returns true if both LabMarks have marks with the same value.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabMark // instanceof handles nulls
                && marks.equals(((LabMark) other).marks)); // state check
    }

    /**
     * Returns a description of {@code this} in String format
     */
    public String describe() {
        if (isEmpty()) {
            throw new IllegalLabStateException();
        }
        return String.format(MARKS_DESCRIPTION, this);
    }
}

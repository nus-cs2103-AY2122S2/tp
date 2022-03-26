package seedu.address.model.lab;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;

/**
 * Represents the marks attained in a Lab assignment.
 * Guarantees: immutable; is valid as declared in {@link #isValidLabMark(String)}
 */
public class LabMark {

    public static final String MESSAGE_CONSTRAINTS =
            "Lab mark should be a non-negative integer.";

    public static final String VALIDATION_REGEX = "0|([1-9]\\d*)";

    /*
     * A placeholder value to represent that the marks have not been initialized.
     */
    public static final String MARKS_UNKNOWN = "Unknown";

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
     * Returns true if a given string is a valid lab mark.
     */
    public static boolean isValidLabMark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the marks have not been initialized.
     */
    public boolean isEmpty() {
        return this.marks.equals(Optional.empty());
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
     * Returns true if both LabScores have scores with the same value.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabMark // instanceof handles nulls
                && marks.equals(((LabMark) other).marks)); // state check
    }

}

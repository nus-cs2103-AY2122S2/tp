package seedu.trackbeau.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.AppUtil.checkArgument;

/**
 * Represents a Customer's Feedback of the booking in trackBeau.
 * Guarantees: immutable; is valid as declared in {@link #isValidFeedback(String)}
 */
public class Feedback {

    public static final String MESSAGE_CONSTRAINTS =
            "Feedback should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of feedback must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String feedback;

    /**
     * Constructs a {@code feedback}.
     *
     * @param feedback A valid feedback.
     */
    public Feedback(String feedback) {
        requireNonNull(feedback);
        checkArgument(isValidFeedback(feedback), MESSAGE_CONSTRAINTS);
        this.feedback = feedback;
    }

    /**
     * Returns true if a given string is a valid feedback.
     */
    public static boolean isValidFeedback(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return feedback;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Feedback // instanceof handles nulls
                && feedback.equals(((Feedback) other).feedback)); // state check
    }

    @Override
    public int hashCode() {
        return feedback.hashCode();
    }

}

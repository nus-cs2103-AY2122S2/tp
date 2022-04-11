package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a applicant's job application stage in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStage(String)}
 */
public class Stage {

    public static final String MESSAGE_CONSTRAINTS =
            "Stage should be only INPROGRESS or ACCEPTED or REJECTED (case-sensitive)";

    public static final String VALIDATION_REGEX = "INPROGRESS|ACCEPTED|REJECTED";

    public final String value;

    /**
     * Constructs a {@code Stage}.
     *
     * @param stage A valid stage.
     */
    public Stage(String stage) {
        requireNonNull(stage);
        checkArgument(isValidStage(stage), MESSAGE_CONSTRAINTS);
        value = stage;
    }

    /**
     * Returns true if a given string is a valid stage.
     */
    public static boolean isValidStage(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean contains(String s) {
        return value.toLowerCase().contains(s.toLowerCase());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Stage // instanceof handles nulls
                && value.equals(((Stage) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

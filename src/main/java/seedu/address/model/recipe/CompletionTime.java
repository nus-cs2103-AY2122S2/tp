package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Recipe's completion time in the recipe book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompletionTime(Integer)}
 */
public class CompletionTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Completion time should be a valid, reasonable amount of time in minutes that's greater than 0 "
                    + "and less than a year";

    public final Integer value;

    /**
     * Constructs a {@code CompletionTime}.
     *
     * @param completionTime A valid completion time.
     */
    public CompletionTime(Integer completionTime) {
        requireNonNull(completionTime);
        checkArgument(isValidCompletionTime(completionTime), MESSAGE_CONSTRAINTS);

        value = completionTime;
    }

    public static boolean isValidCompletionTime(Integer test) {
        return (test > 0 && test <= 525600);
    }

    @Override
    public String toString() {
        return String.format("%d mins", value);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CompletionTime)) {
            return false;
        }

        if (this == o) {
            return true;
        }

        CompletionTime other = (CompletionTime) o;
        return this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

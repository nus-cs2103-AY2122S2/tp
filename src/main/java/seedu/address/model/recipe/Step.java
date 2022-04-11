package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a step in the list of steps to make the Recipe in the recipe book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStep(String)},
 */
public class Step {
    public static final String MESSAGE_CONSTRAINTS = "Recipe should not contain any empty steps";

    public final String value;

    /**
     * Constructs a {@code Steps}.
     *
     * @param step  a step in the list of steps to make the Recipe.
     */
    public Step(String step) {
        requireNonNull(step);
        step = step.strip();
        checkArgument(isValidStep(step), MESSAGE_CONSTRAINTS);
        value = step;
    }

    /**
     * Returns true if the given step is not empty.
     */
    public static boolean isValidStep(String test) {
        return !test.isEmpty();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Step // instanceof handles nulls
                && value.equals(((Step) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

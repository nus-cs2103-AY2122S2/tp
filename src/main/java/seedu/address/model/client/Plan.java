package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's subscription plan of its network.
 * Guarantees: immutable; is valid as declared in {@link #isValidPlan(String)}
 */
public class Plan {
    public static final String MESSAGE_CONSTRAINTS = "Plans can take any values, and it should not be blank";

    /*
     * The first character of the plan must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Plan}.
     *
     * @param plan A valid subscription plan.
     */
    public Plan(String plan) {
        requireNonNull(plan);
        checkArgument(isValidPlan(plan), MESSAGE_CONSTRAINTS);
        value = plan;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidPlan(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Plan // instanceof handles nulls
                && value.equals(((Plan) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

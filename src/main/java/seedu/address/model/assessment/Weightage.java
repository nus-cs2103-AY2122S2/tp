package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the weightage of an Assessment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeightage(String)}
 */
public class Weightage {
    public static final String MESSAGE_CONSTRAINTS = "The weightage should be a number between 0 and 100";
    /**
     * The weightage must be an integer between 0 and 100, inclusive.
     */
    public static final String VALIDATION_REGEX = "^(0|[1-9][0-9]?|100)";
    public final int weightage;

    /**
     * Constructs a {@code Weightage}.
     *
     * @param value a weightage.
     */
    public Weightage(String value) {
        requireNonNull(value);
        checkArgument(isValidWeightage(value), MESSAGE_CONSTRAINTS);
        weightage = Integer.parseInt(value);
    }

    /**
     * Returns true if a given string is a valid weightage.
     */
    public static boolean isValidWeightage(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Integer.toString(weightage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Weightage
                && weightage == ((Weightage) other).weightage);
    }

    @Override
    public int hashCode() {
        return weightage;
    }
}

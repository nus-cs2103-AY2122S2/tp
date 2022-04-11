package woofareyou.model.pet;

import static java.util.Objects.requireNonNull;
import static woofareyou.commons.util.AppUtil.checkArgument;

/**
 * Represents a Pet's diet in WoofAreYou.
 * Guarantees: immutable; is always valid
 */
public class Diet {
    /*
     * Diet should not contain special characters.
    */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9 ]*$";
    public static final String MESSAGE_CONSTRAINTS =
            "Diet description can be empty but should only contain alphanumeric characters or spaces.";

    public final String value;
    /**
     * Constructs a {@code Diet}.
     *
     * @param diet A pet's diet.
     */
    public Diet(String diet) {
        requireNonNull(diet);
        checkArgument(isValidDiet(diet), MESSAGE_CONSTRAINTS);
        value = diet.trim().replaceAll(" +", " ");
    }

    /**
     * Returns true if a given string is a valid diet description.
     */
    public static boolean isValidDiet(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Diet
                && value.equals(((Diet) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

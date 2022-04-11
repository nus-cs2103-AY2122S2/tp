package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's pronouns in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPronoun(String)}
 */
public class Pronoun {

    public static final String MESSAGE_CONSTRAINTS = "Pronouns can take any values, and it should not be blank";

    /*
     * The first character of the pronouns must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String pronoun;

    /**
     * Constructs an {@code Pronoun}.
     *
     * @param pronoun A valid pronoun.
     */
    public Pronoun(String pronoun) {
        requireNonNull(pronoun);
        checkArgument(isValidPronoun(pronoun), MESSAGE_CONSTRAINTS);
        this.pronoun = pronoun;
    }

    /**
     * Returns true if a given string is a valid pronoun.
     */
    public static boolean isValidPronoun(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return pronoun;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Pronoun // instanceof handles nulls
                && pronoun.equals(((Pronoun) other).pronoun)); // state check
    }

    @Override
    public int hashCode() {
        return pronoun.hashCode();
    }
}

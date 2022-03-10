package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person's internship tag in the address book.
 * Guarantees: immutable; is always valid
 */
public class Internship extends Tag {
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS = "Internship can take any values, and it should not be blank";

    public final String value;

    /**
     * Constructs a {@code Internship}.
     *
     * @param internship an internship tag.
     */
    public Internship(String internship) {
        super(requireNonNull(internship));
        checkArgument(isValidTagName(internship), MESSAGE_CONSTRAINTS);
        this.value = internship.trim().toLowerCase();
    }

    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Internship
                && this.value.equals(((Internship) other).value));
    }
}

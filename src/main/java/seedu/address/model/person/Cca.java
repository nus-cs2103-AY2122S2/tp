package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person's cca tag in the address book.
 * Guarantees: immutable; is always valid
 */
public class Cca extends Tag {
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String MESSAGE_CONSTRAINTS = "Cca can take any values, and it should not be blank";

    public final String value;

    /**
     * Constructs a {@code Cca}.
     *
     * @param cca a cca tag.
     */
    public Cca(String cca) {
        super(requireNonNull(cca));
        checkArgument(isValidTagName(cca), MESSAGE_CONSTRAINTS);
        this.value = cca.trim().toLowerCase();
    }

    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Cca
                && this.value.equals(((Cca) other).value));
    }
}

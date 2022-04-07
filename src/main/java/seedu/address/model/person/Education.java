package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tag.Tag;
/**
 * Represents a Person's education tag in the address book.
 * Guarantees: immutable; is always valid
 */
public class Education extends Tag {
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String MESSAGE_CONSTRAINTS = "Education can only take alphanumeric values, and it should not "
            + "be blank";

    public final String value;

    /**
     * Constructs a {@code Education}.
     *
     * @param education an education tag.
     */
    public Education(String education) {
        super(requireNonNull(education));
        checkArgument(isValidTagName(education), MESSAGE_CONSTRAINTS);
        this.value = education.trim().toLowerCase();
    }

    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String getTagString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Education
                && this.value.equals(((Education) other).value));
    }
}

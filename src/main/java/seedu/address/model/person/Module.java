package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person's cca tag in the address book.
 * Guarantees: immutable; is always valid
 */
public class Module extends Tag {
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String MESSAGE_CONSTRAINTS = "Module can only take alphanumeric values, and it should not be "
            + "blank";

    public final String value;

    /**
     * Constructs a {@code Module}.
     *
     * @param module a module tag.
     */
    public Module(String module) {
        super(requireNonNull(module));
        checkArgument(isValidTagName(module), MESSAGE_CONSTRAINTS);
        this.value = module.trim().toLowerCase();
    }

    @Override
    public String getTagString() {
        return this.value;
    }

    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Module
                && this.value.equals(((Module) other).value));
    }
}

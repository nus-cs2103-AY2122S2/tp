package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Modules names should have 2-3 letters prefix "
            + "followed by 4 digits and an optional letter\n";

    public static final String VALIDATION_REGEX = "[a-zA-Z]{2,3}\\d{4}[a-zA-Z]?";

    public final String tagName;

    /**
     * Constructs a {@code Module}.
     *
     * @param tagName A valid module name.
     */
    public Module(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && tagName.equals(((Module) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}

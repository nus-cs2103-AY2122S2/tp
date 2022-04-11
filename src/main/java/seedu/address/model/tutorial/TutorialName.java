package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutorial name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTutorialName(String)}
 */
public class TutorialName {
    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial names should only contain alphanumeric characters and spaces, and should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String NULL_INPUT = "NULL_INPUT";

    public final String name;

    /**
     * Constructs a {@code ClassName}.
     *
     * @param value a class name.
     */
    public TutorialName(String value) {
        requireNonNull(value);
        checkArgument(isValidTutorialName(value), MESSAGE_CONSTRAINTS);
        name = value;
    }

    /**
     * Returns true if given String is a valid class name.
     */
    public static boolean isValidTutorialName(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialName // instanceof handles nulls
                && name.equals(((TutorialName) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

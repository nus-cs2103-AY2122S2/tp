package seedu.address.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents an Expense's description in the Expense Expert.
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the Description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String description;

    /**
     * Construct a {@code Description}
     *
     * @param description to describe the expense.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}

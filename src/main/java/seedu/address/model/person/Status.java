package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Status {


    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be Positive, Negative or Close-Contact";
    private static final String[] STATUS_INPUT = {"Positive", "Negative", "Close-Contact"};
    public final String status;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = status;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidStatus(String test) {
        boolean isValid = false;
        for (String validEntry : STATUS_INPUT) {
            if (test.equals(validEntry)) {
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public String toString() {
        return this.status;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && status.equals(((Status) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's covid test status  in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {


    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be Positive, Negative or Close-Contact";
    private static final String[] STATUS_INPUT = {"Positive", "Negative", "Close-Contact"};
    public static final String POSITIVE = STATUS_INPUT[0];
    public static final String NEGATIVE = STATUS_INPUT[1];
    public static final String CLOSE_CONTACT = STATUS_INPUT[2];
    public final String value;

    /**
     * Constructs a {@code Status}.
     *
     * @param status Covid status of student
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.value = status;
    }

    /**
     * Returns true if a given string is a valid covid status
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
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value.equals(((Status) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's memo in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMemo(String)}
 */
public class Memo {

    /** Maximum characters allowed. */
    public static final int MAXIMUM_CHARACTERS = 1000;

    /** String message that represents invalid input. */
    public static final String MESSAGE_CONSTRAINTS = "Memo can take any values, up to a maximum of "
            + MAXIMUM_CHARACTERS + " characters";

    /** Every character is allowed, up to a maximum of MAXIMUM_CHARACTERS. */
    public static final String VALIDATION_REGEX = ".{0," + MAXIMUM_CHARACTERS + "}";

    /** String representation of Memo. */
    public final String memo;

    /**
     * Constructs a {@code Memo}.
     *
     * @param memo A memo.
     */
    public Memo(String memo) {
        requireNonNull(memo);
        checkArgument(isValidMemo(memo), MESSAGE_CONSTRAINTS);
        this.memo = memo;
    }

    /**
     * Returns true if a given string is a valid memo.
     */
    public static boolean isValidMemo(String memo) {
        return memo.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if {@code memo} is empty. False otherwise.
     *
     * @return true if {@code memo} is empty; otherwise false.
     */
    public boolean isEmpty() {
        return memo.isEmpty();
    }

    /**
     * Returns string representation of Memo.
     *
     * @return string representation of Memo.
     */
    @Override
    public String toString() {
        return memo;
    }

    /**
     * Checks if two Memo object is equal.
     *
     * @param other The other Memo object.
     * @return true if equal; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Memo // instanceof handles nulls
                && memo.equals(((Memo) other).memo)); // state check
    }

    /**
     * Returns hashcode of Memo.
     *
     * @return hashcode of Memo.
     */
    @Override
    public int hashCode() {
        return memo.hashCode();
    }
}

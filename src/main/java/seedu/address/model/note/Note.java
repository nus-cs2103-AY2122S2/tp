package seedu.address.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Note in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidNote(String)}
 */
public class Note {

    public static final String MESSAGE_CONSTRAINTS = "Notes should be a String which is no longer than 50 characters";
    public static final String VALIDATION_REGEX = "\\p{Print}+"; // printable
    public static final int MAX_LENGTH = 50;

    public final String note;

    /**
     * Constructs a {@code Note}.
     *
     * @param note A valid note string.
     */
    public Note(String note) {
        requireNonNull(note);
        checkArgument(isValidNote(note), MESSAGE_CONSTRAINTS);
        this.note = note;
    }

    /**
     * Returns true if a given string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= MAX_LENGTH;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && note.equalsIgnoreCase(((Note) other).note)); // state check
    }

    @Override
    public int hashCode() {
        return note.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return note;
    }

}

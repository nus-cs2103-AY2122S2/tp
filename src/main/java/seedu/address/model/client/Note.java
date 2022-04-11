package seedu.address.model.client;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Client's note in the address book.
 * Guarantees: immutable; has valid String value
 */
public class Note {

    public static final String DEFAULT_NOTE = "";

    public final String value;

    /**
     * Constructs a {@code Note}
     * @param note A note.
     */
    public Note(String note) {
        requireNonNull(note);
        this.value = note;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Note
                && value.equals(((Note) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

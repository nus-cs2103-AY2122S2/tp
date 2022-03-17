package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.Prefix;



/**
 * Represents the Note of a particular transaction.
 * Is not required/optional.
 */
public class Note extends TransactionField {
    public static final Prefix PREFIX = new Prefix("n/", false);
    public static final String EMPTY_DUE_DATE = "";
    private final String note;

    /**
     * Constructs the note of a transaction. consists
     * of String
     *
     * @param note of the transaction.
     */
    public Note(String note) {
        super(PREFIX);
        requireNonNull(note);
        this.note = note;
    }

    @Override
    public String getValue() {
        return this.note;
    }

    @Override
    public String toString() {
        return this.note;
    }

    @Override
    public int hashCode() {
        return this.note.hashCode();
    }
}

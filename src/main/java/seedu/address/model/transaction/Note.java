package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.Prefix;



/**
 * Represents the Note of a particular transaction.
 * Is not required/optional.
 */
public class Note extends TransactionField {

    public static final String FIELD_NAME = "Note";

    public static final Prefix PREFIX = new Prefix("n/", false);
    public static final String EMPTY_NOTE = "";

    private final String value;

    /**
     * Constructs the note of a transaction. consists
     * of String
     *
     * @param note of the transaction.
     */
    public Note(String note) {
        super(PREFIX);
        requireNonNull(note);
        this.value = note;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String generateStringRep() {
        return FIELD_NAME + ": " + this.value;
    }

    @Override
    public String toString() {
        return generateStringRep();
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}

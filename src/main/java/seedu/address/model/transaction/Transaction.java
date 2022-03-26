package seedu.address.model.transaction;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Transaction implements Serializable {
    public static final String MAP_PREFIX = "T";

    private final HashMap<Prefix, TransactionField> fields = new HashMap<>();
    private final String personIdentifier;


    /**
     * Person constructor
     * @param fields A collection of all the person's attributes
     */
    public Transaction(Collection<TransactionField> fields, String personIdentifier) {
        requireAllNonNull(fields, personIdentifier);

        // Add fields.
        for (TransactionField f : fields) {
            checkArgument(f != null, "All fields in Person constructor cannot be null.");
            this.fields.put(f.prefix, f);
        }

        // Check for required fields.
        for (Prefix p : TransactionFieldRegistry.REQUIRED_PREFIXES) {
            checkArgument(this.fields.containsKey(p), "All required fields must be given.");
        }

        this.personIdentifier = personIdentifier;
    }

    public Transaction(Transaction otherTransaction) {
        this(otherTransaction.getFields(), otherTransaction.getIdentifier());
    }

    /**
     * Returns true if the person contains the specified field.
     * @param prefix the field prefix
     * @return return true if the person contains the specified field
     */
    public boolean hasField(Prefix prefix) {
        requireAllNonNull(prefix);
        return fields.containsKey(prefix);
    }

    public Optional<TransactionField> getField(Prefix prefix) {
        requireAllNonNull(prefix);
        return Optional.ofNullable(fields.get(prefix));
    }

    public List<TransactionField> getFields() {
        return Collections.unmodifiableList(new ArrayList<>(fields.values()));
    }

    /**
     * Gets the amount of the transaction
     *
     * @return Amount of the transaction
     */
    public Amount getAmount() {
        return (Amount) this.fields.get(Amount.PREFIX);
    }

    /**
     * Gets the Due date of the transaction
     *
     * @return Due date of the transaction
     */
    public Optional<DueDate> getDueDate() {
        return Optional.ofNullable((DueDate) this.fields.get(DueDate.PREFIX));
    }

    /**
     * Gets the transaction date of the transaction
     *
     * @return transaction date of the transaction
     */
    public TransactionDate getTransactionDate() {
        return (TransactionDate) this.fields.get(TransactionDate.PREFIX);
    }

    /**
     * Gets the person identifier of the transaction
     *
     * @return person's identifier (email)
     */
    public String getIdentifier() {
        return this.personIdentifier;
    }

    /**
     * Gets the note of the transaction
     *
     * @return Optional note of the transaction
     */
    public Optional<Note> getNote() {
        return Optional.ofNullable((Note) this.fields.get(Note.PREFIX));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction otherTr = (Transaction) other;
        return otherTr.getAmount().equals(getAmount())
                && otherTr.getDueDate().equals(getDueDate())
                && otherTr.getTransactionDate().equals(getTransactionDate())
                && otherTr.getIdentifier().equals(getIdentifier());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getAmount(), getDueDate(), getTransactionDate(), getNote());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Amount: ")
                .append(getAmount())
                .append("; Transaction date: ")
                .append(getTransactionDate())
                .append("; Person email: ")
                .append(getIdentifier());

        if (getDueDate().isPresent()) {
            builder.append("; Due date: ")
                    .append(getDueDate().get());
        }

        if (getNote().isPresent()) {
            builder.append("; Note: ")
                    .append(getNote().get());
        }

        return builder.toString();
    }

    public boolean hasIdentifier(String identifier) {
        return this.personIdentifier.equals(identifier);
    }
}


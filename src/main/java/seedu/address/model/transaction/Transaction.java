package seedu.address.model.transaction;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.transaction.util.StatusFactory;
import seedu.address.model.transaction.util.StatusFactoryInterface;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Transaction implements Serializable {
    public static final String MAP_PREFIX = "T";

    private final HashMap<Prefix, TransactionField> fields = new HashMap<>();
    private final long personId;
    private final long transactionId;


    /**
     * Transaction constructor
     * @param fields A collection of all the transaction's attributes
     */
    public Transaction(long transactionId, Collection<TransactionField> fields, long personId) {
        requireAllNonNull(fields, personId);

        this.transactionId = transactionId;

        // Add fields.
        for (TransactionField f : fields) {
            checkArgument(f != null, "All fields in Person constructor cannot be null.");
            this.fields.put(f.prefix, f);
        }

        // Check for required fields.
        for (Prefix p : TransactionFieldRegistry.REQUIRED_PREFIXES) {
            checkArgument(this.fields.containsKey(p), "All required fields must be given.");
        }

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.personId = personId;
    }

    /**
     * Transaction constructor
     * @param fields A collection of all the transaction's attributes
     */
    public Transaction(Collection<TransactionField> fields, long personId) {
        this(new Date().getTime(), fields, personId);
    }

    /**
     * Copy constructor
     */
    public Transaction(Transaction otherTransaction) {
        this(otherTransaction.getTransactionId(), otherTransaction.getFields(), otherTransaction.getPersonId());
    }

    public long getTransactionId() {
        return transactionId;
    }

    /**
     * Returns whether the transaction object is valid
     */
    public boolean isValid() {
        if (!fields.containsKey(DueDate.PREFIX)) {
            return true;
        }

        // Check whether due date >= transaction date
        return ((TransactionDate) fields.get(TransactionDate.PREFIX))
                .isBefore((DueDate) fields.get(DueDate.PREFIX));
    }

    public Optional<TransactionField> getField(Prefix prefix) {
        requireAllNonNull(prefix);
        return Optional.ofNullable(fields.get(prefix));
    }

    /**
     * Removes a field from transaction fields
     * @param prefix
     * @return updated Transaction
     */
    public Transaction removeField(Prefix prefix) {
        requireAllNonNull(prefix);
        HashMap<Prefix, TransactionField> fieldsCopy = new HashMap<>(fields);
        fieldsCopy.remove(prefix);
        return new Transaction(getTransactionId(), fieldsCopy.values(), getPersonId());
    }

    /**
     * Adds a field to the transaction.
     * @param field
     * @return updated transactions
     */
    public Transaction addField(TransactionField field) {
        requireAllNonNull(field);
        HashMap<Prefix, TransactionField> fieldsCopy = new HashMap<>(fields);
        fieldsCopy.put(field.prefix, field);
        return new Transaction(getTransactionId(), fieldsCopy.values(), getPersonId());
    }

    public Transaction setField(TransactionField field) {
        requireAllNonNull(field);
        return this.removeField(field.prefix).addField(field);
    }

    public Transaction setStatusTo(Class<? extends Command> command) {
        return this.setField(new StatusFactory().getStatus(command));
    }

    public Transaction setStatusTo(Class<? extends Command> command, StatusFactoryInterface factory) {
        return this.setField(factory.getStatus(command));
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
     * @return person's identifier (uniqueId)
     */
    public long getPersonId() {
        return this.personId;
    }

    /**
     * Gets the person unique identifier of the transaction
     * in the string representation
     *
     */
    public String getPersonIdString() {
        return String.format("Person ID: %d", getPersonId());
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
     * Gets the status of the transaction
     *
     * @return Status of the transaction
     */
    public Status getStatus() {
        return (Status) this.fields.get(Status.PREFIX);
    }

    /**
     * Returns whether the transaction has been paid or not
     */
    public boolean isPaid() {
        return getStatus().isPaid();
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
                && (otherTr.getPersonId() == getPersonId())
                && (otherTr.getTransactionId()) == getTransactionId();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getAmount(), getDueDate(),
                getTransactionDate(), getNote(),
                getPersonId(), getTransactionId());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getAmount())
                .append("; ")
                .append(getTransactionDate())
                .append("; ")
                .append(getPersonId())
                .append("; ");

        if (getDueDate().isPresent()) {
            builder.append(getDueDate().get())
                    .append("; ");
        }

        if (getNote().isPresent()) {
            builder.append(getNote().get());
        }

        return builder.toString();
    }

    public boolean hasIdentifier(long identifier) {
        return this.getPersonId() == identifier;
    }
}


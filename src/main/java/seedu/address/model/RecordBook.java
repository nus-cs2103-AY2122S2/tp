package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.record.Record;
import seedu.address.model.record.UniqueRecordList;

public class RecordBook implements ReadOnlyRecordBook {

    private final UniqueRecordList records;

    {
        this.records = new UniqueRecordList();
    }

    public RecordBook() {
    }

    /**
     * Creates an RecordBook using the Records in the {@code toBeCopied}
     */
    public RecordBook(ReadOnlyRecordBook toBeCopied) {
        this();
        this.resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the records list with {@code record}.
     * {@code record} must not contain duplicate records.
     */
    public void setRecords(List<Record> records) {
        this.records.setRecords(records);
    }

    /**
     * Resets the existing data of this {@code RecordBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRecordBook newData) {
        requireNonNull(newData);
        this.setRecords(newData.getRecordList());
    }

    //// record-level operations

    /**
     * Returns true if a record with the same identity as {@code record} exists in the record book.
     */
    public boolean hasRecord(Record record) {
        requireNonNull(record);
        return this.records.contains(record);
    }

    /**
     * Adds a record to the record book.
     * The record must not already exist in the record book.
     */
    public void addRecord(Record r) {
        this.records.add(r);
    }

    /**
     * Replaces the given record {@code target} in the list with {@code editedRecord}.
     * {@code target} must exist in the record book.
     * The record identity of {@code editedInsurance} must not be the same as another
     * existing insurance in the insurance book.
     */
    public void setRecord(Record target, Record editedRecord) {
        requireNonNull(editedRecord);

        this.records.setRecord(target, editedRecord);
    }

    /**
     * Removes {@code key} from this {@code RecordBook}.
     * {@code key} must exist in the record book.
     */
    public void removeRecord(Record key) {
        this.records.remove(key);
    }

    /**
     * Sorts the record list.
     * @param comparator Criteria for sorting.
     */
    public void sortRecord(Comparator<Record> comparator) {
        this.records.sort(comparator);
    }

    //// util methods

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Iterator<Record> it = records.iterator(); it.hasNext(); ) {
            Record record = it.next();
            builder.append(record.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public ObservableList<Record> getRecordList() {
        return this.records.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecordBook // instanceof handles nulls
                && this.records.equals(((RecordBook) other).records));
    }

    @Override
    public int hashCode() {
        return this.records.hashCode();
    }
}

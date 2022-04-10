package seedu.address.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.record.exceptions.DuplicateRecordException;
import seedu.address.model.record.exceptions.RecordNotFoundException;

/**
 * A list of records that enforces uniqueness between its elements and does not allow nulls.
 * A record is considered unique by comparing using {@code Record#isSameRecord(Record)}. As such, adding
 * and updating of records uses Record#isSameRecord(Record) for equality so as to ensure that the record
 * being added or updated is unique in terms of identity in the UniqueRecordList. However, the removal of an insurance
 * uses Record#equals(Object) so as to ensure that the insurance with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Record#isSameRecord(Record)
 */
public class UniqueRecordList implements Iterable<Record> {

    private final ObservableList<Record> internalList = FXCollections.observableArrayList();
    private final ObservableList<Record> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(this.internalList);

    /**
     * Returns true if the list contains an equivalent record as the given argument.
     */
    public boolean contains(Record toCheck) {
        requireNonNull(toCheck);
        return this.internalList.stream().anyMatch(toCheck::isSameRecord);
    }

    /**
     * Adds a record to the list.
     * The record must not already exist in the list.
     */
    public void add(Record toAdd) {
        requireNonNull(toAdd);
        if (this.contains(toAdd)) {
            throw new DuplicateRecordException();
        }
        this.internalList.add(toAdd);
    }

    /**
     * Replaces the record {@code target} in the list with {@code editedRecord}.
     * {@code target} must exist in the list.
     * The record identity of {@code editedRecord} must not be the same as another existing record in the list.
     */
    public void setRecord(Record target, Record editedRecord) {
        requireAllNonNull(target, editedRecord);

        int index = this.internalList.indexOf(target);
        if (index == -1) {
            throw new RecordNotFoundException();
        }

        if (!target.isSameRecord(editedRecord) && this.contains(editedRecord)) {
            throw new DuplicateRecordException();
        }

        this.internalList.set(index, editedRecord);
    }

    /**
     * Removes the equivalent record from the list.
     * The record must exist in the list.
     */
    public void remove(Record toRemove) {
        requireNonNull(toRemove);
        if (!this.internalList.remove(toRemove)) {
            throw new RecordNotFoundException();
        }
    }

    public void setRecords(UniqueRecordList replacement) {
        requireNonNull(replacement);
        this.internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code records}.
     * {@code records} must not contain duplicate records.
     */
    public void setRecords(List<Record> records) {
        requireAllNonNull(records);
        if (!this.recordsAreUnique(records)) {
            throw new DuplicateRecordException();
        }

        this.internalList.setAll(records);
    }

    /**
     * Sorts the record list
     */
    public void sort(Comparator<Record> comparator) {
        FXCollections.sort(this.internalList, comparator);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Record> asUnmodifiableObservableList() {
        return this.internalUnmodifiableList;
    }

    @Override
    public Iterator<Record> iterator() {
        return this.internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRecordList // instanceof handles nulls
                && this.internalList.equals(((UniqueRecordList) other).internalList));
    }

    @Override
    public int hashCode() {
        return this.internalList.hashCode();
    }

    /**
     * Returns true if {@code records} contains only unique records.
     */
    private boolean recordsAreUnique(List<Record> records) {
        for (int i = 0; i < records.size() - 1; i++) {
            for (int j = i + 1; j < records.size(); j++) {
                if (records.get(i).isSameRecord(records.get(j))) {
                    return false;
                }

            }
        }
        return true;
    }
}

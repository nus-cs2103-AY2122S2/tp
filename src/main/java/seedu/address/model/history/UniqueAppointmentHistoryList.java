package seedu.address.model.history;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;


/**
 * A list of appointment history that enforces uniqueness between its elements and does not allow nulls.
 * History is considered unique by comparing using {@code History#isSameHistory(History)}. As such,
 * adding and updating of history uses History#isSameHistory(History) for equality so as to ensure
 * that the history being added or updated is unique in terms of identity in the UniqueHistoryList.
 * However, the removal of  history uses history#equals(Object) so as to ensure that the insurance
 * with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 */
public class UniqueAppointmentHistoryList implements Iterable<Appointment> {

    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(this.internalList);

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return this.internalUnmodifiableList;
    }

    @Override
    public Iterator<Appointment> iterator() {
        return this.internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAppointmentHistoryList // instanceof handles nulls
                && this.internalList
                .equals(((UniqueAppointmentHistoryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return this.internalList.hashCode();
    }

    public boolean add(Appointment a) {
        return this.internalList.add(a);
    }
}


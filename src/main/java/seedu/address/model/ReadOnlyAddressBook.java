package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.position.Position;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Applicant> getPersonList();

    /**
     * Returns an unmodifiable view of the position list.
     * This list will not contain any duplicate positions.
     */
    ObservableList<Position> getPositionList();
}

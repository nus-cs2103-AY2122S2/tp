package seedu.trackbeau.model;

import javafx.collections.ObservableList;
import seedu.trackbeau.model.customer.Customer;

/**
 * Unmodifiable view of an trackbeau book
 */
public interface ReadOnlyTrackBeau {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Customer> getPersonList();

}

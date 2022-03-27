package seedu.trackbeau.model;

import javafx.collections.ObservableList;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.service.Service;

/**
 * Unmodifiable view of trackBeau.
 */
public interface ReadOnlyTrackBeau {

    /**
     * Returns an unmodifiable view of the customers list.
     * This list will not contain any duplicate customers.
     */
    ObservableList<Customer> getCustomerList();

    /**
     * Returns an unmodifiable view of the bookings list.
     * This list will not contain any duplicate bookings.
     */
    ObservableList<Booking> getBookingList();

    /**
     * Returns an unmodifiable view of the services list.
     * This list will not contain any duplicate services.
     */
    ObservableList<Service> getServiceList();
}

package seedu.trackbeau.model;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.service.Service;
import seedu.trackbeau.model.uniquelist.UniqueList;

/**
 * Wraps all data at the trackBeau level
 */
public class TrackBeau implements ReadOnlyTrackBeau {

    private final UniqueList<Customer> customers;
    private final UniqueList<Service> services;
    private final UniqueList<Booking> bookings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        customers = new UniqueList<>();
        services = new UniqueList<>();
        bookings = new UniqueList<>();
    }

    public TrackBeau() {}

    /**
     * Creates a TrackBeau using the Customers, Bookings and Services in the {@code toBeCopied}
     */
    public TrackBeau(ReadOnlyTrackBeau toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the customer list with {@code customers}.
     * {@code customers} must not contain duplicate customers.
     */
    public void setCustomers(List<Customer> customers) {
        this.customers.setItems(customers);
    }

    /**
     * Replaces the contents of the service list with {@code services}.
     * {@code services} must not contain duplicate services.
     */
    public void setServices(List<Service> services) {
        this.services.setItems(services);
    }

    /**
     * Replaces the contents of the booking list with {@code bookings}.
     * {@code bookings} must not contain duplicate bookings.
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings.setItems(bookings);
    }

    /**
     * Resets the existing data of this {@code TrackBeau} with {@code newData}.
     */
    public void resetData(ReadOnlyTrackBeau newData) {
        requireNonNull(newData);

        setCustomers(newData.getCustomerList());
        setServices(newData.getServiceList());
        setBookings(newData.getBookingList());
    }

    //// customer-level operations

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in trackBeau.
     */
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return customers.contains(customer);
    }

    /**
     * Returns true if a customer, other than itself, has same phone or email.
     */
    public boolean hasAnotherCustomerWithClashingIdentity(Customer originalCustomer, Customer editedCustomer) {
        requireAllNonNull(originalCustomer, editedCustomer);
        return customers.checkIfClash(originalCustomer, editedCustomer);
    }

    /**
     * Adds a customer to trackBeau.
     * The customer must not already exist in trackBeau.
     */
    public void addCustomer(Customer c) {
        customers.add(c);
    }

    /**
     * Return the index of the customer in the customer list.
     * The customer must already exist in trackBeau.
     */
    public Integer getCustomerIndex(Customer c) {
        return customers.indexOf(c);
    }

    /**
     * Replaces the given customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in trackBeau.
     * The customer identity of {@code editedCustomer} must not be the same as another existing customer in trackBeau.
     */
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);

        customers.setItem(target, editedCustomer);
    }

    /**
     * Removes {@code key} from this {@code TrackBeau}.
     * {@code key} must exist in trackBeau.
     */
    public void removeCustomer(Customer key) {
        customers.remove(key);
    }

    //// service-level operations

    /**
     * Returns true if a service with the same identity as {@code service} exists in trackBeau.
     */
    public boolean hasService(Service service) {
        requireNonNull(service);
        return services.contains(service);
    }

    /**
     * Adds a service to trackBeau.
     * The service must not already exist in trackBeau.
     */
    public void addService(Service s) {
        services.add(s);
    }

    /**
     * Return the index of the service in the service list.
     * The service must already exist in trackBeau.
     */
    public Integer getServiceIndex(Service s) {
        return services.indexOf(s);
    }
    /**
     * Removes {@code key} from this {@code TrackBeau}.
     * {@code key} must exist in trackBeau.
     */
    public void removeService(Service key) {
        services.remove(key);
    }

    /**
     * Replaces the given service {@code target} in the list with {@code editedService}.
     * {@code target} must exist in trackBeau.
     * The service identity of {@code editedService} must not be the same as another existing service in trackBeau.
     */
    public void setService(Service target, Service editedService) {
        requireNonNull(editedService);

        services.setItem(target, editedService);
    }

    /**
     * Returns true if a booking with the same identity as {@code booking} exists in trackBeau.
     */
    public boolean hasBooking(Booking booking) {
        requireNonNull(booking);
        return bookings.contains(booking);
    }

    /**
     * Adds a booking to trackBeau.
     * The customer must not already exist in trackBeau.
     */
    public void addBooking(Booking b) {
        requireNonNull(b);
        bookings.add(b);
    }

    /**
     * Replaces the given booking {@code target} in the list with {@code editedBooking}.
     * {@code target} must exist in trackBeau.
     * The booking identity of {@code editedBooking} must not be the same as another existing booking in trackBeau.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        requireNonNull(editedBooking);

        bookings.setItem(target, editedBooking);
    }

    /**
     * Removes {@code key} from this {@code TrackBeau}.
     * {@code key} must exist in trackBeau.
     */
    public void removeBooking(Booking key) {
        bookings.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        // return customers.asUnmodifiableObservableList().size() + " customers";
        // TODO: refine later
        return customers.asUnmodifiableObservableList().size() + " customers"
                + ", " + services.asUnmodifiableObservableList().size() + " services"
                + ", " + bookings.asUnmodifiableObservableList().size() + " bookings";
    }

    @Override
    public ObservableList<Customer> getCustomerList() {
        return customers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Service> getServiceList() {
        return services.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Booking> getBookingList() {
        return bookings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TrackBeau // instanceof handles nulls
                && customers.equals(((TrackBeau) other).customers)
                && services.equals(((TrackBeau) other).services)
                && bookings.equals(((TrackBeau) other).bookings));
    }

    @Override
    public int hashCode() {
        return customers.hashCode() + bookings.hashCode() + services.hashCode();
    }
}

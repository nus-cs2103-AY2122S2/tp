package seedu.trackbeau.model;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.trackbeau.commons.core.GuiSettings;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.service.Service;

/**
 * Represents the in-memory model of TrackBeau data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TrackBeau trackBeau;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;
    private final FilteredList<Service> services;
    private final FilteredList<Booking> filteredBookings;

    /**
     * Initializes a ModelManager with the given trackBeau and userPrefs.
     */
    public ModelManager(ReadOnlyTrackBeau trackBeau, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(trackBeau, userPrefs);

        logger.fine("Initializing with trackBeau: " + trackBeau + " and user prefs " + userPrefs);

        this.trackBeau = new TrackBeau(trackBeau);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomers = new FilteredList<>(this.trackBeau.getCustomerList());
        services = new FilteredList<>(this.trackBeau.getServiceList());
        filteredBookings = new FilteredList<>(this.trackBeau.getBookingList());
    }

    public ModelManager() {
        this(new TrackBeau(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTrackBeauFilePath() {
        return userPrefs.getTrackBeauFilePath();
    }

    @Override
    public void setTrackBeauFilePath(Path trackBeauFilePath) {
        requireNonNull(trackBeauFilePath);
        userPrefs.setTrackBeauFilePath(trackBeauFilePath);
    }

    //=========== TrackBeau ================================================================================

    @Override
    public void setTrackBeau(ReadOnlyTrackBeau trackBeau) {
        this.trackBeau.resetData(trackBeau);
    }

    @Override
    public ReadOnlyTrackBeau getTrackBeau() {
        return trackBeau;
    }

    //=========== Customers ================================================================================

    @Override
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return trackBeau.hasCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer target) {
        trackBeau.removeCustomer(target);
    }

    @Override
    public void addCustomer(Customer customer) {
        trackBeau.addCustomer(customer);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        trackBeau.setCustomer(target, editedCustomer);

    }

    //=========== Bookings ================================================================================

    @Override
    public boolean hasBooking(Booking booking) {
        requireNonNull(booking);
        return trackBeau.hasBooking(booking);
    }

    @Override
    public void addBooking(Booking booking) {
        trackBeau.addBooking(booking);
        updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
    }

    @Override
    public void deleteBooking(Booking target) {
        trackBeau.removeBooking(target);
    }

    @Override
    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);
        trackBeau.setBooking(target, editedBooking);
    }
    //=========== Filtered Booking List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Booking} backed by the internal list of
     * {@code versionedTrackBeau}
     */
    @Override
    public ObservableList<Booking> getFilteredBookingList() {
        return filteredBookings;
    }

    @Override
    public void updateFilteredBookingList(Predicate<Booking> predicate) {
        requireNonNull(predicate);
        filteredBookings.setPredicate(predicate);
    }

    //=========== Services =============================================================

    @Override
    public boolean hasService(Service service) {
        requireNonNull(service);
        return trackBeau.hasService(service);
    }

    @Override
    public void deleteService(Service target) {
        trackBeau.removeService(target);
    }

    @Override
    public void addService(Service service) {
        trackBeau.addService(service);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void setService(Service target, Service editedService) {
        requireAllNonNull(target, editedService);
        trackBeau.setService(target, editedService);
    }

    @Override
    public ObservableList<Service> getServiceList() {
        return services;
    }

    @Override
    public void updateServiceList(Predicate<Service> predicate) {
        requireNonNull(predicate);
        services.setPredicate(predicate);
    }

    //=========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedTrackBeau}
     */
    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return filteredCustomers;
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return trackBeau.equals(other.trackBeau)
                && userPrefs.equals(other.userPrefs)
                && filteredCustomers.equals(other.filteredCustomers)
                && services.equals(other.services)
                && filteredBookings.equals(other.filteredBookings);
    }

}

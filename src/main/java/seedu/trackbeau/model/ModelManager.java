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
import seedu.trackbeau.model.customer.Customer;

/**
 * Represents the in-memory model of the trackbeau book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TrackBeau addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyTrackBeau addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with trackbeau book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new TrackBeau(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomers = new FilteredList<>(this.addressBook.getCustomerList());
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
    public void setTrackBeauFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setTrackBeauFilePath(addressBookFilePath);
    }

    //=========== TrackBeau ================================================================================

    @Override
    public void setTrackBeau(ReadOnlyTrackBeau addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyTrackBeau getTrackBeau() {
        return addressBook;
    }

    @Override
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return addressBook.hasCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addCustomer(Customer customer) {
        addressBook.addCustomer(customer);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        addressBook.setCustomer(target, editedCustomer);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredCustomers.equals(other.filteredCustomers);
    }

}

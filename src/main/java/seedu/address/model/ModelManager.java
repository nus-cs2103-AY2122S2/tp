package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.customer.Customer;
import seedu.address.model.dish.Dish;
import seedu.address.model.driver.Driver;
import seedu.address.model.order.Order;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Customer> filteredCustomers;
    private final FilteredList<Order> filteredOrders;
    private final FilteredList<Driver> filteredDrivers;
    private final FilteredList<Dish> filteredDishes;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCustomers = new FilteredList<>(this.addressBook.getCustomerList());
        filteredOrders = new FilteredList<>(this.addressBook.getOrderList());
        filteredDrivers = new FilteredList<>(this.addressBook.getDriverList());
        filteredDishes = new FilteredList<>(this.addressBook.getDishList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return addressBook.hasCustomer(customer);
    }

    @Override
    public boolean hasRepeatedNameOrPhone(Customer toRemove, Customer toCheck) {
        requireNonNull(toRemove);
        requireNonNull(toCheck);
        return addressBook.hasRepeatedNameOrPhone(toRemove, toCheck);
    }

    @Override
    public boolean hasRepeatedNameOrPhoneDriver(Driver toRemove, Driver toCheck) {
        requireNonNull(toRemove);
        requireNonNull(toCheck);
        return addressBook.hasRepeatedNameOrPhoneDriver(toRemove, toCheck);
    }



    @Override
    public boolean hasDriver(Driver driver) {
        requireNonNull(driver);
        return addressBook.hasDriver(driver);
    }

    @Override
    public void deleteCustomer(Customer target) {
        addressBook.removeCustomer(target);
    }

    @Override
    public void deleteDriver(Driver target) {
        addressBook.removeDriver(target);
    }

    @Override
    public void addCustomer(Customer customer) {
        addressBook.addCustomer(customer);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    @Override
    public void addDriver(Driver driver) {
        addressBook.addDriver(driver);
        updateFilteredDriverList(PREDICATE_SHOW_ALL_DRIVERS);
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireAllNonNull(target, editedCustomer);

        addressBook.setCustomer(target, editedCustomer);
    }

    @Override

    public void setDish(Dish target, Dish editedDish) {
        requireAllNonNull(target, editedDish);

        addressBook.setDish(target, editedDish);
    }

    public void setDriver(Driver target, Driver editedDriver) {
        requireAllNonNull(target, editedDriver);
        addressBook.setDriver(target, editedDriver);
    }

    //=========== Filtered Dish List Accessors =============================================================
    @Override
    public boolean hasDish(Dish dish) {
        requireNonNull(dish);
        return addressBook.hasDish(dish);
    }

    @Override
    public void deleteDish(Dish target) {
        addressBook.removeDish(target);
    }

    @Override
    public void addDish(Dish dish) {
        addressBook.addDish(dish);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    //=========== Filtered Customer List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Customer} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return filteredCustomers;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Dish} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Dish> getFilteredDishList() {
        return filteredDishes;
    }

    @Override
    public ObservableList<Driver> getFilteredDriverList() {
        return filteredDrivers;
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        requireNonNull(predicate);
        filteredCustomers.setPredicate(predicate);
    }

    @Override
    public void updateFilteredDriverList(Predicate<Driver> predicate) {
        requireNonNull(predicate);
        filteredDrivers.setPredicate(predicate);
    }
    @Override
    public void updateFilteredDishList(Predicate<Dish> predicate) {
        requireNonNull(predicate);
        filteredDishes.setPredicate(predicate);
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
                && filteredCustomers.equals(other.filteredCustomers)
                && filteredDishes.equals(other.filteredDishes);
    }

    //==================== Order features ===================================
    @Override
    public void addOrder(Order order) {
        addressBook.addOrder(order);
        updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Order} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return addressBook.hasOrder(order);
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        addressBook.setOrder(target, editedOrder);
    }
}

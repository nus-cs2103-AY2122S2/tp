package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.customer.Customer;
import seedu.address.model.dish.Dish;
import seedu.address.model.driver.Driver;
import seedu.address.model.order.Order;


/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Customer> PREDICATE_SHOW_ALL_CUSTOMERS = unused -> true;
    Predicate<Driver> PREDICATE_SHOW_ALL_DRIVERS = unused -> true;
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Dish> PREDICATE_SHOW_ALL_DISHES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    boolean hasCustomer(Customer customer);
    boolean hasDriver(Driver driver);

    /**
     * Deletes the given customer.
     * The customer must exist in the address book.
     */
    void deleteCustomer(Customer target);

    void deleteDriver(Driver driverToDelete);
    /**
     * Adds the given customer.
     * {@code customer} must not already exist in the address book.
     */
    void addCustomer(Customer customer);
    void addDriver(Driver driver);

    /**
     * Replaces the given customer {@code target} with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as
     * another existing customer in the address book.
     */
    void setCustomer(Customer target, Customer editedCustomer);

    /**
     * Replaces the given dish {@code target} with {@code editedDish}.
     * {@code target} must exist in the address book.
     * The dish identity of {@code editedDish} must not be the same as
     * another existing dish in FoodOnWheels.
     */
    void setDish(Dish target, Dish editedDish);

    void setDriver(Driver target, Driver editedDriver);


    /** Returns an unmodifiable view of the filtered customer list */
    ObservableList<Customer> getFilteredCustomerList();
    /**
     * Returns true if a dish with the same identity as {@code dish} exists in FoodOnWheels.
     */
    boolean hasDish(Dish dish);

    /**
     * Deletes the given dish.
     * The dish must exist in the address book.
     */
    void deleteDish(Dish target);

    /**
     * Adds the given dish.
     * {@code dish} must not already exist in the address book.
     */
    void addDish(Dish dish);

    /** Returns an unmodifiable view of the filtered driver list */
    ObservableList<Driver> getFilteredDriverList();

    /** Returns an unmodifiable view of the filtered dish list */
    ObservableList<Dish> getFilteredDishList();

    /**
     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCustomerList(Predicate<Customer> predicate);

    /**
     * Adds the given order.
     */
    void addOrder(Order order);

    /** Returns an unmodifiable view of the filtered Order list */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Returns true if an order with the same identity as {@code order} exists in the address book.
     */
    boolean hasOrder(Order order);

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);


    void updateFilteredDriverList(Predicate<Driver> driver);

    /**
     * Updates the filter of the filtered dish list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDishList(Predicate<Dish> predicate);

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the app.
     * The order identity of {@code editedOrder} must not be the same as
     * another existing order in the address book.
     */
    void setOrder(Order target, Order editedOrder);

    boolean hasRepeatedNameOrPhone(Customer toRemove, Customer toCheck);

    boolean hasRepeatedNameOrPhoneDriver(Driver driverToEdit, Driver editedDriver);
}

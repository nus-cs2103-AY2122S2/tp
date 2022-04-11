package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.UniqueCustomerList;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.UniqueDishList;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.UniqueDriverList;
import seedu.address.model.order.Order;
import seedu.address.model.order.UniqueOrderList;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameCustomer comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueCustomerList customers;
    private final UniqueOrderList orders;
    private final UniqueDishList dishes;
    private final UniqueDriverList drivers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        customers = new UniqueCustomerList();
        orders = new UniqueOrderList();
        dishes = new UniqueDishList();
        drivers = new UniqueDriverList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Customers in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the customer list with {@code customers}.
     * {@code customers} must not contain duplicate customers.
     */
    public void setCustomers(List<Customer> customers) {
        this.customers.setCustomers(customers);
    }

    /**
     * Replaces the contents of the dish list with {@code dishes}.
     * {@code dishes} must not contain duplicate dishes.
     */
    public void setDishes(List<Dish> dishes) {
        this.dishes.setDishes(dishes);
    }

    /**
     * Replaces the contents of the driver list with {@code drivers}.
     * {@code drivers} must not contain duplicate drivers.
     */
    public void setDrivers(List<Driver> drivers) {
        this.drivers.setDrivers(drivers);
    }

    /**
     * Replaces the contents of the order list with {@code orders}.
     * {@code orders} must not contain duplicate orders.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setCustomers(newData.getCustomerList());
        setDishes(newData.getDishList());
        setDrivers(newData.getDriverList());
        setOrders(newData.getOrderList());
    }

    //// customer-level operations

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    public boolean hasCustomer(Customer customer) {
        requireNonNull(customer);
        return customers.contains(customer);
    }

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    public boolean hasRepeatedNameOrPhone(Customer toRemove, Customer toCheck) {
        requireNonNull(toRemove);
        requireNonNull(toCheck);
        return customers.containsExcludeCurrent(toRemove, toCheck);
    }

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    public boolean hasRepeatedNameOrPhoneDriver(Driver toRemove, Driver toCheck) {
        requireNonNull(toRemove);
        requireNonNull(toCheck);
        return drivers.containsExcludeCurrentDriver(toRemove, toCheck);
    }

    /**
     * Returns true if a driver with the same identity as {@code driver} exists in the database
     */
    public boolean hasDriver(Driver driver) {
        requireNonNull(driver);
        return drivers.contains(driver);
    }

    /**
     * Adds a customer to the address book.
     * The customer must not already exist in the address book.
     */
    public void addCustomer(Customer p) {
        customers.add(p);
    }

    public void addDriver(Driver d) {
        drivers.add(d);
    }

    /**
     * Replaces the given customer {@code target} in the list with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as
     * another existing customer in the address book.
     */
    public void setCustomer(Customer target, Customer editedCustomer) {
        requireNonNull(editedCustomer);

        customers.setCustomer(target, editedCustomer);
    }

    public void setDriver(Driver target, Driver editedDriver) {
        requireNonNull(editedDriver);
        drivers.setDriver(target, editedDriver);
    }

    /**
     * Replaces the given dish {@code target} in the list with {@code editedDish}.
     * {@code target} must exist in FoodOnWheels.
     * The dish identity of {@code editedDish} must not be the same as
     * another existing dish in FoodOnWheels.
     */
    public void setDish(Dish target, Dish editedDish) {
        requireNonNull(editedDish);

        dishes.setDish(target, editedDish);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCustomer(Customer key) {
        customers.remove(key);
    }

    public void removeDriver(Driver key) {
        drivers.remove(key);
    }

    /**
     * Returns true if a dish with the same identity as {@code dish} exists in the address book.
     */
    public boolean hasDish(Dish dish) {
        requireNonNull(dish);
        return dishes.contains(dish);
    }

    /**
     * Adds a dish to FoodOnWheels.
     * The dish must not already exist in FoodOnWheels.
     */
    public void addDish(Dish d) {
        dishes.add(d);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeDish(Dish key) {
        dishes.remove(key);
    }


    @Override
    public String toString() {
        // TODO: refine later
        return customers.asUnmodifiableObservableList().size() + " customers"
                + dishes.asUnmodifiableObservableList().size() + " dishes "
                + drivers.asUnmodifiableObservableList().size() + " drivers";
    }

    @Override
    public ObservableList<Customer> getCustomerList() {
        return customers.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Driver> getDriverList() {
        return drivers.asUnmodifiableObservableList();
    }
    @Override
    public ObservableList<Dish> getDishList() {
        return dishes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && customers.equals(((AddressBook) other).customers));
    }

    @Override
    public int hashCode() {
        return customers.hashCode();
    }

    /**
     * Adds an order.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    /**
     * Returns true if an order with the same identity as {@code order} exists in the address book.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orders.contains(order);
    }

    /**
     * Replaces the given order {@code target} in the list with {@code editedOrder}.
     * {@code target} must exist in the app.
     * The order identity of {@code editedOrder} must not be the same as
     * another existing order in the app.
     */
    public void setOrder(Order target, Order editedOrder) {
        requireNonNull(editedOrder);

        orders.setOrder(target, editedOrder);
    }
}

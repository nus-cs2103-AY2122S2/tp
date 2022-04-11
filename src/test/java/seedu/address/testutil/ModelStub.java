package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.customer.Customer;
import seedu.address.model.dish.Dish;
import seedu.address.model.driver.Driver;
import seedu.address.model.order.Order;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCustomer(Customer customer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addDriver(Driver driver) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCustomer(Customer customer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasRepeatedNameOrPhone(Customer toRemove, Customer toCheck) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasRepeatedNameOrPhoneDriver(Driver driverToEdit, Driver editedDriver) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasDriver(Driver driver) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteCustomer(Customer target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteDriver(Driver target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCustomer(Customer target, Customer editedCustomer) {
        throw new AssertionError("This method should not be called.");
    }

    @Override

    public void setDish(Dish target, Dish editedDish) {
        throw new AssertionError("This method should not be called.");
    }

    public void setDriver(Driver target, Driver editedDriver) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasDish(Dish dish) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteDish(Dish target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addDish(Dish dish) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Driver> getFilteredDriverList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Dish> getFilteredDishList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCustomerList(Predicate<Customer> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addOrder(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasOrder(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredDriverList(Predicate<Driver> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredDishList(Predicate<Dish> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}

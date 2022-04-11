package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

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
    public void addPerson(Person person) {
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
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasOrder(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addOrder(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteOrder(Order target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Order> getOrderList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updatedSortedOrderList(ObservableList<Order> orderList) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updatedSortedFilteredOrderList(Predicate<Order> predicate) {
        throw new AssertionError("This method should not be called.");
    }

}

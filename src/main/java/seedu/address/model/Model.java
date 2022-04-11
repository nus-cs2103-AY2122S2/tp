package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

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
     * Returns the user prefs' ReadyBakey file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' ReadyBakey file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces ReadyBakey data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the ReadyBakey.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the ReadyBakey.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the ReadyBakey.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the ReadyBakey.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the ReadyBakey.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns true if an order with the same identity as {@code order} exists in ReadyBakey.
     */
    boolean hasOrder(Order order);

    /**
     * Adds the given order.
     * {@code order} must not already exist in the ReadyBakey.
     */
    void addOrder(Order order);

    /**
     * Deletes the given order.
     * The {@code order} must exist in the ReadyBakey.
     */
    void deleteOrder(Order target);

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the ReadyBakey.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the ReadyBakey.
     */
    void setOrder(Order target, Order editedOrder);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered order list */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered orders list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the filtered order list
     */
    ObservableList<Order> getOrderList();

    /**
     * Updates the filter of the filtered orders list to filter by the given {@code predicate}
     * and sorts it by DeliveryDateTime
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updatedSortedFilteredOrderList(Predicate<Order> predicate);

    /**
     * Sorts a given {@code orderList} by DeliveryDateTime
     * @throws NullPointerException if {@code orderList} is null.
     */
    void updatedSortedOrderList(ObservableList<Order> orderList);

}

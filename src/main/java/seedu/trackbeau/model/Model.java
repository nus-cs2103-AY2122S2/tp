package seedu.trackbeau.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.trackbeau.commons.core.GuiSettings;
import seedu.trackbeau.model.customer.Customer;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Customer> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' trackbeau book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' trackbeau book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces trackbeau book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyTrackBeau addressBook);

    /** Returns the TrackBeau */
    ReadOnlyTrackBeau getAddressBook();

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the trackbeau book.
     */
    boolean hasPerson(Customer customer);

    /**
     * Deletes the given customer.
     * The customer must exist in the trackbeau book.
     */
    void deletePerson(Customer target);

    /**
     * Adds the given customer.
     * {@code customer} must not already exist in the trackbeau book.
     */
    void addPerson(Customer customer);

    /**
     * Replaces the given customer {@code target} with {@code editedPerson}.
     * {@code target} must exist in the trackbeau book.
     * The customer identity of {@code editedPerson} must not be the same as another existing customer in trackbeau.
     */
    void setPerson(Customer target, Customer editedCustomer);

    /** Returns an unmodifiable view of the filtered customer list */
    ObservableList<Customer> getFilteredPersonList();

    /**
     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Customer> predicate);
}

package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Entry> PREDICATE_SHOW_ALL = unused -> true;
    Predicate<Entry> PREDICATE_SHOW_NONE = unused -> false;
    Predicate<Entry> PREDICATE_SHOW_UNARCHIVED_ONLY = entry -> !entry.isArchived();
    Predicate<Entry> PREDICATE_SHOW_ARCHIVED_ONLY = Entry::isArchived;

    /**
     * {@code Comparator} that compares {@code Entry}s.
     */
    Comparator<Person> COMPARATOR_PERSON_BY_NAME = (p1, p2) -> p1.getName().toString()
                                                        .compareTo(p2.getName().toString());
    Comparator<Company> COMPARATOR_COMPANY_BY_NAME = (c1, c2) -> c1.getName().toString()
                                                        .compareTo(c2.getName().toString());
    Comparator<Event> COMPARATOR_EVENT_BY_DATE = (e1, e2) -> e1.getDate().compareTo(e2.getDate());

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
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<? super Person> predicate);

    /**
     * Returns true if a company with the {@code companyName} exists in the address book.
     */
    boolean hasCompany(Name companyName);

    /**
     * Returns true if a company with the same identity as {@code company} exists in the address book.
     */
    boolean hasCompany(Company company);

    /**
     * Deletes the given company.
     * The company must exist in the address book.
     */
    void deleteCompany(Company target);

    /**
     * Adds the given company.
     * {@code company} must not already exist in the address book.
     */
    void addCompany(Company company);

    /**
     * Replaces the given company {@code target} with {@code editedCompany}.
     * {@code target} must exist in the address book.
     * The company identity of {@code editedCompany} must not be the same as another existing company
     * in the address book.
     */
    void setCompany(Company target, Company editedCompany);

    /** Returns an unmodifiable view of the filtered company list */
    ObservableList<Company> getFilteredCompanyList();

    /**
     * Updates the filter of the filtered company list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompanyList(Predicate<? super Company> predicate);

    /**
     * Returns true if an event with the same identity as {@code event} exists in the address book.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the address book.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the address book.
     */
    void addEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the address book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the address book.
     */
    void setEvent(Event target, Event editedEvent);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<? super Event> predicate);

    /**
     * Updates the filter of the currently displayed list through the {@code predicate}.
     */
    void updateCurrentlyDisplayedList(Predicate<Entry> predicate);

    /**
     * Updates filtered lists to show only the Persons list filtered through the {@code predicate}.
     */
    void showPersonList(Predicate<? super Person> predicate);

    /**
     * Updates filtered lists to show only the Company list filtered through the {@code predicate}.
     */
    void showCompanyList(Predicate<? super Company> predicate);

    /**
     * Updates filtered lists to show only the Events list filtered through the {@code predicate}.
     */
    void showEventList(Predicate<? super Event> predicate);

    /**
     * Sort and show the filtered {@code Person} list by date and in {@code ordering} order
     */
    void sortPersonListByName(Ordering ordering, Predicate<? super Person> predicate);

    /**
     * Sort and show the filtered {@code Company} list by date and in {@code ordering} order
     */
    void sortCompanyListByName(Ordering ordering, Predicate<? super Company> predicate);

    /**
     * Sort and show the filtered {@code Event} list by date and in {@code ordering} order
     */
    void sortEventListByDate(Ordering ordering, Predicate<? super Event> predicate);

    /**
     * Deletes the entry at the index of the currently displayed list and returns it.
     */
    Entry deleteEntry(int index);

    /**
     * Archives the entry at the index of the currently displayed list and returns it.
     */
    Entry archiveEntry(int index, boolean isArchived) throws CommandException;
}

package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ListType;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.OrderingUtil;
import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Company> filteredCompanies;
    private final FilteredList<Event> filteredEvents;
    private ListType currentlyDisplayedListType;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredCompanies = new FilteredList<>(this.addressBook.getCompanyList());
        filteredEvents = new FilteredList<>(this.addressBook.getEventList());

        currentlyDisplayedListType = ListType.COMPANY;

        filteredCompanies.setPredicate(PREDICATE_SHOW_UNARCHIVED_ONLY);
        // Don't allow deleting/finding/editing on the events or person list at the beginning
        filteredPersons.setPredicate(PREDICATE_SHOW_NONE);
        filteredEvents.setPredicate(PREDICATE_SHOW_NONE);
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

    //========== Person List Modifiers ========================================================================

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //========== Company List Modifiers ======================================================================

    @Override
    public boolean hasCompany(Name companyName) {
        return addressBook.hasCompany(Company.createDummyCompany(companyName));
    }

    @Override
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return addressBook.hasCompany(company);
    }

    @Override
    public void deleteCompany(Company target) {
        addressBook.removeCompany(target);
    }

    @Override
    public void addCompany(Company company) {
        addressBook.addCompany(company);
        updateFilteredCompanyList(PREDICATE_SHOW_ALL);
    }

    /**
     * Replaces the given company {@code target} with {@code editedCompany}.
     * {@code target} must exist in the address book.
     * The company identity of {@code editedCompany} must not be the same as another existing company
     * in the address book.
     */
    @Override
    public void setCompany(Company target, Company editedCompany) {
        requireAllNonNull(target, editedCompany);

        addressBook.setCompany(target, editedCompany);
    }

    //=========== For Events =================================================================================

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return addressBook.hasEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        addressBook.removeEvent(target);
    }

    @Override
    public void addEvent(Event event) {
        addressBook.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        addressBook.setEvent(target, editedEvent);
    }

    //=========== General Filtered List Modifiers ============================================================

    /**
     * Updates each of the filtered lists with the given predicate.
     * @param personPredicate The predicate to apply to the Person list.
     * @param companyPredicate The predicate to apply to the Company list.
     * @param eventPredicate The predicate to apply to the Event list.
     */
    private void updateFilteredLists(Predicate<? super Person> personPredicate,
                                     Predicate<? super Company> companyPredicate,
                                     Predicate<? super Event> eventPredicate) {
        updateFilteredPersonList(personPredicate);
        updateFilteredCompanyList(companyPredicate);
        updateFilteredEventList(eventPredicate);
    }

    @Override
    public void updateCurrentlyDisplayedList(Predicate<Entry> predicate) {
        switch (currentlyDisplayedListType) {
        case PERSON:
            showPersonList(predicate);
            break;
        case COMPANY:
            showCompanyList(predicate);
            break;
        case EVENT:
            showEventList(predicate);
            break;
        default:
            // Should not reach here
        }
    }

    @Override
    public void showPersonList(Predicate<? super Person> predicate) {
        updateFilteredLists(predicate, PREDICATE_SHOW_NONE, PREDICATE_SHOW_NONE);
        currentlyDisplayedListType = ListType.PERSON;
    }

    @Override
    public void showCompanyList(Predicate<? super Company> predicate) {
        updateFilteredLists(PREDICATE_SHOW_NONE, predicate, PREDICATE_SHOW_NONE);
        currentlyDisplayedListType = ListType.COMPANY;
    }

    @Override
    public void showEventList(Predicate<? super Event> predicate) {
        updateFilteredLists(PREDICATE_SHOW_NONE, PREDICATE_SHOW_NONE, predicate);
        currentlyDisplayedListType = ListType.EVENT;
    }

    @Override
    public void sortPersonListByName(Ordering ordering, Predicate<? super Person> predicate) {
        addressBook.sortPersons(OrderingUtil.orderedComparator(ordering, COMPARATOR_PERSON_BY_NAME));
        showPersonList(predicate);
    }

    @Override
    public void sortCompanyListByName(Ordering ordering, Predicate<? super Company> predicate) {
        addressBook.sortCompanies(OrderingUtil.orderedComparator(ordering, COMPARATOR_COMPANY_BY_NAME));
        showCompanyList(predicate);
    }

    @Override
    public void sortEventListByDate(Ordering ordering, Predicate<? super Event> predicate) {
        addressBook.sortEvents(OrderingUtil.orderedComparator(ordering, COMPARATOR_EVENT_BY_DATE));
        showEventList(predicate);
    }

    @Override
    public Entry deleteEntry(int index) {
        switch (currentlyDisplayedListType) {
        case PERSON:
            if (index >= filteredPersons.size()) {
                return null;
            }

            Person personToDelete = filteredPersons.get(index);
            deletePerson(personToDelete);
            return personToDelete;
        case COMPANY:
            if (index >= filteredCompanies.size()) {
                return null;
            }

            Company companyToDelete = filteredCompanies.get(index);
            deleteCompany(companyToDelete);
            addressBook.removeMatchingCompanyName(companyToDelete.getName().toString());
            return companyToDelete;
        case EVENT:
            if (index >= filteredEvents.size()) {
                return null;
            }

            Event eventToDelete = filteredEvents.get(index);
            deleteEvent(eventToDelete);
            return eventToDelete;
        default:
            return null;
        }
    }

    @Override
    public Entry archiveEntry(int index, boolean isArchived) throws CommandException {
        switch (currentlyDisplayedListType) {
        case PERSON:
            if (index >= filteredPersons.size()) {
                return null;
            }

            Person personToArchive = filteredPersons.get(index);
            if (personToArchive.isArchived() == isArchived) {
                String status = isArchived ? "Archived" : "not Archived";
                throw new CommandException(personToArchive.getName().toString() + " is already " + status);
            }

            addressBook.setArchivePerson(personToArchive, isArchived);
            return personToArchive;
        case COMPANY:
            if (index >= filteredCompanies.size()) {
                return null;
            }

            Company companyToArchive = filteredCompanies.get(index);
            if (companyToArchive.isArchived() == isArchived) {
                String status = isArchived ? "Archived" : "not Archived";
                throw new CommandException(companyToArchive.getName().toString() + " is already " + status);
            }

            addressBook.setArchiveCompany(companyToArchive, isArchived);
            return companyToArchive;
        case EVENT:
            if (index >= filteredEvents.size()) {
                return null;
            }

            Event eventToArchive = filteredEvents.get(index);
            if (eventToArchive.isArchived() == isArchived) {
                String status = isArchived ? "Archived" : "not Archived";
                throw new CommandException(eventToArchive.getName().toString() + " is already " + status);
            }

            addressBook.setArchiveEvent(eventToArchive, isArchived);
            return eventToArchive;
        default:
            return null;
        }
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<? super Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Company List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the filtered company list
     */
    @Override
    public ObservableList<Company> getFilteredCompanyList() {
        return filteredCompanies;
    }

    /**
     * Updates the filter of the filtered company list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    public void updateFilteredCompanyList(Predicate<? super Company> predicate) {
        requireNonNull(predicate);
        filteredCompanies.setPredicate(predicate);
    }

    //=========== Filtered Event List Accessors =============================================================

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<? super Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
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
                && filteredPersons.equals(other.filteredPersons)
                && filteredCompanies.equals(other.filteredCompanies)
                && filteredEvents.equals(other.filteredEvents);
    }

}



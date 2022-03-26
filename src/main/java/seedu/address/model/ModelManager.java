package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    /** Internal list of filtered persons, wrapped by sortedPersons */
    private final FilteredList<Person> filteredPersons;
    /** The sorted, filtered list to be shown in the GUI. */
    private final SortedList<Person> displayPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        // A sorted list wrapping over the internal filtered list
        displayPersons = new SortedList<>(filteredPersons);
    }

    public ModelManager() {
        this(new VersionedAddressBook(), new UserPrefs());
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
        updateDisplayPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    public boolean canUndoAddressBook() {
        if (this.addressBook instanceof VersionedAddressBook) {
            VersionedAddressBook versionedAddressBook = (VersionedAddressBook) this.addressBook;
            return versionedAddressBook.canUndo();
        }

        return false;
    }

    public void undoAddressBook(){
        if (this.addressBook instanceof VersionedAddressBook) {
            VersionedAddressBook versionedAddressBook = (VersionedAddressBook) this.addressBook;
            versionedAddressBook.undo();
        }
    }

    public boolean canRedoAddressBook() {
        if (this.addressBook instanceof VersionedAddressBook) {
            VersionedAddressBook versionedAddressBook = (VersionedAddressBook) this.addressBook;
            return versionedAddressBook.canRedo();
        }
        return false;
    }

    public void redoAddressBook(){
        if (this.addressBook instanceof VersionedAddressBook) {
            VersionedAddressBook versionedAddressBook = (VersionedAddressBook) this.addressBook;
            versionedAddressBook.redo();
        }
    }

    public void commitAddressBook() {
        if (this.addressBook instanceof VersionedAddressBook) {
            VersionedAddressBook versionedAddressBook = (VersionedAddressBook) this.addressBook;
            versionedAddressBook.commit();
        }
    }

    //=========== Filtered & Sorted Person List Accessors ==========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getDisplayPersonList() {
        return displayPersons;
    }

    @Override
    public void updateDisplayPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
        // Remove sort criteria on new filter
        displayPersons.setComparator(null);
    }

    @Override
    public void updateDisplayPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        displayPersons.setComparator(comparator);
    }

    @Override
    public void updateDisplayPersonList(Predicate<Person> predicate, Comparator<Person> comparator) {
        requireNonNull(predicate);
        requireNonNull(comparator);
        filteredPersons.setPredicate(predicate);
        displayPersons.setComparator(comparator);
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
                && filteredPersons.equals(other.filteredPersons);
    }

}

package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StateAddressBook stateAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SimpleObjectProperty<Person> personOnDisplay;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.stateAddressBook = new StateAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.stateAddressBook.getPersonList());
        personOnDisplay = new SimpleObjectProperty<>();
        this.stateAddressBook.getPersonList().addListener(this::handleListChangeListener);
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
        this.stateAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return stateAddressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return stateAddressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        stateAddressBook.removePerson(target);
    }

    /**
     * Deletes all the persons from the model.
     *
     * @param persons List of person to delete.
     */
    @Override
    public void deleteAllPerson(ObservableList<Person> persons) {
        requireAllNonNull(persons);
        Person[] personsToDelete = Arrays.stream(persons.toArray())
                .map(o -> (Person) o).toArray(Person[]::new);
        stateAddressBook.removePersons(personsToDelete);
    }


    @Override
    public void addPerson(Person person) {
        stateAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        stateAddressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code stateAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Person on Display Accessors ================================================================

    @Override
    public ReadOnlyProperty<Person> getPersonOnDisplay() {
        return personOnDisplay;
    }

    @Override
    public void updatePersonOnDisplay(Person person) {
        personOnDisplay.set(null);
        personOnDisplay.set(person);
    }

    /**
     * Removes the current {@code personOnDisplay}.
     */
    private void removePersonOnDisplay() {
        updatePersonOnDisplay(null);
    }

    /**
     * Updates the {@code personOnDisplay} according to how the list changes.
     * If a {@code Person} was added, change the {@code personOnDisplay} to the newly added {@code Person}.
     * If a {@code Person} was modified, update the {@code Person}'s data
     * If a {@code Person} was deleted, delete the {@code personOnDisplay} if it's the same as the Person deleted.
     *
     * @param change Contains all the modifications to the list.
     */
    private void handleListChangeListener(ListChangeListener.Change<? extends Person> change) {
        change.next();
        List<? extends Person> addedList = change.getAddedSubList();
        List<? extends Person> removedList = change.getRemoved();

        if (change.getAddedSize() > change.getRemovedSize()) {
            updateDisplayUponAddition(addedList, removedList);
            return;
        } else if (change.getAddedSize() == change.getRemovedSize()) {
            updateDisplayUponModification(addedList, removedList);
        }

        // There's no point checking if the person on display needs to be deleted if it doesn't exist.
        if (personOnDisplay.get() == null) {
            return;
        }
        if (change.getAddedSize() < change.getRemovedSize()) {
            updateDisplayUponDeletion(addedList, removedList);
        }
    }

    /**
     * Updates {@code personOnDisplay} when {@code Person} are added to the {@code AddressBook}.
     *
     * @param addedList List of {@code Person} added.
     * @param removedList List of {@code Person} removed.
     */
    private void updateDisplayUponAddition(
            List<? extends Person> addedList, List<? extends Person> removedList) {
        addedList.forEach(person -> {
            if (!removedList.contains(person)) {
                updatePersonOnDisplay(person);
            }
        });
    }

    /**
     * Updates {@code personOnDisplay} when {@code Person} are modified in the {@code AddressBook}.
     *
     * @param addedList List of {@code Person} added.
     * @param removedList List of {@code Person} removed.
     */
    private void updateDisplayUponModification(
            List<? extends Person> addedList, List<? extends Person> removedList) {
        assert addedList.size() == removedList.size();

        for (int i = 0; i < removedList.size(); i++) {
            Person oldPerson = removedList.get(i);
            Person newPerson = addedList.get(i);
            if (!oldPerson.exactEquals(newPerson)) {
                updatePersonOnDisplay(newPerson);
            }
        }
    }

    /**
     * Updates {@code personOnDisplay} when {@code Person} are deleted from the {@code AddressBook}.
     *
     * @param addedList List of {@code Person} added.
     * @param removedList List of {@code Person} removed.
     */
    private void updateDisplayUponDeletion(
            List<? extends Person> addedList, List<? extends Person> removedList) {
        Person curPerson = personOnDisplay.get();
        if (removedList.contains(curPerson) && !addedList.contains(curPerson)) {
            removePersonOnDisplay();
        }
    }

    @Override
    public void addPersonOnDisplayListener(ChangeListener<? super Person> listener) {
        personOnDisplay.addListener(listener);
    }

    //=========== Undo and redo ==============================================================================

    /**
     * Restores address book to its previous state.
     */
    @Override
    public void undoAddressBook() {
        stateAddressBook.undo();
    }

    /**
     * Restores address book to its previous undid state.
     */
    @Override
    public void redoAddressBook() {
        stateAddressBook.redo();
    }

    /**
     * Returns true if address book is undoable; otherwise returns false.
     *
     * @return true if undoable; false otherwise.
     */
    @Override
    public boolean canUndoAddressBook() {
        return stateAddressBook.isUndoable();
    }

    /**
     * Returns true if address book is redoable; otherwise returns false.
     *
     * @return true if redoable; false otherwise.
     */
    @Override
    public boolean canRedoAddressBook() {
        return stateAddressBook.isRedoable();
    }

    /**
     * Saves the current address book state.
     */
    @Override
    public void saveAddressBookState() {
        stateAddressBook.saveState();
    }

    /**
     * Checks if two {@code ModelManager} is equal.
     *
     * @param obj the other {@code ModelManager} object.
     * @return true if equal; otherwise false.
     */
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
        return stateAddressBook.equals(other.stateAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}

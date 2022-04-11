package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Person;
import seedu.address.model.person.comparators.TagPriorityComparator;
import seedu.address.storage.UndoRedoStorage;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private InsurancePackagesSet insurancePackagesSet;
    private final FilteredList<Person> filteredPersons;

    private UndoRedoStorage undoRedoStorage = new UndoRedoStorage();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        InsurancePackagesSet insurancePackages) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.insurancePackagesSet = new InsurancePackagesSet(insurancePackages);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        undoRedoStorage.addToUndo(copyAddressBook());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new InsurancePackagesSet());
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

    @Override
    public Path getInsurancePackagesFilePath() {
        return userPrefs.getInsurancePackagesFilePath();
    }

    @Override
    public void setInsurancePackagesFilePath(Path insurancePackagesFilePath) {
        requireNonNull(insurancePackagesFilePath);
        userPrefs.setInsurancePackagesFilePath(insurancePackagesFilePath);
    }

    //=========== InsurancePackages ==========================================================================
    @Override
    public void setInsurancePackagesSet(InsurancePackagesSet insurancePackagesSet) {
        this.insurancePackagesSet = new InsurancePackagesSet(insurancePackagesSet);
    }

    @Override
    public InsurancePackagesSet getInsurancePackagesSet() {
        return insurancePackagesSet;
    }

    @Override
    public void addInsurancePackage(InsurancePackage p) {
        this.insurancePackagesSet.addPackage(p);
    }

    @Override
    public void deleteInsurancePackage(InsurancePackage p) {
        this.insurancePackagesSet.removePackage(p);
    }

    @Override
    public boolean hasInsurancePackage(InsurancePackage p) {
        requireNonNull(p);
        return insurancePackagesSet.hasPackage(p);
    }

    @Override
    public void setInsurancePackage(String targetPackageName, String newPackageDesc) {
        insurancePackagesSet.setPackage(targetPackageName, newPackageDesc);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public void resetAddressBook() {
        this.addressBook.resetData(new AddressBook());
        undoRedoStorage.resetRedoStack();
        undoRedoStorage.addToUndo(copyAddressBook());
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
        undoRedoStorage.resetRedoStack();
        undoRedoStorage.addToUndo(copyAddressBook());
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        undoRedoStorage.resetRedoStack();
        undoRedoStorage.addToUndo(copyAddressBook());
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
        undoRedoStorage.resetRedoStack();
        undoRedoStorage.addToUndo(copyAddressBook());
    }

    @Override
    public void undoCommand() throws CommandException {
        AddressBook newAddressBook = undoRedoStorage.undo();
        setAddressBook(newAddressBook);
    }

    @Override
    public void redoCommand() throws CommandException {
        AddressBook newAddressBook = undoRedoStorage.redo();
        setAddressBook(newAddressBook);
    }

    /**
     * Makes a deep copy of an AddressBook
     */
    public AddressBook copyAddressBook() {
        AddressBook copiedAddressBook = new AddressBook();
        int currAddressBookSize = addressBook.getPersonList().size();
        for (int i = 0; i < currAddressBookSize; i++) {
            Person copiedPerson = Person.copyPerson(addressBook.getPersonList().get(i));
            copiedAddressBook.addPerson(copiedPerson);
        }
        return copiedAddressBook;
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

    /**
     * Filters and updates {@code filteredPersons} by {@code predicate}.
     */
    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    /**
     * Sorts the list of {@code Person} in {@code addressBook} by the priority level of their tags.
     */
    @Override
    public void sortByPriority() {
        addressBook.sort(new TagPriorityComparator());
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

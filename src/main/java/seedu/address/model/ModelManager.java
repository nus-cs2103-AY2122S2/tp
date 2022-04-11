package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.commandhistory.CommandHistory;
import seedu.address.model.commandhistory.CommandHistoryEntry;
import seedu.address.model.comparator.AddressComparator;
import seedu.address.model.comparator.DeadlineListComparator;
import seedu.address.model.comparator.EmailComparator;
import seedu.address.model.comparator.FavouriteComparator;
import seedu.address.model.comparator.HighImportanceComparator;
import seedu.address.model.comparator.NameComparator;
import seedu.address.model.comparator.PhoneComparator;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SortedList<Person> sortedPersons;
    private final ObservableList<Person> detailedContactView;
    private final CommandHistory commandHistory;
    private final ObservableList<Tag> activatedTags;

    private ImageDetailsList imagesToView;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        sortedPersons = new SortedList<>(filteredPersons);
        detailedContactView = FXCollections.observableArrayList();
        activatedTags = new FilteredList<>(this.addressBook.getActivatedTagList());
        this.imagesToView = new ImageDetailsList();
        this.commandHistory = new CommandHistory();
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

    @Override
    public Path getContactImagesFilePath() {
        return userPrefs.getContactImagesFilePath();
    }

    @Override
    public void setContactImagesFilePath(Path contactImagesFilePath) {
        requireNonNull(contactImagesFilePath);
        userPrefs.setContactImagesFilePath(contactImagesFilePath);
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
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return addressBook.hasTag(tag);
    }

    @Override
    public void addTag(Tag tag) {
        addressBook.addTag(tag);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);

        addressBook.setTag(target, editedTag);
    }

    @Override
    public void deleteTag(Tag target) {
        addressBook.removeTag(target);
    }

    @Override
    public void addActivatedTag(Tag tag) {
        requireNonNull(tag);
        addressBook.addActivatedTag(tag);
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
    public SortedList<Person> getSortedPersonList() {
        return sortedPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<Tag> getActivatedTagList() {
        return this.activatedTags;
    }

    @Override
    public void clearActivatedTagList() {
        logger.fine("Clearing activated tag lists");
        addressBook.clearActivatedTagList();
    }

    //=========== Detailed Contact View methods =============================================================

    @Override
    public ObservableList<Person> getDetailedContactView() {
        return detailedContactView.filtered(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setDetailedContactView(Person person) {
        if (detailedContactView.size() > 0) {
            clearDetailedContactView();
        }
        logger.fine("Setting " + person.getName().fullName + " in detailed view");
        detailedContactView.add(person);
        assert(detailedContactView.size() == 1);
    }

    @Override
    public void clearDetailedContactView() {
        logger.fine("Clearing detailed view");
        detailedContactView.clear();
    }

    @Override
    public Person getDetailedContactViewPerson() {
        assert detailedContactView.size() == 1;
        return detailedContactView.get(0);
    }

    //=========== Person Images to View ==============================================================================
    @Override
    public void setImagesToView(ImageDetailsList images) {
        this.imagesToView = images;
    }

    @Override
    public ImageDetailsList getImagesToView() {
        return this.imagesToView;
    }

    /**
     * Updates the commandText history
     *
     * @param commandText the text to cache
     */
    @Override
    public void updateCommandHistory(String commandText) {
        commandHistory.cacheCommand(commandText);
    }

    /**
     * Retrieves the i-th latest command text
     * @return
     */
    @Override
    public CommandHistoryEntry getCommandHistory(int i) {
        return commandHistory.retrieveCommand(i);
    }

    @Override
    public void sortFilteredPersonListByName() {
        sortedPersons.setComparator(new NameComparator());
    }

    @Override
    public void sortFilteredPersonListByAddress() {
        sortedPersons.setComparator(new AddressComparator());
    }

    @Override
    public void sortFilteredPersonListByDeadlineList() {
        sortedPersons.setComparator(new DeadlineListComparator());
    }

    @Override
    public void sortFilteredPersonListByEmail() {
        sortedPersons.setComparator(new EmailComparator());
    }

    @Override
    public void sortFilteredPersonListByPhone() {
        sortedPersons.setComparator(new PhoneComparator());
    }

    @Override
    public void sortFilteredPersonListByFavourite() {
        sortedPersons.setComparator(new FavouriteComparator());
    }

    @Override
    public void sortFilteredPersonListByHighImportance() {
        sortedPersons.setComparator(new HighImportanceComparator());
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
                && detailedContactView.equals(other.detailedContactView)
                && imagesToView.equals(other.imagesToView)
                && commandHistory.equals(other.commandHistory)
                && activatedTags.equals(other.activatedTags);
    }
}

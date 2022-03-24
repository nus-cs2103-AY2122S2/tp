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
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleName;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Schedule> filteredSchedules;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with MyGM: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredSchedules = new FilteredList<>(this.addressBook.getScheduleList());
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

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasPersonName(Name targetName) {
        requireNonNull(targetName);
        return addressBook.hasPersonName(targetName);
    }

    @Override
    public boolean hasLineupName(LineupName targetName) {
        requireNonNull(targetName);
        return addressBook.hasLineupName(targetName);
    }

    @Override
    public Person getPerson(Name targetName) {
        requireNonNull(targetName);
        return addressBook.getPerson(targetName);
    }

    @Override
    public Lineup getLineup(LineupName targetName) {
        requireNonNull(targetName);
        return addressBook.getLineup(targetName);
    }

    @Override
    public boolean isPersonInLineup(Person person, Lineup lineup) {
        return lineup.hasPlayer(person);
    }

    @Override
    public void deletePersonFromLineup(Person person, Lineup lineup) {
        lineup.removePlayer(person);
        person.removeFromLineup(lineup);
        addressBook.refresh();
    }

    @Override
    public void addLineup(Lineup toAdd) {
        addressBook.addLineup(toAdd);
        addressBook.refresh();
    }

    @Override
    public void deleteLineup(Lineup lineup) {
        addressBook.deleteLineup(lineup);
        addressBook.refresh();
    }

    @Override
    public boolean hasJerseyNumber(Person person) {
        requireNonNull(person);
        /* to be changed to AB3 */
        return addressBook.hasJerseyNumber(person);
    }

    @Override
    public String getAvailableJerseyNumber() {
        /* to be changed to AB3 */
        return addressBook.getAvailableJerseyNumber();
    }

    @Override
    public boolean isFull() {
        /* to be changed to AB3 */
        return addressBook.isFull();
    }

    /**
     * Refreshes the model to display the change in GUI.
     */
    @Override
    public void refresh() {
        addressBook.refresh();
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
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
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setLineup(Lineup target, Lineup editedLineup) {
        requireAllNonNull(target, editedLineup);
        addressBook.setLineup(target, editedLineup);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void putPersonIntoLineup(Person player, Lineup lineup) {
        player.addLineupName(lineup);
        addressBook.addPersonToLineup(player, lineup);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    //=========== Schedule
    @Override
    public boolean hasSchedule(Schedule schedule) {
        requireNonNull(schedule);
        return addressBook.hasSchedule(schedule);
    }

    @Override
    public boolean hasScheduleName(ScheduleName targetName) {
        requireNonNull(targetName);
        return addressBook.hasScheduleName(targetName);
    }


    @Override
    public Person getPerson(Name targetName) {
        requireNonNull(targetName);
        return addressBook.getPerson(targetName);
    }


    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
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
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
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
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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

package seedu.contax.model;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.contax.commons.core.GuiSettings;
import seedu.contax.commons.core.LogsCenter;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final Schedule schedule;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlySchedule schedule,
                        ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, schedule, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + " , schedule: " + schedule
                + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.schedule = new Schedule(schedule);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new Schedule(), new UserPrefs());
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
    public Path getScheduleFilePath() {
        return userPrefs.getScheduleFilePath();
    }

    @Override
    public void setScheduleFilePath(Path scheduleFilePath) {
        requireNonNull(scheduleFilePath);
        userPrefs.setScheduleFilePath(scheduleFilePath);
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

        // Delete Successful, dissociate target with any appointments.
        // The list of matching appointments is cloned because the list iterator is destroyed upon
        // any modification to the list.
        List<Appointment> oldAppointments = new ArrayList<>(
                schedule.getAppointmentList()
                        .filtered(appointment -> appointment.getPerson().equals(target)));
        oldAppointments.forEach(appointment -> {
            setAppointment(appointment, appointment.withPerson(null));
        });
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

        // Update success, update appointments with target.
        // The list of matching appointments is cloned because the list iterator is destroyed upon
        // any modification to the list.
        List<Appointment> oldAppointments = new ArrayList<>(
                schedule.getAppointmentList()
                        .filtered(appointment -> appointment.getPerson().equals(target)));

        oldAppointments.forEach(appointment -> {
            setAppointment(appointment, appointment.withPerson(editedPerson));
        });
    }

    // Tag management
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
    public void deleteTag(Tag tagToDelete) {
        requireNonNull(tagToDelete);
        removeTagFromPersons(tagToDelete);
        addressBook.removeTag(tagToDelete);
    }

    @Override
    public void removeTagFromPersons(Tag tagToDelete) {
        requireNonNull(tagToDelete);
        List<Person> persons = addressBook.getPersonList();
        for (Person oldPerson : persons) {
            Set<Tag> updatedTags = new HashSet<>(Set.copyOf(oldPerson.getTags()));
            boolean isRemoved = updatedTags.remove(tagToDelete);
            if (isRemoved) {
                Person updatedPerson = oldPerson.updateTags(updatedTags);
                setPerson(oldPerson, updatedPerson);
            }
        }
    }

    @Override
    public ObservableList<Tag> getTagList() {
        return addressBook.getTagList();
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

    //=========== Schedule ===================================================================================
    @Override
    public void setSchedule(ReadOnlySchedule schedule) {
        this.schedule.resetData(schedule);
    }

    @Override
    public ReadOnlySchedule getSchedule() {
        return schedule;
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return schedule.hasAppointment(appointment);
    }

    @Override
    public boolean hasOverlappingAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return schedule.hasOverlappingAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        requireNonNull(appointment);
        schedule.removeAppointment(appointment);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        requireNonNull(appointment);
        schedule.addAppointment(appointment);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);
        schedule.setAppointment(target, editedAppointment);
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
                && schedule.equals(other.schedule)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}

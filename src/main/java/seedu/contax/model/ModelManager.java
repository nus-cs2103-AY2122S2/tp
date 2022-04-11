package seedu.contax.model;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.contax.commons.core.GuiSettings;
import seedu.contax.commons.core.LogsCenter;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.model.chrono.TimeRange;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;

/**
 * Represents the in-memory model of the both the AddressBook and Schedule.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final Schedule schedule;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Appointment> filteredAppointments;
    private final FilteredList<Tag> filteredTags;

    private final AppointmentSlotList displayedAppointmentSlots;
    private final CompositeObservableList<ScheduleItem> scheduleItemList;

    /**
     * Initializes a ModelManager with the given addressBook, schedule and userPrefs.
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
        filteredAppointments = new FilteredList<>(this.schedule.getAppointmentList());
        filteredTags = new FilteredList<>(this.addressBook.getTagList());

        displayedAppointmentSlots = new AppointmentSlotList(this.schedule);
        scheduleItemList = new CompositeObservableList<>(filteredAppointments,
                displayedAppointmentSlots.getSlotList());
    }

    /**
     * Initializes an empty ModelManager.
     */
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
        requireNonNull(addressBook);
        this.addressBook.resetData(addressBook);

        Predicate<Appointment> shouldSyncAppointment = (appointment)
            -> appointment.getPerson() != null && !this.addressBook.hasPerson(appointment.getPerson());
        Consumer<Appointment> syncAction = (appointment)
            -> this.schedule.setAppointment(appointment, appointment.withPerson(null));
        syncScheduleWithAddressBook(shouldSyncAppointment, syncAction);
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
        Predicate<Appointment> shouldSyncAppointment = (appointment) -> target.equals(appointment.getPerson());
        Consumer<Appointment> syncAction = (appointment)
            -> this.schedule.setAppointment(appointment, appointment.withPerson(null));
        syncScheduleWithAddressBook(shouldSyncAppointment, syncAction);
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
        Predicate<Appointment> shouldSyncAppointment = (appointment) -> target.equals(appointment.getPerson());
        Consumer<Appointment> syncAction = (appointment)
            -> this.schedule.setAppointment(appointment, appointment.withPerson(editedPerson));
        syncScheduleWithAddressBook(shouldSyncAppointment, syncAction);
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
        addressBook.removeTag(tagToDelete);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        requireNonNull(target);
        requireNonNull(editedTag);

        addressBook.setTag(target, editedTag);
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

    //=========== Filtered Tag List Accessors =============================================================

    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        requireNonNull(predicate);
        filteredTags.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the {@code Tag} backed by the internal list of {@code versionedAddressBook}.
     */
    @Override
    public ObservableList<Tag> getFilteredTagList() {
        return filteredTags;
    }

    //=========== Schedule ===================================================================================
    @Override
    public void setSchedule(ReadOnlySchedule schedule) {
        requireNonNull(schedule);
        this.schedule.resetData(schedule);

        Predicate<Appointment> shouldSyncAppointment = (appointment)
            -> appointment.getPerson() != null && !this.addressBook.hasPerson(appointment.getPerson());
        Consumer<Appointment> syncAction = (appointment)
            -> this.addressBook.addPerson(appointment.getPerson());
        syncScheduleWithAddressBook(shouldSyncAppointment, syncAction);
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

    //=========== Filtered Appointment List Accessors ========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointments} backed by the appointments list of
     * {@code Schedule}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<? super Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    //=========== Appointment Slot List Operations ===========================================================

    @Override
    public ObservableList<AppointmentSlot> getDisplayedAppointmentSlots() {
        return displayedAppointmentSlots.getSlotList();
    }

    @Override
    public void setDisplayedAppointmentSlotRange(TimeRange range, int minimumDuration) {
        requireNonNull(range);
        displayedAppointmentSlots.updateFilteredRange(range, minimumDuration);
    }

    @Override
    public void clearDisplayedAppointmentSlots() {
        displayedAppointmentSlots.updateFilteredRange(null, 0);
    }

    @Override
    public ObservableList<ScheduleItem> getScheduleItemList() {
        return this.scheduleItemList.getUnmodifiableResultList();
    }

    /**
     * Synchronizes the schedule by performing {@code syncAction} on every Appointment in the Schedule that
     * matches {@code syncFilter}.
     *
     * @param syncFilter A {@code Predicate} matching the Appointments that require synchronization.
     * @param syncAction A {@code Consumer} that performs the actual synchronization on an Appointment.
     */
    private void syncScheduleWithAddressBook(Predicate<Appointment> syncFilter,
                                             Consumer<Appointment> syncAction) {
        List<Appointment> matchingAppointments =
                new ArrayList<>(this.schedule.getAppointmentList().filtered(syncFilter));
        matchingAppointments.forEach(syncAction);
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
                && filteredPersons.equals(other.filteredPersons)
                && filteredAppointments.equals(other.filteredAppointments)
                && filteredTags.equals(other.filteredTags)
                && displayedAppointmentSlots.equals(other.displayedAppointmentSlots);
    }

}

package seedu.contax.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.contax.commons.core.GuiSettings;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluates to true */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Tag> PREDICATE_SHOW_ALL_TAGS = unused -> true;

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
    void updateFilteredPersonList(Predicate<Person> predicate);

    // Tag management
    /**
     * Returns true if a matching {@code tag} exists in the address book.
     */
    boolean hasTag(Tag tag);

    /**
     * Adds the given tag into the address book.
     * Tag must not already exist in the address book.
     */
    void addTag(Tag tag);

    /**
     * Deletes the given tag from the address book.
     * The supplied tag must already exist in the address book.
     */
    void deleteTag(Tag tagToDelete);

    /**
     * Replaces the given {@code target} with {@code editedTag}.
     * {@code target} must exist in the address book.
     * {@code editedTag} must not be the same as another existing tag in the address book.
     */
    void setTag(Tag target, Tag editedTag);

    /**
     * Updates the filter of the filtered tag list to the filter given by {@code predicate}.
     */
    void updateFilteredTagList(Predicate<Tag> predicate);

    /** Returns an unmodifiable view of the filtered tag list. */
    ObservableList<Tag> getFilteredTagList();

    // Appointments
    /**
     * Returns the user prefs' schedule file path.
     */
    Path getScheduleFilePath();

    /**
     * Sets the user prefs' schedule file path.
     */
    void setScheduleFilePath(Path scheduleFilePath);

    /**
     * Replaces schedule data with the data in {@code schedule}.
     */
    void setSchedule(ReadOnlySchedule schedule);

    /** Returns the Schedule */
    ReadOnlySchedule getSchedule();

    /**
     * Returns true if a matching {@code appointment} exists in the schedule.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Returns true if an overlapping {@code appointment} exists in the schedule.
     * See {@link Appointment#isOverlapping(ScheduleItem)} for the overlap checking logic.
     */
    boolean hasOverlappingAppointment(Appointment appointment);

    /**
     * Deletes the given appointment from the schedule.
     * The supplied appointment must exist in the schedule.
     */
    void deleteAppointment(Appointment appointment);

    /**
     * Adds the given appointment into the schedule.
     * An overlapping {@code appointment} must not already exist in the schedule.
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the schedule.
     * The appointment {@code editedAppointment} must not overlap with an existing appointment in the
     * schedule.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /** Returns an unmodifiable view of the filtered appointment list. */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /** Returns an unmodifiable view of the slots between appointments to be highlighted. */
    ObservableList<AppointmentSlot> getDisplayedAppointmentSlots();

    /**
     * Sets the list of slots between appointments to highlight.
     *
     * @param items A list containing the ranges between appointments to highlight.
     */
    void setDisplayedAppointmentSlots(List<AppointmentSlot> items);

    /**
     * Clears all the highlighted empty slots in the Schedule.
     */
    void clearDisplayedAppointmentSlots();

    /**
     * Returns a list of {@code ScheduleItems} currently in the Schedule.
     */
    ObservableList<ScheduleItem> getScheduleItemList();

}

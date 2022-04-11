package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;

/**
 * Wraps all data of LinkyTime
 * Duplicates are not allowed (by .equals comparison)
 */
public class LinkyTime implements ReadOnlyLinkyTime {

    private final UniqueMeetingList meetings;
    private final UniqueModuleList modules;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        meetings = new UniqueMeetingList();
        modules = new UniqueModuleList();
    }

    public LinkyTime() {}

    /**
     * Creates a LinkyTime using the Meetings in the {@code toBeCopied}
     */
    public LinkyTime(ReadOnlyLinkyTime toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the meeting list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Replaces the contents of the module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Resets the existing data of this {@code LinkyTime} with {@code newData}.
     */
    public void resetData(ReadOnlyLinkyTime newData) {
        requireNonNull(newData);

        setMeetings(newData.getMeetingList());
        setModules(newData.getModuleList());
    }

    //// meeting-level operations

    /**
     * Returns true if a meeting that is identical to {@code meeting} exists in LinkyTime.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds a meeting to LinkyTime.
     * The meeting must not already exist in LinkyTime.
     */
    public void addMeeting(Meeting m) {
        meetings.add(m);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in LinkyTime.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);

        meetings.setMeeting(target, editedMeeting);
    }

    /**
     * Sorts the meeting list according to the in built sort function.
     */
    public void sortMeetings() {
        meetings.sortMeetings();
    }

    /**
     * Removes {@code key} from {@code LinkyTime}.
     * {@code key} must exist in LinkyTime.
     */
    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    //// module-level operations

    /**
     * Returns true if a module that is identical to {@code module} exists in LinkyTime.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Adds a module to LinkyTime.
     * The module must not already exist in LinkyTime.
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in LinkyTime.
     */
    public void setModule(Module target, Module editedModule) {
        requireNonNull(editedModule);
        modules.setModule(target, editedModule);
    }

    /**
     * Removes {@code key} from {@code LinkyTime}.
     * {@code key} must exist in LinkyTime.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    /**
     * Sorts all the modules in LinkyTime by alphabetical order.
     * It is case insensitive.
     */
    public void sortModules() {
        modules.sortModules();
    }

    //// util methods

    @Override
    public String toString() {
        return meetings.asUnmodifiableObservableList().size() + " meetings\n"
                + modules.asUnmodifiableObservableList().size() + " modules";
        // TODO: refine later
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkyTime // instanceof handles nulls
                && meetings.equals(((LinkyTime) other).meetings)
                && modules.equals(((LinkyTime) other).modules));
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetings, modules);
    }
}

package unibook.model.module.group;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.model.module.Module;
import unibook.model.module.exceptions.DuplicateMeetingTimeException;
import unibook.model.module.exceptions.MeetingTimeNotFoundException;
import unibook.model.person.Student;
import unibook.model.person.exceptions.PersonNotFoundException;

/**
 * Represents a group of students within a module.
 */
public class Group {
    private String name;
    private final Module module;
    private final ObservableList<Student> members;
    private final ObservableList<LocalDateTime> meetingTimes;

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Instantiates a group object.
     *
     * @param name         of the group.
     * @param module       that the group is in.
     * @param members      of the group.
     * @param meetingTimes meeting times of the group.
     */
    public Group(String name, Module module, ObservableList<Student> members,
                 ObservableList<LocalDateTime> meetingTimes) {
        this.name = name;
        this.module = module;
        this.meetingTimes = meetingTimes;
        this.members = members;
    }

    /**
     * Instantiates a group object, with an empty list of associated students.
     *
     * @param name         of the group.
     * @param module       that the group is in.
     * @param meetingTimes meeting times of the group.
     */
    public Group(String name, Module module, ObservableList<LocalDateTime> meetingTimes) {
        this.name = name;
        this.module = module;
        this.meetingTimes = meetingTimes;
        this.members = FXCollections.observableArrayList();
    }

    /**
     * Instantiates a group object, with an empty list of meeting times and associated students.
     *
     * @param name   of the group.
     * @param module that the group is in.
     */
    public Group(String name, Module module) {
        this.name = name;
        this.module = module;
        this.meetingTimes = FXCollections.observableArrayList();
        this.members = FXCollections.observableArrayList();
    }

    /**
     * Returns the group name of the group object.
     *
     * @return group name of the group object.
     */
    public String getGroupName() {
        return name;
    }

    /**
     * Edit the group name of the group object.
     */
    public void editGroupName(String name) {
        this.name = name;
    }

    public Module getModule() {
        return module;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    /**
     * Returns the member students of the group.
     *
     * @return members of the group.
     */
    public ObservableList<Student> getMembers() {
        return members;
    }

    /**
     * Adds a member to the group.
     *
     * @param student
     */
    public void addMember(Student student) {
        requireNonNull(student);
        members.add(student);
    }

    /**
     * Removes the specific student from the group.
     *
     * @param student
     */
    public void removeMember(Student student) {
        requireNonNull(student);
        if (!members.contains(student)) {
            throw new PersonNotFoundException();
        }
        members.remove(student);
    }

    /**
     * Add a meeting datetime to the group.
     *
     * @param meetingTime meeting datetime to add.
     */
    public void addMeetingTime(LocalDateTime meetingTime) {
        requireNonNull(meetingTime);
        //ensure no duplicate meeting times can exist
        if (meetingTimes.contains(meetingTime)) {
            throw new DuplicateMeetingTimeException();
        }
        meetingTimes.add(meetingTime);
    }

    /**
     * Remove a meeting datetime from the group.
     *
     * @param meetingTime meeting datetime to remove.
     */
    public void removeMeetingTime(LocalDateTime meetingTime) {
        requireNonNull(meetingTime);
        if (!meetingTimes.contains(meetingTime)) {
            throw new MeetingTimeNotFoundException();
        }
        meetingTimes.remove(meetingTime);
    }

    /**
     * Edit a meeting datetime from the group.
     *
     * @param idx index of the meeting time to edit
     * @param meetingTime meeting datetime to remove.
     */
    public void editMeetingTime(int idx, LocalDateTime meetingTime) {
        requireNonNull(meetingTime);
        this.meetingTimes.set(idx, meetingTime);
    }

    /**
     * Returns true only if the name of the group, its meeting times, and the module code of the module it is linked to
     * are the same.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Group) {
            Group otherGroup = (Group) other;
            return getGroupName().equals(otherGroup.getGroupName())
                && meetingTimes.equals(otherGroup.getMeetingTimes())
                && getModule().getModuleCode().equals(otherGroup.getModule().getModuleCode());
        } else {
            return false;
        }
    }

    /**
     * Returns the list of meeting times for a group.
     *
     * @return list of meeting times for a group.
     */
    public ObservableList<LocalDateTime> getMeetingTimes() {
        return meetingTimes;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getGroupName())
            .append("; Module: ")
            .append(getModule().getModuleCode());
        return builder.toString();
    }
}

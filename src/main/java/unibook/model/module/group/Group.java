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
    private final String name;
    private final Module module;
    private final ObservableList<Student> members;
    private final ObservableList<LocalDateTime> meetingTimes;

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
     * Returns the group name of the group object.
     *
     * @return group name of the group object.
     */
    public String getGroupName() {
        return name;
    }

    public Module getModule() {
        return module;
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
}

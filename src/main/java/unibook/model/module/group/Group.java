package unibook.model.module.group;

import static java.util.Objects.requireNonNull;
import static unibook.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

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
    public static final String NAME_CONSTRAINT_MESSAGE = "A group name is limited in length to within 50 characters,"
            + " and cannot be blank.";
    //Group name can only have alphanumeric characters

    private static final int MAX_GROUP_NAME_LENGTH = 50;
    private final ObservableList<Student> members;
    private final ObservableList<LocalDateTime> meetingTimes;
    private String name;
    private Module module;

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
        this.name = name.trim();
        checkArgument(isValidName(this.name), NAME_CONSTRAINT_MESSAGE);
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
        this(name, module, FXCollections.observableArrayList(), meetingTimes);
    }

    /**
     * Instantiates a group object, with an empty list of meeting times and associated students.
     *
     * @param name   of the group.
     * @param module that the group is in.
     */
    public Group(String name, Module module) {
        this(name, module, FXCollections.observableArrayList(), FXCollections.observableArrayList());
    }

    /**
     * Instantiates a group object with only the name, to be used to match groups to delete
     */
    public Group(String name) {
        this(name, null, FXCollections.observableArrayList(), FXCollections.observableArrayList());
    }

    /**
     * Returns true if a given string is a valid group name, meaning it must be within 50 characters,
     * and is not blank (just consisting of only whitespaces).
     */
    public static boolean isValidName(String test) {
        String trimmedTest = test.trim();
        return trimmedTest.length() > 0 && trimmedTest.length() <= MAX_GROUP_NAME_LENGTH;
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
     * Returns of whether student in group
     *
     * @return boolean of whether student exists.
     */
    public boolean hasMember(Student s) {
        for (Student student : members) {
            if (student.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Edit the module of the group object.
     */
    public void editModule(Module module) {
        this.module = module;
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
     * Removes the groups in the group list of each student that has this group
     */
    public void removeStudentsFromThisGroup() {
        for (Student student : members) {
            student.removeGroup(module.getModuleCode(), this);
        }
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
     * Remove a meeting based on Index given
     *
     * @param index
     */
    public void removeMeetingTime(int index) {
        meetingTimes.remove(index);
    }


    /**
     * Returns true only if the name of the group provided and this group name
     * are the same, ignoring casing.
     */
    public boolean sameGroupName(Group group) {
        return name.equalsIgnoreCase(group.getGroupName());
    }

    /**
     * Returns True if group name and module code are the same else False
     *
     * @param moduleCode
     * @param groupName
     * @return
     */
    public boolean sameGroupNameAndModule(String moduleCode, String groupName) {
        if (moduleCode.equals(module.getModuleCode().toString())
            && groupName.equalsIgnoreCase(getGroupName())) {
            return true;
        }
        return false;
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

    public ObservableList<LocalDate> getMeetingDates() {
        return FXCollections.observableArrayList(this.meetingTimes.stream().map(mt -> {
            int year = mt.getYear();
            int month = mt.getMonthValue();
            int dayOfMonth = mt.getDayOfMonth();
            return LocalDate.of(year, month, dayOfMonth);
        }).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getGroupName())
            .append("; Module: ")
            .append(getModule().getModuleCode());

        ObservableList<LocalDateTime> meetingTimes = getMeetingTimes();
        if (!meetingTimes.isEmpty()) {
            builder.append("; Meeting Times: ");
            meetingTimes.forEach(builder::append);
        }

        return builder.toString();
    }
}

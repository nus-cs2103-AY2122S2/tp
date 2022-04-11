package unibook.model.module.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unibook.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.model.module.exceptions.DuplicateMeetingTimeException;
import unibook.model.person.Student;
import unibook.model.person.exceptions.PersonNotFoundException;
import unibook.testutil.builders.GroupBuilder;
import unibook.testutil.builders.ModuleBuilder;
import unibook.testutil.builders.StudentBuilder;
import unibook.model.module.Module;

public class GroupTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(null));
    }

    @Test
    public void constructor_emptyGroupName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Group(""));
    }

    @Test
    public void isValidGroupName() {
        // null code
        assertThrows(NullPointerException.class, () -> Group.isValidName(null));

        // invalid code
        assertFalse(Group.isValidName(" ")); //Blank with just spaces
        //Cannot contain more than 51 characters
        assertFalse(Group.isValidName("gxxzqrpgsjbxbmqyxbzdyuscfkugjpapmkxnkkeymlbtqkbsidg"));

        // valid group names
        assertTrue(Group.isValidName("W17-1"));
        assertTrue(Group.isValidName("Study Group"));
        assertTrue(Group.isValidName("T12"));
        assertTrue(Group.isValidName("T8/W16"));
    }

    @Test
    public void add_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupBuilder().build().addMember(null));
    }

    @Test
    public void add_member_addsMember() {
        Student studentToAdd = new StudentBuilder().withName("new student").build();
        ObservableList<Student> expectedMembers = FXCollections.observableArrayList();
        expectedMembers.add(studentToAdd);
        Group expectedGroup = new GroupBuilder().withMembers(expectedMembers).build();
        Group group = new GroupBuilder().withMembers(FXCollections.observableArrayList()).build();
        group.addMember(studentToAdd);
        assertEquals(expectedGroup, group);
    }

    @Test
    public void remove_existingMember_removesMember() {
        ObservableList<Student> members = FXCollections.observableArrayList();
        Student student = new StudentBuilder().build();
        members.add(student);
        Group group = new GroupBuilder().withMembers(members).build();
        Group expectedGroup = new GroupBuilder().withMembers(FXCollections.observableArrayList()).build();
        group.removeMember(student);
        assertEquals(expectedGroup, group);
    }

    @Test
    public void remove_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupBuilder().build().removeMember(null));
    }

    @Test
    public void remove_memberDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> new GroupBuilder().build()
                .removeMember(new StudentBuilder().withName("ghost").build()));
    }

    @Test
    public void remove_studentsFromGroup_removesStudentFromGroup() {
        Group group = new GroupBuilder().build();
        Set<Group> groupsInStudent = new HashSet<>();
        groupsInStudent.add(group);

        //students to associate with the group
        Student student1 = new StudentBuilder().withName("A").withGroups(new HashSet<>(groupsInStudent)).build();
        Student student2 = new StudentBuilder().withName("B").withGroups(new HashSet<>(groupsInStudent)).build();

        //add the association from group to students
        group.addMember(student1);
        group.addMember(student2);

        group.removeStudentsFromThisGroup();

        //now both student1 and student2 should no longer have group in their group list
        assertTrue(student1.getGroups().isEmpty());
        assertTrue(student2.getGroups().isEmpty());
    }

    @Test
    public void add_nullMeetingTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupBuilder().build().addMeetingTime(null));
    }

    @Test
    public void add_duplicateMeetingTime_throwsDuplicateMeetingTimeException() {
        Group group = new GroupBuilder().build();
        LocalDateTime meetingTime = LocalDateTime.of(2022, 1, 1, 10, 0);
        ObservableList<LocalDateTime> meetingTimes = FXCollections.observableArrayList();
        meetingTimes.add(meetingTime);
        assertThrows(DuplicateMeetingTimeException.class, () -> new GroupBuilder().withMeetingTimes(meetingTimes)
                .build().addMeetingTime(meetingTime));
    }

    @Test
    public void add_meetingTime_addsMeetingTime() {
        Group group = new GroupBuilder().build();
        LocalDateTime meetingTime = LocalDateTime.of(2022, 1, 1, 10, 0);
        ObservableList<LocalDateTime> meetingTimes = FXCollections.observableArrayList();
        meetingTimes.add(meetingTime);
        group.addMeetingTime(meetingTime);
        Group expectedGroup = new GroupBuilder().withMeetingTimes(meetingTimes).build();
        assertEquals(group, expectedGroup);
    }

    @Test
    public void remove_meetingTime_removesMeetingTime() {
        LocalDateTime meetingTime1 = LocalDateTime.of(2022, 1, 1, 10, 0);
        LocalDateTime meetingTime2 = LocalDateTime.of(2022, 2, 1, 10, 0);
        ObservableList<LocalDateTime> meetingTimes = FXCollections.observableArrayList();
        meetingTimes.add(meetingTime1);
        meetingTimes.add(meetingTime2);
        Group group = new GroupBuilder().withMeetingTimes(meetingTimes).build();
        Group expectedGroup = new GroupBuilder().withMeetingTimes(FXCollections.observableArrayList()).build();

        //remove both meeting times one by one
        group.removeMeetingTime(1);
        group.removeMeetingTime(0);
        assertEquals(group, expectedGroup);
    }

    @Test
    public void test_sameGroupName_differentGroupName_returnsFalse() {
        Group group1 = new GroupBuilder().withName("A").build();
        Group group2 = new GroupBuilder().withName("B").build();
        assertTrue(!group1.sameGroupName(group2));
    }

    @Test
    public void test_sameGroupName_sameGroupName_returnsTrue() {
        Group group1 = new GroupBuilder().withName("A").build();
        Group group2 = new GroupBuilder().withName("A").build();
        Group group3 = new GroupBuilder().withName("a").build(); //same name, different case
        assertTrue(group1.sameGroupName(group2));
        assertTrue(group1.sameGroupName(group3));
    }

    @Test
    public void test_sameGroupNameAndModule_differentGroupNameOrModule_returnsFalse() {
        Module module1 = new ModuleBuilder().withModuleCode("M1").build();
        Module module2 = new ModuleBuilder().withModuleCode("M2").build();

        Group group = new GroupBuilder().withName("A").withModule(module1).build();

        //same group name, different module
        assertTrue(!group.sameGroupNameAndModule(module2.getModuleCode().toString(), "A"));
        //same module, different group name
        assertTrue(!group.sameGroupNameAndModule(module1.getModuleCode().toString(), "B"));
        //different moudle and group name
        assertTrue(!group.sameGroupNameAndModule(module2.getModuleCode().toString(), "B"));
        //same group name and module
        assertTrue(group.sameGroupNameAndModule(module1.getModuleCode().toString(), "A"));
    }

}

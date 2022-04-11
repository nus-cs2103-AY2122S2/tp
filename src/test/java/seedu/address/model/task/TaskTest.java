package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_PRESENTATION;
import static seedu.address.testutil.TypicalGroups.NUS_DATA_SCIENCE_SOCIETY;
import static seedu.address.testutil.TypicalGroups.NUS_FINTECH_SOCIETY;
import static seedu.address.testutil.TypicalTasks.MEETING;
import static seedu.address.testutil.TypicalTasks.PRESENTATION;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains unit tests for {@code Task}.
 */
public class TaskTest {
    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(MEETING.isSameTask(MEETING));

        // null -> returns false
        assertFalse(MEETING.isSameTask(null));

        // same name -> returns true
        Task editedMeeting = new TaskBuilder(MEETING).build();
        assertTrue(MEETING.isSameTask(editedMeeting));

        // different name -> returns false
        editedMeeting = new TaskBuilder(MEETING)
                .withTaskName(VALID_TASK_NAME_PRESENTATION).build();
        assertFalse(MEETING.isSameTask(editedMeeting));

        // group with same task -> returns true
        Group editedNusFintechSociety = new GroupBuilder(NUS_FINTECH_SOCIETY).build();
        editedNusFintechSociety.addTask(MEETING);
        assertTrue(MEETING.isSameTask(editedNusFintechSociety.getTaskList().getTask(MEETING)));

        // different group with same task -> returns true
        Group editedNusDataScienceSociety = new GroupBuilder(NUS_DATA_SCIENCE_SOCIETY).build();
        editedNusDataScienceSociety.addTask(MEETING);
        assertTrue(editedNusFintechSociety.getTaskList().getTask(MEETING).isSameTask(
                editedNusDataScienceSociety.getTaskList().getTask(MEETING)));

        // different group with different task -> returns false
        editedNusFintechSociety.addTask(PRESENTATION);
        assertFalse(editedNusFintechSociety.getTaskList().getTask(PRESENTATION).isSameTask(
                editedNusDataScienceSociety.getTaskList().getTask(MEETING)));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = MEETING + " ";
        editedMeeting = new TaskBuilder(MEETING).withTaskName(nameWithTrailingSpaces).build();
        assertFalse(MEETING.isSameTask(editedMeeting));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task meetingCopy = new TaskBuilder(MEETING).build();
        assertTrue(MEETING.equals(meetingCopy));

        // same object -> returns true
        assertTrue(MEETING.equals(MEETING));

        // null -> returns false
        assertFalse(MEETING.equals(null));

        // different type -> returns false
        assertFalse(MEETING.equals(5));

        // different task-> returns false
        assertFalse(MEETING.equals(PRESENTATION));

        // different taskName -> returns false
        Task editedMeeting = new TaskBuilder(MEETING)
                .withTaskName(VALID_TASK_NAME_PRESENTATION).build();
        assertFalse(MEETING.equals(editedMeeting));

        // task name differs in case, all other attributes same -> returns true
        Task editedMeetingLowerCase = new TaskBuilder(MEETING)
                .withTaskName(VALID_TASK_NAME_MEETING.toLowerCase()).build();
        assertTrue(MEETING.isSameTask(editedMeetingLowerCase));
    }
}

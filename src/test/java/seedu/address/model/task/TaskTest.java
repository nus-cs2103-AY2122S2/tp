package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalTasks.FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.SECOND_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        //same object -> returns true
        assertTrue(FIRST_TASK.isSameTask(FIRST_TASK));

        //null -> returns false
        assertFalse(FIRST_TASK.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedFirstTask = new TaskBuilder().withName("Meetings").withDate("09-01-2011")
                .withStartTime("00:00").withEndTime("01:00").withTags("hello").build();
        assertTrue(FIRST_TASK.isSameTask(editedFirstTask));

        // different name, all other attributes same -> returns false
        editedFirstTask = new TaskBuilder().withName("Swimming").withDate("09-10-2022")
                .withStartTime("09:00").withEndTime("10:00")
                .withTags("friends", "colleagues")
                .withPersons(ALICE.getName().fullName, BENSON.getName().fullName).build();
        assertFalse(FIRST_TASK.isSameTask(editedFirstTask));

        // name with trailing spaces, all other attributes same -> returns false
        editedFirstTask = new TaskBuilder().withName("Meetings  ").withDate("09-10-2022")
                .withStartTime("09:00").withEndTime("10:00")
                .withTags("friends", "colleagues")
                .withPersons(ALICE.getName().fullName, BENSON.getName().fullName).build();
        assertFalse(FIRST_TASK.isSameTask(editedFirstTask));
    }

    @Test
    public void hasDateTimeConflict() {

        //same object -> returns true
        assertTrue(FIRST_TASK.hasDateTimeConflict(FIRST_TASK));

        // non-conflicting time range, all attributes remains the same -> returns false
        Task editedFirstTask = new TaskBuilder().withName("Meetings").withDate("09-10-2022")
                .withStartTime("00:00").withEndTime("01:00")
                .withTags("friends", "colleagues")
                .withPersons(ALICE.getName().fullName, BENSON.getName().fullName).build();
        assertFalse(FIRST_TASK.hasDateTimeConflict(editedFirstTask));

        // different date, all attributes remains the same -> returns false
        editedFirstTask = new TaskBuilder().withName("Meetings").withDate("10-10-2022")
                .withStartTime("09:00").withEndTime("10:00")
                .withTags("friends", "colleagues")
                .withPersons(ALICE.getName().fullName, BENSON.getName().fullName).build();
        assertFalse(FIRST_TASK.hasDateTimeConflict(editedFirstTask));

        // conflicting time range v1, all attributes remains the same -> returns true
        editedFirstTask = new TaskBuilder().withName("Meetings").withDate("09-10-2022")
                .withStartTime("09:00").withEndTime("10:30")
                .withTags("friends", "colleagues")
                .withPersons(ALICE.getName().fullName, BENSON.getName().fullName).build();
        assertTrue(FIRST_TASK.hasDateTimeConflict(editedFirstTask));

        // conflicting time range v2, all attributes remains the same -> returns true
        editedFirstTask = new TaskBuilder().withName("Meetings").withDate("09-10-2022")
                .withStartTime("09:30").withEndTime("10:30")
                .withTags("friends", "colleagues")
                .withPersons(ALICE.getName().fullName, BENSON.getName().fullName).build();
        assertTrue(FIRST_TASK.hasDateTimeConflict(editedFirstTask));

        // edited start time is the same as the original end time, all attributes remains the same -> returns false
        editedFirstTask = new TaskBuilder().withName("Meetings").withDate("09-10-2022")
                .withStartTime("10:00").withEndTime("10:30")
                .withTags("friends", "colleagues")
                .withPersons(ALICE.getName().fullName, BENSON.getName().fullName).build();
        assertFalse(FIRST_TASK.hasDateTimeConflict(editedFirstTask));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Task firstTaskCopy = new TaskBuilder().withName("Meetings").withDate("09-10-2022")
                .withStartTime("09:00").withEndTime("10:00")
                .withTags("friends", "colleagues")
                .withPersons(ALICE.getName().fullName, BENSON.getName().fullName).build();

        assertTrue(FIRST_TASK.equals(firstTaskCopy));

        // same object -> returns true
        assertTrue(FIRST_TASK.equals(FIRST_TASK));

        // null -> returns false
        assertFalse(FIRST_TASK.equals(null));

        // different type -> returns false
        assertFalse(FIRST_TASK.equals(5));

        // different task -> returns false
        assertFalse(FIRST_TASK.equals(SECOND_TASK));

        // different name -> returns false
        Task editedFirstTask = new TaskBuilder(firstTaskCopy).withName("Swimming").build();
        assertFalse(FIRST_TASK.equals(editedFirstTask));

        // different date -> returns false
        editedFirstTask = new TaskBuilder(firstTaskCopy).withDate("01-10-2022").build();
        assertFalse(FIRST_TASK.equals(editedFirstTask));

        // different start time -> returns false
        editedFirstTask = new TaskBuilder(firstTaskCopy).withStartTime("22:00").build();
        assertFalse(FIRST_TASK.equals(editedFirstTask));

        // different end time -> returns false
        editedFirstTask = new TaskBuilder(firstTaskCopy).withEndTime("23:00").build();
        assertFalse(FIRST_TASK.equals(editedFirstTask));

        // different tags -> returns false
        editedFirstTask = new TaskBuilder(firstTaskCopy).withTags("neighbours").build();
        assertFalse(FIRST_TASK.equals(editedFirstTask));

        // different persons (represented by names) -> returns false
        editedFirstTask = new TaskBuilder(firstTaskCopy).withPersons("Alex").build();
        assertFalse(FIRST_TASK.equals(editedFirstTask));
    }
}

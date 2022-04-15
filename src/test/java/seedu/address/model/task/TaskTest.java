package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TEST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.CS2105_MIDTERM;
import static seedu.address.testutil.TypicalTasks.CS2107_TUTORIAL;

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
        // same object -> returns true
        assertTrue(CS2105_MIDTERM.isSameTask(CS2105_MIDTERM));

        // null -> returns false
        assertFalse(CS2105_MIDTERM.isSameTask(null));

        // same name, all other attributes different -> returns true
        Task editedMidtermTask = new TaskBuilder(CS2105_MIDTERM)
                .withDescription(VALID_DESCRIPTION_TUTORIAL)
                .withDeadline(VALID_DEADLINE_TUTORIAL)
                .withCompletionStatus(VALID_COMPLETION_STATUS_FALSE)
                .withTags(VALID_TAG_TEST).build();
        assertTrue(CS2105_MIDTERM.isSameTask(editedMidtermTask));

        // different name, all other attributes same -> returns false
        editedMidtermTask = new TaskBuilder(CS2105_MIDTERM).withName(VALID_NAME_TUTORIAL).build();
        assertFalse(CS2105_MIDTERM.isSameTask(editedMidtermTask));

        // name differs in case, all other attributes same -> returns false
        Task editedTutorialTask = new TaskBuilder(CS2107_TUTORIAL)
                .withName(VALID_NAME_TUTORIAL.toLowerCase())
                .build();
        assertFalse(CS2107_TUTORIAL.isSameTask(editedTutorialTask));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_TUTORIAL + " ";
        editedTutorialTask = new TaskBuilder(CS2107_TUTORIAL).withName(nameWithTrailingSpaces).build();
        assertFalse(CS2107_TUTORIAL.isSameTask(editedTutorialTask));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task midtermCopy = new TaskBuilder(CS2105_MIDTERM).build();
        assertTrue(CS2105_MIDTERM.equals(midtermCopy));

        // same object -> returns true
        assertTrue(CS2105_MIDTERM.equals(CS2105_MIDTERM));

        // null -> returns false
        assertFalse(CS2105_MIDTERM.equals(null));

        // different type -> returns false
        assertFalse(CS2105_MIDTERM.equals(5));

        // different task -> returns false
        assertFalse(CS2105_MIDTERM.equals(CS2107_TUTORIAL));

        // different name -> returns false
        Task editedMidtermTask = new TaskBuilder(CS2105_MIDTERM).withName(VALID_NAME_TUTORIAL).build();
        assertFalse(CS2105_MIDTERM.equals(editedMidtermTask));

        // different description -> returns false
        editedMidtermTask = new TaskBuilder(CS2105_MIDTERM).withDescription(VALID_DESCRIPTION_TUTORIAL).build();
        assertFalse(CS2105_MIDTERM.equals(editedMidtermTask));

        // different deadline -> returns false
        editedMidtermTask = new TaskBuilder(CS2105_MIDTERM).withDeadline(VALID_DEADLINE_TUTORIAL).build();
        assertFalse(CS2105_MIDTERM.equals(editedMidtermTask));

        // different completion status -> returns false
        editedMidtermTask = new TaskBuilder(CS2105_MIDTERM).withCompletionStatus(VALID_COMPLETION_STATUS_TRUE).build();
        assertFalse(CS2105_MIDTERM.equals(editedMidtermTask));

        // different tags -> returns false
        editedMidtermTask = new TaskBuilder(CS2105_MIDTERM).withTags(VALID_TAG_TEST).build();
        assertFalse(CS2105_MIDTERM.equals(editedMidtermTask));
    }
}

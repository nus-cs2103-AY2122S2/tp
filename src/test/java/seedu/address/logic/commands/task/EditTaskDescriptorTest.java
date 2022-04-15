package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.EditTaskDescriptor;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_TASK1);
        assertTrue(DESC_TASK1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TASK1.equals(DESC_TASK1));

        // null -> returns false
        assertFalse(DESC_TASK1.equals(null));

        // different types -> returns false
        assertFalse(DESC_TASK1.equals(5));

        // different values -> returns false
        assertFalse(DESC_TASK1.equals(DESC_TASK2));

        // different name -> returns false
        EditTaskDescriptor editedTask1 = new EditTaskDescriptorBuilder(DESC_TASK1).withName(VALID_NAME_TASK2).build();
        assertFalse(DESC_TASK1.equals(editedTask1));

        // different date -> returns false
        editedTask1 = new EditTaskDescriptorBuilder(DESC_TASK1).withDate(VALID_DATE_TASK2).build();
        assertFalse(DESC_TASK1.equals(editedTask1));

        // different start time -> returns false
        editedTask1 = new EditTaskDescriptorBuilder(DESC_TASK1).withStartTime(VALID_STARTTIME_TASK2).build();
        assertFalse(DESC_TASK1.equals(editedTask1));

        // different end time -> returns false
        editedTask1 = new EditTaskDescriptorBuilder(DESC_TASK1).withEndTime(VALID_ENDTIME_TASK2).build();
        assertFalse(DESC_TASK1.equals(editedTask1));

        // different tags -> returns false
        editedTask1 = new EditTaskDescriptorBuilder(DESC_TASK1).withTags(VALID_TAG_FRIEND).build();
        assertFalse(DESC_TASK1.equals(editedTask1));

        // different persons -> returns false
        editedTask1 = new EditTaskDescriptorBuilder(DESC_TASK1).withPersons(VALID_PERSON2).build();
        assertFalse(DESC_TASK1.equals(editedTask1));
    }
}

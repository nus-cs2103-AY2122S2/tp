package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LECTURE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.testutil.meeting.EditMeetingDescriptorBuilder;

public class EditMeetingDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        final EditMeetingDescriptor descriptorWithSameValues = new EditMeetingDescriptor(DESC_TUTORIAL);
        assertTrue(DESC_TUTORIAL.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TUTORIAL.equals(DESC_TUTORIAL));

        // null -> returns false
        assertFalse(DESC_TUTORIAL.equals(null));

        // different types -> returns false
        assertFalse(DESC_TUTORIAL.equals(5));

        // different values -> returns false
        assertFalse(DESC_TUTORIAL.equals(DESC_LECTURE));

        // different name -> returns false
        EditMeetingDescriptor editedTutorial = new EditMeetingDescriptorBuilder(DESC_TUTORIAL)
                .withName(VALID_NAME_LECTURE).build();
        assertFalse(DESC_TUTORIAL.equals(editedTutorial));

        // different url -> returns false
        editedTutorial = new EditMeetingDescriptorBuilder(DESC_TUTORIAL).withUrl(VALID_URL_LECTURE).build();
        assertFalse(DESC_TUTORIAL.equals(editedTutorial));

        // different datetime -> returns false
        editedTutorial = new EditMeetingDescriptorBuilder(DESC_TUTORIAL).withDateTime(VALID_DATETIME_LECTURE).build();
        assertFalse(DESC_TUTORIAL.equals(editedTutorial));

        // different duration -> returns false
        editedTutorial = new EditMeetingDescriptorBuilder(DESC_TUTORIAL).withDuration(VALID_DURATION_LECTURE).build();
        assertFalse(DESC_TUTORIAL.equals(editedTutorial));

        // different module -> returns false
        final Index firstModule = Index.fromZeroBased(0);
        editedTutorial = new EditMeetingDescriptorBuilder(DESC_TUTORIAL).withModule(firstModule).build();
        assertFalse(DESC_TUTORIAL.equals(editedTutorial));

        // different recurrence -> returns false
        editedTutorial = new EditMeetingDescriptorBuilder(DESC_TUTORIAL).withIsRecurring(VALID_RECURRING_LECTURE)
                .build();
        assertFalse(DESC_TUTORIAL.equals(editedTutorial));

        // different tags -> returns false
        editedTutorial = new EditMeetingDescriptorBuilder(DESC_TUTORIAL).withTags(VALID_TAG_LECTURE).build();
        assertFalse(DESC_TUTORIAL.equals(editedTutorial));
    }
}

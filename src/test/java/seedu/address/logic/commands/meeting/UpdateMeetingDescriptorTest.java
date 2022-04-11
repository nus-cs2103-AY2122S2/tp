package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_QUARTERLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGENDA_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDEE_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLACE_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.UpdateMeetingDescriptor;
import seedu.address.testutil.UpdateMeetingDescriptorBuilder;

public class UpdateMeetingDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        UpdateMeetingDescriptor descriptorWithSameValues = new UpdateMeetingDescriptor(DESC_QUARTERLY);
        assertTrue(DESC_QUARTERLY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_QUARTERLY.equals(DESC_QUARTERLY));

        // null -> returns false
        assertFalse(DESC_QUARTERLY.equals(null));

        // different types -> returns false
        assertFalse(DESC_QUARTERLY.equals(5));

        // different values -> returns false
        assertFalse(DESC_QUARTERLY.equals(DESC_PROJECT));

        // different agenda -> returns false
        UpdateMeetingDescriptor editedQuarterly = new UpdateMeetingDescriptorBuilder(DESC_QUARTERLY)
                .withAgenda(VALID_AGENDA_PROJECT).build();
        assertFalse(DESC_QUARTERLY.equals(editedQuarterly));

        // different meeting time -> returns false
        editedQuarterly = new UpdateMeetingDescriptorBuilder(DESC_QUARTERLY)
                .withTime(VALID_TIME_PROJECT).build();
        assertFalse(DESC_QUARTERLY.equals(editedQuarterly));

        // different meeting place -> returns false
        editedQuarterly = new UpdateMeetingDescriptorBuilder(DESC_QUARTERLY)
                .withPlace(VALID_PLACE_PROJECT).build();
        assertFalse(DESC_QUARTERLY.equals(editedQuarterly));

        // different attendees -> returns false
        editedQuarterly = new UpdateMeetingDescriptorBuilder(DESC_QUARTERLY)
                .withAttendees(VALID_ATTENDEE_PROJECT).build();
        assertFalse(DESC_QUARTERLY.equals(editedQuarterly));
    }
}

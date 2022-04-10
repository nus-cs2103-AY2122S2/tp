package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FELIX;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TED;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditAppointmentDescriptor descriptorWithSameValues = new EditAppointmentDescriptor(DESC_FELIX);
        assertTrue(DESC_FELIX.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FELIX.equals(DESC_FELIX));

        // null -> returns false
        assertFalse(DESC_FELIX.equals(null));

        // different types -> returns false
        assertFalse(DESC_FELIX.equals(5));

        // different values -> returns false
        assertFalse(DESC_FELIX.equals(DESC_TED));

        // different description -> returns false
        EditAppointmentDescriptor editedFelix = new EditAppointmentDescriptorBuilder(DESC_FELIX)
                .withDescription(VALID_DESCRIPTION_TED).build();
        assertFalse(DESC_FELIX.equals(editedFelix));

        // different datetime -> returns false
        editedFelix = new EditAppointmentDescriptorBuilder(DESC_FELIX).withDateTime(VALID_DATETIME_TED).build();
        assertFalse(DESC_FELIX.equals(editedFelix));
    }
}

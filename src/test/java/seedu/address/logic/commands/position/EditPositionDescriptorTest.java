package seedu.address.logic.commands.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_NAME_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_NAME_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_OPENINGS_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_EXPERIENCE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditPositionDescriptorBuilder;

public class EditPositionDescriptorTest {
    @Test
    public void equals() {
        // same value -> returns true
        EditPositionCommand.EditPositionDescriptor descriptorWithSameValues =
                new EditPositionCommand.EditPositionDescriptor(DESC_JR_SWE);
        assertTrue(DESC_JR_SWE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_JR_SWE.equals(DESC_JR_SWE));

        // different values -> returns false
        assertFalse(DESC_JR_SWE.equals(DESC_SR_FE_SWE));

        // null -> returns false
        assertFalse(DESC_JR_SWE.equals(null));

        // different type -> returns false
        assertFalse(DESC_JR_SWE.equals(1));

        // different position name -> returns false
        EditPositionCommand.EditPositionDescriptor editedJrSwe =
                new EditPositionDescriptorBuilder(DESC_JR_SWE).withPositionName(VALID_POSITION_NAME_SR_FE_SWE).build();
        assertFalse(DESC_JR_SWE.equals(editedJrSwe));

        // different description -> returns false
        editedJrSwe = new EditPositionDescriptorBuilder(DESC_JR_SWE)
                .withDescription(VALID_DESCRIPTION_NAME_SR_FE_SWE).build();
        assertFalse(DESC_JR_SWE.equals(editedJrSwe));

        // different number of openings -> returns false
        editedJrSwe = new EditPositionDescriptorBuilder(DESC_JR_SWE)
                .withDescription(VALID_POSITION_OPENINGS_SR_FE_SWE).build();
        assertFalse(DESC_JR_SWE.equals(editedJrSwe));

        // different requirements -> returns false
        editedJrSwe = new EditPositionDescriptorBuilder(DESC_JR_SWE)
                .withDescription(VALID_REQUIREMENT_EXPERIENCE).build();
        assertFalse(DESC_JR_SWE.equals(editedJrSwe));
    }
}

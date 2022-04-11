package manageezpz.logic.commands;

import static manageezpz.logic.commands.CommandTestUtil.DESC_AMY;
import static manageezpz.logic.commands.CommandTestUtil.DESC_BOB;
import static manageezpz.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static manageezpz.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static manageezpz.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.EditEmployeeCommand.EditEmployeeDescriptor;
import manageezpz.testutil.EditEmployeeDescriptorBuilder;

public class EditEmployeeDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEmployeeCommand.EditEmployeeDescriptor descriptorWithSameValues = new EditEmployeeDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditEmployeeDescriptor editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}

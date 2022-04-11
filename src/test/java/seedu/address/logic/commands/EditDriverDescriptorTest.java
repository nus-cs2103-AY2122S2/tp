package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_DRIVER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_DRIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditDriverDescriptorBuilder;

public class EditDriverDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditDriverCommand.EditDriverDescriptor descriptorWithSameValues =
                new EditDriverCommand.EditDriverDescriptor(DESC_AMY_DRIVER);
        assertTrue(DESC_AMY_DRIVER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY_DRIVER.equals(DESC_AMY_DRIVER));

        // null -> returns false
        assertFalse(DESC_AMY_DRIVER.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_DRIVER.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_DRIVER.equals(DESC_BOB_DRIVER));

        // different name -> returns false
        EditDriverCommand.EditDriverDescriptor editedAmy = new EditDriverDescriptorBuilder(DESC_AMY_DRIVER)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY_DRIVER.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditDriverDescriptorBuilder(DESC_AMY_DRIVER).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY_DRIVER.equals(editedAmy));

    }
}

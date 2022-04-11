package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditClientDescriptor;
import seedu.address.testutil.EditClientDescriptorBuilder;

public class EditClientDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditClientDescriptor descriptorWithSameValues = new EditClientDescriptor(DESC_APPLE);
        assertTrue(DESC_APPLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPLE.equals(DESC_APPLE));

        // null -> returns false
        assertFalse(DESC_APPLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPLE.equals(DESC_BURGER));

        // different name -> returns false
        EditClientDescriptor editedApple = new EditClientDescriptorBuilder(DESC_APPLE)
                .withName(VALID_NAME_BURGER).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different phone -> returns false
        editedApple = new EditClientDescriptorBuilder(DESC_APPLE).withPhone(VALID_PHONE_BURGER).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different email -> returns false
        editedApple = new EditClientDescriptorBuilder(DESC_APPLE).withEmail(VALID_EMAIL_BURGER).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different address -> returns false
        editedApple = new EditClientDescriptorBuilder(DESC_APPLE).withAddress(VALID_ADDRESS_BURGER).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new EditClientDescriptorBuilder(DESC_APPLE).withTags(VALID_TAG_TECH).build();
        assertFalse(DESC_APPLE.equals(editedApple));
    }
}

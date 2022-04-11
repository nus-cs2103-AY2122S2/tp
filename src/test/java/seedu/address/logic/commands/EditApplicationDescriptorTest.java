package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOCAL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class EditApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditApplicationDescriptor descriptorWithSameValues = new EditApplicationDescriptor(DESC_SHOPEE);
        assertTrue(DESC_SHOPEE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_SHOPEE.equals(DESC_SHOPEE));

        // null -> returns false
        assertFalse(DESC_SHOPEE.equals(null));

        // different types -> returns false
        assertFalse(DESC_SHOPEE.equals(5));

        // different values -> returns false
        assertFalse(DESC_SHOPEE.equals(DESC_GARENA));

        // different name -> returns false
        EditApplicationDescriptor editedShopee =
                new EditApplicationDescriptorBuilder(DESC_SHOPEE).withName(VALID_NAME_GARENA).build();
        assertFalse(DESC_SHOPEE.equals(editedShopee));

        // different phone -> returns false
        editedShopee = new EditApplicationDescriptorBuilder(DESC_SHOPEE).withPhone(VALID_PHONE_GARENA).build();
        assertFalse(DESC_SHOPEE.equals(editedShopee));

        // different email -> returns false
        editedShopee = new EditApplicationDescriptorBuilder(DESC_SHOPEE).withEmail(VALID_EMAIL_GARENA).build();
        assertFalse(DESC_SHOPEE.equals(editedShopee));

        // different address -> returns false
        editedShopee = new EditApplicationDescriptorBuilder(DESC_SHOPEE).withAddress(VALID_ADDRESS_GARENA).build();
        assertFalse(DESC_SHOPEE.equals(editedShopee));

        // different tags -> returns false
        editedShopee = new EditApplicationDescriptorBuilder(DESC_SHOPEE).withTags(VALID_TAG_LOCAL).build();
        assertFalse(DESC_SHOPEE.equals(editedShopee));
    }
}

package seedu.ibook.logic.commands.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.commands.CommandTestUtil.ITEM_DESCRIPTOR_A;
import static seedu.ibook.logic.commands.CommandTestUtil.ITEM_DESCRIPTOR_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_QUANTITY_B;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.item.UpdateItemCommand.UpdateItemDescriptor;
import seedu.ibook.testutil.UpdateItemDescriptorBuilder;

public class UpdateItemDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        UpdateItemDescriptor descriptorWithSameValues = new UpdateItemDescriptor(ITEM_DESCRIPTOR_A);
        assertTrue(ITEM_DESCRIPTOR_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(ITEM_DESCRIPTOR_A.equals(ITEM_DESCRIPTOR_A));

        // null -> returns false
        assertFalse(ITEM_DESCRIPTOR_A.equals(null));

        // different types -> returns false
        assertFalse(ITEM_DESCRIPTOR_A.equals(5));

        // different values -> returns false
        assertFalse(ITEM_DESCRIPTOR_A.equals(ITEM_DESCRIPTOR_B));

        // different expiry date -> returns false
        UpdateItemDescriptor updatedA = new UpdateItemDescriptorBuilder(ITEM_DESCRIPTOR_A)
                .withExpiryDate(VALID_EXPIRY_DATE_B).build();
        assertFalse(ITEM_DESCRIPTOR_A.equals(updatedA));

        // different quantity -> returns false
        updatedA = new UpdateItemDescriptorBuilder(ITEM_DESCRIPTOR_A)
                .withQuantity(VALID_QUANTITY_B).build();
        assertFalse(ITEM_DESCRIPTOR_A.equals(updatedA));
    }

}

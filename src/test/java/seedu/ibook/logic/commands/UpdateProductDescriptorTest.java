package seedu.ibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.commands.CommandTestUtil.DESC_A;
import static seedu.ibook.logic.commands.CommandTestUtil.DESC_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.testutil.UpdateProductDescriptorBuilder;

public class UpdateProductDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        UpdateProductDescriptor descriptorWithSameValues = new UpdateProductDescriptor(DESC_A);
        assertTrue(DESC_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_A.equals(DESC_A));

        // null -> returns false
        assertFalse(DESC_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_A.equals(5));

        // different values -> returns false
        assertFalse(DESC_A.equals(DESC_B));

        // different name -> returns false
        UpdateProductDescriptor editedA = new UpdateProductDescriptorBuilder(DESC_A).withName(VALID_NAME_B).build();
        assertFalse(DESC_A.equals(editedA));

        // different category -> returns false
        editedA = new UpdateProductDescriptorBuilder(DESC_A).withCategory(VALID_CATEGORY_B).build();
        assertFalse(DESC_A.equals(editedA));

        // different description -> returns false
        editedA = new UpdateProductDescriptorBuilder(DESC_A).withDescription(VALID_DESCRIPTION_B).build();
        assertFalse(DESC_A.equals(editedA));

        // different price -> returns false
        editedA = new UpdateProductDescriptorBuilder(DESC_A).withPrice(VALID_PRICE_B).build();
        assertFalse(DESC_A.equals(editedA));
    }

}

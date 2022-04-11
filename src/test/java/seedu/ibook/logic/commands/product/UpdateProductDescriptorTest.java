package seedu.ibook.logic.commands.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.commands.CommandTestUtil.PRODUCT_DESCRIPTOR_A;
import static seedu.ibook.logic.commands.CommandTestUtil.PRODUCT_DESCRIPTOR_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DISCOUNT_RATE_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DISCOUNT_START_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.product.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.testutil.UpdateProductDescriptorBuilder;

public class UpdateProductDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        UpdateProductDescriptor descriptorWithSameValues = new UpdateProductDescriptor(PRODUCT_DESCRIPTOR_A);
        assertTrue(PRODUCT_DESCRIPTOR_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(PRODUCT_DESCRIPTOR_A.equals(PRODUCT_DESCRIPTOR_A));

        // null -> returns false
        assertFalse(PRODUCT_DESCRIPTOR_A.equals(null));

        // different types -> returns false
        assertFalse(PRODUCT_DESCRIPTOR_A.equals(5));

        // different values -> returns false
        assertFalse(PRODUCT_DESCRIPTOR_A.equals(PRODUCT_DESCRIPTOR_B));

        // different name -> returns false
        UpdateProductDescriptor editedA = new UpdateProductDescriptorBuilder(PRODUCT_DESCRIPTOR_A)
                .withName(VALID_NAME_B).build();
        assertFalse(PRODUCT_DESCRIPTOR_A.equals(editedA));

        // different category -> returns false
        editedA = new UpdateProductDescriptorBuilder(PRODUCT_DESCRIPTOR_A).withCategory(VALID_CATEGORY_B).build();
        assertFalse(PRODUCT_DESCRIPTOR_A.equals(editedA));

        // different description -> returns false
        editedA = new UpdateProductDescriptorBuilder(PRODUCT_DESCRIPTOR_A).withDescription(VALID_DESCRIPTION_B).build();
        assertFalse(PRODUCT_DESCRIPTOR_A.equals(editedA));

        // different price -> returns false
        editedA = new UpdateProductDescriptorBuilder(PRODUCT_DESCRIPTOR_A).withPrice(VALID_PRICE_B).build();
        assertFalse(PRODUCT_DESCRIPTOR_A.equals(editedA));

        // different discount rate -> returns false
        editedA = new UpdateProductDescriptorBuilder(PRODUCT_DESCRIPTOR_A)
                .withDiscountRate(VALID_DISCOUNT_RATE_B).build();
        assertFalse(PRODUCT_DESCRIPTOR_A.equals(editedA));

        // different discount start -> returns false
        editedA = new UpdateProductDescriptorBuilder(PRODUCT_DESCRIPTOR_A)
                .withDiscountStart(VALID_DISCOUNT_START_B).build();
        assertFalse(PRODUCT_DESCRIPTOR_A.equals(editedA));
    }

}

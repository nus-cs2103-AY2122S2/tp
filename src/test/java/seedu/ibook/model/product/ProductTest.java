package seedu.ibook.model.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.ibook.testutil.TypicalItems.Q10_2022_03_01;
import static seedu.ibook.testutil.TypicalItems.Q5_2020_01_01;
import static seedu.ibook.testutil.TypicalItems.Q5_2022_03_01;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.exceptions.ElementNotFoundException;
import seedu.ibook.model.item.Quantity;
import seedu.ibook.testutil.ProductBuilder;

class ProductTest {

    private final Product testProduct = new ProductBuilder(PRODUCT_A).build();

    @Test
    void getTotalQuantity_noItems_returnZero() {
        assertEquals(new Quantity(0), testProduct.getTotalQuantity());
    }

    @Test
    void getTotalQuantity_multipleQuantities_getSum() {
        testProduct.addItem(Q5_2022_03_01);
        assertEquals(new Quantity(5), testProduct.getTotalQuantity());

        testProduct.addItem(Q10_2022_03_01);
        assertEquals(new Quantity(15), testProduct.getTotalQuantity());
    }

    @Test
    void addItem_singleItem_itemAdded() {
        testProduct.addItem(Q5_2022_03_01);
        assertEquals(1, testProduct.getItems().size());
    }

    @Test
    void addItem_multipleDifferentItems_itemAdded() {
        testProduct.addItem(Q5_2022_03_01);
        testProduct.addItem(Q5_2020_01_01);
        assertEquals(2, testProduct.getItems().size());
    }

    @Test
    void addItem_multipleSameItems_itemCombined() {
        testProduct.addItem(Q5_2022_03_01);
        testProduct.addItem(Q5_2022_03_01);
        assertEquals(1, testProduct.getItems().size());
    }

    @Test
    void removeItem_itemDoesNotExist_throwElementNotFoundException() {
        assertThrows(ElementNotFoundException.class, () -> testProduct.removeItem(Q5_2020_01_01));
    }
}

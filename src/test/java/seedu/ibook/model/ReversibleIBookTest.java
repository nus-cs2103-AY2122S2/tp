package seedu.ibook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.exceptions.DuplicateElementException;
import seedu.ibook.model.IBookTest.IBookStub;
import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ProductBuilder;

public class ReversibleIBookTest {
    private ReversibleIBook reversibleIBook = new ReversibleIBook(new IBook());

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), reversibleIBook.getProductList());
    }

    @Test
    public void reversibleResetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reversibleIBook.reversibleResetData(null));
    }

    @Test
    public void reversibleResetData_withValidReadOnlyIBook_replacesData() {
        IBook newData = getTypicalIBook();
        reversibleIBook.reversibleResetData(newData);
        assertEquals(newData, reversibleIBook);
    }

    @Test
    public void reversibleResetData_withDuplicateProducts_throwsDuplicateProductException() {
        // Two products with the same identity fields
        Product updatedProduct = new ProductBuilder(PRODUCT_A)
                .withPrice(VALID_PRICE_B)
                .withDescription(VALID_DESCRIPTION_B)
                .build();
        List<Product> newProducts = Arrays.asList(PRODUCT_A, updatedProduct);
        IBookStub newData = new IBookStub(newProducts);

        assertThrows(DuplicateElementException.class, () -> reversibleIBook.reversibleResetData(newData));
    }

    @Test
    public void undoRedo() {
        reversibleIBook = new ReversibleIBook(new IBook());
        ReversibleIBook reversibleIBookCopy = new ReversibleIBook(new IBook());
        assertFalse(reversibleIBook.canUndo());
        assertFalse(reversibleIBook.canRedo());

        reversibleIBook.reversibleAddProduct(PRODUCT_A);
        reversibleIBook.saveChanges();
        reversibleIBookCopy.reversibleAddProduct(PRODUCT_A);
        reversibleIBookCopy.saveChanges();

        assertTrue(reversibleIBook.canUndo());
        assertFalse(reversibleIBook.canRedo());

        reversibleIBook.undo();
        assertFalse(reversibleIBook.canUndo());
        assertTrue(reversibleIBook.canRedo());
        assertNotEquals(reversibleIBook, reversibleIBookCopy);

        reversibleIBook.redo();
        assertEquals(reversibleIBook, reversibleIBookCopy);

        reversibleIBook.reversibleAddProduct(PRODUCT_B);
        reversibleIBook.saveChanges();
        assertNotEquals(reversibleIBook, reversibleIBookCopy);

        reversibleIBook.undo();
        assertEquals(reversibleIBook, reversibleIBookCopy);

        reversibleIBook.undo();
        reversibleIBook.addProduct(PRODUCT_B);
        reversibleIBook.saveChanges();
        assertNotEquals(reversibleIBook, reversibleIBookCopy);
    }

}

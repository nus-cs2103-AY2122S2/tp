package seedu.ibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.commands.CommandTestUtil.DESC_A;
import static seedu.ibook.logic.commands.CommandTestUtil.DESC_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.commons.core.index.Index;
import seedu.ibook.logic.commands.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.model.IBook;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ProductBuilder;
import seedu.ibook.testutil.UpdateProductDescriptorBuilder;

public class UpdateCommandTest {

    private Model model = new ModelManager(getTypicalIBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Product updatedProduct = new ProductBuilder().build();
        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder(updatedProduct).build();
        UpdateCommand updateCommand = new UpdateCommand(INDEX_FIRST_PRODUCT, descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PRODUCT_SUCCESS, updatedProduct);

        Model expectedModel = new ModelManager(new IBook(model.getIBook()), new UserPrefs());
        expectedModel.setProduct(model.getFilteredProductList().get(0), updatedProduct);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastProduct = Index.fromOneBased(model.getFilteredProductList().size());
        Product lastProduct = model.getFilteredProductList().get(indexLastProduct.getZeroBased());

        ProductBuilder productInList = new ProductBuilder(lastProduct);
        Product updatedProduct = productInList.withName(VALID_NAME_B).withCategory(VALID_CATEGORY_B)
                .withPrice(VALID_PRICE_B).build();

        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder().withName(VALID_NAME_B)
                .withCategory(VALID_CATEGORY_B).withPrice(VALID_PRICE_B).build();
        UpdateCommand updateCommand = new UpdateCommand(indexLastProduct, descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PRODUCT_SUCCESS, updatedProduct);

        Model expectedModel = new ModelManager(new IBook(model.getIBook()), new UserPrefs());
        expectedModel.setProduct(lastProduct, updatedProduct);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        UpdateCommand updateCommand = new UpdateCommand(INDEX_FIRST_PRODUCT, new UpdateProductDescriptor());
        Product updatedProduct = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PRODUCT_SUCCESS, updatedProduct);

        Model expectedModel = new ModelManager(new IBook(model.getIBook()), new UserPrefs());

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Product productInFilteredList = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        Product updatedProduct = new ProductBuilder(productInFilteredList).withName(VALID_NAME_B).build();
        UpdateCommand updateCommand = new UpdateCommand(INDEX_FIRST_PRODUCT,
                new UpdateProductDescriptorBuilder().withName(VALID_NAME_B).build());

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PRODUCT_SUCCESS, updatedProduct);

        Model expectedModel = new ModelManager(new IBook(model.getIBook()), new UserPrefs());
        expectedModel.setProduct(model.getFilteredProductList().get(0), updatedProduct);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateProductUnfilteredList_failure() {
        Product firstProduct = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder(firstProduct).build();
        UpdateCommand updateCommand = new UpdateCommand(INDEX_SECOND_PRODUCT, descriptor);

        assertCommandFailure(updateCommand, model, UpdateCommand.MESSAGE_DUPLICATE_PRODUCT);
    }

    @Test
    public void execute_duplicateProductFilteredList_failure() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        // update product in filtered list into a duplicate in iBook
        Product productInList = model.getIBook().getProductList().get(INDEX_SECOND_PRODUCT.getZeroBased());
        UpdateCommand updateCommand = new UpdateCommand(INDEX_FIRST_PRODUCT,
                new UpdateProductDescriptorBuilder(productInList).build());

        assertCommandFailure(updateCommand, model, UpdateCommand.MESSAGE_DUPLICATE_PRODUCT);
    }

    @Test
    public void execute_invalidProductIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProductList().size() + 1);
        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder().withName(VALID_NAME_B).build();
        UpdateCommand updateCommand = new UpdateCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(updateCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    /**
     * Update filtered list where index is larger than size of filtered list,
     * but smaller than size of ibook
     */
    @Test
    public void execute_invalidProductIndexFilteredList_failure() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);
        Index outOfBoundIndex = INDEX_SECOND_PRODUCT;
        // ensures that outOfBoundIndex is still in bounds of iBook list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getIBook().getProductList().size());

        UpdateCommand updateCommand = new UpdateCommand(outOfBoundIndex,
                new UpdateProductDescriptorBuilder().withName(VALID_NAME_B).build());

        assertCommandFailure(updateCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final UpdateCommand standardCommand = new UpdateCommand(INDEX_FIRST_PRODUCT, DESC_A);

        // same values -> returns true
        UpdateProductDescriptor copyDescriptor = new UpdateProductDescriptor(DESC_A);
        UpdateCommand commandWithSameValues = new UpdateCommand(INDEX_FIRST_PRODUCT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_SECOND_PRODUCT, DESC_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_FIRST_PRODUCT, DESC_B)));
    }

}

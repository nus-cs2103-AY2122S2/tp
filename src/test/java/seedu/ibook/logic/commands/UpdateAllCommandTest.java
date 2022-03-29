package seedu.ibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.commands.CommandTestUtil.DESC_A;
import static seedu.ibook.logic.commands.CommandTestUtil.DESC_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.model.IBook;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ProductBuilder;
import seedu.ibook.testutil.UpdateProductDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAllCommand}.
 */
public class UpdateAllCommandTest {

    private Model model = new ModelManager(getTypicalIBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedSingleProductUnfilteredList_success() {
        Model originalModel = new ModelManager(new IBook(), new UserPrefs());
        originalModel.addProduct(PRODUCT_A);

        Product updatedProduct = new ProductBuilder().build();
        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder(updatedProduct).build();
        UpdateAllCommand updateAllCommand = new UpdateAllCommand(descriptor);

        String expectedMessage = UpdateAllCommand.MESSAGE_UPDATE_ALL_PRODUCT_SUCCESS
                + String.format(UpdateAllCommand.MESSAGE_UPDATED_PRODUCT, updatedProduct);

        Model expectedModel = new ModelManager(new IBook(originalModel.getIBook()), new UserPrefs());
        expectedModel.setProduct(originalModel.getFilteredProductList().get(0), updatedProduct);

        assertCommandSuccess(updateAllCommand, originalModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedSingleProductFilteredList_success() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Product updatedProduct = new ProductBuilder().build();
        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder(updatedProduct).build();
        UpdateAllCommand updateAllCommand = new UpdateAllCommand(descriptor);

        String expectedMessage = UpdateAllCommand.MESSAGE_UPDATE_ALL_PRODUCT_SUCCESS
                + String.format(UpdateAllCommand.MESSAGE_UPDATED_PRODUCT, updatedProduct);

        Model expectedModel = new ModelManager(new IBook(model.getIBook()), new UserPrefs());
        expectedModel.setProduct(model.getFilteredProductList().get(0), updatedProduct);

        assertCommandSuccess(updateAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Product productInFilteredList = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        Product updatedProduct = new ProductBuilder(productInFilteredList).withName(VALID_NAME_B).build();
        UpdateAllCommand updateAllCommand = new UpdateAllCommand(
                new UpdateProductDescriptorBuilder().withName(VALID_NAME_B).build());

        String expectedMessage = UpdateAllCommand.MESSAGE_UPDATE_ALL_PRODUCT_SUCCESS
                + String.format(UpdateAllCommand.MESSAGE_UPDATED_PRODUCT, updatedProduct);

        Model expectedModel = new ModelManager(new IBook(model.getIBook()), new UserPrefs());
        expectedModel.setProduct(model.getFilteredProductList().get(0), updatedProduct);

        assertCommandSuccess(updateAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateProductUnfilteredList_failure() {
        model.clearProductFilters();

        Product firstProduct = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder(firstProduct).build();
        UpdateAllCommand updateAllCommand = new UpdateAllCommand(descriptor);

        assertCommandFailure(updateAllCommand, model, UpdateAllCommand.MESSAGE_DUPLICATE_PRODUCT);
    }

    @Test
    public void execute_duplicateProductFilteredList_failure() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        // update product in filtered list into a duplicate in iBook
        Product productInList = model.getIBook().getProductList().get(INDEX_SECOND_PRODUCT.getZeroBased());
        UpdateAllCommand updateAllCommand = new UpdateAllCommand(
                new UpdateProductDescriptorBuilder(productInList).build());

        assertCommandFailure(updateAllCommand, model, UpdateAllCommand.MESSAGE_DUPLICATE_PRODUCT);
    }

    @Test
    public void execute_emptyFilteredList_failure() {
        model.updateProductFilters(unused -> false);

        UpdateAllCommand updateAllCommand = new UpdateAllCommand(
                new UpdateProductDescriptorBuilder().withDescription(VALID_DESCRIPTION_B).build());

        assertCommandFailure(updateAllCommand, model, UpdateAllCommand.MESSAGE_NO_PRODUCT_TO_UPDATE);
    }

    @Test
    public void equals() {
        final UpdateAllCommand standardCommand = new UpdateAllCommand(DESC_A);

        // same values -> returns true
        UpdateProductDescriptor copyDescriptor = new UpdateProductDescriptor(DESC_A);
        UpdateAllCommand commandWithSameValues = new UpdateAllCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_FIRST_PRODUCT, DESC_B)));
    }
}

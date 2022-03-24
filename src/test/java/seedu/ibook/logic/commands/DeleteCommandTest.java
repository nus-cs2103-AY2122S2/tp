package seedu.ibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.logic.commands.CommandTestUtil.showProductAtIndex;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.commons.core.index.Index;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.filters.ProductFilter;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalIBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Product productToDelete = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PRODUCT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete);

        ModelManager expectedModel = new ModelManager(model.getIBook(), new UserPrefs());
        expectedModel.deleteProduct(productToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProductList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Product productToDelete = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PRODUCT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PRODUCT_SUCCESS, productToDelete);

        Model expectedModel = new ModelManager(model.getIBook(), new UserPrefs());
        expectedModel.deleteProduct(productToDelete);
        showNoProduct(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProductAtIndex(model, INDEX_FIRST_PRODUCT);

        Index outOfBoundIndex = INDEX_SECOND_PRODUCT;
        // ensures that outOfBoundIndex is still in bounds of ibook list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getIBook().getProductList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PRODUCT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PRODUCT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PRODUCT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different product -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */

    private void showNoProduct(Model model) {
        model.updateProductFilters(new ProductFilter() {
            @Override
            public boolean test(Product other) {
                return false;
            }
        });

        assertTrue(model.getFilteredProductList().isEmpty());
    }
}

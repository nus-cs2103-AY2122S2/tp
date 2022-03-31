package seedu.ibook.logic.commands.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT_FIRST_ITEM;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT_SECOND_ITEM;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBookWithItems;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;

class DeleteItemCommandTest {

    private final Model model = new ModelManager(getTypicalIBookWithItems(), new UserPrefs());

    @Test
    void execute_validIndexUnfilteredList_success() throws CommandException {
        Item itemToDelete = model.getItem(INDEX_FIRST_PRODUCT_FIRST_ITEM);
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(INDEX_FIRST_PRODUCT_FIRST_ITEM);

        String expectedMessage = String.format(DeleteItemCommand.MESSAGE_DELETE_ITEM_SUCCESS, itemToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalIBookWithItems(), new UserPrefs());
        Product targetProduct = expectedModel.getProduct(INDEX_FIRST_PRODUCT);
        expectedModel.deleteItem(targetProduct, itemToDelete);

        assertCommandSuccess(deleteItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProductIndexFilteredList_throwsCommandException() {
        CompoundIndex outOfBoundIndex = CompoundIndex.fromOneBased(model.getFilteredProductList().size() + 1, 1);
        DeleteItemCommand deleteCommand = new DeleteItemCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PRODUCT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidItemIndexFilteredList_throwsCommandException() throws CommandException {
        CompoundIndex outOfBoundIndex =
            CompoundIndex.fromOneBased(1, model.getProduct(INDEX_FIRST_PRODUCT).getFilteredItems().size() + 1);
        DeleteItemCommand deleteCommand = new DeleteItemCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteItemCommand deleteFirstCommand = new DeleteItemCommand(INDEX_FIRST_PRODUCT_FIRST_ITEM);
        DeleteItemCommand deleteSecondCommand = new DeleteItemCommand(INDEX_FIRST_PRODUCT_SECOND_ITEM);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteItemCommand deleteFirstCommandCopy = new DeleteItemCommand(INDEX_FIRST_PRODUCT_FIRST_ITEM);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different product -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }
}

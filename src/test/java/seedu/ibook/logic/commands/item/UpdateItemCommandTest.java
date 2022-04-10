package seedu.ibook.logic.commands.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.ibook.logic.commands.CommandTestUtil.ITEM_DESCRIPTOR_A;
import static seedu.ibook.logic.commands.CommandTestUtil.ITEM_DESCRIPTOR_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_B;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.ibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.ibook.logic.commands.item.UpdateItemCommand.UpdateItemDescriptor;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT_FIRST_ITEM;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT_SECOND_ITEM;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT_FIRST_ITEM;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBookWithItems;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.logic.commands.ClearCommand;
import seedu.ibook.model.IBook;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.item.Quantity;
import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ItemBuilder;
import seedu.ibook.testutil.UpdateItemDescriptorBuilder;

class UpdateItemCommandTest {

    private final Model model = new ModelManager(getTypicalIBookWithItems(), new UserPrefs());

    @Test
    void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UpdateItemCommand(INDEX_FIRST_PRODUCT_FIRST_ITEM, null));
        assertThrows(NullPointerException.class, () -> new UpdateItemCommand(null, new UpdateItemDescriptor()));
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Product product = model.getFilteredProductList().get(0);

        Item updatedItem = new ItemBuilder().build(product);
        UpdateItemDescriptor descriptor = new UpdateItemDescriptorBuilder(updatedItem).build();
        UpdateItemCommand updateItemCommand = new UpdateItemCommand(INDEX_FIRST_PRODUCT_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(
                UpdateItemCommand.MESSAGE_UPDATE_ITEM_SUCCESS, product.getName(), updatedItem);

        Model expectedModel = new ModelManager(new IBook(model.getIBook()), new UserPrefs());
        expectedModel.updateItem(product, product.getFilteredItems().get(0), updatedItem);

        assertCommandSuccess(updateItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Product product = model.getFilteredProductList().get(0);

        Item updatedItem = new ItemBuilder().withExpiryDate(VALID_EXPIRY_DATE_B).build(product);
        UpdateItemDescriptor descriptor = new UpdateItemDescriptorBuilder(updatedItem).build();
        UpdateItemCommand updateItemCommand = new UpdateItemCommand(INDEX_FIRST_PRODUCT_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(
                UpdateItemCommand.MESSAGE_UPDATE_ITEM_SUCCESS, product.getName(), updatedItem);

        Model expectedModel = new ModelManager(new IBook(model.getIBook()), new UserPrefs());
        expectedModel.updateItem(product, product.getFilteredItems().get(0), updatedItem);

        assertCommandSuccess(updateItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        UpdateItemCommand updateItemCommand = new UpdateItemCommand(
                INDEX_FIRST_PRODUCT_FIRST_ITEM, new UpdateItemDescriptor());
        Product product = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        Item updatedItem = product.getFilteredItems().get(0);

        String expectedMessage = String.format(
                UpdateItemCommand.MESSAGE_UPDATE_ITEM_SUCCESS, product.getName(), updatedItem);

        Model expectedModel = new ModelManager(new IBook(model.getIBook()), new UserPrefs());

        assertCommandSuccess(updateItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidItemIndexUnfilteredList_failure() {
        CompoundIndex outOfBoundCompoundIndex = CompoundIndex.fromOneBased(1,
                model.getFilteredProductList().get(0).getFilteredItems().size() + 1);
        UpdateItemDescriptor descriptor = new UpdateItemDescriptorBuilder().withExpiryDate(VALID_EXPIRY_DATE_A).build();
        UpdateItemCommand updateItemCommand = new UpdateItemCommand(outOfBoundCompoundIndex, descriptor);

        assertCommandFailure(updateItemCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_excessQuantityUnfilteredList_failure() {
        Product firstProduct = model.getFilteredProductList().get(INDEX_FIRST_PRODUCT.getZeroBased());
        Item firstItemInProduct = firstProduct.getFilteredItems().get(0);
        Item secondItemInProduct = firstProduct.getFilteredItems().get(1);

        UpdateItemDescriptor descriptor = new UpdateItemDescriptorBuilder(secondItemInProduct)
                .withExpiryDate(firstItemInProduct.getExpiryDate().expiryDate.toString())
                .withQuantity(String.valueOf(Quantity.MAX_QUANTITY - 1))
                .build();
        UpdateItemCommand updateItemCommand = new UpdateItemCommand(INDEX_FIRST_PRODUCT_SECOND_ITEM, descriptor);

        assertCommandFailure(updateItemCommand, model, UpdateItemCommand.MESSAGE_EXCESS_QUANTITY);
    }

    @Test
    void equals() {
        final UpdateItemCommand standardCommand = new UpdateItemCommand(
                INDEX_FIRST_PRODUCT_FIRST_ITEM, ITEM_DESCRIPTOR_A);

        // same values -> returns true
        UpdateItemDescriptor copyDescriptor = new UpdateItemDescriptor(ITEM_DESCRIPTOR_A);
        UpdateItemCommand commandWithSameValues = new UpdateItemCommand(
                INDEX_FIRST_PRODUCT_FIRST_ITEM, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new UpdateItemCommand(INDEX_SECOND_PRODUCT_FIRST_ITEM, ITEM_DESCRIPTOR_A));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new UpdateItemCommand(INDEX_FIRST_PRODUCT_FIRST_ITEM, ITEM_DESCRIPTOR_B));
    }
}

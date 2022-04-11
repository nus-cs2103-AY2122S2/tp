package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLLECTIONTYPE_BOB_TYPE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELIVERYDATETIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertOrderCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.address.testutil.TypicalOrders.getTypicalAddressBookOrders;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditOrderCommand.EditOrderDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.EditOrderDescriptorBuilder;
import seedu.address.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditOrderCommand.
 */
public class EditOrderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());
    @Test
    public void execute_detailsFieldSpecifiedUnfilteredList_success() {
        Order editedOrder = new OrderBuilder(model.getFilteredOrderList().get(0))
                .withDetails("1: Choc Cake").build(); // Creates the order in orderbuilder
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder(editedOrder).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_ORDER, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertOrderCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastOrder = Index.fromOneBased(model.getFilteredOrderList().size());
        Order lastOrder = model.getFilteredOrderList().get(indexLastOrder.getZeroBased());

        OrderBuilder orderInList = new OrderBuilder(lastOrder);

        Order editedOrder = orderInList
                .withDetails(VALID_DETAILS_BOB).withDeliveryDateTime(VALID_DELIVERYDATETIME_BOB)
                .withCollectionType(VALID_COLLECTIONTYPE_BOB_TYPE).build();

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withDetails(VALID_DETAILS_BOB)
                .withDeliveryDateTime(VALID_DELIVERYDATETIME_BOB)
                .withCollectionType(VALID_COLLECTIONTYPE_BOB_TYPE).build();



        EditOrderCommand editOrderCommand = new EditOrderCommand(indexLastOrder, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(lastOrder, editedOrder);

        assertOrderCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Index indexLastOrder = Index.fromOneBased(model.getFilteredOrderList().size());
        Order lastOrder = model.getFilteredOrderList().get(indexLastOrder.getZeroBased());

        OrderBuilder orderInList = new OrderBuilder(lastOrder);

        Order editedOrder = orderInList
                .withDetails(VALID_DETAILS_BOB)
                .withDeliveryDateTime(VALID_DELIVERYDATETIME_BOB)
                .withRemark(VALID_REMARK_BOB)
                .withCollectionType(VALID_COLLECTIONTYPE_BOB_TYPE).build();

        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder()
                .withDetails(VALID_DETAILS_BOB)
                .withDeliveryDateTime(VALID_DELIVERYDATETIME_BOB)
                .withRemark(VALID_REMARK_BOB)
                .withCollectionType(VALID_COLLECTIONTYPE_BOB_TYPE).build();

        EditOrderCommand editOrderCommand = new EditOrderCommand(indexLastOrder, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(lastOrder, editedOrder);

        assertOrderCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_ORDER, new EditOrderDescriptor());
        Order editedOrder = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertOrderCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        Order editedOrder = new OrderBuilder(orderInFilteredList).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_ORDER,
                new EditOrderDescriptorBuilder().build());

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertOrderCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        EditOrderDescriptor descriptor = new EditOrderDescriptorBuilder().build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidOrderIndexFilteredList_failure() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);
        Index outOfBoundIndex = INDEX_SECOND_ORDER;
        // ensures that outOfBoundIndex is still in bounds of ReadyBakey Order list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex,
                new EditOrderDescriptorBuilder().build());

        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditOrderCommand standardCommand = new EditOrderCommand(INDEX_FIRST_ORDER, DESC_AMY_ORDER);

        // same values -> returns true
        EditOrderDescriptor copyDescriptor = new EditOrderDescriptor(DESC_AMY_ORDER);
        EditOrderCommand commandWithSameValues = new EditOrderCommand(INDEX_FIRST_ORDER, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditOrderCommand(INDEX_SECOND_ORDER, DESC_AMY_ORDER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditOrderCommand(INDEX_FIRST_ORDER, DESC_BOB_ORDER)));
    }

}

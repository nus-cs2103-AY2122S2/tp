package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertPersonCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showOrderAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.address.testutil.TypicalOrders.getTypicalAddressBookOrders;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.OrderBuilder;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnmarkOrderCommand}.
 */
public class UnmarkOrderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index indexLastOrder = Index.fromOneBased(model.getFilteredOrderList().size());
        Order unmarkedOrder = model.getFilteredOrderList().get(indexLastOrder.getZeroBased());

        assertFalse(unmarkedOrder.isComplete());

        OrderBuilder orderInList = new OrderBuilder(unmarkedOrder);
        Order editedMarkedOrder = orderInList.withComplete(true).build(); //Setting complete as true

        model.setOrder(unmarkedOrder, editedMarkedOrder);

        assertTrue(model.getFilteredOrderList().get(indexLastOrder.getZeroBased()).isComplete());

        UnmarkOrderCommand unmarkOrderCommand = new UnmarkOrderCommand(indexLastOrder);

        String expectedMessage = String.format(UnmarkOrderCommand.MESSAGE_UNMARK_ORDER_SUCCESS, unmarkedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertPersonCommandSuccess(unmarkOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        UnmarkOrderCommand unmarkOrderCommand = new UnmarkOrderCommand(outOfBoundIndex);

        assertCommandFailure(unmarkOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Order unmarkedOrderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());

        assertFalse(unmarkedOrderInFilteredList.isComplete());

        Order editedMarkedOrder = new OrderBuilder(unmarkedOrderInFilteredList).withComplete(true).build();

        model.setOrder(unmarkedOrderInFilteredList, editedMarkedOrder);

        assertTrue(model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased()).isComplete());

        UnmarkOrderCommand unmarkOrderCommand = new UnmarkOrderCommand(INDEX_FIRST_ORDER);

        String expectedMessage = String.format(UnmarkOrderCommand.MESSAGE_UNMARK_ORDER_SUCCESS,
                unmarkedOrderInFilteredList);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertPersonCommandSuccess(unmarkOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Index outOfBoundIndex = INDEX_SECOND_ORDER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        UnmarkOrderCommand unmarkOrderCommand = new UnmarkOrderCommand(outOfBoundIndex);

        assertCommandFailure(unmarkOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnmarkOrderCommand unmarkFirstCommand = new UnmarkOrderCommand(INDEX_FIRST_ORDER);
        UnmarkOrderCommand unmarkSecondCommand = new UnmarkOrderCommand(INDEX_SECOND_ORDER);

        // same object -> returns true
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommand));

        // same values -> returns true
        UnmarkOrderCommand unmarkFirstCommandCopy = new UnmarkOrderCommand(INDEX_FIRST_ORDER);
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstCommand.equals(null));

        // different order -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkSecondCommand));
    }

}

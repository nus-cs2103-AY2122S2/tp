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
 * {@code MarkOrderCommand}.
 */
public class MarkOrderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index indexLastOrder = Index.fromOneBased(model.getFilteredOrderList().size());
        Order lastOrder = model.getFilteredOrderList().get(indexLastOrder.getZeroBased());

        OrderBuilder orderInList = new OrderBuilder(lastOrder);
        Order editedMarkedOrder = orderInList.withComplete(true).build(); //Setting complete as true

        MarkOrderCommand markOrderCommand = new MarkOrderCommand(indexLastOrder);

        String expectedMessage = String.format(MarkOrderCommand.MESSAGE_MARK_ORDER_SUCCESS, editedMarkedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(lastOrder, editedMarkedOrder);

        assertPersonCommandSuccess(markOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        MarkOrderCommand markOrderCommand = new MarkOrderCommand(outOfBoundIndex);

        assertCommandFailure(markOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        Order editedMarkedOrder = new OrderBuilder(orderInFilteredList).withComplete(true).build();
        MarkOrderCommand markOrderCommand = new MarkOrderCommand(INDEX_FIRST_ORDER);

        String expectedMessage = String.format(MarkOrderCommand.MESSAGE_MARK_ORDER_SUCCESS, editedMarkedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedMarkedOrder);

        assertPersonCommandSuccess(markOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST_ORDER);

        Index outOfBoundIndex = INDEX_SECOND_ORDER;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        MarkOrderCommand markOrderCommand = new MarkOrderCommand(outOfBoundIndex);

        assertCommandFailure(markOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkOrderCommand markFirstCommand = new MarkOrderCommand(INDEX_FIRST_ORDER);
        MarkOrderCommand markSecondCommand = new MarkOrderCommand(INDEX_SECOND_ORDER);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkOrderCommand markFirstCommandCopy = new MarkOrderCommand(INDEX_FIRST_ORDER);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different order -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }

}

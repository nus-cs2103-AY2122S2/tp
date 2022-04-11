package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertOrderCommandSuccess;
import static seedu.address.testutil.TypicalOrders.getTypicalAddressBookOrders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.order.OrderIncompleteBeforeDeliveryDateTimePredicate;

public class FindIncompleteOrdersCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        OrderIncompleteBeforeDeliveryDateTimePredicate firstPredicate =
                new OrderIncompleteBeforeDeliveryDateTimePredicate(new DeliveryDateTime("30-12-2022 18:30"));
        OrderIncompleteBeforeDeliveryDateTimePredicate secondPredicate =
                new OrderIncompleteBeforeDeliveryDateTimePredicate(new DeliveryDateTime("30-12-2022 15:30"));

        FindIncompleteOrdersCommand findFirstCommand = new FindIncompleteOrdersCommand(firstPredicate);
        FindIncompleteOrdersCommand findSecondCommand = new FindIncompleteOrdersCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindIncompleteOrdersCommand findFirstCommandCopy = new FindIncompleteOrdersCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different order -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_multipleIncomplete_multipleOrdersFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 3);
        DeliveryDateTime multipleDeliveriesDateTime = new DeliveryDateTime("30-12-2022 18:30");
        OrderIncompleteBeforeDeliveryDateTimePredicate predicate =
                new OrderIncompleteBeforeDeliveryDateTimePredicate(multipleDeliveriesDateTime);
        FindIncompleteOrdersCommand command = new FindIncompleteOrdersCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertOrderCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}

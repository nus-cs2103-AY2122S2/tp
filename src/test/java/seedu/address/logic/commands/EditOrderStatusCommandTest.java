package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.address.testutil.TypicalOrders.getTypicalAddressBook;
import static seedu.address.testutil.TypicalOrders.getTypicalOrders;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.driver.DriverStatus;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.testutil.OrderBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditOrderStatusCommand.
 */
public class EditOrderStatusCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_editToDelivered_success() throws CommandException {
        Order editedOrder = new OrderBuilder().withDriver(getTypicalOrders().get(0).getDriver())
                .withStatus(OrderStatus.DELIVERED).build();

        EditOrderStatusCommand editOrderStatusCommand = new EditOrderStatusCommand(INDEX_FIRST_ORDER,
                OrderStatus.DELIVERED.name());

        model.addDriver(editedOrder.getDriver());

        editOrderStatusCommand.execute(model);
        assertEquals(model.getFilteredOrderList().get(0).getStatus(), editedOrder.getStatus());
    }

    @Test
    public void execute_editToInProgress_success() throws CommandException {
        Order editedOrder = new OrderBuilder().withDriver(getTypicalOrders().get(0).getDriver())
                .withStatus(OrderStatus.IN_PROGRESS).build();

        EditOrderStatusCommand editOrderStatusCommand = new EditOrderStatusCommand(INDEX_FIRST_ORDER,
                OrderStatus.IN_PROGRESS.name());

        model.addDriver(editedOrder.getDriver());

        editOrderStatusCommand.execute(model);
        assertEquals(model.getFilteredOrderList().get(0).getStatus(), editedOrder.getStatus());
    }

    @Test
    public void execute_editToCancelled_success() throws CommandException {
        Order editedOrder = new OrderBuilder().withDriver(getTypicalOrders().get(0).getDriver())
                .withStatus(OrderStatus.CANCELLED).build();

        EditOrderStatusCommand editOrderStatusCommand = new EditOrderStatusCommand(INDEX_FIRST_ORDER,
                OrderStatus.CANCELLED.name());

        model.addDriver(editedOrder.getDriver());

        editOrderStatusCommand.execute(model);
        assertEquals(model.getFilteredOrderList().get(0).getStatus(), editedOrder.getStatus());
    }

    @Test
    public void execute_invalidInput_failure() throws CommandException {
        Order editedOrder = new OrderBuilder().withDriver(getTypicalOrders().get(0).getDriver())
                .withStatus(OrderStatus.DELIVERED).build();

        EditOrderStatusCommand editOrderStatusCommand = new EditOrderStatusCommand(INDEX_FIRST_ORDER,
                "INVALID INPUT");

        model.addDriver(editedOrder.getDriver());

        assertThrows(CommandException.class, () -> editOrderStatusCommand.execute(model));
    }

    @Test
    public void execute_editToInProgressWhenDriverBusy_failure() throws CommandException {
        Order editedOrder = new OrderBuilder().withDriver(getTypicalOrders().get(0).getDriver())
                .withStatus(OrderStatus.DELIVERED).build();

        EditOrderStatusCommand editOrderStatusCommand = new EditOrderStatusCommand(INDEX_FIRST_ORDER,
                "in progress");

        model.addDriver(editedOrder.getDriver());
        model.getFilteredOrderList().get(0).updateStatus(OrderStatus.DELIVERED.name());
        model.getFilteredDriverList().get(0).setStatus(DriverStatus.BUSY);

        assertThrows(CommandException.class, () -> editOrderStatusCommand.execute(model));
    }
}

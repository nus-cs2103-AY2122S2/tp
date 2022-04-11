package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DRIVER_BUSY;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ORDER_STATUS;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DRIVERS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.DriverStatus;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.exception.NoSuchOrderStatusException;

public class EditOrderStatusCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits an order status.\n"
            + "Parameters: "
            + "INDEX " + PREFIX_STATUS + "STATUS\n"
            + "Example: " + COMMAND_WORD
            + " 1 " + PREFIX_STATUS + "delivered";

    public static final String MESSAGE_SUCCESS = "Order edited: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the address book.";


    private final Index index;
    private final String status;

    /**
     * Creates an AddCommand to add the specified {@code Order}
     */
    public EditOrderStatusCommand(Index index, String status) {
        requireAllNonNull(index, status);
        this.index = index;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());
        OrderStatus oldStatus = orderToEdit.getStatus();
        try {
            orderToEdit.updateStatus(status);
        } catch (NoSuchOrderStatusException ex) {
            throw new CommandException(MESSAGE_INVALID_ORDER_STATUS);
        }

        Order editedOrder = orderToEdit.updateStatus(status);

        if (!editedOrder.equals(orderToEdit) && model.hasOrder(editedOrder)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);

        // if editing an order from in progress to delivered or cancelled, the driver will be set to free
        if ((status.equalsIgnoreCase("delivered") || status.equalsIgnoreCase("cancelled"))
                && oldStatus.toString().equalsIgnoreCase("in_progress")) {
            editedOrder.getDriver().setStatus(DriverStatus.FREE);
            model.setDriver(editedOrder.getDriver(), orderToEdit.getDriver());
        }

        // if editing an order from delivered or cancelled to in progress, the driver will be set to busy
        if (status.equalsIgnoreCase("in progress")
                && (oldStatus.toString().equalsIgnoreCase("delivered")
                || oldStatus.toString().equalsIgnoreCase("cancelled"))) {
            for (Driver driver : model.getFilteredDriverList()) {
                if (driver.getName().equals(orderToEdit.getDriver().getName())) {
                    if (driver.isBusy()) {
                        editedOrder = editedOrder.updateStatus(oldStatus.name());
                        throw new CommandException(MESSAGE_DRIVER_BUSY);
                    }
                    break;
                }
            }
            editedOrder.getDriver().setStatus(DriverStatus.BUSY);
            model.setDriver(editedOrder.getDriver(), orderToEdit.getDriver());
        }

        model.updateFilteredDriverList(PREDICATE_SHOW_ALL_DRIVERS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedOrder),
                false, false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EditOrderStatusCommand
                && index.equals(((EditOrderStatusCommand) other).index)
                && status.equals(((EditOrderStatusCommand) other).status));
    }
}

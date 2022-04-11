package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ORDER;
import static seedu.address.testutil.TypicalOrders.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.Order;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.EditOrderDescriptorBuilder;
import seedu.address.testutil.OrderBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditOrderCommand.
 */
public class EditOrderCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldSpecifiedUnfilteredList_success() throws CommandException {
        Order editedOrder = new OrderBuilder().build();
        EditOrderCommand.EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder(editedOrder).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_ORDER, descriptor);

        Order validOrder = new OrderBuilder().build();

        ObservableList customerList = FXCollections.observableArrayList();
        customerList.add(validOrder.getCustomer());

        ObservableList dishList = FXCollections.observableArrayList();
        dishList.addAll(validOrder.getDishes());

        editOrderCommand.execute(model, customerList, dishList);

        assert(model.getFilteredOrderList().get(0).isSameOrder(editedOrder));
    }

    @Test
    public void execute_someFieldSpecifiedUnfilteredList_success() throws CommandException {
        Order editedOrder = new OrderBuilder()
                .withCustomer(new CustomerBuilder().withName(VALID_NAME_BOB).build())
                .build();
        EditOrderCommand.EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder(editedOrder).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_ORDER, descriptor);

        Order validOrder = new OrderBuilder().build();

        ObservableList customerList = FXCollections.observableArrayList();
        customerList.add(validOrder.getCustomer());

        ObservableList dishList = FXCollections.observableArrayList();
        dishList.addAll(validOrder.getDishes());

        editOrderCommand.execute(model, customerList, dishList);

        assert(model.getFilteredOrderList().get(0).isSameOrder(editedOrder));
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() throws CommandException {
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_ORDER,
                new EditOrderCommand.EditOrderDescriptor());

        Order validOrder = new OrderBuilder().build();

        ObservableList customerList = FXCollections.observableArrayList();
        customerList.add(validOrder.getCustomer());

        ObservableList dishList = FXCollections.observableArrayList();
        dishList.addAll(validOrder.getDishes());

        Order orderBeforeExecution = model.getFilteredOrderList().get(0);

        editOrderCommand.execute(model, customerList, dishList);

        // for no field specified, the order should not be changed by the command
        assert(model.getFilteredOrderList().get(0).isSameOrder(orderBeforeExecution));
    }

    @Test
    public void execute_filteredList_success() throws CommandException {
        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST_ORDER.getZeroBased());
        Order editedOrder = new OrderBuilder(orderInFilteredList)
                .withCustomer(new CustomerBuilder().withName(VALID_NAME_BOB).build())
                .build();
        EditOrderCommand.EditOrderDescriptor descriptor =
                new EditOrderDescriptorBuilder(editedOrder).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_ORDER, descriptor);

        Order validOrder = new OrderBuilder().build();

        ObservableList customerList = FXCollections.observableArrayList();
        customerList.add(validOrder.getCustomer());

        ObservableList dishList = FXCollections.observableArrayList();
        dishList.addAll(validOrder.getDishes());

        editOrderCommand.execute(model, customerList, dishList);

        assert(model.getFilteredOrderList().get(0).isSameOrder(editedOrder));
    }

    @Test
    public void equals() {
        final EditOrderCommand standardCommand = new EditOrderCommand(INDEX_FIRST_ORDER, DESC_AMY_ORDER);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCustomerCommand(INDEX_SECOND_ORDER, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCustomerCommand(INDEX_FIRST_ORDER, DESC_BOB)));
    }
}

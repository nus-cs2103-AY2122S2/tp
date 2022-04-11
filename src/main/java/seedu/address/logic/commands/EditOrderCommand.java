package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.dish.Dish;
import seedu.address.model.driver.Driver;
import seedu.address.model.order.Order;

/**
 * Edits the details of an existing order in the order list.
 */
public class EditOrderCommand extends Command {

    public static final String COMMAND_WORD = "editorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the customer and dish details of "
            + "the order identified by the index number used in the displayed order list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_DISH + "DISHES]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_DISH + "kimchi sushi";

    public static final String MESSAGE_EDIT_ORDER_SUCCESS = "Edited Order: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the order list.";

    private final Index index;
    private final EditOrderDescriptor editOrderDescriptor;

    /**
     * @param index of the order in the filtered order list to edit
     * @param editOrderDescriptor details to edit the order with
     */
    public EditOrderCommand(Index index, EditOrderDescriptor editOrderDescriptor) {
        requireNonNull(index);
        requireNonNull(editOrderDescriptor);

        this.index = index;
        this.editOrderDescriptor = editOrderDescriptor;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_EDIT_ORDER_SUCCESS,
                false, false, false, false, true);
    }

    /**
     * Executes the EditOrderCommand and returns the result message.
     */
    public CommandResult execute(Model model, ObservableList<Customer> customers,
                                 ObservableList<Dish> dishes) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());
        Order editedOrder = createEditedOrder(orderToEdit, editOrderDescriptor, customers, dishes);

        if (!orderToEdit.equals(editedOrder) && model.hasOrder(editedOrder)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.setOrder(orderToEdit, editedOrder);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder),
                false, false, false, false, true);
    }

    /**
     * Creates and returns a {@code Order} with the details of {@code orderToEdit}
     * edited with {@code editOrderDescriptor}.
     */
    private static Order createEditedOrder(Order orderToEdit,
                                           EditOrderDescriptor editOrderDescriptor,
                                           ObservableList<Customer> customers,
                                           ObservableList<Dish> dishes) throws CommandException {
        assert orderToEdit != null;

        Customer updatedCustomer = orderToEdit.getCustomer();

        if (editOrderDescriptor.getCustomerPhone().isPresent()) {
            boolean customerFound = false;
            // matching customer phone number in input to the actual customer in stored data
            for (Customer storedCustomer : customers) {
                if (storedCustomer.getPhone().toString().equals(editOrderDescriptor.customerPhone)) {
                    updatedCustomer = storedCustomer;
                    customerFound = true;
                    break;
                }
            }

            if (!customerFound) {
                throw new CommandException(Messages.MESSAGE_NO_CUSTOMER_FOUND);
            }
        }

        Driver updatedDriver = orderToEdit.getDriver();

        ArrayList<Dish> updatedDishes = new ArrayList<>();
        if (editOrderDescriptor.getDishes().isPresent()) {
            List<String> dishesInputList = editOrderDescriptor.getDishes().get();
            for (Dish dish : dishes) {
                if (dishesInputList.contains(dish.toString())) {
                    updatedDishes.add(dish);
                }
            }

            if (dishesInputList.size() != updatedDishes.size()) {
                throw new CommandException(Messages.MESSAGE_NO_DISH_FOUND);
            }
        } else {
            updatedDishes = orderToEdit.getDishes();
        }

        return new Order(updatedCustomer, updatedDriver, orderToEdit.getTime(),
                orderToEdit.getOrderNumber(), updatedDishes.toArray(new Dish[0]));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditOrderCommand)) {
            return false;
        }

        // state check
        EditOrderCommand e = (EditOrderCommand) other;
        return index.equals(e.index)
                && editOrderDescriptor.equals(e.editOrderDescriptor);
    }

    /**
     * Stores the details to edit the order with. Each non-empty field value will replace the
     * corresponding field value of the order.
     */
    public static class EditOrderDescriptor {
        private String customerPhone;
        private String[] dishes;

        public EditOrderDescriptor() {}

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(customerPhone, dishes);
        }

        public void setCustomerPhone(String phoneInput) {
            customerPhone = phoneInput;
        }

        public Optional<String> getCustomerPhone() {
            return Optional.ofNullable(customerPhone);
        }

        public void setDishes(String ... dishesInput) {
            this.dishes = dishesInput;
        }

        /**
         * Returns an unmodifiable dishes list, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code dishes} is null.
         */
        public Optional<List<String>> getDishes() {
            return (dishes != null) ? Optional.of(List.of(dishes)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditOrderCommand.EditOrderDescriptor)) {
                return false;
            }

            // state check
            EditOrderCommand.EditOrderDescriptor e = (EditOrderCommand.EditOrderDescriptor) other;

            return getCustomerPhone().equals(e.getCustomerPhone())
                    && getDishes().equals(e.getDishes());
        }
    }
}

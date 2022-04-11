package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CUSTOMER_PHONE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DISH_INPUT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_FREE_DRIVER;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.dish.Dish;
import seedu.address.model.driver.Driver;
import seedu.address.model.order.Order;

public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an order.\n"
            + "Parameters: " + PREFIX_PHONE
            + "PHONE " + PREFIX_DISH + "DISHES\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PHONE
            + "87654321 " + PREFIX_DISH + "Chicken Pasta, Fries";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists!";


    private final String phoneInput;
    private final String[] dishesInput;

    /**
     * Creates an AddCommand to add the specified {@code Order}
     */
    public AddOrderCommand(String phone, String ... dishes) {
        requireAllNonNull(phone, dishes);
        this.phoneInput = phone;
        this.dishesInput = dishes;
    }

    @Override
    public CommandResult execute(Model model) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Executes the AddOrderCommand and returns the result message.
     */
    public CommandResult execute(Model model,
                                 ObservableList<Customer> customers, ObservableList<Driver> drivers,
                                 ObservableList<Dish> dishes) throws CommandException {
        requireAllNonNull(model, customers, drivers, dishes);

        // checking for free driver to assign for the newly created order
        Driver freeDriver = null;
        for (Driver driver : drivers) {
            if (driver.isFree()) {
                freeDriver = driver;
                break;
            }
        }

        if (freeDriver == null) {
            throw new CommandException(MESSAGE_NO_FREE_DRIVER);
        }

        // matching customer phone number in input to the actual customer in stored data
        Customer customer = null;
        for (Customer storedCustomer : customers) {
            if (storedCustomer.getPhone().toString().equals(phoneInput)) {
                customer = storedCustomer;
                break;
            }
        }

        if (customer == null) {
            throw new CommandException(MESSAGE_INVALID_CUSTOMER_PHONE);
        }

        // matching dishes in string in the input to the actual dishes in menu
        ArrayList<Dish> addedDishes = new ArrayList<Dish> ();

        List<String> dishesInputList = Arrays.asList(dishesInput);
        for (Dish dish : dishes) {
            if (dishesInputList.contains(dish.toString())) {
                addedDishes.add(dish);
            }
        }

        if (dishesInputList.size() != addedDishes.size()) {
            throw new CommandException(MESSAGE_INVALID_DISH_INPUT);
        }

        Order toAdd = new Order(customer, freeDriver, addedDishes.toArray(new Dish[0]));

        if (model.hasOrder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.addOrder(toAdd);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                false, false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddOrderCommand)
                && (phoneInput.equals(((AddOrderCommand) other).phoneInput)
                && Arrays.equals(dishesInput, ((AddOrderCommand) other).dishesInput));
    }

}

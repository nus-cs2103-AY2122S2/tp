package seedu.address.storage;

import static seedu.address.logic.commands.AddDishCommand.MESSAGE_DUPLICATE_DISH;
import static seedu.address.logic.commands.AddDriverCommand.MESSAGE_DUPLICATE_DRIVER;
import static seedu.address.logic.commands.AddOrderCommand.MESSAGE_DUPLICATE_ORDER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.customer.Customer;
import seedu.address.model.dish.Dish;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.DriverStatus;
import seedu.address.model.order.Order;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_CUSTOMER = "Customers list contains duplicate customer(s).";

    private final List<JsonAdaptedCustomer> customers = new ArrayList<>();
    private final List<JsonAdaptedDish> dishes = new ArrayList<>();
    private final List<JsonAdaptedDriver> drivers = new ArrayList<>();
    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given customers.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("customers") List<JsonAdaptedCustomer> customers,
                                       @JsonProperty("dishes") List<JsonAdaptedDish> dishes,
                                       @JsonProperty("drivers") List<JsonAdaptedDriver> drivers,
                                       @JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.dishes.addAll(dishes);
        this.drivers.addAll(drivers);
        this.orders.addAll(orders);
        this.customers.addAll(customers);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        customers.addAll(source.getCustomerList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
        dishes.addAll(source.getDishList().stream().map(JsonAdaptedDish::new).collect(Collectors.toList()));
        drivers.addAll(source.getDriverList().stream().map(JsonAdaptedDriver::new).collect(Collectors.toList()));
        orders.addAll(source.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedCustomer jsonAdaptedCustomer : customers) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (addressBook.hasCustomer(customer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CUSTOMER);
            }
            addressBook.addCustomer(customer);
        }

        for (JsonAdaptedDish jsonAdaptedDish : dishes) {
            Dish dish = jsonAdaptedDish.toModelType();
            if (addressBook.hasDish(dish)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DISH);
            }
            addressBook.addDish(dish);
        }

        for (JsonAdaptedDriver jsonAdaptedDriver : drivers) {
            Driver driver = jsonAdaptedDriver.toModelType();
            if (addressBook.hasDriver(driver)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DRIVER);
            }
            addressBook.addDriver(driver);
        }

        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();
            if (addressBook.hasOrder(order)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER);
            }
            if (order.getStatus().toString().equalsIgnoreCase("IN_PROGRESS")) {
                Driver orderDriver = order.getDriver();
                for (Driver driver : addressBook.getDriverList()) {
                    if (driver.getName().equals(orderDriver.getName())) {
                        driver.setStatus(DriverStatus.BUSY);
                    }
                }
            }
            addressBook.addOrder(order);
        }

        return addressBook;
    }

}

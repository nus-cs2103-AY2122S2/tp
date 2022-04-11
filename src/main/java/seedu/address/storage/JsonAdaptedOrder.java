package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.customer.AddressCustomer;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameCustomer;
import seedu.address.model.customer.PhoneCustomer;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";
    public static final String INVALID_DISH_MESSAGE = "Number of dishes and prices are not equal!";

    private final String customerName;
    private final String customerPhone;
    private final String customerAddress;
    private final String driverName;
    private final String driverPhone;
    private final String status;
    private final ArrayList<String> dishNames;
    private final ArrayList<String> dishPrices;
    private final LocalDateTime time;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given dish details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("customerName") String customerName,
                            @JsonProperty("customerPhone") String customerPhone,
                            @JsonProperty("customerAddress") String customerAddress,
                            @JsonProperty("driverName") String driverName,
                            @JsonProperty("driverPhone") String driverPhone,
                            @JsonProperty("status") String status,
                            @JsonProperty("dishName") ArrayList<String> dishNames,
                            @JsonProperty("dishPrice") ArrayList<String> dishPrices,
                            @JsonProperty("time") LocalDateTime time) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.status = status;
        this.dishNames = dishNames;
        this.dishPrices = dishPrices;
        this.time = time;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        customerName = source.getCustomerName();
        customerPhone = source.getCustomerPhone();
        customerAddress = source.getCustomerAddress().toString();
        driverName = source.getDriverName();
        driverPhone = source.getDriver().getPhone().toString();
        status = source.getStatus().toString();
        dishNames = new ArrayList<String>();
        dishPrices = new ArrayList<String>();
        for (Dish dish : source.getDishes()) {
            dishNames.add(dish.toString());
            dishPrices.add(dish.getPrice().toString());
        }
        time = source.getTime();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (customerName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NameCustomer.class.getSimpleName()));
        }

        if (customerPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PhoneCustomer.class.getSimpleName()));
        }

        if (customerAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AddressCustomer.class.getSimpleName()));
        }

        final Customer customer = new Customer(new NameCustomer(customerName),
                new PhoneCustomer(customerPhone), new AddressCustomer(customerAddress));

        if (driverName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NameDriver.class.getSimpleName()));
        }

        if (driverPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PhoneDriver.class.getSimpleName()));
        }

        final Driver driver = new Driver(new NameDriver(driverName), new PhoneDriver(driverPhone));

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderStatus.class.getSimpleName()));
        }

        final OrderStatus orderStatus;

        // default is delivered
        if (status.equalsIgnoreCase("in_progress")) {
            orderStatus = OrderStatus.IN_PROGRESS;
        } else if (status.equalsIgnoreCase("cancelled")) {
            orderStatus = OrderStatus.CANCELLED;
        } else {
            orderStatus = OrderStatus.DELIVERED;
        }

        if (dishNames == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NameDish.class.getSimpleName()));
        }

        if (dishPrices == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PriceDish.class.getSimpleName()));
        }

        if (dishNames.size() != dishPrices.size()) {
            throw new IllegalValueException(INVALID_DISH_MESSAGE);
        }

        final ArrayList<Dish> modelDishes = new ArrayList<Dish> ();
        for (int i = 0; i < dishNames.size(); i++) {
            modelDishes.add(new Dish(new NameDish(dishNames.get(i)),
                    new PriceDish(dishPrices.get(i))));
        }

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "time"));
        }

        final LocalDateTime time = this.time;

        return new Order(customer, driver, time, orderStatus, modelDishes.toArray(new Dish[0]));
    }

}


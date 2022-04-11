package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;

import seedu.address.model.customer.Customer;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {

    public static final Customer DEFAULT_CUSTOMER = new CustomerBuilder().build();
    public static final Driver DEFAULT_DRIVER = new Driver(new NameDriver("Zac"), new PhoneDriver("81234567"));
    public static final Dish DEFAULT_DISH = new Dish(new NameDish("Sushi"), new PriceDish("4.99"));
    public static final OrderStatus DEFAULT_ORDER_STATUS = OrderStatus.IN_PROGRESS;
    public static final LocalDateTime DEFAULT_TIME = LocalDateTime.of(2022, 1, 1, 0, 0);

    private static int nextNumber = 1;
    private Customer customer;
    private Driver driver;
    private ArrayList<Dish> dishes;
    private OrderStatus status;
    private int orderNumber;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        customer = DEFAULT_CUSTOMER;
        driver = DEFAULT_DRIVER;
        dishes = new ArrayList<>();
        dishes.add(DEFAULT_DISH);
        status = DEFAULT_ORDER_STATUS;
        orderNumber = nextNumber++;
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        customer = orderToCopy.getCustomer();
        driver = orderToCopy.getDriver();
        dishes = orderToCopy.getDishes();
        status = orderToCopy.getStatus();
        orderNumber = orderToCopy.getOrderNumber();
    }

    /**
     * Sets the {@code Customer} of the {@code Order} that we are building.
     */
    public OrderBuilder withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    /**
     * Sets the {@code Driver} of the {@code Order} that we are building.
     */
    public OrderBuilder withDriver(Driver driver) {
        this.driver = driver;
        return this;
    }

    /**
     * Sets the {@code Dish} of the {@code Order} that we are building.
     */
    public OrderBuilder withDish(ArrayList<Dish> dishes) {
        this.dishes = dishes;
        return this;
    }

    /**
     * Sets the {@code OrderStatus} of the {@code Order} that we are building.
     */
    public OrderBuilder withStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Builds an {@code Order} with the specification provided.
     * @return the Order built
     */
    public Order build() {
        return new Order(customer, driver, DEFAULT_TIME, orderNumber, dishes.toArray(new Dish[0]))
                .updateStatus(status.name());
    }
}

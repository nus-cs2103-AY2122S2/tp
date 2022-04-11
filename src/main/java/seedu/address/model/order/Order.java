package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.customer.AddressCustomer;
import seedu.address.model.customer.Customer;
import seedu.address.model.dish.Dish;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.DriverStatus;
import seedu.address.model.order.exception.NoSuchOrderStatusException;

/**
 * Represents an Order in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order {
    // class-level attribute
    private static int nextOrderNumber = 1;

    // Data fields
    private final Customer customer;
    private final Driver driver;
    private final int orderNumber;
    private final ArrayList<Dish> dishes;
    private final LocalDateTime time;
    private OrderStatus status;

    /**
     * Every field must be present and not null.
     */
    public Order(Customer customer, Driver driver, LocalDateTime time, OrderStatus status,
                 int orderNumber, Dish ... orderedDishes) {
        requireAllNonNull(customer, orderedDishes);
        this.customer = customer;
        this.driver = driver;
        if (status.toString().equalsIgnoreCase("IN_PROGRESS")) {
            driver.setStatus(DriverStatus.BUSY);
        }
        this.dishes = new ArrayList<Dish>();
        this.dishes.addAll(Arrays.asList(orderedDishes));
        this.time = time;
        this.orderNumber = orderNumber;
        this.status = status;
    }

    public Order(Customer customer, Driver driver, LocalDateTime time, int orderNumber, Dish ... orderedDishes) {
        this(customer, driver, time, OrderStatus.IN_PROGRESS, orderNumber, orderedDishes);
    }

    public Order(Customer customer, Driver driver, Dish ... orderedDishes) {
        this(customer, driver, LocalDateTime.now(), orderedDishes);
    }

    public Order(Customer customer, Driver driver, LocalDateTime time, Dish ... orderedDishes) {
        this(customer, driver, time, nextOrderNumber++, orderedDishes);
    }

    public Order(Customer customer, Driver driver, LocalDateTime time, OrderStatus status, Dish ... orderedDishes) {
        this(customer, driver, time, status, nextOrderNumber++, orderedDishes);
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getCustomerName() {
        return customer.getName().fullName;
    }

    public String getCustomerPhone() {
        return customer.getPhone().value;
    }

    public AddressCustomer getCustomerAddress() {
        return customer.getAddress();
    }

    public Driver getDriver() {
        return driver;
    }

    public String getDriverName() {
        return driver.getName().fullName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public double getTotalPrice() {
        return dishes.stream().mapToDouble(x -> x.getPrice().value).sum();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Returns true if both orders share the same unique order number.
     * @param other the other order to be compared with
     * @return a boolean value indicating the equality
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return this.orderNumber == otherOrder.orderNumber;
    }

    /**
     * Returns true if both orders share the same customer phone, driver name and dish names.
     * Mainly used for testing. For program usage, refer to {@code equals} method;
     * @param other the other order to be compared with
     * @return a boolean value indicating the equality
     */
    public boolean isSameOrder(Order other) {
        if (this.getCustomerPhone().equals(other.getCustomerPhone())
                && (this.getDriverName().equals(other.getDriverName()))) {
            for (int i = 0; i < this.getDishes().size(); i++) {
                if (!this.getDishes().get(i).getName().equals(other.getDishes().get(i).getName())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getOrderNumber())
                .append("; Customer: ")
                .append(getCustomerName())
                .append("; Phone: ")
                .append(getCustomerPhone())
                .append("; Address: ")
                .append(getCustomerAddress())
                .append("; Driver: ")
                .append(getDriverName())
                .append("; Status: ")
                .append(getStatus())
                .append("; Time: ")
                .append(getTime().toString().replace("T", " ").split("\\.")[0]);

        ArrayList<Dish> dishes = getDishes();
        builder.append("; Dishes: ");
        dishes.forEach(builder::append);

        return builder.toString();
    }

    /**
     * Updates the status of the {@code Order}.
     * @param status the status in lower-case String
     * @return an {@code Order} with the updated order status.
     */
    public Order updateStatus(String status) {
        status = status.toLowerCase();
        switch (status) {
        case "in progress":
        case "in_progress":
            this.status = OrderStatus.IN_PROGRESS;
            break;
        case "delivered":
            this.status = OrderStatus.DELIVERED;
            this.driver.setStatus(DriverStatus.FREE);
            break;
        case "cancelled":
            this.status = OrderStatus.CANCELLED;
            this.driver.setStatus(DriverStatus.FREE);
            break;
        default:
            throw new NoSuchOrderStatusException();
        }
        return this;
    }
}

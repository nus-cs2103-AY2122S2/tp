package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_NAME_FRIES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_PRICE_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_PRICE_FRIES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.customer.Customer;
import seedu.address.model.dish.Dish;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.DriverStatus;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;

/**
 * A utility class containing a list of {@code Customer} objects to be used in tests.
 */
public class TypicalAddressBook {
    // Customers
    public static final Customer ALICE = new CustomerBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withPhone("94351253")
            .build();
    public static final Customer BENSON = new CustomerBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withPhone("98765432")
            .build();
    public static final Customer CARL = new CustomerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withAddress("wall street").build();
    public static final Customer DANIEL = new CustomerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withAddress("10th street").build();
    public static final Customer ELLE = new CustomerBuilder().withName("Elle Meyer").withPhone("94822245")
            .withAddress("michegan ave").build();
    public static final Customer FIONA = new CustomerBuilder().withName("Fiona Kunz").withPhone("94824275")
            .withAddress("little tokyo").build();
    public static final Customer GEORGE = new CustomerBuilder().withName("George Best").withPhone("94824452")
            .withAddress("4th street").build();

    // Dishes
    public static final Dish APPLE = new DishBuilder().withName("Apple").withPrice("2.50").build();
    public static final Dish BURGER = new DishBuilder().withName("Burger").withPrice("6.50").build();
    public static final Dish COKE = new DishBuilder().withName("Coke").withPrice("3.50").build();
    public static final Dish DUCKRICE = new DishBuilder().withName("Duck Rice").withPrice("5.50").build();

    // Drivers
    public static final Driver ABEL = new DriverBuilder().withName("Abel Door")
            .withPhone("93218721").withStatus(DriverStatus.BUSY)
            .build();
    public static final Driver BAREL = new DriverBuilder().withName("Barel Ran")
            .withPhone("93021872")
            .build();
    public static final Driver CANDY = new DriverBuilder().withName("Candy Ate")
            .withPhone("99102634")
            .build();
    public static final Driver DAREL = new DriverBuilder().withName("Darel Bin")
            .withPhone("91027394")
            .build();

    // Orders
    public static final Order FIRST = new OrderBuilder().withCustomer(ALICE).withDriver(ABEL)
            .withDish(new ArrayList<>(List.of(APPLE, BURGER))).withStatus(OrderStatus.IN_PROGRESS).build();
    public static final Order SECOND = new OrderBuilder().withCustomer(BENSON).withDriver(BAREL)
            .withDish(new ArrayList<>(List.of(BURGER, DUCKRICE))).withStatus(OrderStatus.DELIVERED).build();
    public static final Order THIRD = new OrderBuilder().withCustomer(CARL).withDriver(CANDY)
            .withDish(new ArrayList<>(List.of(BURGER, DUCKRICE))).withStatus(OrderStatus.CANCELLED).build();
    public static final Order FOURTH = new OrderBuilder().withCustomer(DANIEL).withDriver(DAREL)
            .withDish(new ArrayList<>(List.of(COKE))).withStatus(OrderStatus.DELIVERED).build();

    // Manually added - Dish's details found in {@code CommandTestUtil}
    public static final Dish FRIES = new DishBuilder().withName(VALID_DISH_NAME_FRIES).withPrice(VALID_DISH_PRICE_FRIES)
            .build();
    public static final Dish DONUT = new DishBuilder().withName(VALID_DISH_NAME_DONUT).withPrice(VALID_DISH_PRICE_DONUT)
            .build();

    // Manually added
    public static final Customer HOON = new CustomerBuilder().withName("Hoon Meier").withPhone("84824244")
            .withAddress("little india").build();
    public static final Customer IDA = new CustomerBuilder().withName("Ida Mueller").withPhone("84821314")
            .withAddress("chicago ave").build();

    // Manually added - Customer's details found in {@code CommandTestUtil}
    public static final Customer AMY = new CustomerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withAddress(VALID_ADDRESS_AMY).build();
    public static final Customer BOB = new CustomerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical customers and dishes.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Customer customer : getTypicalCustomers()) {
            ab.addCustomer(customer);
        }
        for (Dish dish : getTypicalDishes()) {
            ab.addDish(dish);
        }
        for (Driver driver : getTypicalDrivers()) {
            ab.addDriver(driver);
        }
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

    public static List<Customer> getTypicalCustomers() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Dish> getTypicalDishes() {
        return new ArrayList<>(Arrays.asList(APPLE, BURGER, COKE, DUCKRICE));
    }

    public static List<Driver> getTypicalDrivers() {
        return new ArrayList<>(Arrays.asList(ABEL, BAREL, CANDY, DAREL));
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(FIRST, SECOND, THIRD, FOURTH));
    }
}

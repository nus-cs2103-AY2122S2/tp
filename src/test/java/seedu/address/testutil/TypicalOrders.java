package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;
import seedu.address.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {

    public static final Order AMY_ORDER = new OrderBuilder()
            .withCustomer(new CustomerBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                    .withAddress(VALID_ADDRESS_AMY).build())
            .withDriver(new Driver(new NameDriver("Zac"), new PhoneDriver("83725646")))
            .withDish(new ArrayList<Dish>(Arrays.asList(new Dish(new NameDish("Sushi"), new PriceDish("4.99")))))
            .build();

    public static final Order BOB_ORDER = new OrderBuilder()
            .withCustomer(new CustomerBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withAddress(VALID_ADDRESS_BOB).build())
            .withDriver(new Driver(new NameDriver("Yong Jie"), new PhoneDriver("94615254")))
            .withDish(new ArrayList<Dish>(Arrays.asList(new Dish(new NameDish("Kimchi"), new PriceDish("5.50")),
                    new Dish(new NameDish("Truffle Fries"), new PriceDish("6.70")))))
            .build();

    public static final Order CARL_ORDER = new OrderBuilder()
            .withCustomer(new CustomerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withAddress("wall street").build())
            .withDriver(new Driver(new NameDriver("Xavier"), new PhoneDriver("87776888")))
            .withDish(new ArrayList<Dish>(Arrays.asList(new Dish(new NameDish("Fried Rice"), new PriceDish("6.50")))))
            .build();

    public static final Order DANIEL_ORDER = new OrderBuilder()
            .withCustomer(new CustomerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withAddress("10th street").build())
            .withDriver(new Driver(new NameDriver("Wilson"), new PhoneDriver("85736285")))
            .withDish(new ArrayList<Dish>(Arrays.asList(new Dish(new NameDish("Milktea"), new PriceDish("3.20")))))
            .build();

    public static final Order ELLE_ORDER = new OrderBuilder()
            .withCustomer(new CustomerBuilder().withName("Elle Meyer").withPhone("94822224")
            .withAddress("michegan ave").build())
            .withDriver(new Driver(new NameDriver("Vivi"), new PhoneDriver("82364513")))
            .withDish(new ArrayList<Dish>(Arrays.asList(new Dish(new NameDish("Chicken chop"), new PriceDish("4.50")))))
            .build();

    private TypicalOrders() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical orders.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(AMY_ORDER, BOB_ORDER, CARL_ORDER, DANIEL_ORDER, ELLE_ORDER));
    }
}

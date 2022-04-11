package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.customer.AddressCustomer;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameCustomer;
import seedu.address.model.customer.PhoneCustomer;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.DriverStatus;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;
import seedu.address.model.order.Order;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer(new NameCustomer("Alex Yeoh"), new PhoneCustomer("98765432"),
                new AddressCustomer("Blk 30 Geylang Street 29, #06-40")),
            new Customer(new NameCustomer("Bernice Yu"), new PhoneCustomer("81232231"),
                new AddressCustomer("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
            new Customer(new NameCustomer("Charlotte Oliveiro"), new PhoneCustomer("88887777"),
                new AddressCustomer("Blk 11 Ang Mo Kio Street 74, #11-04"))
        };
    }

    public static Dish[] getSampleDishes() {
        return new Dish[] {
            new Dish(new NameDish("Fries"), new PriceDish("3.50")),
            new Dish(new NameDish("Pizza"), new PriceDish("10.00")),
            new Dish(new NameDish("Chicken Nuggets"), new PriceDish("8.00")),
            new Dish(new NameDish("Burger"), new PriceDish("5.50")),
            new Dish(new NameDish("Coke"), new PriceDish("1.50")),
            new Dish(new NameDish("Lemon Juice"), new PriceDish("1.00"))
        };
    }

    public static Driver[] getSampleDrivers() {
        return new Driver[] {
            new Driver(new NameDriver("Adam"), new PhoneDriver("88993823")),
            new Driver(new NameDriver("Joe"), new PhoneDriver("91232323")),
            new Driver(new NameDriver("Eva"), new PhoneDriver("81923823"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleAb.addCustomer(sampleCustomer);
        }
        for (Dish sampleDish : getSampleDishes()) {
            sampleAb.addDish(sampleDish);
        }
        for (Driver sampleDrive : getSampleDrivers()) {
            sampleAb.addDriver(sampleDrive);
        }
        sampleAb.getDriverList().get(2).setStatus(DriverStatus.ABSENT);

        sampleAb.addOrder(new Order(sampleAb.getCustomerList().get(0), sampleAb.getDriverList().get(0),
                sampleAb.getDishList().get(0), sampleAb.getDishList().get(1), sampleAb.getDishList().get(5)));
        sampleAb.getOrderList().get(0).updateStatus("delivered");

        sampleAb.addOrder(new Order(sampleAb.getCustomerList().get(2), sampleAb.getDriverList().get(1),
                sampleAb.getDishList().get(3), sampleAb.getDishList().get(2), sampleAb.getDishList().get(4)));

        sampleAb.addOrder(new Order(sampleAb.getCustomerList().get(1), sampleAb.getDriverList().get(0),
                sampleAb.getDishList().get(0), sampleAb.getDishList().get(5)));
        sampleAb.getOrderList().get(2).updateStatus("cancelled");
        return sampleAb;
    }

}

package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DELIVERYDATETIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELIVERYDATETIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.order.CollectionType;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;


public class TypicalOrders {
    public static final Order EMILY = new OrderBuilder()
            .withUuid("237e9877-e79b-12d4-a765-321741963000")
            .withDetails("1:vanilla cake")
            .withRemark("Allergic to Nuts")
            .withDeliveryDateTime("10-12-2022 09:30")
            .withCollectionType(CollectionType.DELIVERY)
            .withComplete(false).build();

    public static final Order SIMON = new OrderBuilder()
            .withUuid("ea3cb232-f297-451c-80d8-c1800fef118f")
            .withDetails("1:chocolate cake")
            .withRemark("Add Chocolate")
            .withDeliveryDateTime("11-12-2022 19:30")
            .withCollectionType(CollectionType.PICKUP)
            .withComplete(false).build();

    public static final Order JERRY = new OrderBuilder()
            .withUuid("15580af3-4d85-4948-ba35-628962989444")
            .withDetails("1:jerryfavouritecheesecake")
            .withRemark("Allergic to Cheese")
            .withDeliveryDateTime("15-12-2022 20:30")
            .withCollectionType(CollectionType.DELIVERY)
            .withComplete(false).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person EMILY_PERSON = new PersonBuilder().withName("Emily").withPhone("90032123")
            .withEmail("Emily@gmail.com").withAddress("Market 123").withRemark("Loves to eat Mint Leaves")
            .withTags(VALID_TAG_FRIEND).withUuid("237e9877-e79b-12d4-a765-321741963000").build();
    public static final Person SIMON_PERSON = new PersonBuilder().withName("Simon").withPhone("91029322")
            .withEmail("simon@hotmail.com").withAddress("Village 123").withRemark("Loves to watch Pokemon")
            .withTags(VALID_TAG_FRIEND).withUuid("ea3cb232-f297-451c-80d8-c1800fef118f")
            .build();
    public static final Person JERRY_PERSON = new PersonBuilder().withName("Jerry").withPhone("82932342")
            .withEmail("jerry@hotmail.com").withAddress("Apartment 123").withRemark("Loves to watch Soccer")
            .withTags(VALID_TAG_FRIEND).withUuid("15580af3-4d85-4948-ba35-628962989444")
            .build();


    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Order AMY = new OrderBuilder().withDetails(VALID_DETAILS_AMY)
            .withDeliveryDateTime(VALID_DELIVERYDATETIME_AMY).withCollectionType(CollectionType.DELIVERY)
            .build();
    public static final Order BOB = new OrderBuilder().withDetails(VALID_DETAILS_BOB)
            .withDeliveryDateTime(VALID_DELIVERYDATETIME_BOB).withCollectionType(CollectionType.PICKUP)
            .build();


    private TypicalOrders() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical orders.
     */
    public static AddressBook getTypicalAddressBookOrders() {
        AddressBook ab = new AddressBook();
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(EMILY, SIMON, JERRY));
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(EMILY_PERSON, SIMON_PERSON, JERRY_PERSON));
    }


}

package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.CollectionType;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Remark("Allergic to Peanuts"),
                getTagSet("Regular"), UUID.fromString("6ea390c0-242c-4643-a547-21cc85773f88")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Remark("Birthday on 6 December"),
                getTagSet("Member", "Regular"), UUID.fromString("0d13afaa-db95-4a2a-b64d-5270e39d243d")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Remark("Loves yuzu"),
                getTagSet("Member"),  UUID.fromString("556094dd-acde-462a-9d69-0f7cb1e3f34c")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Remark("Prefers savoury to sweet"),
                getTagSet("Member"),  UUID.fromString("a0f493f4-832d-46b3-9bcf-6da00b95f964")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Remark("Hates Pistachios"),
                getTagSet("Member"),  UUID.fromString("6eb6c957-01ba-4b3b-9c3a-155d119f46f1")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Remark("Loves Games and Anime"),
                getTagSet("NonMember"),  UUID.fromString("ae458f91-d7c5-49b2-b71c-164c1402db95")),
            new Person(new Name("Peter Lim"), new Phone("90018002"), new Email("peter@gmail.com"),
                    new Address("Blk 135 Ang Mo Kio Avenue 1 #02-12"), new Remark("Loves Football"),
                    getTagSet("Member"),  UUID.fromString("55e01e21-9ecc-468c-9c0d-48fd51893739")),
            new Person(new Name("Alex Tan"), new Phone("80021111"), new Email("Alex@hotmail.com"),
                    new Address("Thomson Street 13 #12-42"), new Remark("Allergic to Gluten"),
                    getTagSet("Member"),  UUID.fromString("58ec0244-fa7a-4bce-aa47-325832838ac4"))
        };
    }

    public static Order[] getSampleOrders() {
        return new Order[]{
            new Order(new Remark("No Peanuts"), getDetailsList("3:Strawberry Cupcakes"),
                    new DeliveryDateTime("25-12-2022 15:30"), CollectionType.DELIVERY,
                    UUID.fromString("6ea390c0-242c-4643-a547-21cc85773f88")),
            new Order(new Remark("Please make delivery by 4pm latest"), getDetailsList("1:Chocolate Cake"),
                    new DeliveryDateTime("22-12-2022 16:00"), CollectionType.DELIVERY,
                    UUID.fromString("6ea390c0-242c-4643-a547-21cc85773f88")),
            new Order(new Remark("No candles needed"), getDetailsList("1:Cake"),
                    new DeliveryDateTime("12-12-2022 12:30"), CollectionType.PICKUP,
                    UUID.fromString("0d13afaa-db95-4a2a-b64d-5270e39d243d")),
                new Order(new Remark("More yuzu flavour"), getDetailsList("2:Yuzu Lemon Cheese Cake"),
                        new DeliveryDateTime("15-08-2022 14:00"), CollectionType.DELIVERY,
                        UUID.fromString("556094dd-acde-462a-9d69-0f7cb1e3f34c")),
                new Order(new Remark(""), getDetailsList("1:Burnt basque cheesecake"),
                        new DeliveryDateTime("09-09-2022 19:00"), CollectionType.PICKUP,
                        UUID.fromString("a0f493f4-832d-46b3-9bcf-6da00b95f964")),
                new Order(new Remark(""), getDetailsList("1:Burnt basque cheesecake", "3:Kiwi Lemon Tart"),
                        new DeliveryDateTime("11-11-2022 19:00"), CollectionType.PICKUP,
                        UUID.fromString("a0f493f4-832d-46b3-9bcf-6da00b95f964")),
                new Order(new Remark("cake shaped in a football"), getDetailsList("1:Rainbow Cake"),
                        new DeliveryDateTime("10-10-2022 10:00"), CollectionType.DELIVERY,
                        UUID.fromString("0d13afaa-db95-4a2a-b64d-5270e39d243d")),
                new Order(new Remark("more chocolate"), getDetailsList("2:chocolate banana cake"),
                        new DeliveryDateTime("08-08-2022 13:00"), CollectionType.DELIVERY,
                        UUID.fromString("6eb6c957-01ba-4b3b-9c3a-155d119f46f1")),
                new Order(new Remark("Put anime figurines"), getDetailsList("1:Fruit Cake"),
                        new DeliveryDateTime("10-10-2022 14:30"), CollectionType.DELIVERY,
                        UUID.fromString("ae458f91-d7c5-49b2-b71c-164c1402db95")),
                new Order(new Remark(""), getDetailsList("1:Apple Pie", "3:Fruit Tart"),
                        new DeliveryDateTime("15-09-2022 12:00"), CollectionType.DELIVERY,
                        UUID.fromString("55e01e21-9ecc-468c-9c0d-48fd51893739")),
                new Order(new Remark(""), getDetailsList("1:Dark Chocolate Cake"),
                        new DeliveryDateTime("11-11-2022 11:11"), CollectionType.DELIVERY,
                        UUID.fromString("55e01e21-9ecc-468c-9c0d-48fd51893739")),
                new Order(new Remark("gluten free"), getDetailsList("1:Gluten Free Cheese Cake"),
                        new DeliveryDateTime("12-12-2022 14:00"), CollectionType.DELIVERY,
                        UUID.fromString("58ec0244-fa7a-4bce-aa47-325832838ac4")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Order sampleOrder : getSampleOrders()) {
            sampleAb.addOrder(sampleOrder);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static List<Details> getDetailsList(String... strings) {
        return Arrays.stream(strings)
                .map(Details::new)
                .collect(Collectors.toList());
    }

}

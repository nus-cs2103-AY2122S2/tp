package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Preference;
import seedu.address.model.person.UserType;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Region;
import seedu.address.model.property.Size;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new HashSet<>(),
                    Optional.of(new Preference(Region.WEST, Size.ONE_ROOM, new Price("$100000"), new Price("$200000"))),
                        new UserType("buyer"), new LinkedHashSet<>()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new HashSet<>(Arrays.asList(new Property(Region.SOUTH,
                            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                                Size.FOUR_ROOM, new Price ("$500000")))), Optional.empty(),
                                    new UserType("seller"), new LinkedHashSet<>()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new HashSet<>(),
                    Optional.of(new Preference(Region.EAST, Size.TWO_ROOM, new Price("$100000"), new Price("$150000"))),
                    new UserType("buyer"), new LinkedHashSet<>()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new HashSet<>(Arrays.asList(new Property(Region.SOUTH,
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                            Size.FOUR_ROOM, new Price ("$500000")))), Optional.empty(),
                                new UserType("seller"), new LinkedHashSet<>()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new HashSet<>(),
                    Optional.of(new Preference(Region.WEST, Size.TWO_ROOM, new Price("$50000"), new Price("$100000"))),
                    new UserType("buyer"), new LinkedHashSet<>()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new HashSet<>(),
                    Optional.of(new Preference(Region.SOUTH, Size.ONE_ROOM, new Price("$40000"), new Price("$90000"))),
                    new UserType("buyer"), new LinkedHashSet<>())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

}

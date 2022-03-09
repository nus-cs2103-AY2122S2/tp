package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Customer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSamplePersons() {
        return new Customer[] {
            new Customer(new Name("Alex Yeoh"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Phone("87438807")),
            new Customer(new Name("Bernice Yu"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Phone("99272758")),
            new Customer(new Name("Charlotte Oliveiro"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Phone("93210283")),
            new Customer(new Name("David Li"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Phone("91031282")),
            new Customer(new Name("Irfan Ibrahim"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Phone("92492021")),
            new Customer(new Name("Roy Balakrishnan"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Phone("92624417"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Customer samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

}

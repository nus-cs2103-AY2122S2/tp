package seedu.trackbeau.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.Hair;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.Skin;
import seedu.trackbeau.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TrackBeau} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSamplePersons() {
        return new Customer[] {
            new Customer(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Skin("oily"), new Hair("dry"),
                    getTagSet("Jason"), getTagSet("Acne", "Balding"), getTagSet("Nickel")),
            new Customer(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Skin("sensitive"), new Hair("oily"),
                getTagSet("Jane", "Lee"), getTagSet("Blackheads"), getTagSet("Steroids")),
            new Customer(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Skin("combination"), new Hair("dry"),
                getTagSet("Lee"), getTagSet("Hair loss"), getTagSet("Salicylic acid", "Aluminium compounds")),
            new Customer(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Skin("oily"), new Hair("dry"),
                    getTagSet("Jason"), getTagSet("Chemical peels"), getTagSet("Cobalt")),
            new Customer(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Skin("sensitive"), new Hair("oily"),
                    getTagSet("Jolene", "Lee"), getTagSet("Laser treatment"), getTagSet("Fragrances")),
            new Customer(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Skin("combination"), new Hair("dry"),
                    getTagSet("Lee"), getTagSet("Acne"), getTagSet("Cocoa butter")),
        };
    }

    public static ReadOnlyTrackBeau getSampleAddressBook() {
        TrackBeau sampleAb = new TrackBeau();
        for (Customer sampleCustomer : getSamplePersons()) {
            sampleAb.addCustomer(sampleCustomer);
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

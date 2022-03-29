package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.util.Pair;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Priority;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new InsurancePackage("Golden Package"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagList(new Pair<>("introduce to friends", null))),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new InsurancePackage("Silver Package"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagList(new Pair<>("update insurance package", Priority.PRIORITY_1),
                        new Pair<>("going to move abroad soon", null))),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new InsurancePackage("Undecided"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagList(new Pair<>("tell about Car insurance updates", Priority.PRIORITY_3))),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new InsurancePackage("Undecided"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagList(new Pair<>("contact wife if not available", null))),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new InsurancePackage("Basic Family Package"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagList()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new InsurancePackage("Car Theft Insurance"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagList(new Pair<>("update insurance package", Priority.PRIORITY_1)))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static ArrayList<Tag> getTagList(Pair<String, Priority>... tagPairs) {
        return new ArrayList<>(Arrays.stream(tagPairs)
                .map(x -> new Tag(x.getKey(), x.getValue()))
                .collect(Collectors.toList()));
    }

}

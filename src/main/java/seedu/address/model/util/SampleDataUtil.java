package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.entry.Address;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Email;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Location;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Person;
import seedu.address.model.entry.Phone;
import seedu.address.model.entry.Time;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Name("ABCDE"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), getTagSet("hr")),
            new Person(new Name("Bernice Yu"), new Name("DBSSS"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), getTagSet("senior")),
            new Person(new Name("Charlotte Oliveiro"), new Name("SGShop"), new Phone("93210283"),
                    new Email("charlotte@example.com"), getTagSet("hr")),
            new Person(new Name("David Li"), new Name("ABCDE"), new Phone("91031282"),
                    new Email("lidavid@example.com"), getTagSet("hr")),
            new Person(new Name("Irfan Ibrahim"), new Name("SGShop"), new Phone("92492021"),
                    new Email("irfan@example.com"), getTagSet("senior")),
            new Person(new Name("Roy Balakrishnan"), new Name("DBSSS"), new Phone("92624417"),
                    new Email("royb@example.com"), getTagSet("hr"))
        };
    }

    public static Company[] getSampleCompanies() {
        return new Company[] {
            new Company(new Name("ABCDE"), new Phone("12345678"), new Email("hr@ABCDE.com"),
                    new Address("123 Street Singapore"), getTagSet("OA")),
            new Company(new Name("DBSSS"), new Phone("23859694"), new Email("hr@DBSSS.com"),
                    new Address("456 Street Singapore"), getTagSet("interview")),
            new Company(new Name("SGShop"), new Phone("58496034"), new Email("hr@SGShop.com"),
                    new Address("789 Street Singapore"), getTagSet("applied"))
        };
    }

    public static Event[] getSampleEvents() {
        return new Event[] {
            new Event(new Name("Interview"), new Name("SGShop"), new Date("2022-09-16"), new Time("13:30"),
                    new Location("zoom"), getTagSet("technical")),
            new Event(new Name("Interview"), new Name("DBSSS"), new Date("2022-10-13"), new Time("12:30"),
                    new Location("zoom"), getTagSet("behavioural")),
            new Event(new Name("Online Assessment"), new Name("DBSSS"), new Date("2022-04-23"), new Time("14:30"),
                    new Location("hackerrank"), getTagSet("OOP")),
            new Event(new Name("Online Assessment"), new Name("SGShop"), new Date("2022-05-16"), new Time("15:30"),
                    new Location("hackereartch"), getTagSet("datastructure")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Company sampleCompany : getSampleCompanies()) {
            sampleAb.addCompany(sampleCompany);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
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

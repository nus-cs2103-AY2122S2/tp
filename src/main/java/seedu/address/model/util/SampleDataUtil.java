package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentID;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new StudentID("E0123456"), new Name("Alex Yeoh"), new Phone("87438807"), new Email("E0123456@u.nus.edu"),
                new Course("Business Analytics"), getTagSet("friends")),
            new Person(new StudentID("E0234567"), new Name("Bernice Yu"), new Phone("99272758"), new Email("E0234567@u.nus.edu"),
                new Course("Computer Engineering"), getTagSet("colleagues", "friends")),
            new Person(new StudentID("E0345678"), new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("E0345678@u.nus.edu"),
                new Course("Computer Science"), getTagSet("neighbours")),
            new Person(new StudentID("E0456789"), new Name("David Li"), new Phone("91031282"), new Email("E0456789@u.nus.edu"),
                new Course("Information Security"), getTagSet("family")),
            new Person(new StudentID("E0567890"), new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("E0567890@u.nus.edu"),
                new Course("Information Systems"), getTagSet("classmates")),
            new Person(new StudentID("E0678901"), new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("E0678901@u.nus.edu"),
                new Course("Computer Science"), getTagSet("colleagues"))
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
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}

package seedu.unite.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.Unite;
import seedu.unite.model.person.Address;
import seedu.unite.model.person.Course;
import seedu.unite.model.person.Email;
import seedu.unite.model.person.MatricCard;
import seedu.unite.model.person.Name;
import seedu.unite.model.person.Person;
import seedu.unite.model.person.Phone;
import seedu.unite.model.person.Telegram;
import seedu.unite.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Unite} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new Course("Computer Science"), new MatricCard("A1223456C"),
                new Telegram("alexyeoh123")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new Course("Math"), new MatricCard("A2343456D"),
                new Telegram("bernicenice")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new Course("Engineering"), new MatricCard("A1234123C"),
                new Telegram("charlotte123")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new Course("English"), new MatricCard("A7654321E"),
                new Telegram("DavidLiu")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new Course("Computer Science"), new MatricCard("A8172387C"),
                new Telegram("Ibrahim")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new Course("Science"), new MatricCard("A1928383L"),
                new Telegram("Balakrishnan"))
        };
    }

    public static ReadOnlyUnite getSampleUnite() {
        Unite sampleAb = new Unite();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
            Set<Tag> newTags = samplePerson.getTags();
            for (Tag newTag : newTags) {
                if (!sampleAb.hasTag(newTag)) {
                    sampleAb.addTag(newTag);
                }
            }
        }
        sampleAb.addTag(new Tag("Professors", "Consultation on next Monday"));
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

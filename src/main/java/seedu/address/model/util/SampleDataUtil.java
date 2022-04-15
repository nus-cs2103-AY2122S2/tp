package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.TaskBook;
import seedu.address.model.name.Name;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.EndTime;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), getNoteList("good offence"),
                getNoteList("bad defense"), getNoteList("likes bing chilling")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), getNoteList("good offence"),
                    getNoteList("bad defense"), getNoteList("surgery scheduled today")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), getNoteList("good offence"),
                    getNoteList("bad defense"), getNoteList("birthday next Monday")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), getNoteList("good offence"),
                    getNoteList("bad defense"), getNoteList("buy a new phone")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), getNoteList("good offence"),
                    getNoteList("bad defense"), getNoteList("play games together tonight")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), getNoteList("good offence"),
                    getNoteList("bad defense"), getNoteList("meeting with him this Sunday"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Shareholders Meeting"), new Date("10-04-2022"), new StartTime("09:00"),
                    new EndTime("12:00"), getTagSet("team1"), getPersonSet("Alex Yeoh")),
            new Task(new Name("Team Training"), new Date("11-04-2022"), new StartTime("09:00"),
                    new EndTime("12:00"), getTagSet("team1"), getPersonSet("Alex Yeoh", "Bernice Yu")),
            new Task(new Name("Annual Dinner"), new Date("11-04-2022"), new StartTime("18:00"),
                    new EndTime("20:00"), getTagSet("family", "friends"), getPersonSet("Irfan Ibrahim")),
            new Task(new Name("Recruitment Talk"), new Date("12-04-2022"), new StartTime("15:00"),
                    new EndTime("18:00"), getTagSet("school"), getPersonSet("Irfan Ibrahim"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyTaskBook getSampleTaskBook() {
        TaskBook sampleTb = new TaskBook();
        for (Task sampleTask : getSampleTasks()) {
            sampleTb.addTask(sampleTask);
        }
        return sampleTb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a person set containing the list of strings given.
     */
    public static Set<Name> getPersonSet(String... strings) {
        return Arrays.stream(strings)
                .map(Name::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a note list containing the list of strings given.
     */
    public static List<Note> getNoteList(String... strings) {
        return Arrays.stream(strings)
                .map(Note::new)
                .collect(Collectors.toList());
    }

}

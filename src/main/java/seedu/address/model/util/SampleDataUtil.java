package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ContactList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tasks.Deadline;
import seedu.address.model.tasks.Description;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.model.tasks.ReadOnlyTaskList;
import seedu.address.model.tasks.Task;

/**
 * Contains utility methods for populating {@code ContactList} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new StudentNumber("A8743880A"), new Email("alexyeoh@example.com"),
            new Mod("CS2030S"), new Group("T01"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new StudentNumber("A9927275B"), new Email("berniceyu@example.com"),
                new Mod("CS2030S"), new Group("T01"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new StudentNumber("A9321028C"),
                    new Email("charlotte@example.com"), new Mod("CS2030S"), new Group("T02"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new StudentNumber("A9103128D"), new Email("lidavid@example.com"),
                new Mod("CS2030S"), new Group("T03"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new StudentNumber("A9249202E"), new Email("irfan@example.com"),
                new Mod("CS2030S"), new Group("T02"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new StudentNumber("A9262441F"), new Email("royb@example.com"),
                new Mod("CS2030S"), new Group("T01"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyContactList getSampleContactList() {
        ContactList sampleAb = new ContactList();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static Mod[] getSampleModules() {
        return new Mod[] {
            new Mod("CS2108", "W12"),
            new Mod("CS2109S", "W14"),
            new Mod("CS2100", "W16"),
        };
    }

    public static UniqueModuleList getSampleModuleList() {
        UniqueModuleList moduleList = new UniqueModuleList();
        for (Mod mod : getSampleModules()) {
            moduleList.add(mod);
        }
        return moduleList;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Description("Check CS2100 Assignment 1 submissions"), new Deadline("2022-03-21T18:16")),
            new Task(new Description("Mark CS2030S Lab 6"), new Deadline("2022-03-27T23:59"))
        };
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        PriorityTaskList sampleAb = new PriorityTaskList();
        for (Task task : getSampleTasks()) {
            sampleAb.add(task);
        }
        return sampleAb;
    }
}

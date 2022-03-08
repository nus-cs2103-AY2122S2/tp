package unibook.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import unibook.model.ReadOnlyUniBook;
import unibook.model.UniBook;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleName;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Person;
import unibook.model.person.Phone;
import unibook.model.person.Student;
import unibook.model.tag.Tag;

/**
 * Contains utility methods for populating {@code UniBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("friends"), getModuleSet("CS2106")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends"), getModuleSet("CS2103")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getTagSet("neighbours"), getModuleSet("CS2103")),
        };
    }

    public static Module[] getSampleModules() {
        Module testModule1 = new Module(new ModuleName("Software Engineering"), new ModuleCode("CS2103"));
        Module testModule2 = new Module(new ModuleName("Introduction to Operating Systems"), new ModuleCode("CS2106"));

        return new Module[] {testModule1, testModule2};
    }

    public static ReadOnlyUniBook getSampleUniBook() {
        UniBook sampleAb = new UniBook();

        //modules[0] == cs2103, modules[1] == cs2106
        Module[] modules = getSampleModules();

        //Initialising test module sets to pass into Student constructor
        Set<Module> testModuleSet1 = new HashSet<>();
        Set<Module> testModuleSet2 = new HashSet<>();
        Set<Module> testModuleSet3 = new HashSet<>();

        //Test module sets for s1, s2, s3 respectively
        testModuleSet1.add(modules[1]);
        testModuleSet2.add(modules[0]);
        testModuleSet3.add(modules[0]);

        //Initialising student objects
        Student s1 = new Student(new Name("Alex Yeoh"),
                new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("friends"), testModuleSet1);
        Student s2 = new Student(new Name("Bernice Yu"),
                new Phone("99272758"), new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends"), testModuleSet2);
        Student s3 = new Student(new Name("Charlotte Oliveiro"),
                new Phone("93210283"), new Email("charlotte@example.com"),
                getTagSet("neighbours"), testModuleSet3);

        //Add students to module's student list
        modules[1].addStudent(s1);
        modules[0].addStudent(s2);
        modules[0].addStudent(s3);

        //Add students to sample Unibook
        sampleAb.addPerson(s1);
        sampleAb.addPerson(s2);
        sampleAb.addPerson(s3);

        //Add modules to sample Unibook
        sampleAb.addModule(modules[0]);
        sampleAb.addModule(modules[1]);
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

    /**
     * Returns a set of Module objects that have the given moduleCodes.
     *
     * @param moduleCodes module codes of the module objects
     * @return set of Module objects
     */
    public static Set<Module> getModuleSet(String... moduleCodes) {
        return Arrays.stream(moduleCodes)
            .map(moduleCode -> new Module(new ModuleName("placeholder module"), new ModuleCode(moduleCode)))
            .collect(Collectors.toSet());
    }

}

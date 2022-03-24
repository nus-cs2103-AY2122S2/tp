package unibook.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.model.ReadOnlyUniBook;
import unibook.model.UniBook;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
import unibook.model.module.ModuleName;
import unibook.model.module.group.Group;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Office;
import unibook.model.person.Phone;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.model.tag.Tag;

/**
 * Contains utility methods for populating {@code UniBook} with sample data.
 */
public class SampleDataUtil {

    /**
     * Instantiates sample modules.
     *
     * @return
     */
    public static Module[] getSampleModules() {
        Module sampleModule1 = new Module(new ModuleName("Software Engineering"), new ModuleCode("CS2103"));
        Module sampleModule2 = new Module(new ModuleName("Introduction to Operating Systems"),
                new ModuleCode("CS2106"));

        return new Module[] {sampleModule1, sampleModule2};
    }

    /**
     * Instantiates sample groups, using passed in modules array.
     *
     * @param sampleModules
     * @return array of sample groups.
     */
    public static Group[] getSampleGroups(Module[] sampleModules) {
        //Sample meeting time collection for group
        ObservableList<LocalDateTime> sampleMeetingTimes1 = FXCollections.observableArrayList();
        sampleMeetingTimes1.add(LocalDateTime.of(2022, 5, 4, 13, 0));
        Group sampleGroup1 = new Group("W16-1", sampleModules[0], sampleMeetingTimes1);
        return new Group[] {sampleGroup1};
    }

    /**
     * Instantiates sample key events, using passed in modules array.
     * @param sampleModules
     * @return array of key events
     */
    public static ModuleKeyEvent[] getSampleEvents(Module[] sampleModules) {
        LocalDateTime sampleTime = LocalDateTime.of(2022, 5, 4, 14, 0);
        ModuleKeyEvent sampleModuleKeyEvent1 = new ModuleKeyEvent(ModuleKeyEvent.KeyEventType.EXAM, sampleTime,
                sampleModules[0]);
        return new ModuleKeyEvent[] {sampleModuleKeyEvent1};
    }


    public static ReadOnlyUniBook getSampleUniBook() {
        UniBook sampleAb = new UniBook();

        //modules[0] == cs2103, modules[1] == cs2106
        Module[] modules = getSampleModules();

        //groups[0] == W16-1
        Group[] groups = getSampleGroups(modules);

        //keyEvents[0] = exam on May 4th, 2022 at 2PM
        ModuleKeyEvent[] keyEvents = getSampleEvents(modules);

        //Initialising sample module sets to pass into Student constructor
        Set<Module> sampleModuleSet1 = new HashSet<>();
        Set<Module> sampleModuleSet2 = new HashSet<>();
        Set<Module> sampleModuleSet3 = new HashSet<>();

        //Sample module sets for s1, s2, p1 respectively
        sampleModuleSet1.add(modules[1]);
        sampleModuleSet2.add(modules[0]);
        sampleModuleSet3.add(modules[0]);

        //Initialising sample group sets to pass into Student constructor
        Set<Group> sampleGroupSet1 = new HashSet<>();

        //Sample group sets for s1
        sampleGroupSet1.add(groups[0]);

        //Initialising students and professor objects
        Student s1 = new Student(new Name("Alex Yeoh"),
            new Phone("87438807"), new Email("alexyeoh@example.com"),
            getTagSet("friend", "roommate"), sampleModuleSet1, sampleGroupSet1);
        Student s2 = new Student(new Name("Bernice Yu"),
            new Phone("99272758"), new Email("berniceyu@example.com"),
            getTagSet("friend"), sampleModuleSet2, sampleGroupSet1);
        Professor p1 = new Professor(new Name("Charlotte Oliveiro"),
            new Phone("93210283"), new Email("charlotte@example.com"),
            getTagSet("helpful"), new Office("COM1 02-10"),
            sampleModuleSet3);

        //Add persons to their module's list of associated people
        modules[1].addStudent(s1);
        modules[0].addStudent(s2);
        modules[0].addProfessor(p1);

        //Add students to their groups
        groups[0].addMember(s1);

        //Add people to sample Unibook
        sampleAb.addPerson(s1);
        sampleAb.addPerson(s2);
        sampleAb.addPerson(p1);

        //Add modules to sample Unibook
        sampleAb.addModule(modules[0]);
        sampleAb.addModule(modules[1]);

        //Add groups to sample Unibook
        sampleAb.addGroupToModule(groups[0]);

        //Add key events to sample Unibook
        sampleAb.addKeyEventToModule(keyEvents[0]);

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

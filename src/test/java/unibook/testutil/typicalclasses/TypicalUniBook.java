package unibook.testutil.typicalclasses;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unibook.model.UniBook;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleKeyEvent;
import unibook.model.module.ModuleName;
import unibook.model.module.group.Group;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Office;
import unibook.model.person.Person;
import unibook.model.person.Phone;
import unibook.model.person.Professor;
import unibook.model.person.Student;
import unibook.model.tag.Tag;

/**
 * Contains method to instantiate a typical instance of UniBook.
 */
public class TypicalUniBook {

    //Data for objects already in typical unibook
    public static final String VALID_MODULE_NAME_CS2103 = "Software Engineering";
    public static final String VALID_MODULE_NAME_CS2106 = "Introduction to Operating Systems";
    public static final String VALID_MODULE_CODE_CS2106 = "CS2106";
    public static final String VALID_MODULE_CODE_CS2103 = "CS2103";
    public static final String VALID_NAME_STUDENT_ALEX = "Alex Yeoh";
    public static final String VALID_NAME_STUDENT_BERNICE = "Bernice Yu";
    public static final String VALID_NAME_PROFESSOR_CHARLOTTE = "Charlotte Oliveiro";
    public static final String VALID_PHONE_STUDENT_ALEX = "87438807";
    public static final String VALID_PHONE_STUDENT_BERNICE = "99272758";
    public static final String VALID_PHONE_PROFESSOR_CHARLOTTE = "93210283";
    public static final String VALID_EMAIL_STUDENT_ALEX = "alexyeoh@example.com";
    public static final String VALID_EMAIL_STUDENT_BERNICE = "berniceyu@example.com";
    public static final String VALID_EMAIL_PROFESSOR_CHARLOTTE = "charlotte@example.com";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_ROOMMATE = "roommate";
    public static final String VALID_TAG_HELPFUL = "helpful";
    public static final LocalDateTime VALID_MEETING_TIME =
        LocalDateTime.of(2022, 5, 4, 13, 0);
    public static final String VALID_GROUP_NAME = "W16-1";
    public static final String VALID_OFFICE_1 = "COM1 02-10";
    public static final ModuleKeyEvent.KeyEventType VALID_EVENT_TYPE = ModuleKeyEvent.KeyEventType.valueOf("EXAM");
    public static final LocalDateTime VALID_EVENT_TIME =
            LocalDateTime.of(2022, 5, 4, 14, 0);

    /**
     * Generate the typical modules.
     *
     * @return array of typical modules
     */
    public static Module[] generateTypicalModules() {
        Module sampleModule1 =
            new Module(new ModuleName(VALID_MODULE_NAME_CS2103), new ModuleCode(VALID_MODULE_CODE_CS2103));
        Module sampleModule2 =
            new Module(new ModuleName(VALID_MODULE_NAME_CS2106), new ModuleCode(VALID_MODULE_CODE_CS2106));

        return new Module[] {sampleModule1, sampleModule2};
    }

    /**
     * Generate the typical groups.
     *
     * @param typicalModules typical modules that typical groups are under.
     * @return array of typical groups.
     */
    public static Group[] generateTypicalGroups(Module[] typicalModules) {
        //Sample meeting time collection for group
        ObservableList<LocalDateTime> typicalMeetingTimes1 = FXCollections.observableArrayList();
        typicalMeetingTimes1.add(VALID_MEETING_TIME);

        Group sampleGroup1 = new Group(VALID_GROUP_NAME, typicalModules[0], typicalMeetingTimes1);
        return new Group[] {sampleGroup1};
    }

    /**
     * Instantiates sample key events, using passed in modules array.
     * @param sampleModules
     * @return array of key events
     */
    public static ModuleKeyEvent[] getSampleEvents(Module[] sampleModules) {
        ModuleKeyEvent sampleModuleKeyEvent1 = new ModuleKeyEvent(VALID_EVENT_TYPE, VALID_EVENT_TIME,
                sampleModules[0]);
        return new ModuleKeyEvent[] {sampleModuleKeyEvent1};
    }

    /**
     * Generate a typical instance of UniBook.
     */
    public static UniBook getTypicalUniBook() {
        UniBook typicalUniBook = new UniBook();

        //modules[0] == cs2103, modules[1] == cs2106
        Module[] modules = generateTypicalModules();

        //groups[0] == W16-1
        Group[] groups = generateTypicalGroups(modules);

        //keyEvents[0] = exam on May 4th, 2022 at 2PM
        ModuleKeyEvent[] keyEvents = getSampleEvents(modules);

        //Initialising sample module sets to pass into Student constructor
        Set<Module> typicalModuleSet1 = new HashSet<>();
        Set<Module> typicalModuleSet2 = new HashSet<>();
        Set<Module> typicalModuleSet3 = new HashSet<>();

        //Sample module sets for s1, s2, p1 respectively
        typicalModuleSet1.add(modules[1]);
        typicalModuleSet2.add(modules[0]);
        typicalModuleSet3.add(modules[0]);

        //Initialising sample group sets to pass into Student constructor
        Set<Group> typicalGroupSet1 = new HashSet<>();

        //Sample group sets for s1
        typicalGroupSet1.add(groups[0]);

        //Initialising students and professor objects
        Student s1 = new Student(new Name(VALID_NAME_STUDENT_ALEX),
            new Phone(VALID_PHONE_STUDENT_ALEX), new Email(VALID_EMAIL_STUDENT_ALEX),
            getTagSet(VALID_TAG_FRIEND, VALID_TAG_ROOMMATE), typicalModuleSet1, typicalGroupSet1);
        Student s2 = new Student(new Name(VALID_NAME_STUDENT_BERNICE),
            new Phone(VALID_PHONE_STUDENT_BERNICE), new Email(VALID_EMAIL_STUDENT_BERNICE),
            getTagSet(VALID_TAG_FRIEND), typicalModuleSet2, typicalGroupSet1);
        Professor p1 = new Professor(new Name(VALID_NAME_PROFESSOR_CHARLOTTE),
            new Phone(VALID_PHONE_PROFESSOR_CHARLOTTE), new Email(VALID_EMAIL_PROFESSOR_CHARLOTTE),
            getTagSet(VALID_TAG_HELPFUL), new Office(VALID_OFFICE_1),
            typicalModuleSet3);

        //Add persons to their module's list of associated people
        modules[1].addStudent(s1);
        modules[0].addStudent(s2);
        modules[0].addProfessor(p1);

        //Add students to their groups
        groups[0].addMember(s1);

        //Add people to sample Unibook
        typicalUniBook.addPerson(s1);
        typicalUniBook.addPerson(s2);
        typicalUniBook.addPerson(p1);

        //Add modules to sample Unibook
        typicalUniBook.addModule(modules[0]);
        typicalUniBook.addModule(modules[1]);

        //Add groups to sample Unibook
        typicalUniBook.addGroupToModule(groups[0]);

        //Add key events to modules in sample Unibook
        typicalUniBook.addKeyEventToModule(keyEvents[0]);
        return typicalUniBook;
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
     * Returns the typical modules in typical unibook as a list.
     */
    public static List<Module> getTypicalModules() {
        return new ArrayList<>(getTypicalUniBook().getModuleList());
    }

    /**
     * Returns the typical persons in typical unibook as a list.
     *
     * @return
     */
    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(getTypicalUniBook().getPersonList());
    }
}

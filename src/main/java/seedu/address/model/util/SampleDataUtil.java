package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTAssist;
import seedu.address.model.TAssist;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.classgroup.ClassGroupType;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.ModuleName;
import seedu.address.model.tamodule.TaModule;

/**
 * Contains utility methods for populating {@code TAssist} with sample data.
 */
public class SampleDataUtil {

    /** TODO: To be removed. */
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new seedu.address.model.person.Name("Alex Yeoh"),
                    new seedu.address.model.person.Phone("87438807"),
                    new seedu.address.model.person.Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends")),
            new Person(new seedu.address.model.person.Name("Bernice Yu"),
                    new seedu.address.model.person.Phone("99272758"),
                    new seedu.address.model.person.Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Person(new seedu.address.model.person.Name("Charlotte Oliveiro"),
                    new seedu.address.model.person.Phone("93210283"),
                    new seedu.address.model.person.Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new Person(new seedu.address.model.person.Name("David Li"),
                    new seedu.address.model.person.Phone("91031282"),
                    new seedu.address.model.person.Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family")),
        };
    }

    /** TODO: To be removed. */
    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (TaModule sampleModule : getSampleModules()) {
            sampleAb.addModule(sampleModule);
        }
        for (ClassGroup sampleClassGroup : getSampleClassGroups()) {
            sampleAb.addClassGroup(sampleClassGroup);
        }
        return sampleAb;
    }

    /**
     * TODO: To be removed.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new StudentId("E0123456"), new Name("Alex Yeoh"),
                    new Phone("87438807"), new Email("alexyeoh@example.com")),
            new Student(new StudentId("E0123457"), new Name("Bernice Yu"),
                    new Phone("99272758"), new Email("berniceyu@example.com")),
            new Student(new StudentId("E0123458"), new Name("Charlotte Oliveiro"),
                    new Phone("93210283"), new Email("charlotte@example.com")),
            new Student(new StudentId("E0123459"), new Name("David Li"),
                    new Phone("91031282"), new Email("lidavid@example.com")),
            new Student(new StudentId("E0123460"), new Name("Irfan Ibrahim"),
                    new Phone("92492021"), new Email("irfan@example.com")),
            new Student(new StudentId("E0123461"), new Name("Roy Balakrishnan"),
                    new Phone("92624417"), new Email("royb@example.com"))
        };
    }

    public static TaModule[] getSampleModules() {
        return new TaModule[] {
            new TaModule(new ModuleName("Software Engineering"),
                    new ModuleCode("CS2103T"), new AcademicYear("21S1")),
            new TaModule(new ModuleName("Programming Methodology II"),
                    new ModuleCode("CS2030"), new AcademicYear("21S1")),
            new TaModule(new ModuleName("Data Structures and Algorithms"),
                    new ModuleCode("CS2040"), new AcademicYear("21S1"))
        };
    }

    public static ClassGroup[] getSampleClassGroups() {
        TaModule[] taModules = getSampleModules();
        return new ClassGroup[] {
            new ClassGroup(new ClassGroupId("G01"),
                    ClassGroupType.TUTORIAL, taModules[0]),
            new ClassGroup(new ClassGroupId("B10A"),
                    ClassGroupType.LAB, taModules[2]),
            new ClassGroup(new ClassGroupId("S01"),
                    ClassGroupType.SECTIONAL, taModules[0]),
            new ClassGroup(new ClassGroupId("R03"),
                    ClassGroupType.RECITATION, taModules[1])
        };
    }

    public static ReadOnlyTAssist getSampleTAssist() {
        TAssist sampleTAssist = new TAssist();
        for (Student sampleStudent : getSampleStudents()) {
            sampleTAssist.addStudent(sampleStudent);
        }
        for (TaModule sampleModule : getSampleModules()) {
            sampleTAssist.addModule(sampleModule);
        }
        for (ClassGroup sampleClassGroup : getSampleClassGroups()) {
            sampleTAssist.addClassGroup(sampleClassGroup);
        }
        return sampleTAssist;
    }

}

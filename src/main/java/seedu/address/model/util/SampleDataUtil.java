package seedu.address.model.util;

import java.util.Optional;

import seedu.address.model.ReadOnlyTAssist;
import seedu.address.model.TAssist;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.SimpleName;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.classgroup.ClassGroupType;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.Telegram;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.ModuleName;
import seedu.address.model.tamodule.TaModule;

/**
 * Contains utility methods for populating {@code TAssist} with sample data.
 */
public class SampleDataUtil {

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new StudentId("E0123456"), new Name("Alex Yeoh"),
                    new Email("alexyeoh@example.com"), Optional.of(new Telegram("alexyeoh"))),
            new Student(new StudentId("E0123457"), new Name("Bernice Yu"),
                    new Email("berniceyu@example.com"), Optional.of(new Telegram("bernice_yu"))),
            new Student(new StudentId("E0123458"), new Name("Charlotte Oliveiro"),
                    new Email("charlotte@example.com"), Optional.of(new Telegram("Char_Oliverio"))),
            new Student(new StudentId("E0123459"), new Name("David Li"),
                    new Email("lidavid@example.com"), Optional.of(new Telegram("David12Li"))),
            new Student(new StudentId("E0123460"), new Name("Irfan Ibrahim"),
                    new Email("irfan@example.com"), Optional.of(new Telegram("_irfanIbrahim_"))),
            new Student(new StudentId("E0123461"), new Name("Roy Balakrishnan"),
                    new Email("royb@example.com"))
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

    public static Assessment[] getSampleAssessment() {
        TaModule[] taModules = getSampleModules();
        return new Assessment[] {
            new Assessment(new AssessmentName("Participation"), taModules[0], Optional.of(new SimpleName("part"))),
            new Assessment(new AssessmentName("Participation"), taModules[1], Optional.of(new SimpleName("part"))),
            new Assessment(new AssessmentName("Lab 1"), taModules[1]),
            new Assessment(new AssessmentName("Lab 2"), taModules[1]),
            new Assessment(new AssessmentName("Lab 3"), taModules[1]),
            new Assessment(new AssessmentName("Participation"), taModules[2], Optional.of(new SimpleName("part"))),
            new Assessment(new AssessmentName("Lab 1"), taModules[2], Optional.of(new SimpleName("lab1"))),
            new Assessment(new AssessmentName("Lab 2"), taModules[2], Optional.of(new SimpleName("lab2"))),
            new Assessment(new AssessmentName("Lab 3"), taModules[2], Optional.of(new SimpleName("lab3")))
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
        for (Assessment sampleAssessment : getSampleAssessment()) {
            sampleTAssist.addAssessment(sampleAssessment);
        }
        return sampleTAssist;
    }

}

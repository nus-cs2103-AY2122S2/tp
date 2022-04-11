package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.LessonBook;
import seedu.address.model.ReadOnlyLessonBook;
import seedu.address.model.ReadOnlyStudentBook;
import seedu.address.model.StudentBook;
import seedu.address.model.lesson.DateTimeSlot;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonAddress;
import seedu.address.model.lesson.LessonName;
import seedu.address.model.lesson.Subject;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code StudentBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Lesson[] getSampleLessons() {
        DateTimeSlot firstLessonDateTimeSlot = new DateTimeSlot(
                LocalDateTime.of(2022, 1, 5, 17, 50, 0),
                2, 0);
        DateTimeSlot secondLessonDateTimeSlot = new DateTimeSlot(
                LocalDateTime.of(2022, 1, 7, 18, 0, 0),
                2, 0);
        DateTimeSlot thirdLessonDateTimeSlot = new DateTimeSlot(
                LocalDateTime.of(2022, 1, 9, 0, 50),
                2, 5);

        return new Lesson[] {
            Lesson.makeTemporaryLesson(new LessonName("Make up lesson for George"), new Subject("Geography"),
                    new LessonAddress("Blk 47 Tampines Street 20, #17-35"), firstLessonDateTimeSlot),
            Lesson.makeTemporaryLesson(new LessonName("Trial lesson for Jake"), new Subject("Biology"),
                    new LessonAddress("Blk 47 Tampines Street 20, #17-35"), secondLessonDateTimeSlot),
            Lesson.makeTemporaryLesson(new LessonName("Make up lesson for Henry"), new Subject("Physics"),
                    new LessonAddress("Blk 47 Tampines Street 20, #17-35"), thirdLessonDateTimeSlot)
        };
    }

    public static ReadOnlyStudentBook getSampleAddressBook() {
        StudentBook sampleAb = new StudentBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    public static ReadOnlyLessonBook getSampleLessonBook() {
        LessonBook sampleLb = new LessonBook();
        for (Lesson sampleLesson : getSampleLessons()) {
            sampleLb.addLesson(sampleLesson);
        }
        return sampleLb;
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

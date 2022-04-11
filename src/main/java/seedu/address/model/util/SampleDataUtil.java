package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Email;
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Telegram(null), new Email("alexyeoh@u.nus.edu"),
                new GitHub("alex-yeoh"),
                getTutorialGroupSet("CS2100 G01", "CS3230 T03")),
            new Student(new Name("Bernice Yu"), new Telegram("bernice01"), new Email("berniceyu@u.nus.edu"),
                new GitHub(null),
                getTutorialGroupSet("CS2100 G01", "CS2106 T05")),
            new Student(new Name("Charlotte Oliveiro"), new Telegram("@CharlotteO"), new Email("charlotte@u.nus.edu"),
                new GitHub("charlotte-oliveiro"),
                getTutorialGroupSet("CS2106 T05")),
            new Student(new Name("David Li"), new Telegram(null), new Email("callmedavid@u.nus.edu"),
                new GitHub(null),
                getTutorialGroupSet("CS2100 G01")),
            new Student(new Name("Irfan Ibrahim"), new Telegram("irfanIbrahim"), new Email("irfan@u.nus.edu"),
                new GitHub("irfan-the-jet-plane"),
                getTutorialGroupSet("CS2100 G01", "CS2106 T05")),
            new Student(new Name("Roy Balakrishnan"), new Telegram("@royBalakrishnan"), new Email("royb@u.nus.edu"),
                new GitHub("roy-balakrishnan"),
                getTutorialGroupSet("CS2106 T05", "CS3230 T03"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns a tutorial group set containing the list of strings given.
     */
    public static Set<TutorialGroup> getTutorialGroupSet(String... strings) {
        return Arrays.stream(strings)
                .map(TutorialGroup::new)
                .collect(Collectors.toSet());
    }

}

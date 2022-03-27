package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabList;
import seedu.address.model.student.Email;
import seedu.address.model.student.GithubUsername;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[]{
            new Student(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), getTagSet("friends"),
                new GithubUsername("alexyeoh"), new Telegram("alex_yeoh"), new StudentId("A0123456B")),
            new Student(new Name("Bernice Yu"), new Email("berniceyu@example.com"), getTagSet("colleagues", "friends"),
                new GithubUsername("berniceyu"), new Telegram("bernice_yu"), new StudentId("A1234567C")),
            new Student(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"), getTagSet("neighbors"),
                new GithubUsername("charlotteoliveiro"), new Telegram("charlotte_oliverio"),
                new StudentId("A0123457C")),
            new Student(new Name("David Li"), new Email("lidavid@example.com"), getTagSet("family"),
                new GithubUsername("davidli"), new Telegram("david_li"), new StudentId("A0123458D")),
            new Student(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), getTagSet("classmates"),
                new GithubUsername("irfanibrahim"), new Telegram("irfan_ibrahim"), new StudentId("A0123459E")),
            new Student(new Name("Roy Balakrishnan"), new Email("royb@example.com"), getTagSet("colleagues"),
                new GithubUsername("roybalakrishnan"), new Telegram("roy_balakrishnan"),
                new StudentId("A0123450F"))
        };
    }

    /**
     * Returns a sample TAddressBook based on the sample students.
     */
    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
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
     * Returns a LabList containing the list of strings given.
     */
    public static LabList getLabSet(LabTriplet... strings) {
        LabList ll = new LabList();

        ll.setLabs(Arrays.stream(strings)
                .map(x -> (new Lab(x.getLabNumber()).of(x.getLabStatus(), x.getLabMark())))
                .collect(Collectors.toList()));
        return ll;
    }

}

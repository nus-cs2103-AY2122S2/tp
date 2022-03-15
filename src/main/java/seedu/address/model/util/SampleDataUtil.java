package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.candidate.Course;
import seedu.address.model.candidate.Email;
import seedu.address.model.candidate.Name;
import seedu.address.model.candidate.Phone;
import seedu.address.model.candidate.StudentID;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Candidate[] getSamplePersons() {
        return new Candidate[] {
            new Candidate(new StudentID("E0123456"), new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("E0123456@u.nus.edu"), new Course("Business Analytics"), getTagSet("friends")),
            new Candidate(new StudentID("E0234567"), new Name("Bernice Yu"), new Phone("99272758"),
                new Email("E0234567@u.nus.edu"), new Course("Computer Engineering"),
                getTagSet("colleagues", "friends")),
            new Candidate(new StudentID("E0345678"), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("E0345678@u.nus.edu"), new Course("Computer Science"), getTagSet("neighbours")),
            new Candidate(new StudentID("E0456789"), new Name("David Li"), new Phone("91031282"),
                new Email("E0456789@u.nus.edu"), new Course("Information Security"), getTagSet("family")),
            new Candidate(new StudentID("E0567890"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                new Email("E0567890@u.nus.edu"), new Course("Information Systems"), getTagSet("classmates")),
            new Candidate(new StudentID("E0678901"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                new Email("E0678901@u.nus.edu"), new Course("Computer Science"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Candidate sampleCandidate : getSamplePersons()) {
            sampleAb.addPerson(sampleCandidate);
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

}

package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Age;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Gender;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.position.Requirement;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Applicant[] getSamplePersons() {
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Age("21"), new Address("Blk 30 Geylang Street 29, #06-40"), new Gender("M"),
                getTagSet("friends")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Age("22"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Gender("F"),
                getTagSet("colleagues", "friends")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Age("23"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Gender("F"),
                getTagSet("neighbours")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Age("24"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Gender("M"),
                getTagSet("family")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Age("25"), new Address("Blk 47 Tampines Street 20, #17-35"), new Gender("M"),
                getTagSet("classmates")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Age("26"), new Address("Blk 45 Aljunied Street 85, #11-31"), new Gender("M"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Applicant sampleApplicant : getSamplePersons()) {
            sampleAb.addPerson(sampleApplicant);
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
     * Returns a requirement set containing the list of strings given.
     */
    public static Set<Requirement> getRequirementSet(String... strings) {
        return Arrays.stream(strings)
                .map(Requirement::new)
                .collect(Collectors.toSet());
    }
}

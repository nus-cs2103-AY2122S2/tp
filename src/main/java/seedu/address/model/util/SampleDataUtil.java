package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.CovidStatus;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Block("A"), new Faculty("SOC"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                        new MatriculationNumber("a1234567m"), new CovidStatus("hrn"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Block("A"), new Faculty("FOS"), new Phone("99272758"),
                        new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        new MatriculationNumber("a7364736m"), new CovidStatus("negative"),
                        getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Block("B"), new Faculty("FOD"), new Phone("93210283"),
                        new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        new MatriculationNumber("a4724567m"), new CovidStatus("hrn"), getTagSet("neighbours")),
            new Person(new Name("David Li"), new Block("C"), new Faculty("FOL"), new Phone("91031282"),
                        new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        new MatriculationNumber("a1234423m"), new CovidStatus("negative"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Block("D"), new Faculty("FASS"), new Phone("92492021"),
                        new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                        new MatriculationNumber("a6374567m"), new CovidStatus("hrn"),
                        getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Block("E"), new Faculty("BIZ"), new Phone("92624417"),
                        new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                        new MatriculationNumber("a8273645m"), new CovidStatus("positive"),
                    getTagSet("colleagues")),
            new Person(new Name("Vivian Alex"), new Block("E"), new Faculty("SOC"), new Phone("94482014"),
                        new Email("Viviana@hotmail.com"), new Address("33 Crescent Way"),
                        new MatriculationNumber("a0215656g"), new CovidStatus("HRN"), getTagSet("Leader")),
            new Person(new Name("Michael Alex"), new Block("D"), new Faculty("BIZ"), new Phone("94405502"),
                        new Email("MichaelHandsome@hotmail.com"), new Address("33 Pasir Ris Drive"),
                        new MatriculationNumber("a0113432e"), new CovidStatus("positive"),
                    getTagSet("Leader")),
            new Person(new Name("Alex Ferguson"), new Block("C"), new Faculty("FOL"), new Phone("87880914"),
                        new Email("heyalexa@gmail.com"), new Address("12 Kent Ridge Drive, #22-22"),
                        new MatriculationNumber("a0014988b"), new CovidStatus("HRN"), getTagSet("Leader")),
            new Person(new Name("Jurgen Klopp"), new Block("E"), new Faculty("FASS"), new Phone("92882028"),
                        new Email("champs@ynwa.com"), new Address("74 Serangoon Avenue, #9-18"),
                        new MatriculationNumber("a0436372e"), new CovidStatus("negative"), getTagSet("Leader"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

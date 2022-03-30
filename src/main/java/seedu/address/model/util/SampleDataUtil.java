package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.util.Pair;
import seedu.address.model.AddressBook;
import seedu.address.model.InsurancePackagesSet;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Priority;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    private static final int NUM_SAMPLES = 6;

    private static final List<Name> sampleNames = List.of(
            new Name("Alex Yeoh"), new Name("Bernice Yu"), new Name("Charlotte Oliveiro"),
            new Name("David Li"), new Name("Irfan Ibrahim"), new Name("Roy Balakrishnan"));

    private static final List<Phone> samplePhones = List.of(
            new Phone("87438807"), new Phone("99272758"), new Phone("93210283"),
            new Phone("91031282"), new Phone("92492021"), new Phone("92624417"));

    private static final List<Email> sampleEmails = List.of(
            new Email("alexyeoh@example.com"), new Email("berniceyu@example.com"),
            new Email("charlotte@example.com"), new Email("lidavid@example.com"),
            new Email("irfan@example.com"), new Email("royb@example.com"));

    private static final List<InsurancePackage> samplePackages = List.of(
            new InsurancePackage("Golden Package", "Luxurious coverage for many aspects"),
            new InsurancePackage("Silver Package", "Plenty coverage for most aspects"),
            new InsurancePackage("Bronze Package", "An affordable package to cover essential aspects"),
            new InsurancePackage("Golden Plus Package", "A VIP Special for those already under the Golden Package"),
            new InsurancePackage("Basic Package", "Basic insurance package to cover basic needs"),
            new InsurancePackage("Theft Insurance Package", "For theft protection"));

    private static final List<Address> sampleAddresses = List.of(
            new Address("Blk 30 Geylang Street 29, #06-40"),
            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
            new Address("Blk 47 Tampines Street 20, #17-35"),
            new Address("Blk 45 Aljunied Street 85, #11-31"));

    private static final List<List<Tag>> sampleTags = List.of(
            getTagList(new Pair<>("introduce to friends", null)),
            getTagList(new Pair<>("going to move abroad soon", Priority.PRIORITY_1)),
            getTagList(new Pair<>("tell about Car insurance updates", Priority.PRIORITY_3)),
            getTagList(new Pair<>("contact wife if not available", null)),
            getTagList(),
            getTagList(new Pair<>("update insurance package", Priority.PRIORITY_1)));

    private static Person[] samplePersons = null;

    public static Person[] getSamplePersons() {
        if (samplePersons == null) {
            samplePersons = new Person[NUM_SAMPLES];
            for (int i = 0; i < NUM_SAMPLES; i++) {
                samplePersons[i] = new Person(
                    sampleNames.get(i),
                    samplePhones.get(i),
                    sampleEmails.get(i),
                    samplePackages.get(i),
                    sampleAddresses.get(i),
                    (ArrayList<Tag>) sampleTags.get(i)
                );
            }
        }
        return samplePersons;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static InsurancePackagesSet getSampleInsurancePackages() {
        InsurancePackagesSet sampleIp = new InsurancePackagesSet();
        for (InsurancePackage samplePackage : samplePackages) {
            sampleIp.addPackage(samplePackage);
        }
        return sampleIp;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static ArrayList<Tag> getTagList(Pair<String, Priority>... tagPairs) {
        return new ArrayList<>(Arrays.stream(tagPairs)
                .map(x -> new Tag(x.getKey(), x.getValue()))
                .collect(Collectors.toList()));
    }

}

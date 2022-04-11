package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cca;
import seedu.address.model.person.Education;
import seedu.address.model.person.Email;
import seedu.address.model.person.Internship;
import seedu.address.model.person.Module;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final List<Tag> ALEX_EDUCATIONS = Arrays.asList(
            new Education[]{new Education("computer science"), new Education("nus")});
    private static final List<Tag> ALEX_CCAS = Arrays.asList(new Cca[]{new Cca("bouldering")});
    private static final List<Tag> ALEX_MODULES = Arrays.asList(
            new Module[]{new Module("cs2030s"), new Module("cs2040s")});
    private static final List<Tag> ALEX_INTERNSHIPS = Arrays.asList(new Internship[]{new Internship("shopee")});

    private static final List<Tag> BERNICE_EDUCATIONS = Arrays.asList(
            new Education[]{new Education("computer engineering"), new Education("nus")});
    private static final List<Tag> BERNICE_CCAS = Arrays.asList(new Cca[]{new Cca("cheerleading")});
    private static final List<Tag> BERNICE_MODULES = Arrays.asList(
            new Module[]{new Module("cs2030s"), new Module("cs2100")});
    private static final List<Tag> BERNICE_INTERNSHIPS = Arrays.asList(new Internship[]{new Internship("gic")});

    private static final List<Tag> CHARLOTTE_EDUCATIONS = Arrays.asList(
            new Education[]{new Education("business analytics"), new Education("nus")});
    private static final List<Tag> CHARLOTTE_CCAS = Arrays.asList(new Cca[]{new Cca("gymnastic")});
    private static final List<Tag> CHARLOTTE_MODULES = Arrays.asList(
            new Module[]{new Module("cs2030"), new Module("ma2001")});
    private static final List<Tag> CHARLOTTE_INTERNSHIPS = Arrays.asList(new Internship[]{new Internship("bosch")});

    private static Person alexYeoh = new Person(new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
            ALEX_EDUCATIONS, ALEX_INTERNSHIPS, ALEX_MODULES, ALEX_CCAS);
    private static Person berniceYu = new Person(new Name("Bernice Yu"), new Phone("99272758"),
            new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            BERNICE_EDUCATIONS, BERNICE_INTERNSHIPS, BERNICE_MODULES, BERNICE_CCAS);
    private static Person charlotteOliveiro = new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
            new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            CHARLOTTE_EDUCATIONS, CHARLOTTE_INTERNSHIPS, CHARLOTTE_MODULES, CHARLOTTE_CCAS);
    private static Person davidLi = new Person(new Name("David Li"), new Phone("91031282"),
            new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"));
    private static Person irfanIbrahim = new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
            new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"));
    private static Person royBalakrishnan = new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
            new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"));

    public static Person[] getSamplePersons() {

        return new Person[]{alexYeoh, berniceYu, charlotteOliveiro, davidLi, irfanIbrahim, royBalakrishnan};
    }

    public static Event[] getSampleEvents() {
        return new Event[]{};
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Event sampleEvent : getSampleEvents()) {
            sampleAb.addEvent(sampleEvent);
        }
        return sampleAb;
    }

}

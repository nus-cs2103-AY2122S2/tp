package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new StudentId("A0000000Z"), new Name("Alex Yeoh"), new ModuleCode("CS2100"),
                    new Phone("87438807"), new TelegramHandle("alexxx"), new Email("alexyeoh@example.com")),
            new Person(new StudentId("A1111111Z"), new Name("Bernice Yu"), new ModuleCode("CS2101"),
                    new Phone("99272758"), new TelegramHandle("bernice"), new Email("berniceyu@example.com")),
            new Person(new StudentId("A2222222Z"), new Name("Charlotte Oliveiro"), new ModuleCode("CS2102"),
                    null, new TelegramHandle("charlotte"), new Email("charlotte@example.com")),
            new Person(new StudentId("A3333333Z"), new Name("David Li"), new ModuleCode("CS2103"),
                    new Phone("91031282"), null, new Email("lidavid@example.com")),
            new Person(new StudentId("A4444444Z"), new Name("Irfan Ibrahim"), new ModuleCode("CS2104"),
                    new Phone("92492021"), new TelegramHandle("irfan"), null),
            new Person(new StudentId("A5555555Z"), new Name("Roy Balakrishnan"), new ModuleCode("CS2105"),
                    null, null, null)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }
}

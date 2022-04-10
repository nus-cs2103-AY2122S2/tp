package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.InsuranceBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyInsuranceBook;
import seedu.address.model.ReadOnlyRecordBook;
import seedu.address.model.RecordBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Description;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.insurance.Price;
import seedu.address.model.insurance.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.record.ClientID;
import seedu.address.model.record.EndDate;
import seedu.address.model.record.InsuranceID;
import seedu.address.model.record.Record;
import seedu.address.model.record.StartDate;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family")),
            new Person(new Name("Sherlock Holmes"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"))
        };
    }

    public static Insurance[] getSampleInsurances() {
        return new Insurance[]{
            new Insurance(new Title("Motor Insurance"), new Price("700")),
            new Insurance(new Title("Home Contents Insurance"), new Price("1000")),
            new Insurance(new Title("Travel Insurance Annual Cover"), new Price("200")),
            new Insurance(new Title("Travel Insurance Family Plan"), new Price("7000")),
            new Insurance(new Title("Work Injury Compensation Insurance"), new Price("9000")),
            new Insurance(new Title("Aviation Insurance"), new Price("120")),
            new Insurance(new Title("Business Insurance"), new Price("300")),
            new Insurance(new Title("Reinsurance"), new Price("3030"))
        };
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[]{
            new Appointment(new Description("Meet James at UTown"), new DateTime("02-03-2023 18:00")),
            new Appointment(new Description("Discuss contract details with Jessie"),
                    new DateTime("23-04-2023 15:00")),
            new Appointment(new Description("Sign contract with Tony at Bugis Junction"),
                    new DateTime("12-05-2023 09:00")),
            new Appointment(new Description("First meeting with Alex at office"),
                    new DateTime("28-02-2023 10:00")),
            new Appointment(new Description("Finalize paperwork with Sherlock"),
                    new DateTime("22-03-2023 11:00")),
            new Appointment(new Description("Sign contract with James at Bugis Junction"),
                        new DateTime("22-03-2019 11:00")),
            new Appointment(new Description("Dinner with Roy Balakrishnan"),
                        new DateTime("22-03-2018 11:00"))
        };
    }

    public static Record[] getSampleRecords() {
        return new Record[]{
            new Record(new ClientID(("Alex Yeoh"), true), new InsuranceID(("Motor Insurance"), true),
                    new StartDate("02-01-2021"), new EndDate("01-03-2026")),
            new Record(new ClientID(("Bernice Yu"), true), new InsuranceID(("Home Contents Insurance"), true),
                    new StartDate("02-02-2010"), new EndDate("09-03-2012")),
            new Record(new ClientID(("Bernice Yu"), true), new InsuranceID(("Aviation Insurance"), true),
                    new StartDate("02-05-2020"), new EndDate("22-03-2023")),
            new Record(new ClientID(("Bernice Yu"), true), new InsuranceID(("Reinsurance"), true),
                    new StartDate("12-03-2019"), new EndDate("02-07-2020")),
            new Record(new ClientID(("Roy Balakrishnan"), true),
                    new InsuranceID(("Home Contents Insurance"), true),
                    new StartDate("22-03-2020"), new EndDate("02-04-2025")),
            new Record(new ClientID(("Roy Balakrishnan"), true),
                    new InsuranceID(("Travel Insurance Family Plan"), true),
                        new StartDate("22-03-2020"), new EndDate("02-04-2025")),
            new Record(new ClientID(("Sherlock Holmes"), true),
                    new InsuranceID(("Travel Insurance Annual Cover"), true),
                        new StartDate("22-03-2020"), new EndDate("12-04-2028")),
            new Record(new ClientID(("Sherlock Holmes"), true), new InsuranceID(("Home Contents Insurance"), true),
                        new StartDate("22-03-2020"), new EndDate("24-09-2025")),
            new Record(new ClientID(("David Li"), true), new InsuranceID(("Travel Insurance Annual Cover"), true),
                        new StartDate("22-06-2020"), new EndDate("02-04-2025")),
            new Record(new ClientID(("David Li"), true), new InsuranceID(("Travel Insurance Family Plan"), true),
                        new StartDate("22-09-2020"), new EndDate("02-04-2025")),
            new Record(new ClientID(("David Li"), true), new InsuranceID(("Home Contents Insurance"), true),
                        new StartDate("22-10-2020"), new EndDate("02-04-2025")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyInsuranceBook getSampleInsuranceBook() {
        InsuranceBook sampleAb = new InsuranceBook();
        for (Insurance sampleInsurance : getSampleInsurances()) {
            sampleAb.addInsurance(sampleInsurance);
        }
        return sampleAb;
    }

    public static ReadOnlyAppointmentBook getSampleAppointmentBook() {
        AppointmentBook sampleAb = new AppointmentBook();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAb.addAppointment(sampleAppointment);
        }
        return sampleAb;
    }

    public static ReadOnlyRecordBook getSampleRecordBook() {
        RecordBook sampleRb = new RecordBook();
        for (Record sampleRecord : getSampleRecords()) {
            sampleRb.addRecord(sampleRecord);
        }
        return sampleRb;
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

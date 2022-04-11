package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.LabelUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.label.Label;
import seedu.address.model.meeting.Agenda;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingPlace;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(
                    new Id("1ddd72fa-e32e-4ed7-b475-32d4b19f6b70"),
                    new Name("Alex Yeoh"),
                    new HashMap<>() {{
                        put(new Label("Personal", false), new Phone("87438807"));
                        put(new Label("Work", false), new Phone("82165492"));
                    }},
                    new HashMap<>() {{
                        put(new Label("Personal", false), new Email("alexyeoh@example.com"));
                        put(new Label("Work", false), new Email("alex_y@company.com"));
                    }},
                    new HashMap<>() {{
                        put(new Label("Home", false), new Address("Blk 30 Geylang Street 29, #06-40"));
                        put(new Label("Office", false), new Address("123 Raffles Business Tower"));
                    }},
                    new Company("Monsters Inc"),
                    new JobTitle("Scarer"),
                    new HashSet<>(Arrays.asList(new Pronoun("he"), new Pronoun("him"))),
                    new HashSet<>(Arrays.asList(new Tag("friend")))
            ),
            new Person(
                    new Id("2ddd72fa-e32e-4ed7-b475-32d4b19f6b70"),
                    new Name("Bernice Yu"),
                    new HashMap<>() {{
                        put(new Label("Personal", false), new Phone("99272758"));
                        put(new Label("Work", false), new Phone("93210283"));
                    }},
                    new HashMap<>() {{
                        put(new Label("Personal", false), new Email("berniceyu@example.com"));
                        put(new Label("Work", false), new Email("bernice_y@company.com"));
                    }},
                    new HashMap<>() {{
                        put(new Label("Home", false),
                                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"));
                        put(new Label("Office", false), new Address("1725 Slough Avenue, Scranton PA"));
                    }},
                    new Company("Dunder Mifflin"),
                    new JobTitle("Salesperson"),
                    new HashSet<>(Arrays.asList(new Pronoun("she"), new Pronoun("her"))),
                    new HashSet<>(Arrays.asList(new Tag("colleague"), new Tag("friend")))
            ),
            new Person(
                    new Id("3ddd72fa-e32e-4ed7-b475-32d4b19f6b70"),
                    new Name("David Li"),
                    new HashMap<>() {{
                        put(new Label("Personal", false), new Phone("91031282"));
                        put(new Label("Work", false), new Phone("92492021"));
                    }},
                    new HashMap<>() {{
                        put(new Label("Personal", false), new Email("lidavid@example.com"));
                        put(new Label("Work", false), new Email("david_li@company.com"));
                    }},
                    new HashMap<>() {{
                        put(new Label("Home", false),
                                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"));
                        put(new Label("Office", false),
                                new Address("43 Springfield Avenue, Greendale County"));
                    }},
                    new Company("Greendale College"),
                    new JobTitle("Spanish Professor"),
                    new HashSet<>(Arrays.asList(new Pronoun("he"), new Pronoun("him"))),
                    new HashSet<>(Arrays.asList(new Tag("family")))
            )
        };
    }

    public static Meeting[] getSampleMeetings() {
        Person[] samplePersons = getSamplePersons();
        return new Meeting[]{
            new Meeting(
                    new Agenda("Paper supply for Greendale College"),
                    new MeetingPlace("David's Office"),
                    new MeetingTime("02-04-2023 13:30"),
                    new HashSet<>(Arrays.asList(samplePersons[1].getId(), samplePersons[2].getId()))
            )
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyMeetingBook getSampleMeetingBook() {
        MeetingBook sampleMb = new MeetingBook();
        for (Meeting sampleMeeting : getSampleMeetings()) {
            sampleMb.addMeeting(sampleMeeting);
        }
        return sampleMb;
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
     * Returns a pronoun set containing the list of strings given.
     */
    public static Set<Pronoun> getPronounSet(String... strings) {
        return Arrays.stream(strings)
                .map(Pronoun::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a address map containing the list of strings given.
     */
    public static Map<Label, Address> getAddressMap(String... strings) {
        Map<Label, Address> addresses = Arrays.stream(strings)
                .map(addressLabelPair -> addressLabelPair.split(" l/"))
                .collect(Collectors.toMap(addressLabelPair -> addressLabelPair.length == 1
                                ? new Label(addressLabelPair[0], true)
                                : new Label(addressLabelPair[1], false),
                    addressLabelPair -> new Address(addressLabelPair[0])));

        return LabelUtil.replaceAddressPlaceholders(addresses);
    }

    /**
     * Returns a email map containing the list of strings given.
     */
    public static Map<Label, Email> getEmailMap(String... strings) {
        Map<Label, Email> emails = Arrays.stream(strings)
                .map(emailLabelPair -> emailLabelPair.split(" l/"))
                .collect(Collectors.toMap(emailLabelPair -> emailLabelPair.length == 1
                                ? new Label(emailLabelPair[0], true)
                                : new Label(emailLabelPair[1], false),
                    emailLabelPair -> new Email(emailLabelPair[0])));

        return LabelUtil.replaceEmailPlaceholders(emails);
    }

    /**
     * Returns a phone map containing the list of strings given.
     */
    public static Map<Label, Phone> getPhoneMap(String... strings) {
        Map<Label, Phone> numbers = Arrays.stream(strings)
                .map(phoneLabelPair -> phoneLabelPair.split(" l/"))
                .collect(Collectors.toMap(phoneLabelPair -> phoneLabelPair.length == 1
                                ? new Label(phoneLabelPair[0], true)
                                : new Label(phoneLabelPair[1], false),
                    phoneLabelPair -> new Phone(phoneLabelPair[0])));

        return LabelUtil.replacePhonePlaceholders(numbers);
    }

    /**
     * Returns an attendee set containing the list of strings given.
     */
    public static Set<Id> getAttendeeSet(String... strings) {
        return Arrays.stream(strings)
                .map(Id::new)
                .collect(Collectors.toSet());
    }
}

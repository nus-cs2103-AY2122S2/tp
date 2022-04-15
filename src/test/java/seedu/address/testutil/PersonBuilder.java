package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.name.Name;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private List<Note> strengths;
    private List<Note> weaknesses;
    private List<Note> miscellaneous;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        strengths = new ArrayList<>();
        weaknesses = new ArrayList<>();
        miscellaneous = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        strengths = new ArrayList<>(personToCopy.getStrengths());
        weaknesses = new ArrayList<>(personToCopy.getWeaknesses());
        miscellaneous = new ArrayList<>(personToCopy.getMiscellaneous());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code strengths} into a {@code List<Note>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withStrengths(String... strengths) {
        List<Note> noteList = SampleDataUtil.getNoteList(strengths);
        this.strengths.addAll(noteList);
        return this;
    }

    /**
     * Parses the {@code weaknesses} into a {@code List<Note>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withWeaknesses(String... weaknesses) {
        List<Note> noteList = SampleDataUtil.getNoteList(weaknesses);
        this.weaknesses.addAll(noteList);
        return this;
    }

    /**
     * Parses the {@code misc} into a {@code List<Note>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withMisc(String... misc) {
        List<Note> noteList = SampleDataUtil.getNoteList(misc);
        this.miscellaneous.addAll(noteList);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, tags, strengths, weaknesses, miscellaneous);
    }
}

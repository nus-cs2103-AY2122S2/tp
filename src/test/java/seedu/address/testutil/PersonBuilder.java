package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;
import seedu.address.model.telegram.Telegram;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy2@gmail.com";
    public static final String DEFAULT_FACULTY = "Computing";
    public static final String DEFAULT_ROLE = "Professor";
    public static final String DEFAULT_TELEGRAM = "@Amy23";
    public static final boolean DEFAULT_FAVOURITE = false;

    private Name name;
    private Phone phone;
    private Email email;
    private Faculty faculty;
    private Role role;
    private Telegram telegram;
    private Favourite favourite;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        faculty = new Faculty(DEFAULT_FACULTY);
        role = new Role(DEFAULT_ROLE);
        telegram = new Telegram(Optional.of(DEFAULT_TELEGRAM));
        favourite = new Favourite(DEFAULT_FAVOURITE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        faculty = personToCopy.getFaculty();
        role = personToCopy.getRole();
        telegram = personToCopy.getTelegram();
        favourite = personToCopy.getFavourite();
        tags = new HashSet<>(personToCopy.getTags());
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
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(Optional.ofNullable(telegram));
        return this;
    }

    /**
     * Sets the {@code Favourite} of the {@code Person} that we are building.
     */
    public PersonBuilder withFavourite(boolean isFavourite) {
        this.favourite = new Favourite(isFavourite);
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
     * Sets the {@code Faculty} of the {@code Person} that we are building.
     */
    public PersonBuilder withFaculty(String faculty) {
        this.faculty = new Faculty(faculty);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Person} that we are building.
     */
    public PersonBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, faculty, role, telegram, favourite, tags);
    }

}

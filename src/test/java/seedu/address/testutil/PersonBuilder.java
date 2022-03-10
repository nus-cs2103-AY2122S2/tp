package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.parser.TagCommandParser;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

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
    private List<Tag> educations;
    private List<Tag> internships;
    private List<Tag> modules;
    private List<Tag> ccas;


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        educations = new ArrayList<>();
        internships = new ArrayList<>();
        modules = new ArrayList<>();
        ccas = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        educations = personToCopy.getEducations();
        internships = personToCopy.getInternships();
        modules = personToCopy.getModules();
        ccas = personToCopy.getCcas();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code Education} of the {@code Person} that we are building.
     */
    public PersonBuilder withEducations(String educations) {
        this.educations = TagCommandParser.convertToList(educations, "education");
        return this;
    }

    /**
     * Sets the {@code Internship} of the {@code Person} that we are building.
     */
    public PersonBuilder withInternships(String internships) {
        this.internships = TagCommandParser.convertToList(internships, "internship");
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code Person} that we are building.
     */
    public PersonBuilder withModules(String modules) {
        this.modules = TagCommandParser.convertToList(modules, "module");
        return this;
    }

    /**
     * Sets the {@code Cca} of the {@code Person} that we are building.
     */
    public PersonBuilder withCcas(String ccas) {
        this.ccas = TagCommandParser.convertToList(ccas, "cca");
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address);
    }

}

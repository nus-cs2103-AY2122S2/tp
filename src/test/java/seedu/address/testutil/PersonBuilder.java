package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

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
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_EDUCATION = "Computer Science";
    public static final String DEFAULT_INTERNSHIP = "GIC";
    public static final String DEFAULT_MODULE = "CS2040S";
    public static final String DEFAULT_CCA = "Netball";

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
    public PersonBuilder withEducation(String education) {
        educations.add(new Education(education));
        return this;
    }

    /**
     * Sets the {@code Internship} of the {@code Person} that we are building.
     */
    public PersonBuilder withInternship(String internship) {
        internships.add(new Internship(internship));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code Person} that we are building.
     */
    public PersonBuilder withModule(String module) {
        modules.add(new Module(module));
        return this;
    }

    /**
     * Sets the {@code Cca} of the {@code Person} that we are building.
     */
    public PersonBuilder withCca(String cca) {
        ccas.add(new Cca(cca));
        return this;
    }

    private boolean isAllTagsEmpty() {
        return educations.isEmpty() && internships.isEmpty() && modules.isEmpty() && ccas.isEmpty();
    }

    /**
     * Creates a Person with the appropriate fields.
     */
    public Person build() {
        return new Person(name, phone, email, address, educations, internships, modules, ccas);
    }

}

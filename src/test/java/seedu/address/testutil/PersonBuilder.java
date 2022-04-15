package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_BLOCK = "C";
    public static final String DEFAULT_FACULTY = "FOL";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_MATRICULATION_NUMBER = "A0263512N";
    public static final String DEFAULT_COVID_STATUS = "negative";

    private Name name;
    private Block block;
    private Faculty faculty;
    private Phone phone;
    private Email email;
    private Address address;
    private MatriculationNumber matriculationNumber;
    private CovidStatus covidStatus;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        block = new Block(DEFAULT_BLOCK);
        faculty = new Faculty(DEFAULT_FACULTY);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        matriculationNumber = new MatriculationNumber(DEFAULT_MATRICULATION_NUMBER);
        covidStatus = new CovidStatus(DEFAULT_COVID_STATUS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        block = personToCopy.getBlock();
        faculty = personToCopy.getFaculty();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        matriculationNumber = personToCopy.getMatriculationNumber();
        covidStatus = personToCopy.getStatus();
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
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone.trim());
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email.trim());
        return this;
    }

    /**
     * Sets the {@Code Faculty} of the {@Code Person} that we are building
     */
    public PersonBuilder withFaculty(String faculty) {
        this.faculty = new Faculty(faculty);
        return this;
    }

    /**
     * Sets the {@Code MatriculationNumber} of the {@Code Person} that we are building
     */
    public PersonBuilder withMatriculationNumber(String matriculationNumber) {
        this.matriculationNumber = new MatriculationNumber(matriculationNumber.trim());
        return this;
    }

    /**
     * Sets the {@Code CovidStatus} of the {@Code Person} that we are building
     */
    public PersonBuilder withCovidStatus(String covidStatus) {
        this.covidStatus = new CovidStatus(covidStatus);
        return this;
    }

    /**
     * Sets the {@Code Block} of the {@Code Person} that we are building
     */
    public PersonBuilder withBlock(String block) {
        this.block = new Block(block);
        return this;
    }

    /**
     * Build a Person object.
     * @return Person object with this Object's attributes.
     */
    public Person build() {
        return new Person(name, block, faculty, phone, email, address, matriculationNumber, covidStatus, tags);
    }

}

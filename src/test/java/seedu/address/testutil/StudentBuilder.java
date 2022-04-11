package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.util.SampleDataUtil;

public class StudentBuilder {
    public static final String DEFAULT_NAME = "Default Student";
    public static final String DEFAULT_PHONE = "85351234";
    public static final String DEFAULT_EMAIL = "defaultstudent@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Wood West Ave 6, #08-111";
    public static final String DEFAULT_NUSNETID = "e0123456";
    public static final String DEFAULT_TUTORIAL_NAME = "Default Tutorial Name";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private NusNetId nusNetId;
    private TutorialName tutName;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        nusNetId = new NusNetId(DEFAULT_NUSNETID);
        tutName = new TutorialName(DEFAULT_TUTORIAL_NAME);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        tags = new HashSet<>(studentToCopy.getTags());
        nusNetId = studentToCopy.getStudentId();
        tutName = studentToCopy.getTutorialName();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        nusNetId = new NusNetId(DEFAULT_NUSNETID);
        tutName = new TutorialName(DEFAULT_TUTORIAL_NAME);
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code NusNetId} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentId(String nusNetId) {
        this.nusNetId = new NusNetId(nusNetId);
        return this;
    }

    /**
     * Sets the {@code TutorialName} of the {@code Student} that we are building.
     */
    public StudentBuilder withTutorialName(String tutName) {
        this.tutName = new TutorialName(tutName);
        return this;
    }

    public Student build() {
        return new Student(name, phone, email, address, tags, nusNetId, tutName);
    }

}

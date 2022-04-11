package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_NUMBER = "A0123456B";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GROUP = "T01";
    public static final String DEFAULT_MOD = "CS2103T";

    private Name name;
    private StudentNumber studentNumber;
    private Email email;
    private Group group;
    private Mod mod;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        studentNumber = new StudentNumber(DEFAULT_STUDENT_NUMBER);
        email = new Email(DEFAULT_EMAIL);
        group = new Group(DEFAULT_GROUP);
        mod = new Mod(DEFAULT_MOD);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        studentNumber = personToCopy.getStudentNumber();
        email = personToCopy.getEmail();
        group = personToCopy.getGroup();
        mod = personToCopy.getMod();
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
     * Sets the {@code Mod} of the {@code Person} that we are building.
     */
    public PersonBuilder withMod(String mod) {
        this.mod = new Mod(mod);
        return this;
    }

    /**
     * Sets the {@code StudentNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentNumber(String studentNumber) {
        this.studentNumber = new StudentNumber(studentNumber);
        return this;
    }
    /**
     * Sets the {@code Group} of the {@code Person} that we are building.
     */
    public PersonBuilder withGroup(String group) {
        this.group = new Group(group);
        return this;
    }
    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(name, studentNumber, email, mod, group, tags);
    }

}

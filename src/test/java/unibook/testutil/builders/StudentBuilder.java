package unibook.testutil.builders;

import java.util.HashSet;
import java.util.Set;

import unibook.model.module.Module;
import unibook.model.module.group.Group;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Phone;
import unibook.model.person.Student;
import unibook.model.tag.Tag;
import unibook.model.util.SampleDataUtil;

/**
 * Used for instantiating a {@code Student} object for use in testing.
 * Recommended to use default fields where possible.
 */
public class StudentBuilder {
    public static final String DEFAULT_NAME = "James";
    public static final String DEFAULT_PHONE = "91893940";
    public static final String DEFAULT_EMAIL = "bond@m16.com";


    private Name name;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;
    private Set<Module> modules;
    private Set<Group> groups;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
        modules = new HashSet<>();
        groups = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        tags = new HashSet<>(studentToCopy.getTags());
        modules = new HashSet<>(studentToCopy.getModules());
        groups = new HashSet<>(studentToCopy.getGroups());
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
    public StudentBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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


    public Student build() {
        return new Student(name, phone, email, tags, modules, groups);
    }
}

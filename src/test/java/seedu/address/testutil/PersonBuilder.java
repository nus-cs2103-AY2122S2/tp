package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.entry.Email;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Person;
import seedu.address.model.entry.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_COMPANY = "DBSSS";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final boolean DEFAULT_ARCHIVED = false;

    private Name name;
    private Name companyName;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;
    private boolean isArchived;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        companyName = new Name(DEFAULT_COMPANY);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        tags = new HashSet<>();
        isArchived = DEFAULT_ARCHIVED;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        companyName = personToCopy.getCompanyName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        tags = new HashSet<>(personToCopy.getTags());
        isArchived = personToCopy.isArchived();
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
     * Sets the {@code companyName} of the {@code Person} that we are building.
     */
    public PersonBuilder withCompanyName(String companyName) {
        this.companyName = new Name(companyName);
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
     * Sets the {@code isArchived} of the {@code Person} that we are building.
     */
    public PersonBuilder withArchived(boolean isArchived) {
        this.isArchived = isArchived;
        return this;
    }

    public Person build() {
        return new Person(name, companyName, phone, email, tags, isArchived);
    }

}

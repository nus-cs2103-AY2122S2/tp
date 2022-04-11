package seedu.address.testutil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.address.model.label.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_COMPANY = "KFC";
    public static final String DEFAULT_JOB_TITLE = "Cook";

    private Name name;
    private Company company;
    private JobTitle jobTitle;
    private Map<Label, Phone> numbers;
    private Map<Label, Address> addresses;
    private Map<Label, Email> emails;
    private Set<Pronoun> pronouns;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        company = new Company(DEFAULT_COMPANY);
        jobTitle = new JobTitle(DEFAULT_JOB_TITLE);
        numbers = new HashMap<Label, Phone>();
        addresses = new HashMap<Label, Address>();
        emails = new HashMap<Label, Email>();
        pronouns = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        company = personToCopy.getCompany().orElse(null);
        jobTitle = personToCopy.getJobTitle().orElse(null);
        numbers = personToCopy.getNumbers();
        emails = personToCopy.getEmails();
        addresses = personToCopy.getAddresses();
        pronouns = new HashSet<>(personToCopy.getPronouns());
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
     * Sets the {@code Company} of the {@code Person} that we are building.
     */
    public PersonBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withoutCompany() {
        this.company = null;
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code Person} that we are building.
     */
    public PersonBuilder withJobTitle(String jobTitle) {
        this.jobTitle = new JobTitle(jobTitle);
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code Person} that we are building to null.
     */
    public PersonBuilder withoutJobTitle() {
        this.jobTitle = null;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Creates an empty {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withoutTags() {
        this.tags = new HashSet<>();
        return this;
    }

    /**
     * Parses the {@code pronouns} into a {@code Set<Pronoun>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withPronouns(String... pronouns) {
        this.pronouns = SampleDataUtil.getPronounSet(pronouns);
        return this;
    }

    /**
     * Sets the {@code Addresses} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddresses(String... addresses) {
        this.addresses = SampleDataUtil.getAddressMap(addresses);
        return this;
    }

    /**
     * Sets the {@code Numbers} of the {@code Person} that we are building.
     */
    public PersonBuilder withNumbers(String... numbers) {
        this.numbers = SampleDataUtil.getPhoneMap(numbers);
        return this;
    }

    /**
     * Sets the {@code Emails} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmails(String... emails) {
        this.emails = SampleDataUtil.getEmailMap(emails);
        return this;
    }

    /**
     * Sets the {@code Emails} of the {@code Person} that we are building to an empty map.
     */
    public PersonBuilder withoutEmails() {
        this.emails = new HashMap<Label, Email>();
        return this;
    }

    /**
     * Builds a {@code Person} with the fields of {@code PersonBuilder}
     */
    public Person build() {
        return new Person(name, numbers, emails, addresses, company, jobTitle, pronouns, tags);
    }

    @Override
    public String toString() {
        return "PersonBuilder{"
                + "name=" + name
                + ", company=" + company
                + ", jobTitle=" + jobTitle
                + ", numbers=" + numbers
                + ", addresses=" + addresses
                + ", emails=" + emails
                + ", pronouns=" + pronouns
                + ", tags=" + tags
                + '}';
    }
}

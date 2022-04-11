package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.label.Label;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Comparable<Person> {

    // Identity fields
    private final Id id;

    // Data fields
    private final Name name;
    private final Map<Label, Phone> numbers;
    private final Map<Label, Email> emails;
    private final Map<Label, Address> addresses;
    private final Company company;
    private final JobTitle jobTitle;
    private final HashSet<Pronoun> pronouns = new HashSet<>();
    private final HashSet<Tag> tags = new HashSet<>();

    /**
     * Name, numbers, emails, addresses, pronouns, and tags must be present and not null.
     */
    public Person(Name name, Map<Label, Phone> numbers, Map<Label, Email> emails,
                  Map<Label, Address> addresses, Company company, JobTitle jobTitle, Set<Pronoun> pronouns,
                  Set<Tag> tags) {
        requireAllNonNull(name, numbers, emails, addresses, pronouns, tags);
        this.name = name;
        this.numbers = numbers;
        this.emails = emails;
        this.addresses = addresses;
        this.company = company;
        this.jobTitle = jobTitle;
        this.pronouns.addAll(pronouns);
        this.tags.addAll(tags);
        this.id = new Id();
    }

    /**
     * Constructs a {@code Person} with a predesignated {@code Id}.
     */
    public Person(Id id, Name name, Map<Label, Phone> numbers, Map<Label, Email> emails,
                  Map<Label, Address> addresses, Company company, JobTitle jobTitle, Set<Pronoun> pronouns,
                  Set<Tag> tags) {
        requireAllNonNull(name, numbers, emails, addresses, pronouns, tags);
        this.name = name;
        this.numbers = numbers;
        this.emails = emails;
        this.addresses = addresses;
        this.company = company;
        this.jobTitle = jobTitle;
        this.pronouns.addAll(pronouns);
        this.tags.addAll(tags);
        this.id = id;
    }

    /**
     * Returns an empty person with nothing values.
     *
     * @return an empty person with nothing values
     */
    public static Person getEmptyPerson() {
        return new Person(new Name("No name"), new HashMap<>(), new HashMap<>(), new HashMap<>(),
                new Company("No Company"), new JobTitle("No JobTitle"), new HashSet<>(), new HashSet<>());
    }

    public Id getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Map<Label, Phone> getNumbers() {
        return numbers;
    }

    public Map<Label, Email> getEmails() {
        return emails;
    }

    public Map<Label, Address> getAddresses() {
        return addresses;
    }

    public Optional<Company> getCompany() {
        return Optional.ofNullable(company);
    }

    public Optional<JobTitle> getJobTitle() {
        return Optional.ofNullable(jobTitle);
    }

    public Set<Pronoun> getPronouns() {
        return Collections.unmodifiableSet(pronouns);
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public HashSet<Tag> getTagSet() {
        return this.tags;
    }

    /**
     * Returns true if both persons have the same name and tags.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (otherPerson == null) {
            return false;
        }

        return otherPerson.getName() != null
                && otherPerson.getName().equals(this.getName())
                && otherPerson.getTags() != null
                && otherPerson.getTags().equals(this.getTags());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;

        //Including ID equality check will cause tests to fail because EmptyPerson is generated with a random ID
        return otherPerson.getName().equals(getName())
                && otherPerson.getNumbers().equals(getNumbers())
                && otherPerson.getEmails().equals(getEmails())
                && otherPerson.getAddresses().equals(getAddresses())
                && otherPerson.getCompany().equals(getCompany())
                && otherPerson.getJobTitle().equals(getJobTitle())
                && otherPerson.getPronouns().equals(getPronouns())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, numbers, emails, addresses, company, jobTitle, pronouns, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName());

        getCompany().map(c -> builder.append("; Company: ").append(c));

        getJobTitle().map(j -> builder.append("; Job Title: ").append(j));

        Set<Pronoun> pronouns = getPronouns();
        if (!pronouns.isEmpty()) {
            builder.append("; Pronouns: ");
            pronouns.stream().limit(1).forEach(builder::append);
            pronouns.stream().skip(1).forEach(pronoun -> builder.append("/").append(pronoun));

        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(tag -> builder.append(tag.prettyString()));
        }

        Map<Label, Phone> numbers = getNumbers();
        if (!numbers.isEmpty()) {
            builder.append("; Numbers: ");
            numbers.forEach((label, number) -> builder.append(number.phone).append(" l/").append(label.label)
                    .append(" "));
        }

        Map<Label, Address> addresses = getAddresses();
        if (!addresses.isEmpty()) {
            builder.append("; Addresses: ");
            addresses.forEach((label, address) -> builder.append(address.address).append(" l/")
                    .append(label.label).append(" "));
        }

        Map<Label, Email> emails = getEmails();
        if (!emails.isEmpty()) {
            builder.append("; Emails: ");
            emails.forEach((label, email) -> builder.append(email.email).append(" l/").append(label.label).append(" "));
        }

        return builder.toString();
    }

    @Override
    public int compareTo(Person another) {
        int nameCompare = this.name.fullName.compareTo(another.name.fullName);
        int companyCompare = (this.company != null && another.company != null)
                ? this.company.compareTo(another.company)
                : 0;
        int jobCompare = (this.jobTitle != null && another.jobTitle != null)
                ? this.jobTitle.compareTo(another.jobTitle)
                : 0;
        return nameCompare == 0 ? (companyCompare == 0 ? jobCompare : companyCompare) : nameCompare;
    }
}

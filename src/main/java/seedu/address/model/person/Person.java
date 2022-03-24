package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final InsurancePackage insurancePackage;
    private final Address address;
    private ArrayList<Tag> tags = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email,
                  InsurancePackage insurancePackage, Address address, ArrayList<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.insurancePackage = insurancePackage;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Copies a person object.
     */
    public static Person copyPerson(Person personToCopy) {
        Name copiedName = personToCopy.getName();
        Phone copiedPhone = personToCopy.getPhone();
        Email copiedEmail = personToCopy.getEmail();
        InsurancePackage copiedInsurancePackage = personToCopy.getInsurancePackage();
        Address copiedAddress = personToCopy.getAddress();
        ArrayList<Tag> copiedTags = new ArrayList<>();
        copiedTags.addAll(personToCopy.getTags());
        return new Person(copiedName, copiedPhone, copiedEmail, copiedInsurancePackage, copiedAddress, copiedTags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public InsurancePackage getInsurancePackage() {
        return insurancePackage;
    }

    public Address getAddress() {
        return address;
    }

    public void setTags(ArrayList<Tag> tagList) {
        this.tags = tagList;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    /**
     * Returns a String of tagname separated by spaces after retrieving the tagname from the {@code Tag } object.
     * @return String of tagnames separated by spaces
     */
    public String getTagsInString() {
        ArrayList<Tag> tags = getTags();
        List<String> tagsListInString = tags.stream().map(tag -> tag.tagName).collect(Collectors.toList());
        return String.join(" ", tagsListInString);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getInsurancePackage().equals(getInsurancePackage())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Insurance Package: ")
                .append(getInsurancePackage())
                .append("; Address: ")
                .append(getAddress());

        ArrayList<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}

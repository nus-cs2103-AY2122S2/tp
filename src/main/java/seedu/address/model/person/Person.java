package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.tag.ExactTagComparator;
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
    private final Address address;
    private final ContactedDate contactedDate;
    private final Memo memo;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  ContactedDate contactedDate, Memo memo, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.contactedDate = contactedDate;
        this.memo = memo;
        this.tags.addAll(tags);
    }

    /**
     * Returns name.
     *
     * @return name.
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns phone.
     *
     * @return phone.
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns email.
     *
     * @return email.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns address.
     *
     * @return address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns contactedDate.
     *
     * @return contactedDate.
     */
    public ContactedDate getContactedDate() {
        return contactedDate;
    }

    /**
     * Returns memo.
     *
     * @return memo.
     */
    public Memo getMemo() {
        return memo;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if memo is empty, false otherwise.
     *
     * @return If memo is empty true; otherwise false.
     */
    public boolean isMemoEmpty() {
        return memo.isEmpty();
    }

    /**
     * Returns true if both persons have the same name, phone and email.
     * This defines a weaker notion of equality between two persons.
     *
     * @param otherPerson The other {@code Person}.
     * @return true if both persons have the same name, phone and email.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     *
     * @param other The other Object.
     * @return true if both persons have the same identity and data fields; otherwise false.
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
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getContactedDate().equals(getContactedDate())
                && otherPerson.getMemo().equals(getMemo())
                && otherPerson.getTags().equals(getTags());
    }

    /**
     * Returns true if both persons have the exact same identity and data fields.
     * Unlike {@code equals(Object o)} which is ignores uppercase and lowercase, this method is case-sensitive.
     *
     * @param otherPerson The other person.
     * @return true if both persons have the same identity and data fields; otherwise false.
     */
    public boolean exactEquals(Person otherPerson) {
        if (otherPerson == null) { // Short-circuit if the other person is null
            return false;
        }
        Set<Tag> otherTagList = otherPerson.getTags();
        boolean areTagsEqual = compareTagList(otherTagList);

        return otherPerson.getName().exactEquals(getName())
                && otherPerson.getPhone().exactEquals(getPhone())
                && otherPerson.getEmail().exactEquals(getEmail())
                && otherPerson.getAddress().exactEquals(getAddress())
                && otherPerson.getContactedDate().exactEquals(getContactedDate())
                && otherPerson.getMemo().exactEquals(getMemo())
                && areTagsEqual;
    }

    /**
     * Returns true if the provided tag list is identical to the tag list of this person object.
     * Unlike calling {@code tagList.equals()}, this method is case-sensitive.
     *
     * @param otherTagList the other tag list.
     * @return Whether the tag lists are identical.
     */
    private boolean compareTagList(Set<Tag> otherTagList) {
        Set<Tag> tagList = getTags();
        if (otherTagList.size() != tagList.size()) {
            return false;
        }

        ExactTagComparator<Tag> comparator = new ExactTagComparator<>();
        TreeSet<Tag> tagTreeSet = new TreeSet<>(comparator);
        TreeSet<Tag> otherTagTreeSet = new TreeSet<>(comparator);
        tagTreeSet.addAll(tagList);
        otherTagTreeSet.addAll(otherTagList);
        tagTreeSet.retainAll(otherTagTreeSet);
        return tagTreeSet.size() == tagList.size();
    }

    /**
     * Returns hashcode of Person.
     *
     * @return hashcode of Person.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, contactedDate, memo, tags);
    }

    /**
     * Returns string representation of Person.
     *
     * @return string representation of Person.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Contacted Date: ")
                .append(getContactedDate());

        if (!memo.isEmpty()) {
            builder.append(" Memo: ").append(getMemo());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> builder.append(String.format("[%s]", tag)));
        }
        return builder.toString();
    }

}

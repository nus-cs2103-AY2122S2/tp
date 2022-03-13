package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Serializable {
    private final HashMap<Prefix, Field> fields = new HashMap<>();
    private final HashSet<Tag> tags = new HashSet<>();

    /**
     * Person constructor
     * @param fields A collection of all the person's attributes
     * @param tags A collection of all the person's tags
     */
    public Person(Collection<Field> fields, Collection<Tag> tags) {
        requireAllNonNull(tags, fields);
        // Add tags.
        for (Tag t : tags) {
            checkArgument(t != null, "All tags in Person constructor cannot be null.");
            this.tags.add(t);
        }

        // Add fields.
        for (Field f : fields) {
            checkArgument(f != null, "All fields in Person constructor cannot be null.");
            this.fields.put(f.prefix, f);
        }

        // Check for required fields.
        for (Prefix p : FieldRegistry.REQUIRED_PREFIXES) {
            checkArgument(this.fields.containsKey(p), "All required fields must be given.");
        }
    }

    public Person(Person otherPerson) {
        this(otherPerson.getFields(), otherPerson.getTags());
    }

    /**
     * Returns true if the person contains the specified field.
     * @param prefix the field prefix
     * @return return true if the person contains the specified field
     */
    public boolean hasField(Prefix prefix) {
        requireAllNonNull(prefix);
        return fields.containsKey(prefix);
    }

    /**
     * Add a field to the person. If the field already exists, it is replaced.
     * @param field the field to add
     * @return a person with the field added
     */
    public Person addField(Field field) {
        requireAllNonNull(field);
        Map<Prefix, Field> updatedFields = new HashMap<>(fields);
        updatedFields.put(field.prefix, field);
        return new Person(updatedFields.values(), tags);
    }

    /**
     * Remove a field from the person. If the field does not exists, this does nothing.
     * @param prefix the prefix of the field to remove
     * @return a person with the field removed
     */
    public Person removeField(Prefix prefix) {
        requireAllNonNull(prefix);
        if (hasField(prefix)) {
            Map<Prefix, Field> updatedFields = new HashMap<>(fields);
            updatedFields.remove(prefix);
            return new Person(updatedFields.values(), tags);
        }
        return this;
    }

    public Optional<Field> getField(Prefix prefix) {
        requireAllNonNull(prefix);
        return Optional.ofNullable(fields.get(prefix));
    }

    public List<Field> getFields() {
        return Collections.unmodifiableList(new ArrayList<>(fields.values()));
    }

    public Name getName() {
        return (Name) this.fields.get(Name.PREFIX);
    }

    public Phone getPhone() {
        return (Phone) this.fields.get(Phone.PREFIX);
    }

    public Email getEmail() {
        return (Email) this.fields.get(Email.PREFIX);
    }

    public Address getAddress() {
        return (Address) this.fields.get(Address.PREFIX);
    }

    public Membership getMembership() {
        return (Membership) this.fields.get(Membership.PREFIX);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Person setTags(Collection<Tag> tags) {
        return new Person(this.fields.values(), tags);
    }

    public Person setTags(Tag... tags) {
        return setTags(List.of(tags));
    }

    /**
     * Add tags to the person.
     * @param tags the tags to add
     * @return a person with the tags added
     */
    public Person addTags(Collection<Tag> tags) {
        HashSet<Tag> updatedTags = new HashSet<>(this.tags);
        for (Tag t : tags) {
            checkArgument(t != null, "Cannot add null tags!");
            updatedTags.add(t);
        }
        return new Person(this.fields.values(), updatedTags);
    }

    /**
     * Add tags to the person.
     * @param tags the tags to add
     * @return a person with the tags added
     */
    public Person addTags(Tag... tags) {
        return addTags(List.of(tags));
    }

    /**
     * Remove tags from the person.
     * @param tags the tags to remove
     * @return a person with the tags remove
     */
    public Person removeTags(Collection<Tag> tags) {
        HashSet<Tag> updatedTags = new HashSet<>(this.tags);
        for (Tag t : tags) {
            updatedTags.remove(t);
        }
        return new Person(this.fields.values(), updatedTags);
    }

    /**
     * Remove tags from the person.
     * @param tags the tags to remove
     * @return a person with the tags remove
     */
    public Person removeTags(Tag... tags) {
        return removeTags(tags);
    }

    /**
     * Adds a membership to the person
     *
     * @param membership Membership to add
     * @return A new person
     */
    public Person addMembership(Membership membership) {
        HashMap<Prefix, Field> newFields = new HashMap<>(this.fields);
        newFields.put(Membership.PREFIX, membership);
        return new Person(newFields.values(), tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        return otherPerson != null && otherPerson.getEmail().equals(getEmail());
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
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}

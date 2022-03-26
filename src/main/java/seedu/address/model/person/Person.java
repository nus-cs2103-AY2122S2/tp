package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Serializable {
    public static final String MAP_PREFIX = "P";

    private final long uniqueId;
    private final HashMap<Prefix, Field> fields = new HashMap<>();
    private final HashSet<Tag> tags = new HashSet<>();

    private Person(long uniqueId, Collection<Field> fields, Collection<Tag> tags) {
        requireAllNonNull(tags, fields);

        // Set unique ID.
        this.uniqueId = uniqueId;

        // Add tags.
        for (Tag t : tags) {
            requireAllNonNull(t);
            this.tags.add(t);
        }

        // Add fields.
        for (Field f : fields) {
            requireAllNonNull(f);
            this.fields.put(f.prefix, f);
        }

        // Check for required fields.
        for (Prefix p : FieldRegistry.REQUIRED_PREFIXES) {
            checkArgument(this.fields.containsKey(p), "All required fields must be given.");
        }
    }

    /**
     * Person constructor
     *
     * @param fields A collection of all the person's attributes
     * @param tags   A collection of all the person's tags
     */
    public Person(Collection<Field> fields, Collection<Tag> tags) {
        this(new Date().getTime(), fields, tags);

        // Sleep for 2 microseconds to ensure that it is impossible for another Person to have the same unique ID.
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getUniqueId() {
        return uniqueId;
    }

    /**
     * Returns true if the person contains the specified field.
     *
     * @param prefix the field prefix
     * @return return true if the person contains the specified field
     */
    public boolean hasField(Prefix prefix) {
        requireAllNonNull(prefix);
        return fields.containsKey(prefix);
    }

    /**
     * Add fields to the person. If the field already exists, it is replaced.
     *
     * @param fields the fields to add
     * @return a person with the fields added
     */
    public Person addFields(Field... fields) {
        return addFields(List.of(fields));
    }

    /**
     * Add fields to the person. If the field already exists, it is replaced.
     *
     * @param fields the fields to add
     * @return a person with the fields added
     */
    public Person addFields(Collection<Field> fields) {
        requireAllNonNull(fields);
        Map<Prefix, Field> updatedFields = new HashMap<>(this.fields);
        for (Field f : fields) {
            requireAllNonNull(f);
            updatedFields.put(f.prefix, f);
        }
        return new Person(this.uniqueId, updatedFields.values(), tags);
    }

    /**
     * Remove fields from the person. If the field does not exist, this does nothing.
     *
     * @param prefixes the prefixes of the fields to remove
     * @return a person with the fields removed
     */
    public Person removeFields(Prefix prefixes) {
        return removeFields(List.of(prefixes));
    }

    /**
     * Remove fields from the person. If the field does not exist, this does nothing.
     *
     * @param prefixes the prefixes of the fields to remove
     * @return a person with the fields removed
     */
    public Person removeFields(Collection<Prefix> prefixes) {
        requireAllNonNull(prefixes);
        Map<Prefix, Field> updatedFields = new HashMap<>(this.fields);
        for (Prefix p : prefixes) {
            requireAllNonNull(p);
            updatedFields.remove(p);
        }
        return new Person(this.uniqueId, updatedFields.values(), tags);
    }

    public Person setFields(Collection<Field> fields) {
        return new Person(this.uniqueId, fields, this.tags);
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

    public boolean hasTag(Tag tag) {
        return tags.contains(tag);
    }

    /**
     * Add tags to the person.
     *
     * @param tags the tags to add
     * @return a person with the tags added
     */
    public Person addTags(Collection<Tag> tags) {
        HashSet<Tag> updatedTags = new HashSet<>(this.tags);
        updatedTags.addAll(tags);
        return new Person(this.uniqueId, this.fields.values(), updatedTags);
    }

    /**
     * Add tags to the person.
     *
     * @param tags the tags to add
     * @return a person with the tags added
     */
    public Person addTags(Tag... tags) {
        return addTags(List.of(tags));
    }

    /**
     * Remove tags from the person.
     *
     * @param tags the tags to remove
     * @return a person with the tags remove
     */
    public Person removeTags(Collection<Tag> tags) {
        requireAllNonNull(tags);
        HashSet<Tag> updatedTags = new HashSet<>(this.tags);
        for (Tag t : tags) {
            requireAllNonNull(t);
            updatedTags.remove(t);
        }
        return new Person(this.uniqueId, this.fields.values(), updatedTags);
    }

    /**
     * Remove tags from the person.
     *
     * @param tags the tags to remove
     * @return a person with the tags remove
     */
    public Person removeTags(Tag... tags) {
        return removeTags(tags);
    }

    public Person setTags(Collection<Tag> tags) {
        return new Person(this.uniqueId, this.fields.values(), tags);
    }

    public Person setTags(Tag... tags) {
        return setTags(List.of(tags));
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
        return new Person(this.uniqueId, newFields.values(), tags);
    }

    /**
     * Returns true if both persons have the same email.
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
     * @param other the other Person to compare to
     * @return true if both persons have the same identity and data fields
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

        // Required Fields
        builder.append(getName())
                .append("; Phone: ").append(getPhone())
                .append("; Email: ").append(getEmail())
                .append("; Address: ").append(getAddress());

        // Tags
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}

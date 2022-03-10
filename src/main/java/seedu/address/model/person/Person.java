package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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
public class Person {
    private final Set<Tag> tags = new HashSet<>();
    private final Map<Prefix, Field> fields = new HashMap<>();

    /**
     * Deprecated constructor.
     * @param name the person's name
     * @param phone the person's phone
     * @param email the person's email
     * @param address the person's address
     * @param tags the person's tags
     */
    @Deprecated
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);

        // Add fields.
        fields.put(name.prefix, name);
        fields.put(phone.prefix, phone);
        fields.put(email.prefix, email);
        fields.put(address.prefix, address);
        Remark remark = new Remark("");
        fields.put(remark.prefix, remark);

        // Add tags.
        for (Tag t : tags) {
            checkArgument(t != null, "All tags in Person constructor cannot be null.");
            this.tags.add(t);
        }
    }

    /**
     * Placeholder
     * @param fields placeholder
     * @param tags placeholder
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

    public Person setField(Field field) {
        Map<Prefix, Field> updatedFields = new HashMap<>(fields);
        if (field == null) {
            updatedFields.remove(field.prefix);
        } else {
            updatedFields.put(field.prefix, field);
        }
        return new Person(updatedFields.values(), tags);
    }

    public Optional<Field> getField(Prefix prefix) {
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

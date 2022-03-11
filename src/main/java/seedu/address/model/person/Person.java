package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Favourite favourite;

    // Data fields
    private final Address address;
    private final Set<Property> properties;
    private final Optional<Property> preference;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * This constructor is used when editing a Client.
     * Favourited clients will remain favourited & unfavourited clients will remain unfavourited
     */
    public Person(Name name, Phone phone, Email email, Favourite favourite, Address address,
            Set<Property> properties, Optional<Property> preference, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, favourite, address, properties, preference, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.favourite = favourite;
        this.properties = properties;
        this.preference = preference;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     * This constructor is used for adding a new Client, thus default status is unfavourited(false)
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Property> properties,
            Optional<Property> preference, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, properties, preference, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.favourite = new Favourite(false);
        this.address = address;
        this.properties = properties;
        this.preference = preference;
        this.tags.addAll(tags);
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

    public Favourite getFavourite() {
        return favourite;
    }

    /**
     * Toggles the favourite status of Person
     */
    public void toggleFavourite() {
        boolean toggledStatus = !favourite.getStatus();
        favourite.setStatus(toggledStatus);
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable property set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Property> getProperties() {
        return Collections.unmodifiableSet(properties);
    }

    public Optional<Property> getPreference() {
        return preference;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
     * Returns true if the given {@code buyer}'s {@code preference}
     * matches with {@code this} person's {@code property}.
     */
    public boolean matches(Person buyer) {
        if (properties.isEmpty()) {
            return false;
        }
        if (buyer.preference.isEmpty()) {
            return false;
        }

        for (Property p : properties) {
            if (p.matches(buyer.preference.get())) {
                return true;
            }
        }

        return false;
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
                && otherPerson.getProperties().equals(getProperties())
                && otherPerson.getPreference().equals(getPreference())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, favourite, address, properties, preference, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Favourite: ")
                .append(getFavourite())
                .append("; Address: ")
                .append(getAddress());

        Set<Property> properties = getProperties();
        if (!properties.isEmpty()) {
            builder.append("; Properties: ");
            properties.forEach(builder::append);
        }

        if (getPreference().isPresent()) {
            builder.append("; Preference: ");
            builder.append(getPreference().get());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}

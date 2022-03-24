package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.property.Property;
import seedu.address.model.userimage.UserImage;

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
    private final Optional<Preference> preference;
    private final UserType userType;
    private final Set<UserImage> userImages;

    /**
     * This constructor is used when editing a Client.
     * Favourited clients will remain favourited & unfavourited clients will remain unfavourited
     */
    public Person(Name name, Phone phone, Email email, Favourite favourite, Address address,
            Set<Property> properties, Optional<Preference> preference, UserType userType, Set<UserImage> userImages) {
        requireAllNonNull(name, phone, email, favourite, address, properties, preference, userType, userImages);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.favourite = favourite;
        this.properties = properties;
        this.preference = preference;
        this.address = address;
        this.userType = userType;
        this.userImages = userImages;
    }

    /**
     * Every field must be present and not null.
     * This constructor is used for adding a new Client, thus default status is unfavourited(false)
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Property> properties,
            Optional<Preference> preference, UserType userType, Set<UserImage> userImages) {
        requireAllNonNull(name, phone, email, address, properties, preference, userType, userImages);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.favourite = new Favourite(false);
        this.address = address;
        this.properties = properties;
        this.preference = preference;
        this.userType = userType;
        this.userImages = userImages;
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

    public Optional<Preference> getPreference() {
        return preference;
    }

    public UserType getUserType() {
        return userType;
    }

    public Set<UserImage> getUserImages() {
        return userImages;
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
     * matches with at least one of {@code this} person's {@code properties}.
     */
    public boolean matches(Person buyer) {
        if (properties.isEmpty()) {
            return false;
        }
        if (buyer.preference.isEmpty()) {
            return false;
        }

        for (Property p : properties) {
            if (buyer.preference.get().matches(p)) {
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
                && otherPerson.getUserType().equals(getUserType())
                && otherPerson.getUserImages().equals(getUserImages());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, favourite, address, properties, preference, userType, userImages);
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

        if (getFavourite() != null) {
            builder.append("; Favourite: Favourited");
        }

        Set<Property> properties = getProperties();
        if (!properties.isEmpty()) {
            builder.append("; Properties: ");
            properties.forEach(builder::append);
        }

        if (getPreference().isPresent()) {
            builder.append("; Preference: ");
            builder.append(getPreference().get());
        }

        builder.append("; User Type: ").append(getUserType());

        Set<UserImage> userImages = getUserImages();
        if (!getUserImages().isEmpty()) {
            for (UserImage image : userImages) {
                builder.append("; UserImage: ");
                builder.append(image.getFilePath());
                builder.append("; Description: ");
                builder.append(image.getDescription());
            }
        }
        return builder.toString();
    }
}

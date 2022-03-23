package seedu.address.model.pet;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Pet in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Pet {

    // Identity fields
    private final Name name;
    private final OwnerName ownerName;
    private final Phone phone;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Diet diet;
    private final Appointment appointment;
    private final AttendanceHashMap attendanceHashMap;

    /**
     * Every field must be present and not null.
     */
    public Pet(Name name, OwnerName ownerName, Phone phone, Address address,
               Set<Tag> tags, Diet diet, Appointment appointment, AttendanceHashMap attendanceHashMap) {
        requireAllNonNull(name, ownerName, phone, address, tags, appointment, attendanceHashMap);
        this.name = name;
        this.ownerName = ownerName;
        this.phone = phone;
        this.address = address;
        this.diet = diet;
        this.appointment = appointment;
        this.tags.addAll(tags);
        this.attendanceHashMap = attendanceHashMap;
    }


    public Name getName() {
        return name;
    }

    public OwnerName getOwnerName() {
        return ownerName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public Diet getDiet() {
        return diet;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public AttendanceHashMap getAttendanceHashMap() {
        return attendanceHashMap.getCopy();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both pets have the same name.
     * This defines a weaker notion of equality between two pets.
     */
    public boolean isSamePet(Pet otherPet) {
        if (otherPet == this) {
            return true;
        }

        return otherPet != null
                && otherPet.getName().equals(getName());
    }

    /**
     * Returns true if both pets have the same identity and data fields.
     * This defines a stronger notion of equality between two pets.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Pet)) {
            return false;
        }

        Pet otherPet = (Pet) other;
        return otherPet.getName().equals(getName())
                && otherPet.getOwnerName().equals(getOwnerName())
                && otherPet.getPhone().equals(getPhone())
                && otherPet.getAddress().equals(getAddress())
                && otherPet.getDiet().equals(getDiet())
                && otherPet.getAppointment().equals(getAppointment())
                && otherPet.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, ownerName, phone, address, tags, diet, appointment);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; OwnerName: ")
                .append(getOwnerName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Address: ")
                .append(getAddress())
                .append("; Diet: ")
                .append(getDiet())
                .append("; Appointment: ")
                .append(getAppointment());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}

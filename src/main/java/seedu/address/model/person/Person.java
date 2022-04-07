package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
/**
 * Represents a Person in Tracey.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    public static final String[] UNIQUE_ATTRIBUTE_TYPES = {"Phone", "Email", "Matriculation Number"};

    // Identity fields
    private final Name name;
    private final Block block;
    private final Faculty faculty;
    private final Phone phone;
    private final Email email;
    private final MatriculationNumber number;
    // Data fields
    private final Address address;
    private final CovidStatus status;
    private final Set<Tag> tags = new HashSet<>();
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Block block, Faculty faculty, Phone phone, Email email, Address address,
                  MatriculationNumber number, CovidStatus status, Set<Tag> tags) {
        requireAllNonNull(name, faculty, phone, email, address, number, status, tags);
        this.name = name;
        this.block = block;
        this.faculty = faculty;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.number = number;
        this.status = status;
        this.tags.addAll(tags);
    }
    public Name getName() {
        return name;
    }
    public Block getBlock() {
        return block;
    }
    public Faculty getFaculty() {
        return faculty;
    }
    public Phone getPhone() {
        return phone;
    }
    public Email getEmail() {
        return email;
    }
    public MatriculationNumber getMatriculationNumber() {
        return number;
    }
    public CovidStatus getStatus() {
        return status;
    }
    /**
     * Returns the Covid Status as a String instead of type CovidStatus.
     *
     * @return A string of the covidStatus
     */
    public String getStatusAsString() {
        return status.toString();
    }
    /**
     * Returns the block as a String instead of type Block.
     *
     * @return A string of the block
     */
    public String getBlockAsString() {
        return block.toString();
    }
    /**
     * Predicate to check if the status of Person object matches the given CovidStatus.
     *
     * @param status given status to check if matched by status property in Person
     * @return Boolean value to show if there is a match
     */
    public boolean isStatus(CovidStatus status) {
        return this.status.equals(status);
    }

    /**
     * Predicate to check if the faculty of Person object matches the given faculty.
     *
     * @param faculty given faculty to check if matched by faculty property in Person
     * @return boolean value to show if there is a match
     */
    public boolean isFaculty(Faculty faculty) {
        return this.faculty.equals(faculty);
    }

    /**
     * Returns the Faculty of this person as a String instead of type Faculty.
     *
     * @return A string of the faculty
     */
    public String getFacultyAsString() {
        return faculty.toString();
    }

    /**
     * Predicate to check if the block of Person object matches the given block.
     *
     * @param block given block to check if matched by block property in Person
     * @return Boolean value to show if there is a match
     */
    public boolean isBlock(Block block) {
        return this.block.equals(block);
    }

    public Address getAddress() {
        return address;
    }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    /**
     * Returns true if both persons have the same phone number or matriculation number or email
     * This defines a stronger notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }
        return otherPerson != null
                && (otherPerson.getPhone().equals(getPhone())
                || otherPerson.getMatriculationNumber().equals(getMatriculationNumber())
                || otherPerson.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have different phone numbers or matriculation numbers or emails
     * This helps to identify if any of the unique details of a person has been altered.
     */
    public boolean isDifferentPerson(Person otherPerson) {
        if (otherPerson == this) {
            return false;
        }
        return otherPerson != null
                && !(otherPerson.getPhone().equals(getPhone())
                && otherPerson.getMatriculationNumber().equals(getMatriculationNumber())
                && otherPerson.getEmail().equals(getEmail()));
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
                && otherPerson.getBlock().equals(getBlock())
                && otherPerson.getFaculty().equals(getFaculty())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getMatriculationNumber().equals(getMatriculationNumber())
                && otherPerson.getStatus().equals(getStatus())
                && otherPerson.getTags().equals(getTags());
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, block, faculty, phone, email, address, number, status, tags);
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Block: ")
                .append(getBlock())
                .append("; Faculty: ")
                .append(getFaculty())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Matriculation Number: ")
                .append(getMatriculationNumber())
                .append("; Covid Status: ")
                .append(getStatus());
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}

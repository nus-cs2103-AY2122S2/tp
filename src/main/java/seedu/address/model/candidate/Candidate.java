package seedu.address.model.candidate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Candidate {

    // Identity fields
    private final StudentID studentID;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Course course;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Candidate(StudentID studentID, Name name, Phone phone, Email email, Course course, Set<Tag> tags) {
        requireAllNonNull(studentID, name, phone, email, course, tags);
        this.studentID = studentID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.course = course;
        this.tags.addAll(tags);
    }

    public StudentID getStudentID() {
        return studentID;
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

    public Course getCourse() {
        return course;
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
    public boolean isSamePerson(Candidate otherCandidate) {
        if (otherCandidate == this) {
            return true;
        }

        return otherCandidate != null
                && otherCandidate.getStudentID().equals(getStudentID())
                && otherCandidate.getName().equals(getName());
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

        if (!(other instanceof Candidate)) {
            return false;
        }

        Candidate otherCandidate = (Candidate) other;
        return otherCandidate.getStudentID().equals(getStudentID())
                && otherCandidate.getName().equals(getName())
                && otherCandidate.getPhone().equals(getPhone())
                && otherCandidate.getEmail().equals(getEmail())
                && otherCandidate.getCourse().equals(getCourse())
                && otherCandidate.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentID, name, phone, email, course, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName() + ", " + getStudentID())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Course: ")
                .append(getCourse());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}

package seedu.address.model.person;

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
public class Person {

    // Identity fields
    private final StudentId studentId;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Course course;
    private final ApplicationStatus applicationStatus;
    private final InterviewStatus interviewStatus;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(StudentId studentId, Name name, Phone phone, Email email, Course course, Set<Tag> tags,
                  ApplicationStatus applicationStatus, InterviewStatus interviewStatus) {
        requireAllNonNull(studentId, name, phone, email, course, tags);
        this.studentId = studentId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.course = course;
        this.tags.addAll(tags);
        this.applicationStatus = applicationStatus;
        this.interviewStatus = interviewStatus;
    }

    public StudentId getStudentId() {
        return studentId;
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

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public InterviewStatus getInterviewStatus() {
        return interviewStatus;
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
                && otherPerson.getStudentId().equals(getStudentId())
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
        return otherPerson.getStudentId().equals(getStudentId())
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getCourse().equals(getCourse())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getApplicationStatus().equals(getApplicationStatus())
                && otherPerson.getInterviewStatus().equals(getInterviewStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, phone, email, course, tags, applicationStatus, interviewStatus);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Student ID: ").append(getStudentId())
                .append("; Name: ")
                .append(getName())
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
        builder.append("; Application Status: ")
                .append(getApplicationStatus());
        builder.append("; Interview Status: ")
                .append(getInterviewStatus());
        return builder.toString();
    }

}

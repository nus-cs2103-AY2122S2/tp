package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.DisplayType;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialName;

/**
 * Represents a Student in a Tutorial in the addressbook.
 * A Student is a Person with a Student ID and a Tutorial.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {
    // Identity fields
    private static final DisplayType displayType = DisplayType.STUDENT;
    private final NusNetId nusNetId;
    private final TutorialName tutName;

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, NusNetId id, TutorialName tut) {
        super(name, phone, email, address, tags);
        requireAllNonNull(id, tut);
        nusNetId = id;
        tutName = tut;
    }

    /**
     * Returns true if given object is a Student.
     */
    public static boolean isStudent(Object object) {
        return object instanceof Student;
    }

    /**
     * Returns true if given student belongs to a Tutorial.
     */
    public boolean isInTutorial(TutorialName tutName) {
        return tutName.equals(getTutorialName());
    }

    public NusNetId getStudentId() {
        return nusNetId;
    }

    public TutorialName getTutorialName() {
        return tutName;
    }

    @Override
    public DisplayType getDisplayType() {
        return displayType;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getStudentId().equals(getStudentId())
                && otherStudent.getTutorialName().equals(getTutorialName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), getTags(), nusNetId, tutName);
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
                .append(getAddress())
                .append("; NUSNET ID: ")
                .append(getStudentId())
                .append("; Tutorial Class: ")
                .append(getTutorialName());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}

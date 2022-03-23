package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityType;

//@@author wxliong
/**
 * Represents a Student in the TAssist.
 * Guarantees: details are present and not null (except Telegram handle), field values are validated, immutable.
 */
public class Student implements Entity {

    // Identity fields
    private final StudentId studentId;
    private final Name name;
    private final Email email;
    private final Optional<Telegram> telegram;

    /**
     * Every field except Telegram handle must be present and not null.
     */
    public Student(StudentId studentId, Name name, Email email) {
        this(studentId, name, email, Optional.empty());
    }

    /**
     * Every field must be present and not null.
     */
    public Student(StudentId studentId, Name name, Email email, Optional<Telegram> telegram) {
        requireAllNonNull(studentId, name, email, telegram);
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.telegram = telegram;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Optional<Telegram> getTelegram() {
        return telegram;
    }

    /**
     * Returns true if both students have the same student ID.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentId().equals(getStudentId());
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.STUDENT;
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getStudentId().equals(getStudentId())
                && otherStudent.getName().equals(getName())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getTelegram().equals(getTelegram());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentId, name, email, telegram);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getStudentId())
                .append("; Name: ")
                .append(getName())
                .append("; Email: ")
                .append(getEmail());
        if (telegram.isPresent()) {
            builder.append("; Telegram: ")
                    .append(getTelegram().get().value);
        }

        return builder.toString();
    }

}

package seedu.address.model.studentattendance;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.student.Student;

//@@author jxt00
/**
 * Represents a StudentAttendance in the TAssist.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class StudentAttendance {
    // Identity field
    private final Student student;
    // Data field
    private final Attendance attendance;

    /**
     * Constructs a {@code StudentAttendance}.
     * Every field must be present and not null.
     *
     * @param student A valid student.
     * @param attendance A valid attendance.
     */
    public StudentAttendance(Student student, Attendance attendance) {
        requireAllNonNull(student, attendance);
        this.student = student;
        this.attendance = attendance;
    }

    public Student getStudent() {
        return student;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    /**
     * Marks the attendance of the given student if the student match the
     * student identity in this {@code StudentAttendance}.
     */
    public StudentAttendance markAttendance() {
        if (this.attendance.value) {
            return this;
        }
        return new StudentAttendance(this.student, new Attendance(true));
    }

    /**
     * Unmarks the attendance of the given student if the student match the
     * student identity in this {@code StudentAttendance}.
     */
    public StudentAttendance unmarkAttendance(Student s) {
        if (this.attendance.value) {
            return new StudentAttendance(student, new Attendance(false));
        }
        return this;
    }

    /**
     * Returns true if both student attendances have the same student.
     * This defines a weaker notion of equality between two student attendances.
     */
    public boolean isSameStudentAttendance(StudentAttendance otherStudentAttendance) {
        if (otherStudentAttendance == this) {
            return true;
        }

        return otherStudentAttendance != null
                && otherStudentAttendance.getStudent().equals(getStudent());
    }

    @Override
    public String toString() {
        return student.toString() + ": " + attendance.value.toString();
    }

    /**
     * Returns true if both student attendances have the same identity and data fields.
     * This defines a stronger notion of equality between two student attendances.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StudentAttendance)) {
            return false;
        }

        StudentAttendance otherStudentAttendance = (StudentAttendance) other;
        return otherStudentAttendance.getStudent().equals(getStudent())
                && otherStudentAttendance.getAttendance().equals(getAttendance());
    }
}

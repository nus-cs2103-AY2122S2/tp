package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.studentattendance.StudentAttendance;

//@@author jxt00
/**
 * Represents a Lesson in the TAssist.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {
    // Identity field
    private final WeekId weekId;
    // Data field
    private final List<StudentAttendance> studentAttendanceList;

    /**
     * Constructs a {@code Lesson}.
     * {@code weekId} must be present and not null.
     *
     * @param weekId A valid week ID.
     */
    public Lesson(WeekId weekId) {
        requireAllNonNull(weekId);
        this.weekId = weekId;
        this.studentAttendanceList = new ArrayList<>();
    }

    /**
     * Constructs a {@code Lesson}.
     * {@code weekId} and {@code studentAttendanceList} must be present and not null.
     *
     * @param weekId A valid week ID.
     * @param studentAttendanceList A valid student attendance list.
     */
    public Lesson(WeekId weekId, List<StudentAttendance> studentAttendanceList) {
        requireAllNonNull(weekId, studentAttendanceList);
        this.weekId = weekId;
        this.studentAttendanceList = new ArrayList<>(studentAttendanceList);
    }

    public WeekId getWeekId() {
        return weekId;
    }

    public List<StudentAttendance> getStudentAttendanceList() {
        return studentAttendanceList;
    }

    /**
     * Returns true if both lessons have the same week ID.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getWeekId().equals(getWeekId());
    }

    /**
     * Returns true if both lessons have the same identity and data fields.
     * This defines a stronger notion of equality between two lessons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getWeekId().equals(getWeekId())
                && otherLesson.getStudentAttendanceList().equals(getStudentAttendanceList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekId, studentAttendanceList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getWeekId());

        List<StudentAttendance> studentAttendances = getStudentAttendanceList();
        if (!studentAttendances.isEmpty()) {
            builder.append("; Student Attendances: ");
            studentAttendances.forEach(builder::append);
        }
        return builder.toString();
    }
}

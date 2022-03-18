package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RecurringLesson extends Lesson {
    private static final String RECURRING_LESSON_DESCRIPTOR = "============== [RECURRING LESSON] ==============";
    private DayOfWeek dayOfLesson;

    /**
     * Every field must be present and not null.
     */
    protected RecurringLesson(LessonName name, Subject subject, LessonAddress address,
                              DateTimeSlot dateTimeSlot, EnrolledStudents enrolledStudents) {
        super(name, subject, address, dateTimeSlot, enrolledStudents);
        requireAllNonNull(dateTimeSlot);
        dayOfLesson = DayOfWeek.from(super.getDateTimeSlot().getDateOfLesson());
    }

    @Override
    public DateTimeSlot getDateTimeSlot() {
        DateTimeSlot start = super.getDateTimeSlot();
        if (start.getDateOfLesson().isAfter(LocalDateTime.now())) {
            return start;
        } else {
            return new DateTimeSlot(LocalDateTime.of(LocalDateTime.now().with(dayOfLesson).toLocalDate(),
                    start.getDateOfLesson().toLocalTime()), start.getHours(), start.getMinutes());
        }
    }

    @Override
    public boolean isConflictingWithLesson(Lesson otherLesson) {
        requireAllNonNull(otherLesson);
        if (otherLesson == this) {
            return true;
        }
        if (!dayOfLesson.equals(DayOfWeek.from(otherLesson.getDateTimeSlot().getDateOfLesson()))) {
            return false;
        }
        if (otherLesson instanceof TemporaryLesson) {
            LocalDateTime tempDate = LocalDateTime.of(otherLesson.getDateTimeSlot().getDateOfLesson().toLocalDate(),
                    this.getDateTimeSlot().getDateOfLesson().toLocalTime());
            DateTimeSlot temp = new DateTimeSlot(tempDate,
                    this.getDateTimeSlot().getHours(), this.getDateTimeSlot().getMinutes());
            return temp.isConflictingWith(otherLesson.getDateTimeSlot());
        } else {
            LocalDate dummyDate = LocalDateTime.now().toLocalDate();
            DateTimeSlot thisLesson = new DateTimeSlot(LocalDateTime.of(dummyDate,
                    this.getDateTimeSlot().getDateOfLesson().toLocalTime()),
                    this.getDateTimeSlot().getHours(),
                    this.getDateTimeSlot().getMinutes());
            DateTimeSlot compareLesson = new DateTimeSlot(LocalDateTime.of(dummyDate,
                    otherLesson.getDateTimeSlot().getDateOfLesson().toLocalTime()),
                    otherLesson.getDateTimeSlot().getHours(),
                    otherLesson.getDateTimeSlot().getMinutes());
            return thisLesson.isConflictingWith(compareLesson);
        }
    }

    /**
     * Returns true if both lessons have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecurringLesson)) {
            return false;
        }

        RecurringLesson otherLesson = (RecurringLesson) other;

        return otherLesson.getName().equals(getName())
                && otherLesson.getSubject().equals(getSubject())
                && otherLesson.getLessonAddress().equals(getLessonAddress())
                && otherLesson.getDateTimeSlot().equals(getDateTimeSlot());
    }

    @Override
    public String toString() {
        StringBuilder lessonString = new StringBuilder();

        lessonString.append(RECURRING_LESSON_DESCRIPTOR)
                .append(System.getProperty("line.separator"))
                .append(super.toString());

        return lessonString.toString();
    }
}

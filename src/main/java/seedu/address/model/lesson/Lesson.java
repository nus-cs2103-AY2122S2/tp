package seedu.address.model.lesson;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Lesson {

    // Identity fields
    private final Name name;
    private final Subject subject;

    // Data fields
    // no data fields in abstract class

    /**
     * Every field must be present and not null.
     */
    public Lesson(Name name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public Name getName() {
        return name;
    }

    public Subject getSubject() {
        return subject;
    }

    /**
     * Returns true if both lessons clash.
     */
    public abstract boolean isClashingWithLesson(Lesson otherLesson);

    /**
     * Returns true if both lessons have the same name, subject and timeslot.
     * @param otherLesson
     */
    public abstract boolean isSameLesson(Lesson otherLesson);

    /**
     * Returns the date and time that the lesson starts and ends.
     */
    public abstract TimeSlot getTimeSlot();
}

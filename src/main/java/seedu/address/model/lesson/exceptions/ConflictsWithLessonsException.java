package seedu.address.model.lesson.exceptions;

import java.util.List;

import seedu.address.model.lesson.Lesson;

/**
 * Signals that the operation will result in two lessons with conflicting timeslots.
 */
public class ConflictsWithLessonsException extends RuntimeException {
    public static final String ERROR_MESSAGE = "WARNING: This lesson cannot be added as it conflicts"
            + " with these existing lessons in your schedule:";

    private final Lesson lessonToAdd;
    private final List<Lesson> conflictingLessons;

    /**
     * Creates an exception specifying which two lessons would conflict with each other.
     * @param lessonToAdd
     * @param conflictingLessons
     */
    public ConflictsWithLessonsException(Lesson lessonToAdd, List<Lesson> conflictingLessons) {
        super();

        this.lessonToAdd = lessonToAdd;
        this.conflictingLessons = conflictingLessons;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder();

        message.append(ERROR_MESSAGE)
                .append(getPadding());

        for (Lesson l : conflictingLessons) {
            message.append(l.toString())
                    .append(getPadding());
        }

        return message.toString();
    }

    public List<Lesson> getConflictingLessons() {
        return List.copyOf(this.conflictingLessons);
    }

    private String getPadding() {
        return System.getProperty("line.separator") + System.getProperty("line.separator");
    }
}

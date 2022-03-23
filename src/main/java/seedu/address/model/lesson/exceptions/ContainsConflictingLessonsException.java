package seedu.address.model.lesson.exceptions;

import java.util.List;

import seedu.address.model.lesson.Lesson;

/**
 * Signals that the operation will result in two lessons with conflicting timeslots.
 */
public class ContainsConflictingLessonsException extends RuntimeException {
    public static final String ERROR_MESSAGE = "These lessons have conflicting timeslots:";
    public static final String PADDING = "   ->";

    private final List<Lesson> conflictingLessons;

    /**
     * Creates an exception specifying which lessons in the list conflict with each other.
     * @param conflictingLessons
     */
    public ContainsConflictingLessonsException(List<Lesson> conflictingLessons) {
        super();
        this.conflictingLessons = conflictingLessons;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder();
        message.append(getPadding())
                .append(ERROR_MESSAGE)
                .append(getPadding());

        for (Lesson l : conflictingLessons) {
            message.append(l.toString())
                    .append(getPadding());
        }

        return message.toString();
    }

    private String getPadding() {
        return System.getProperty("line.separator") + System.getProperty("line.separator");
    }
}

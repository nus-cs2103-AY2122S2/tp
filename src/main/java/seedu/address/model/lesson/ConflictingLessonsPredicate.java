package seedu.address.model.lesson;

import java.util.function.Predicate;

/**
 * Tests that a {@code Lesson} clashes with the specified {@code Lesson}.
 */
public class ConflictingLessonsPredicate implements Predicate<Lesson> {
    private final Lesson lessonToCheck;

    public ConflictingLessonsPredicate(Lesson lessonToCheck) {
        this.lessonToCheck = lessonToCheck;
    }

    @Override
    public boolean test(Lesson lesson) {
        return lesson.isConflictingWithLesson(lessonToCheck);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConflictingLessonsPredicate // instanceof handles nulls
                && lessonToCheck.equals(((ConflictingLessonsPredicate) other).lessonToCheck)); // state check
    }

}

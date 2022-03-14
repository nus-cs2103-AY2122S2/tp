package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.lesson.exceptions.ConflictsWithLessonException;
import seedu.address.model.lesson.exceptions.LessonNotFoundException;
import seedu.address.model.student.Student;

/**
 * A list of lessons that enforces uniqueness between its elements and does not allow nulls.
 * A lesson is considered unique by comparing using {@code Lesson#isClashingWith(Lesson)}.
 * As such, adding and updating of lessons uses Lesson#isClashingWith(Lesson) for equality so as to
 * ensure that the lesson being added or updated does not clash with any lesson in the UniqueLessonList.
 * However, the removal of a lesson uses Lesson#equals(Object) to ensure that the lesson with exactly the
 * same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Lesson#isConflictingWithLesson(Lesson)
 */
public class UniqueLessonList implements Iterable<Lesson> {

    private final ObservableList<Lesson> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lesson> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if some lesson in the list conflicts with the specified lesson
     */
    public boolean hasConflictingLesson(Lesson toCheck) {
        requireNonNull(toCheck);

        return findLessonConflictingWith(toCheck) != null;
    }

    /**
     * Returns some lesson in the list with timeslot that overlaps with the specified lesson, if exists.
     */
    public Lesson findLessonConflictingWith(Lesson toCheck) {
        requireNonNull(toCheck);

        for (Lesson existingLesson : internalList) {
            if (existingLesson.isConflictingWithLesson(toCheck)) {
                return existingLesson;
            }
        }

        return null;
    }

    /**
     * Adds a lesson to the list.
     * The time slot of the lesson must not conflict with any of the existing lessons in the list.
     */
    public void add(Lesson toAdd) {
        requireNonNull(toAdd);
        if (hasConflictingLesson(toAdd)) {

            throw new ConflictsWithLessonException(findLessonConflictingWith(toAdd), toAdd);
        }
        internalList.add(toAdd);
    }

    public void assignStudent(Student student, Index lessonId) {
        requireAllNonNull(student, lessonId);
        internalList.get(lessonId.getZeroBased()).assignStudent(student);
    }

    /**
     * Replaces the lesson {@code target} in the list with {@code editedLesson}.
     * {@code target} must exist in the list.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in the list.
     */
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireAllNonNull(target, editedLesson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LessonNotFoundException();
        }

        if (hasConflictingLesson(editedLesson)) {
            throw new ConflictsWithLessonException(findLessonConflictingWith(editedLesson), editedLesson);
        }

        internalList.set(index, editedLesson);
    }

    /**
     * Removes the equivalent lesson from the list.
     * The lesson must exist in the list.
     */
    public void remove(Lesson toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LessonNotFoundException();
        }
    }

    public void setLessons(UniqueLessonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code lessons}.
     * {@code lessons} must not contain lessons with overlapping timeslots.
     */
    public void setLessons(List<Lesson> lessons) {
        requireAllNonNull(lessons);
        if (!lessonsDoNotConflict(lessons)) {
            List<Lesson> conflictingLessons = findConflictingLessons(lessons);
            throw new ConflictsWithLessonException(conflictingLessons.get(0), conflictingLessons.get(1));
        }

        internalList.setAll(lessons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lesson> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Lesson> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLessonList // instanceof handles nulls
                        && internalList.equals(((UniqueLessonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code lessons} contains only non-conflicting lessons.
     */
    private boolean lessonsDoNotConflict(List<Lesson> lessons) {
        for (int i = 0; i < lessons.size() - 1; i++) {
            for (int j = i + 1; j < lessons.size(); j++) {
                if (lessons.get(i).isConflictingWithLesson(lessons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a list containing two instances of conflicting lessons in {@code lessons}, if any.
     */
    private List<Lesson> findConflictingLessons(List<Lesson> lessons) {
        for (int i = 0; i < lessons.size() - 1; i++) {
            for (int j = i + 1; j < lessons.size(); j++) {
                if (lessons.get(i).isConflictingWithLesson(lessons.get(j))) {
                    return Arrays.asList(lessons.get(i), lessons.get(j));
                }
            }
        }
        return null;
    }
}

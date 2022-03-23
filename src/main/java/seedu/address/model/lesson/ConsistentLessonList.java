package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.lesson.exceptions.ConflictsWithLessonsException;
import seedu.address.model.lesson.exceptions.ContainsConflictingLessonsException;
import seedu.address.model.lesson.exceptions.LessonNotFoundException;
import seedu.address.model.student.Student;

/**
 * A list of lessons that enforces consistency between the lessons contained in it and does not allow nulls.
 *
 * A lesson is considered consistent if its assigned date and time does not clash with any other lesson inside
 * the list. This is achieved using {@code Lesson#isClashingWith(Lesson)}.
 *
 * As such, adding and updating of lessons uses Lesson#isClashingWith(Lesson) to ensure that the lesson
 * being added or updated does not clash with any lesson in the ConsistentLessonList.
 *
 * However, the removal of a lesson uses Lesson#equals(Object) to ensure that the lesson with exactly the
 * same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Lesson#isConflictingWithLesson(Lesson)
 */
public class ConsistentLessonList implements Iterable<Lesson> {

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
     * Returns a list of lessons in the list with timeslot that overlaps with the specified lesson, if exists.
     */
    public List<Lesson> findAllLessonsConflictingWith(Lesson toCheck) {
        requireNonNull(toCheck);

        List<Lesson> conflictingLessons = new ArrayList<>();
        for (Lesson existingLesson : internalList) {
            if (existingLesson.isConflictingWithLesson(toCheck)) {
                conflictingLessons.add(existingLesson);
            }
        }
        return conflictingLessons;
    }

    /**
     * Adds a lesson to the list.
     * The time slot of the lesson must not conflict with any of the existing lessons in the list.
     */
    public void add(Lesson toAdd) {
        requireNonNull(toAdd);
        if (hasConflictingLesson(toAdd)) {
            throw new ConflictsWithLessonsException(toAdd, findAllLessonsConflictingWith(toAdd));
        }
        internalList.add(toAdd);
    }

    /**
     * Assigns the lesson to the student's enrolled lessons.
     * @param student the student that is enrolling in the lesson
     * @param lesson the lesson that the student is enrolling in
     */
    public void assignStudent(Student student, Lesson lesson) {
        requireAllNonNull(student, lesson);
        assert internalList.contains(lesson) : "Cannot find lesson object in internal list.";
        internalList.get(internalList.indexOf(lesson)).assignStudent(student);
    }

    /**
     * Unassigns the student from the lesson's enrolled students.
     * @param student the student that is being deleted
     */
    public void unassignStudent(Student student) {
        requireNonNull(student);
        for (Lesson lesson: internalList) {
            if (lesson.hasAlreadyAssigned(student)) {
                lesson.unassignStudent(student);
            }
        }
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
            throw new ConflictsWithLessonsException(editedLesson, findAllLessonsConflictingWith(editedLesson));
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

    public void setLessons(ConsistentLessonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code lessons}.
     * {@code lessons} must not contain lessons with overlapping timeslots.
     */
    public void setLessons(List<Lesson> lessons) {
        requireAllNonNull(lessons);
        if (lessonsDoConflict(lessons)) {
            List<Lesson> conflictingLessons = findConflictingLessons(lessons);
            throw new ContainsConflictingLessonsException(conflictingLessons);
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
                || (other instanceof ConsistentLessonList // instanceof handles nulls
                        && internalList.equals(((ConsistentLessonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns false if {@code lessons} contains only non-conflicting lessons.
     */
    private boolean lessonsDoConflict(List<Lesson> lessons) {
        for (int i = 0; i < lessons.size() - 1; i++) {
            for (int j = i + 1; j < lessons.size(); j++) {
                if (lessons.get(i).isConflictingWithLesson(lessons.get(j))) {
                    return true;
                }
            }
        }
        return false;
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

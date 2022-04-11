package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.lesson.ConsistentLessonList;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;

/**
 * Wraps all data at the address-book level
 * Lessons with conflicting timeslots are not allowed (by .isConflictingWithLesson comparison)
 */
public class LessonBook implements ReadOnlyLessonBook {

    private final ConsistentLessonList lessons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        lessons = new ConsistentLessonList();
    }

    public LessonBook() {}

    /**
     * Creates an StudentBook using the Students in the {@code toBeCopied}
     */
    public LessonBook(ReadOnlyLessonBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the lesson list with {@code lessons}.
     * {@code students} must not contain duplicate lessons.
     */
    public void setLessons(List<Lesson> lessons) {
        this.lessons.setLessons(lessons);
    }

    /**
     * Resets the existing data of this {@code LessonBook} with {@code newData}.
     */
    public void resetData(ReadOnlyLessonBook newData) {
        requireNonNull(newData);

        setLessons(newData.getLessonList());
    }

    //// lesson-level operations

    /**
     * Returns true if a lesson that conflicts with {@code lesson} exists in the list of lessons.
     */
    public boolean hasConflictingLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessons.hasConflictingLesson(lesson);
    }

    /**
     * Adds a lesson to the lesson book.
     * The lesson must not already exist in the lesson book.
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void assignStudent(Student student, Lesson lesson) {
        lessons.assignStudent(student, lesson);
    }

    public void unasssignStudent(Student student) {
        lessons.unassignStudent(student);
    }

    /**
     * Replaces the given lesson {@code target} in the list with {@code editedLesson}.
     * {@code target} must exist in the student book.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in the lesson book.
     */
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireNonNull(editedLesson);

        lessons.setLesson(target, editedLesson);
    }

    /**
     * Removes {@code key} from this {@code LessonBook}.
     * {@code key} must exist in the lesson book.
     */
    public void deleteLesson(Lesson key) {
        lessons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return lessons.asUnmodifiableObservableList().size() + " lessons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Lesson> getLessonList() {
        return lessons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonBook // instanceof handles nulls
                && lessons.equals(((LessonBook) other).lessons));
    }

    @Override
    public int hashCode() {
        return lessons.hashCode();
    }
}

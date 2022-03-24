package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Lesson> PREDICATE_SHOW_ALL_LESSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' student book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Sets the user prefs' student book file path.
     */
    void setStudentBookFilePath(Path studentBookFilePath);

    /**
     * Replaces student book data with the data in {@code studentBook}.
     */
    void setStudentBook(ReadOnlyStudentBook studentBook);

    /** Returns the StudentBook */
    ReadOnlyStudentBook getStudentBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the student book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the student book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the student book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * student book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    //=========== LessonBook =================================================================================

    /** Returns the LessonBook */
    ReadOnlyLessonBook getLessonBook();

    /** Returns an unmodifiable view of the filtered lesson list */
    ObservableList<Lesson> getFilteredLessonList();

    /**
     * Returns true if a lesson that conflicts with {@code lesson} exists in the list of lessons.
     */
    boolean hasConflictingLesson(Lesson lesson);

    /**
     * Adds the given lesson.
     * The lesson must not already exist in the lesson book.
     */
    void addLesson(Lesson lesson);

    /**
     * Deletes the given lesson.
     * The lesson must exist in the lesson book.
     */
    void deleteLesson(Lesson lesson);

    /**
     * Updates the filter of the filtered lesson list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredLessonList(Predicate<Lesson> predicate);

    /**
     * Updates both filtered lesson and students list.
     */
    void updateAssignment(Student student, Lesson lesson);

    /**
     * Updates both filtered lesson and students list.
     */
    void updateUnassignment(Student student, Lesson lesson);

    /**
     * Sets the selected {@code Student} with the given {@code Student} for UI use.
     * @param student The given {@code Student}.
     */
    void setSelectedStudent(Student student);

    /** Returns the selected {@code Student} */
    Student getSelectedStudent();

    /**
     * Sets the selected {@code Lesson} with the given {@code Lesson} for UI use.
     * @param lesson The given {@code Lesson}.
     */
    void setSelectedLesson(Lesson lesson);

    /** Returns the selected {@code Lesson} */
    Lesson getSelectedLesson();

    /**
     * Checks if the {@code Index} provided is out of bounds of the {@code filteredStudentList}
     */
    boolean checkStudentListIndex(Index studentId);

    /**
     * Checks if the {@code Index} provided is out of bounds of the {@code filteredLessonList}
     */
    boolean checkLessonListIndex(Index lessonId);
}

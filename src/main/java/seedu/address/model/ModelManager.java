package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;

/**
 * Represents the in-memory model of the student book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StudentBook studentBook;
    private final LessonBook lessonBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Lesson> filteredLessons;
    private Student selectedStudent;
    private Lesson selectedLesson;

    /**
     * Initializes a ModelManager with the given studentBook and userPrefs.
     */
    public ModelManager(ReadOnlyStudentBook studentBook, ReadOnlyLessonBook lessonBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(studentBook, userPrefs);

        logger.fine("Initializing with student book: " + studentBook + " and user prefs " + userPrefs);

        this.studentBook = new StudentBook(studentBook);
        this.lessonBook = new LessonBook(lessonBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.studentBook.getStudentList());
        filteredLessons = new FilteredList<>(this.lessonBook.getLessonList());
    }

    // TODO: delete this constructor after updating testcases (creating TypicalLessons class etc)
    public ModelManager(ReadOnlyStudentBook studentBook, ReadOnlyUserPrefs userPrefs) {
        this(studentBook, new LessonBook(), userPrefs);
    }

    public ModelManager() {
        this(new StudentBook(), new LessonBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getStudentBookFilePath() {
        return userPrefs.getStudentBookFilePath();
    }

    @Override
    public void setStudentBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setStudentBookFilePath(addressBookFilePath);
    }

    //=========== StudentBook ================================================================================

    @Override
    public void setStudentBook(ReadOnlyStudentBook addressBook) {
        this.studentBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyStudentBook getStudentBook() {
        return studentBook;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return studentBook.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        studentBook.removeStudent(target);
        lessonBook.unasssignStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        studentBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        studentBook.setStudent(target, editedStudent);
    }

    //=========== LessonBook =================================================================================

    // TODO: add the same functions for LessonBook
    // TODO: also, add the abstract function declarations for these functions in the Model interface

    @Override
    public LessonBook getLessonBook() {
        return lessonBook;
    }

    @Override
    public boolean hasConflictingLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessonBook.hasConflictingLesson(lesson);
    }

    @Override
    public void addLesson(Lesson lesson) {
        lessonBook.addLesson(lesson);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public void deleteLesson(Lesson lesson) {
        lessonBook.deleteLesson(lesson);
        studentBook.unassignLesson(lesson);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        filteredStudents.stream().forEach(student -> {
            for (Lesson lesson: filteredLessons) {
                if (lesson.hasAlreadyAssigned(student)) {
                    student.assignLesson(lesson);
                }
            }
        });
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return studentBook.equals(other.studentBook)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

    //=========== Filtered Lesson List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Lesson} backed by the internal list of
     * {@code versionedLessonBook}
     */
    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
        return filteredLessons;
    }

    @Override
    public void updateFilteredLessonList(Predicate<Lesson> predicate) {
        requireNonNull(predicate);
        filteredLessons.setPredicate(predicate);
    }

    @Override
    public void updateAssignment(Student studentToAssign, Lesson lessonToAssign) {
        lessonBook.assignStudent(studentToAssign, lessonToAssign);
        studentBook.assignLesson(lessonToAssign, studentToAssign);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void updateUnassignment(Student student, Lesson lesson) {
        student.unassignLesson(lesson);
        lesson.unassignStudent(student);
        updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    public boolean checkStudentListIndex(Index studentId) {
        return filteredStudents.size() < studentId.getOneBased();
    }

    public boolean checkLessonListIndex(Index lessonId) {
        return filteredLessons.size() < lessonId.getOneBased();
    }

    //=========== Selected Student and Lesson Accessors and Setter ============================================

    public void setSelectedStudent(Student student) {
        selectedStudent = student;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedLesson(Lesson lesson) {
        selectedLesson = lesson;
    }

    public Lesson getSelectedLesson() {
        return selectedLesson;
    }

}

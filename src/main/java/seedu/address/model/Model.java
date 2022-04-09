package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.lab.Lab;
import seedu.address.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

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
     * Returns the user prefs' TAddressBook file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' TAddressBook file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces TAddressBook data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the TAddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the TAddressBook.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the TAddressBook.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * TAddressBook.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Returns an unmodifiable view of the filtered student list
     */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate} and
     * the previous {@code predicate}
     *
     * @param predicate The new predicate to add on to the current predicate
     */
    void addOnFilteredStudentList(Predicate<Student> predicate);

    /**
     * Returns whether the model's UniqueStudentList is empty.
     *
     * @return true is the model's UniqueStudentList is empty and false otherwise.
     */
    boolean isStudentListEmpty();

    /**
     * Returns true if a lab with the same identity as {@code lab} exists in the TAddressBook.
     */
    boolean hasLab(Lab lab);

    /**
     * Adds the given lab.
     * {@code lab} must not already exist in the TAddressbook.
     */
    void addLab(Lab lab);

    /**
     * Removes the given lab.
     * {@code lab} must already exist in the TAddressbook.
     */
    void removeLab(Lab lab);

    /**
     * Returns the list of labs in the Model as an ArrayList.
     */
    ArrayList<Lab> getLabsAsArrayList();
}

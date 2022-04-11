package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.inputhistory.InputHistoryResult;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyStudentBook;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the StudentBook.
     *
     * @see seedu.address.model.Model#getStudentBook()
     */
    ReadOnlyStudentBook getStudentBook();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered list of lessons */
    ObservableList<Lesson> getFilteredLessonList();

    /**
     * Returns the user prefs' student book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the selected student from {@code Model}.
     */
    Student getSelectedStudent();

    /**
     * Returns the selected lesson from {@code Model}.
     */
    Lesson getSelectedLesson();

    /**
     * Add a new user input to the history.
     *
     * @param userInput The new user input.
     */
    void addNewUserInputToHistory(String userInput);

    /**
     * Retrieve the previous user's input from {@code UserInputHistory}.
     *
     * @return The previous user's input as a {@code InputHistoryResult} object.
     */
    InputHistoryResult getPreviousInput();

    /**
     * Retrieve the following user's input from {@code UserInputHistory}.
     *
     * @return The following user's input as a {@code InputHistoryResult} object.
     */
    InputHistoryResult getNextInput();
}

package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTAssist;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.person.Person;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

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
     * Returns the TAssist.
     *
     * @see Model#getTAssist()
     */
    ReadOnlyTAssist getTAssist();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered list of modules */
    ObservableList<TaModule> getFilteredModuleList();

    /** Returns an unmodifiable view of the filtered list of class groups */
    ObservableList<ClassGroup> getFilteredClassGroupList();

    /** to be removed */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getTAssistFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

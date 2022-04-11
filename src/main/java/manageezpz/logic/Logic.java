package manageezpz.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import manageezpz.commons.core.GuiSettings;
import manageezpz.logic.commands.CommandResult;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.ReadOnlyAddressBook;
import manageezpz.model.person.Person;
import manageezpz.model.task.Task;

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
     * Returns the AddressBook.
     *
     * @see manageezpz.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of tasks */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

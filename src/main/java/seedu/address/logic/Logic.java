package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.LibTaskParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyLibTask;
import seedu.address.model.book.Book;
import seedu.address.model.patron.Patron;

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
     * Returns the LibTask.
     *
     * @see seedu.address.model.Model#getLibTask()
     */
    ReadOnlyLibTask getLibTask();

    /**
     * Returns the LibTaskParser.
     *
     * @see LibTaskParser
     */
    LibTaskParser getLibTaskParser();

    /** Returns an unmodifiable view of the filtered list of patrons */
    ObservableList<Patron> getFilteredPatronList();

    /** Returns an unmodifiable view of the filtered list of books */
    ObservableList<Book> getFilteredBookList();

    /**
     * Returns the user prefs' LibTask file path.
     */
    Path getLibTaskFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Stores previous command.
     */
    void storePreviousCommand(String previousCommand);
}

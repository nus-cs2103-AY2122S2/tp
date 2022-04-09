package seedu.unite.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.unite.commons.core.GuiSettings;
import seedu.unite.logic.commands.CommandResult;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.Model;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.person.Person;

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
     * Returns the Unite.
     *
     * @see seedu.unite.model.Model#getUnite()
     */
    ReadOnlyUnite getUnite();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' unite file path.
     */
    Path getUniteFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the Model
     */
    Model getModel();
}

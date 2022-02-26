package seedu.trackbeau.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.trackbeau.commons.core.GuiSettings;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.customer.Customer;

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
     * Returns the TrackBeau.
     *
     * @see seedu.trackbeau.model.Model#getTrackBeau()
     */
    ReadOnlyTrackBeau getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Customer> getFilteredPersonList();

    /**
     * Returns the user prefs' trackbeau book file path.
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

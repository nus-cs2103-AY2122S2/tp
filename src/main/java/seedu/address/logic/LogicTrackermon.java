package seedu.address.logic;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResultTrackermon;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyShowList;
import seedu.address.model.show.Show;

import java.nio.file.Path;

/**
 * API of the Logic component
 */
public interface LogicTrackermon {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResultTrackermon execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ShowList.
     *
     * @see seedu.address.model.ModelTrackermon#getShowList()
     */
    ReadOnlyShowList getShowList();

    /** Returns an unmodifiable view of the filtered list of shows */
    ObservableList<Show> getFilteredShowList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getShowListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyInternApplyMemory;
import seedu.address.model.application.Application;
import seedu.address.model.summarybar.SummaryBox;

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
     * Returns InternApply Memory.
     *
     * @see seedu.address.model.Model#getInternApplyMemory()
     */
    ReadOnlyInternApplyMemory getInternApplyMemory();

    /** Returns an unmodifiable view of the filtered list of applications */
    ObservableList<Application> getFilteredApplicationsList();

    /** Returns an unmodifiable view of the upcoming list of applications */
    ObservableList<Application> getUpcomingApplicationsList();

    /** Returns a modifiable view of the list of summary boxes */
    ObservableList<SummaryBox> getSummaryBoxList();

    /**
     * Returns the user prefs' intern apply file path.
     */
    Path getInternApplyFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

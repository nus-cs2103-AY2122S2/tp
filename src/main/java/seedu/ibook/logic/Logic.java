package seedu.ibook.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.ibook.commons.core.GuiSettings;
import seedu.ibook.logic.commands.CommandResult;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.ReadOnlyIBook;
import seedu.ibook.model.product.Product;

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
     * Returns the list of products.
     *
     */
    ReadOnlyIBook getIBook();

    /** Returns an unmodifiable view of the filtered list of products */
    ObservableList<Product> getFilteredIBook();

    /**
     * Returns the user prefs' ibook file path.
     */
    Path getIBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

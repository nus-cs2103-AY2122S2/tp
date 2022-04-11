package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.commandhistory.CommandHistoryEntry;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    CommandResult executeInDetailedViewMode(String commandText) throws CommandException, ParseException;

    /**
     * Caches raw user input.
     */
    void cacheCommandText(String commandText);

    /**
     * Retrieves the command text
     * @param i the number of commands to backstep to
     */
    CommandHistoryEntry getCommandText(int i);

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<Person> getFilteredPersonList();

    ObservableList<Tag> getActivatedTagList();

    SortedList<Person> getSortedPersonList();

    /** Returns the detailed view of a contact */
    ObservableList<Person> getDetailedContactView();

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

    /**
     * Gets images to be displayed.
     */
    ImageDetailsList getImagesToView();

}

package seedu.contax.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.contax.commons.core.GuiSettings;
import seedu.contax.logic.commands.CommandResult;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.model.person.Person;
import seedu.contax.model.tag.Tag;

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

    /**
     * Returns the AddressBook.
     *
     * @see seedu.contax.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons. */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the list of appointments and highlighted empty slots. */
    ObservableList<ScheduleItem> getScheduleItemList();

    /** Returns an unmodifiable view of the filtered list of tags. */
    ObservableList<Tag> getFilteredTagList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' schedule file path.
     */
    Path getScheduleFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

}

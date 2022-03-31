package woofareyou.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import woofareyou.commons.core.GuiSettings;
import woofareyou.logic.commands.CommandResult;
import woofareyou.logic.commands.exceptions.CommandException;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.ReadOnlyAddressBook;
import woofareyou.model.pet.Pet;
import woofareyou.model.Model;

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
     * @see Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of pets */
    ObservableList<Pet> getFilteredPetList();

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

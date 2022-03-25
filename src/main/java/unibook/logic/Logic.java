package unibook.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import unibook.commons.core.GuiSettings;
import unibook.logic.commands.CommandResult;
import unibook.logic.commands.exceptions.CommandException;
import unibook.logic.parser.exceptions.ParseException;
import unibook.model.Model;
import unibook.model.ReadOnlyUniBook;
import unibook.model.module.Module;
import unibook.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText         The command as entered by the user.
     * @param isPersonListShowing boolean value of whether person list is showing.
     * @param isModuleListShowing boolean value of whether module list is showing.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText,
                          Boolean isPersonListShowing,
                          Boolean isModuleListShowing,
                          Boolean isGroupListShowing) throws CommandException, ParseException;

    /**
     * Returns the UniBook.
     *
     * @see Model#getUniBook()
     */
    ReadOnlyUniBook getUniBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' UniBook file path.
     */
    Path getUniBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns nan unmodifiable view of the filtered list of modules.
     */
    ObservableList<Module> getFilteredModuleList();
}

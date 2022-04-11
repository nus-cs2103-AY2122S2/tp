package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyExpenseExpert;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;
import seedu.address.model.person.Person;

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
     * Returns the ExpenseExpert.
     *
     * @see seedu.address.model.Model#getExpenseExpert()
     */
    ReadOnlyExpenseExpert getExpenseExpert();

    /** Returns an unmodifiable view of the filtered list of expenses */
    ObservableList<Expense> getFilteredExpenseList();

    ObservableList<Person> getFilteredPersonList();

    /** Returns the budget */
    Budget getBudget();

    /** Returns whether budget is defined */
    boolean hasUndefinedBudget();

    /**
     * Returns the user prefs' expense expert file path.
     */
    Path getExpenseExpertFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

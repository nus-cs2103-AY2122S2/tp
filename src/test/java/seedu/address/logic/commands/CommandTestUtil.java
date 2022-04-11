package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_NAME;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExpenseExpert;
import seedu.address.model.Model;
import seedu.address.model.expense.DescriptionContainsKeywordsPredicate;
import seedu.address.model.expense.Expense;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameContainsKeywordsPredicate;
import seedu.address.testutil.EditExpenseDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    /*
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    */
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    public static final String VALID_DESCRIPTION_ANNUAL_SPOTIFY = "Annual Spotify Fees";
    public static final String VALID_DESCRIPTION_BUILD_A_BEAR = "Build a Bear Workshop";
    public static final String VALID_EXPENSE_CATEGORY_ENTERTAINMENT = "Entertainment";
    public static final String VALID_EXPENSE_CATEGORY_UTILITY = "Utility";
    public static final String VALID_EXPENSE_CATEGORY_TRANSPORT = "Transport";
    public static final String VALID_AMOUNT_ANNUAL_SPOTIFY = "200";
    public static final String VALID_AMOUNT_BUILD_A_BEAR = "80";
    public static final String VALID_EXPENSE_DATE_ANNUAL_SPOTIFY = "2022-02-02";
    public static final String VALID_EXPENSE_DATE_BUILD_A_BEAR = "2022-03-02";

    public static final String VALID_BUDGET_AMOUNT_ALPHA = "900";
    public static final String VALID_BUDGET_AMOUNT_BETA = "600";
    public static final String VALID_BUDGET_DATE_ALPHA = "2022-01-01";
    public static final String VALID_BUDGET_DATE_BETA = "2022-03-10";
    public static final String VALID_OUTDATED_DATE = "1900-12-12";
    public static final String VALID_FILTER_DATE = "2022-03-03";
    public static final String VALID_UPDATED_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    public static final String VALID_PERSON_1_NAME = "Bob";
    public static final String VALID_PERSON_2_NAME = "Alex";
    public static final String VALID_PERSON_1_AMOUNT = "40";
    public static final String VALID_PERSON_2_AMOUNT = "200";



    public static final String DESCRIPTION_DESC_ANNUAL_SPOTIFY =
            " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_ANNUAL_SPOTIFY;
    public static final String DESCRIPTION_DESC_BUILD_A_BEAR =
            " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BUILD_A_BEAR;
    public static final String EXPENSE_CATEGORY_DESC_ENTERTAINMENT =
            " " + PREFIX_EXPENSE_CATEGORY + VALID_EXPENSE_CATEGORY_ENTERTAINMENT;
    public static final String EXPENSE_CATEGORY_DESC_TRANSPORT =
            " " + PREFIX_EXPENSE_CATEGORY + VALID_EXPENSE_CATEGORY_TRANSPORT;
    public static final String AMOUNT_DESC_ANNUAL_SPOTIFY = " " + PREFIX_AMOUNT + VALID_AMOUNT_ANNUAL_SPOTIFY;
    public static final String AMOUNT_DESC_BUILD_A_BEAR = " " + PREFIX_AMOUNT + VALID_AMOUNT_BUILD_A_BEAR;
    public static final String EXPENSE_DATE_DESC_ANNUAL_SPOTIFY = " " + PREFIX_DATE
            + VALID_EXPENSE_DATE_ANNUAL_SPOTIFY;
    public static final String EXPENSE_DATE_DESC_BUILD_A_BEAR = " " + PREFIX_DATE
            + VALID_EXPENSE_DATE_BUILD_A_BEAR;


    public static final String BUDGET_AMOUNT_DESC_ALPHA =
            " " + PREFIX_AMOUNT + VALID_BUDGET_AMOUNT_ALPHA;
    public static final String BUDGET_AMOUNT_DESC_BETA =
            " " + PREFIX_AMOUNT + VALID_BUDGET_AMOUNT_BETA;
    public static final String BUDGET_DATE_DESC_ALPHA =
            " " + PREFIX_DATE + VALID_BUDGET_DATE_ALPHA;
    public static final String BUDGET_DATE_DESC_BETA =
            " " + PREFIX_DATE + VALID_BUDGET_DATE_BETA;
    public static final String FILTER_DATE_DESC = " " + PREFIX_FILTER_DATE + VALID_FILTER_DATE;
    public static final String ADD_EXPENSE_CATEGORY_DESC = " "
            + PREFIX_EXPENSE_CATEGORY + VALID_EXPENSE_CATEGORY_ENTERTAINMENT;

    public static final String PERSON_NAME_1 =
            " " + PREFIX_PERSON_NAME + VALID_PERSON_1_NAME;
    public static final String PERSON_NAME_2 =
            " " + PREFIX_PERSON_NAME + VALID_PERSON_2_NAME;
    public static final String PERSON_AMOUNT_1 =
            " " + PREFIX_AMOUNT + VALID_PERSON_1_AMOUNT;
    public static final String PERSON_AMOUNT_2 =
            " " + PREFIX_AMOUNT + VALID_PERSON_2_AMOUNT;


    public static final seedu.address.logic.commands.EditCommand.EditExpenseDescriptor DESC_ANNUAL_SPOTIFY_FEES;
    public static final seedu.address.logic.commands.EditCommand.EditExpenseDescriptor DESC_BUILD_A_BEAR;
    public static final seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor ALEX;
    public static final seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor BOB;

    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "2!";
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + "B@sketb@ll";
    public static final String INVALID_EXPENSE_CATEGORY_DESC = " " + PREFIX_EXPENSE_CATEGORY + "Tr@nsport";
    public static final String INVALID_EXPENSE_DATE_DESC = " " + PREFIX_DATE + "2022/03/03";
    public static final String INVALID_FILTER_DATE_DESC = " " + PREFIX_FILTER_DATE + "2022";
    public static final String INVALID_ADD_EXPENSE_CATEGORY_DESC = " " + PREFIX_EXPENSE_CATEGORY + "Entert@inment";
    public static final String INVALID_PERSON_NAME = " " + PREFIX_PERSON_NAME + "123";
    public static final String INVALID_PERSON_AMOUNT = " " + PREFIX_AMOUNT + "abc";

    static {
        DESC_ANNUAL_SPOTIFY_FEES = new EditExpenseDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_ANNUAL_SPOTIFY)
                .withExpenseCategory(VALID_EXPENSE_CATEGORY_ENTERTAINMENT)
                .withAmount(VALID_AMOUNT_ANNUAL_SPOTIFY)
                .withExpenseDate(VALID_EXPENSE_DATE_ANNUAL_SPOTIFY).build();

        DESC_BUILD_A_BEAR = new EditExpenseDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_BUILD_A_BEAR)
                .withExpenseCategory(VALID_EXPENSE_CATEGORY_ENTERTAINMENT)
                .withAmount(VALID_AMOUNT_BUILD_A_BEAR)
                .withExpenseDate(VALID_EXPENSE_DATE_BUILD_A_BEAR).build();

        ALEX = new EditPersonDescriptorBuilder().withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        BOB = new EditPersonDescriptorBuilder().withPersonAmount(VALID_PERSON_2_AMOUNT).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - Expense Expert, filtered expense list and selected expense in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ExpenseExpert expectedExpenseExpert = new ExpenseExpert(actualModel.getExpenseExpert());
        List<Expense> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExpenseList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedExpenseExpert, actualModel.getExpenseExpert());
        assertEquals(expectedFilteredList, actualModel.getFilteredExpenseList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the expense at the given {@code targetIndex} in the
     * {@code model}'s ExpenseExpert.
     */
    public static void showExpenseAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredExpenseList().size());

        Expense expense = model.getFilteredExpenseList().get(targetIndex.getZeroBased());
        final String[] splitName = expense.getDescription().description.split("\\s+");
        model.updateFilteredExpenseList(new DescriptionContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredExpenseList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s ExpenseExpert.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getPersonName().personName.split("\\s+");
        model.updateFilteredPersonList(new PersonNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}

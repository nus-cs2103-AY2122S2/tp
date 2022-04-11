package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_DATE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.expense.ExpenseCategoryIsParsedCategoryPredicate;
import seedu.address.model.expense.ExpenseDateIsParsedDatePredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String MONTH_FORMAT = "yyyy-MM";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILTER_DATE, PREFIX_EXPENSE_CATEGORY);

        boolean checkFilterDatePresent = arePrefixesPresent(argMultimap, PREFIX_FILTER_DATE);
        boolean checkExpenseCategoryPresent = arePrefixesPresent(argMultimap, PREFIX_EXPENSE_CATEGORY);

        if (!(checkFilterDatePresent || checkExpenseCategoryPresent) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        ExpenseDateIsParsedDatePredicate datePredicate = null;
        ExpenseCategoryIsParsedCategoryPredicate categoryPredicate = null;

        if (checkFilterDatePresent) {
            String trimmedDate = argMultimap.getValue(PREFIX_FILTER_DATE).get().trim();

            if (trimmedDate.length() != 10 && trimmedDate.length() != 7) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }

            datePredicate = processDate(trimmedDate);

        }

        if (checkExpenseCategoryPresent) {
            String trimmedExpenseCategory = argMultimap.getValue(PREFIX_EXPENSE_CATEGORY).get().trim();

            if (!ExpenseCategory.isValidExpenseCategory(trimmedExpenseCategory)) {
                throw new ParseException(ExpenseCategory.MESSAGE_CONSTRAINTS);
            }

            if (trimmedExpenseCategory.length() == 0) {
                throw new ParseException(ExpenseCategory.MESSAGE_CONSTRAINTS);
            }

            categoryPredicate = new ExpenseCategoryIsParsedCategoryPredicate(trimmedExpenseCategory);
        }

        if (datePredicate == null && categoryPredicate == null) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        return new FilterCommand(datePredicate, categoryPredicate);

    }


    /**
     * Return a Date Predicate based on the String passed in.
     *
     * @param trimmedDateString valid date string input
     * @return Date Predicate of parsed string.
     * @throws ParseException if date is invalid.
     */
    public static ExpenseDateIsParsedDatePredicate processDate(String trimmedDateString) throws ParseException {
        if (trimmedDateString.length() == 7) {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(MONTH_FORMAT);
                YearMonth ym = YearMonth.parse(trimmedDateString, dtf);
                return new ExpenseDateIsParsedDatePredicate(trimmedDateString);
            } catch (DateTimeParseException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
        }

        if (trimmedDateString.length() == 10) {
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(trimmedDateString);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
                LocalDate ld = LocalDate.parse(trimmedDateString, dtf);
                return new ExpenseDateIsParsedDatePredicate(trimmedDateString);
            } catch (java.text.ParseException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

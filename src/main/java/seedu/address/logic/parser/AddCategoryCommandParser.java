package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPENSE_CATEGORY;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expense.ExpenseCategory;

public class AddCategoryCommandParser implements Parser<AddCategoryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCategoryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXPENSE_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_EXPENSE_CATEGORY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCategoryCommand.MESSAGE_USAGE));
        }

        ExpenseCategory expenseCategory = ParserUtil.parseExpenseCategory(
                argMultimap.getValue(PREFIX_EXPENSE_CATEGORY).get());

        return new AddCategoryCommand(expenseCategory);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}


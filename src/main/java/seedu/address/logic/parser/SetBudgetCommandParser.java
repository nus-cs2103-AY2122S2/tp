package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;

import java.util.stream.Stream;

import seedu.address.logic.commands.SetBudgetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;

/**
 * Parses input arguments and creates a new SetBudgetCommand object
 */
public class SetBudgetCommandParser implements Parser<SetBudgetCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SetBudgetCommand
     * and returns an SetBudgetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetBudgetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT);

        if (!arePrefixesPresent(argMultimap, PREFIX_AMOUNT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBudgetCommand.MESSAGE_USAGE));
        }

        Amount budgetAmount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        if (Double.valueOf(budgetAmount.toString()) >= 100000.00) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBudgetCommand.MESSAGE_USAGE));
        }

        Budget budget = new Budget(budgetAmount);

        return new SetBudgetCommand(budget);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

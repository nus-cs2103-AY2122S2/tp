package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import java.util.stream.Stream;

import seedu.address.logic.commands.sort.SortCommand;
import seedu.address.logic.commands.sort.SortKey;
import seedu.address.logic.commands.sort.SortOrder;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT_KEY, PREFIX_SORT_ORDER);

        if (!arePrefixesPresent(argMultimap, PREFIX_SORT_KEY, PREFIX_SORT_ORDER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        SortKey sortKey = ParserUtil.parseSortKey(argMultimap.getValue(PREFIX_SORT_KEY).get());
        SortOrder sortOrder = ParserUtil.parseSortOrder(argMultimap.getValue(PREFIX_SORT_ORDER).get());

        return new SortCommand(sortKey, sortOrder);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

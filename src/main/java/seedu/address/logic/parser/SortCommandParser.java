package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JERSEY_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.stream.Stream;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    private static final String ASCENDING = "asc";
    private static final String DESCENDING = "desc";

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_HEIGHT, PREFIX_WEIGHT,
                PREFIX_JERSEY_NUMBER);

        // check has preamble or any extreme behaviour
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String sortingOrder; // asc or desc
        if (arePrefixesPresent(argMultimap, PREFIX_HEIGHT)) {
            sortingOrder = parseValidSortingOrder(argMultimap.getValue(PREFIX_HEIGHT).get());
            return new SortCommand(PREFIX_PLAYER, PREFIX_HEIGHT, sortingOrder);
        }
        if (arePrefixesPresent(argMultimap, PREFIX_WEIGHT)) {
            sortingOrder = parseValidSortingOrder(argMultimap.getValue(PREFIX_WEIGHT).get());
            return new SortCommand(PREFIX_PLAYER, PREFIX_WEIGHT, sortingOrder);
        }
        if (arePrefixesPresent(argMultimap, PREFIX_JERSEY_NUMBER)) {
            sortingOrder = parseValidSortingOrder(argMultimap.getValue(PREFIX_JERSEY_NUMBER).get());
            return new SortCommand(PREFIX_PLAYER, PREFIX_JERSEY_NUMBER, sortingOrder);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    private static String parseValidSortingOrder(String sortingOrder) throws ParseException {
        if (sortingOrder.equals(ASCENDING) || sortingOrder.equals(DESCENDING)) {
            return sortingOrder;
        }
        throw new ParseException("You have provided an invalid sorting order!");
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

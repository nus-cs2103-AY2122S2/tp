package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_RANGE_INDEX;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_FROM;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_TO;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.RangeCommand;
import seedu.contax.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new RangeCommand object.
 */
public class RangeCommandParser implements Parser<RangeCommand> {
    public static final String INDEX_FROM_LARGER_THAN_TO =
            "The from and to index provided is invalid as FROM_INDEX > TO_INDEX";
    /**
     * Parses the given {@code String} of arguments in the context of the RangeCommandParser
     * and returns an RangeEditCommandParser object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RangeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RANGE_FROM, PREFIX_RANGE_TO);

        String commandInput = argMultimap.getPreamble();

        if (argMultimap.getValue(PREFIX_RANGE_FROM).isEmpty() || argMultimap.getValue(PREFIX_RANGE_TO).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RangeCommand.MESSAGE_USAGE));
        }

        Index fromIndex;
        Index toIndex;

        try {
            fromIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_RANGE_FROM).get());
            toIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_RANGE_TO).get());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_RANGE_INDEX, pe);
        }
        if (fromIndex.getZeroBased() > toIndex.getZeroBased()) {
            throw new ParseException(INDEX_FROM_LARGER_THAN_TO);
        }

        return new RangeCommand(fromIndex, toIndex, commandInput);
    }
}

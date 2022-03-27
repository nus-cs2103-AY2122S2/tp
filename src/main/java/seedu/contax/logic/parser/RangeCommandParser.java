package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_RANGE_INDEX;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_FROM;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_TO;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.RangeCommand;
import seedu.contax.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new RangeCommandParser object
 */
public class RangeCommandParser implements Parser<RangeCommand> {
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

        Index fromIndex;
        Index toIndex;

        if (argMultimap.getValue(PREFIX_RANGE_FROM).isPresent() && argMultimap.getValue(PREFIX_RANGE_TO).isPresent()) {
            try {
                fromIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_RANGE_FROM).get());
                toIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_RANGE_TO).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_RANGE_INDEX,
                        RangeCommand.MESSAGE_USAGE), pe);
            }
            if (fromIndex.getZeroBased() > toIndex.getZeroBased()) {
                throw new ParseException(String.format(MESSAGE_INVALID_RANGE_INDEX,
                        RangeCommand.MESSAGE_USAGE));
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_RANGE_INDEX,
                    RangeCommand.MESSAGE_USAGE));
        }
        return new RangeCommand(fromIndex, toIndex, commandInput);
    }
}

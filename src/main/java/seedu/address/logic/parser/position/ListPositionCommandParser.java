package seedu.address.logic.parser.position;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ARGUMENT;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.SortArgument;
import seedu.address.logic.commands.position.ListPositionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.GenericListParser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListPositionCommandParser extends GenericListParser<ListPositionCommand> {

    @Override
    public ListPositionCommand returnFullList() {
        return new ListPositionCommand();
    }

    /**
     * Parses the given {@code String} of arguments in the context of performing sort feature
     * and returns an ListPositionCommand object for execution.
     * @param args The input arguments string
     * @return ListPositionCommand object with respective sort argument for execution
     * @throws ParseException if the user input does not conform the expected sort format
     */
    public ListPositionCommand parseSort(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT_ARGUMENT);

        if (argMultimap.getValue(PREFIX_SORT_ARGUMENT).isPresent()) {
            SortArgument sortArgument =
                    ParserUtil.parseSortArgument(argMultimap.getValue(PREFIX_SORT_ARGUMENT).get());

            return new ListPositionCommand(sortArgument);
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ListPositionCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of performing sort feature
     * and returns an ListPositionCommand object for execution.
     * @param args The input arguments string
     * @return ListPositionCommand object with respective sort argument for execution
     * @throws ParseException if the user input does not conform the expected sort format
     */
    public ListPositionCommand parseFilter(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILTER_TYPE, PREFIX_FILTER_ARGUMENT);

        if (argMultimap.getValue(PREFIX_FILTER_TYPE).isPresent()
                && argMultimap.getValue(PREFIX_FILTER_ARGUMENT).isPresent()) {

            FilterType filterType =
                    ParserUtil.parseFilterType(DataType.POSITION, argMultimap.getValue(PREFIX_FILTER_TYPE).get());
            FilterArgument filterArgument =
                    ParserUtil.parseFilterArgument(argMultimap.getValue(PREFIX_FILTER_ARGUMENT).get());

            return new ListPositionCommand(filterType, filterArgument);
        } else {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    ListPositionCommand.MESSAGE_USAGE));
        }
    }
}

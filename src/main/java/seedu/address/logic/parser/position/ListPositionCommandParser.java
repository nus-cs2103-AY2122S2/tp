package seedu.address.logic.parser.position;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ARGUMENT;

import seedu.address.commons.core.DataType;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.SortArgument;
import seedu.address.logic.commands.position.ListPositionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.GenericListParser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListPositionCommandParser extends GenericListParser<ListPositionCommand> {

    @Override
    public ListPositionCommand returnFullList() {
        return new ListPositionCommand();
    }

    @Override
    public ListPositionCommand parseFilterAndSort(ArgumentMultimap args) throws ParseException {
        FilterType filterType =
                ParserUtil.parseFilterType(DataType.POSITION, args.getValue(PREFIX_FILTER_TYPE).get());
        FilterArgument filterArgument =
                ParserUtil.parseFilterArgument(args.getValue(PREFIX_FILTER_ARGUMENT).get());
        SortArgument sortArgument =
                ParserUtil.parseSortArgument(args.getValue(PREFIX_SORT_ARGUMENT).get());

        return new ListPositionCommand(filterType, filterArgument, sortArgument);
    }

    /**
     * Parses the given {@code String} of arguments in the context of performing sort feature
     * and returns an ListPositionCommand object for execution.
     * @param args The input arguments string
     * @return ListPositionCommand object with respective sort argument for execution
     * @throws ParseException if the user input does not conform the expected sort format
     */
    public ListPositionCommand parseSort(ArgumentMultimap args) throws ParseException {
        SortArgument sortArgument =
                ParserUtil.parseSortArgument(args.getValue(PREFIX_SORT_ARGUMENT).get());

        return new ListPositionCommand(sortArgument);
    }

    /**
     * Parses the given {@code String} of arguments in the context of performing sort feature
     * and returns an ListPositionCommand object for execution.
     * @param args The input arguments string
     * @return ListPositionCommand object with respective sort argument for execution
     * @throws ParseException if the user input does not conform the expected sort format
     */
    public ListPositionCommand parseFilter(ArgumentMultimap args) throws ParseException {
        FilterType filterType =
                ParserUtil.parseFilterType(DataType.POSITION, args.getValue(PREFIX_FILTER_TYPE).get());
        FilterArgument filterArgument =
                ParserUtil.parseFilterArgument(args.getValue(PREFIX_FILTER_ARGUMENT).get());

        return new ListPositionCommand(filterType, filterArgument);
    }
}

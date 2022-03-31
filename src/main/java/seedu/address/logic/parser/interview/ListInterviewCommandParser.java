package seedu.address.logic.parser.interview;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ARGUMENT;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.SortArgument;
import seedu.address.logic.commands.interview.ListInterviewCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.GenericListParser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListInterviewCommandParser extends GenericListParser<ListInterviewCommand> {

    public static final String STATUS_REGEX = "pending|passed|failed|accepted|rejected";

    public static final String MESSAGE_INVALID_STATUS =
            "Interview status should only be pending/passed/failed/accepted/rejected (case-sensitive)";

    @Override
    public ListInterviewCommand returnFullList() {
        return new ListInterviewCommand();
    }

    @Override
    public ListInterviewCommand parseFilterAndSort(ArgumentMultimap args) throws ParseException {
        FilterType filterType =
                ParserUtil.parseFilterType(DataType.INTERVIEW, args.getValue(PREFIX_FILTER_TYPE).get());
        FilterArgument filterArgument =
                ParserUtil.parseFilterArgument(args.getValue(PREFIX_FILTER_ARGUMENT).get());
        SortArgument sortArgument =
                ParserUtil.parseSortArgument(args.getValue(PREFIX_SORT_ARGUMENT).get());

        checkFilterTypeArgument(filterType, filterArgument);

        return new ListInterviewCommand(filterType, filterArgument, sortArgument);
    }

    /**
     * Parses the given {@code String} of arguments in the context of performing sort feature
     * and returns an ListInterviewCommand object for execution.
     * @param args The input arguments string
     * @return ListInterviewCommand object with respective sort argument for execution
     * @throws ParseException if the user input does not conform the expected sort format
     */
    @Override
    public ListInterviewCommand parseSort(ArgumentMultimap args) throws ParseException {
        SortArgument sortArgument =
                ParserUtil.parseSortArgument(args.getValue(PREFIX_SORT_ARGUMENT).get());

        return new ListInterviewCommand(sortArgument);
    }

    /**
     * Parses the given {@code String} of arguments in the context of performing filter feature
     * and returns an ListInterviewCommand object for execution.
     * @param args The input arguments string
     * @return ListInterviewCommand object with respective filter type and filter argument for execution
     * @throws ParseException if the user input does not conform the expected filter format
     */
    @Override
    public ListInterviewCommand parseFilter(ArgumentMultimap args) throws ParseException {
        FilterType filterType =
                ParserUtil.parseFilterType(DataType.INTERVIEW, args.getValue(PREFIX_FILTER_TYPE).get());
        FilterArgument filterArgument =
                ParserUtil.parseFilterArgument(args.getValue(PREFIX_FILTER_ARGUMENT).get());

        checkFilterTypeArgument(filterType, filterArgument);

        return new ListInterviewCommand(filterType, filterArgument);
    }

    /**
     * Checks if the given {@code FilterArgument} if valid for the given {@code FilterType}.
     */
    public void checkFilterTypeArgument(FilterType filterType, FilterArgument filterArgument) throws ParseException {
        if (filterType.type.equals("date")) {
            try {
                LocalDate.parse(filterArgument.toString());
            } catch (DateTimeParseException e) {
                throw new ParseException(Messages.MESSAGE_INVALID_DATE);
            }
        } else if (filterType.type.equals("status") && !filterArgument.toString().matches(STATUS_REGEX)) {
            throw new ParseException(MESSAGE_INVALID_STATUS);
        }
    }
}

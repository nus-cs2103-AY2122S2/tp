package seedu.address.logic.parser.applicants;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ARGUMENT;

import seedu.address.commons.core.DataType;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.SortArgument;
import seedu.address.logic.commands.applicant.ListApplicantCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.GenericListParser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListApplicantCommandParser extends GenericListParser<ListApplicantCommand> {

    @Override
    public ListApplicantCommand returnFullList() {
        return new ListApplicantCommand();
    }

    @Override
    public ListApplicantCommand parseFilterAndSort(ArgumentMultimap args) throws ParseException {
        FilterType filterType =
                ParserUtil.parseFilterType(DataType.APPLICANT, args.getValue(PREFIX_FILTER_TYPE).get());
        FilterArgument filterArgument =
                ParserUtil.parseFilterArgument(args.getValue(PREFIX_FILTER_ARGUMENT).get());
        SortArgument sortArgument =
                ParserUtil.parseSortArgument(args.getValue(PREFIX_SORT_ARGUMENT).get());
        return new ListApplicantCommand(filterType, filterArgument, sortArgument);
    }

    /**
     * Parses the given {@code String} of arguments in the context of performing sort feature
     * and returns an ListApplicantCommand object for execution.
     * @param args The input arguments string
     * @return ListApplicantCommand object with respective filter type and filter argument for execution
     */
    @Override
    public ListApplicantCommand parseSort(ArgumentMultimap args) throws ParseException {
        SortArgument sortArgument =
                ParserUtil.parseSortArgument(args.getValue(PREFIX_SORT_ARGUMENT).get());

        return new ListApplicantCommand(sortArgument);
    }

    /**
     * Parses the given {@code String} of arguments in the context of performing filter feature
     * and returns an ListApplicantCommand object for execution.
     * @param args The input arguments string
     * @return ListApplicantCommand object with respective filter type and filter argument for execution
     */
    @Override
    public ListApplicantCommand parseFilter(ArgumentMultimap args) throws ParseException {
        FilterType filterType =
                ParserUtil.parseFilterType(DataType.APPLICANT, args.getValue(PREFIX_FILTER_TYPE).get());
        FilterArgument filterArgument =
                ParserUtil.parseFilterArgument(args.getValue(PREFIX_FILTER_ARGUMENT).get());

        return new ListApplicantCommand(filterType, filterArgument);
    }
}

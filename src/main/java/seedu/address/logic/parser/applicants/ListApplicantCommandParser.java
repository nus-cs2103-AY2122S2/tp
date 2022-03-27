package seedu.address.logic.parser.applicants;

import seedu.address.commons.core.DataType;
import seedu.address.logic.FilterArgument;
import seedu.address.logic.FilterType;
import seedu.address.logic.commands.applicant.ListApplicantCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_ARGUMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class ListApplicantCommandParser implements Parser<ListApplicantCommand> {

    public static final String MESSAGE_FILTER_NO_ARGUMENT = "An argument is required for filter";

    /**
     * Parses the given {@code String} of arguments in the context of the ListApplicantCommand
     * and returns an ListApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListApplicantCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILTER_TYPE, PREFIX_FILTER_ARGUMENT);

        /*
        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddApplicantCommand.MESSAGE_USAGE));
        }
        */

        if (argMultimap.getValue(PREFIX_FILTER_TYPE).isPresent() &&
                argMultimap.getValue(PREFIX_FILTER_ARGUMENT).isPresent()) {

            FilterType filterType =
                    ParserUtil.parseFilterType(DataType.APPLICANT, argMultimap.getValue(PREFIX_FILTER_TYPE).get());
            FilterArgument filterArgument =
                    ParserUtil.parseFilterArgument(argMultimap.getValue(PREFIX_FILTER_ARGUMENT).get());

            return new ListApplicantCommand(filterType, filterArgument);
        } else if (argMultimap.getValue(PREFIX_FILTER_TYPE).isPresent() &&
                !argMultimap.getValue(PREFIX_FILTER_ARGUMENT).isPresent()) {

            throw new ParseException(MESSAGE_FILTER_NO_ARGUMENT);
        } else {
            return new ListApplicantCommand();
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

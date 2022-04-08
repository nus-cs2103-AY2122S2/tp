package woofareyou.logic.parser;

import static java.util.Objects.requireNonNull;
import static woofareyou.commons.core.Messages.FILTER_COMMAND_MORE_THAN_ONE_FIELD;
import static woofareyou.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static woofareyou.logic.parser.CliSyntax.PREFIX_FILTER_BY_APPOINTMENT;
import static woofareyou.logic.parser.CliSyntax.PREFIX_FILTER_BY_DATE;
import static woofareyou.logic.parser.CliSyntax.PREFIX_FILTER_BY_OWNER_NAME;
import static woofareyou.logic.parser.CliSyntax.PREFIX_FILTER_BY_TAG;

import woofareyou.commons.util.FilterUtil;
import woofareyou.logic.commands.FilterCommand;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.filter.AppointmentContainsFilterWordPredicate;
import woofareyou.model.filter.DateContainsFilterDatePredicate;
import woofareyou.model.filter.OwnerNameContainsFilterWordPredicate;
import woofareyou.model.filter.TagContainsFilterWordPredicate;



public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        if (!FilterUtil.isValidFilterArg(trimmedArgs)) {
            throw new ParseException(FILTER_COMMAND_MORE_THAN_ONE_FIELD);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FILTER_BY_DATE, PREFIX_FILTER_BY_TAG,
                        PREFIX_FILTER_BY_OWNER_NAME, PREFIX_FILTER_BY_APPOINTMENT);

        String keyword = ParserUtil.parseFilterArgs(trimmedArgs);

        if (argMultimap.getValue(PREFIX_FILTER_BY_DATE).isPresent()) {
            return new FilterCommand(new DateContainsFilterDatePredicate(keyword));
        } else if (argMultimap.getValue(PREFIX_FILTER_BY_TAG).isPresent()) {
            return new FilterCommand(new TagContainsFilterWordPredicate(keyword));
        } else if (argMultimap.getValue(PREFIX_FILTER_BY_OWNER_NAME).isPresent()) {
            return new FilterCommand(new OwnerNameContainsFilterWordPredicate(keyword));
        } else if (argMultimap.getValue(PREFIX_FILTER_BY_APPOINTMENT).isPresent()) {
            return new FilterCommand(new AppointmentContainsFilterWordPredicate(keyword));
        } else {
            throw new ParseException(FilterCommand.INVALID_FILTER_FIELD);
        }
    }
}

package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.SearchTypeUtil;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.predicate.EventContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindEventPersonCommand object
 */
public class FindEventCommandParser implements Parser<FindEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEventCommand
     * and returns a FindEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COMPANY, PREFIX_START_DATE,
                        PREFIX_END_DATE, PREFIX_TIME, PREFIX_LOCATION, PREFIX_TAG, PREFIX_SEARCH_TYPE);

        String searchTypeString = argMultimap.getValue(PREFIX_SEARCH_TYPE).orElse("unarchived");

        if (!isValid(argMultimap, searchTypeString)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindEventCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).orElse("").split("\\s+");
        String[] companyNameKeywords = argMultimap.getValue(PREFIX_COMPANY).orElse("").split("\\s+");
        Date startDate = argMultimap.getValue(PREFIX_START_DATE).isPresent()
                ? ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get()) : null;
        Date endDate = argMultimap.getValue(PREFIX_END_DATE).isPresent()
                ? ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get()) : null;
        String[] timeKeywords = argMultimap.getValue(PREFIX_TIME).orElse("").split("\\s+");
        String[] locationKeywords = argMultimap.getValue(PREFIX_LOCATION).orElse("").split("\\s+");
        String[] tagKeywords = argMultimap.getValue(PREFIX_TAG).orElse("").split("\\s+");

        SearchTypeUtil.SearchType searchType = ParserUtil.parseSearchType(searchTypeString);

        EventContainsKeywordsPredicate predicate = new EventContainsKeywordsPredicate(Arrays.asList(nameKeywords),
                Arrays.asList(companyNameKeywords),
                startDate, endDate,
                Arrays.asList(timeKeywords),
                Arrays.asList(locationKeywords),
                Arrays.asList(tagKeywords),
                SearchTypeUtil.getPredicate(searchType));

        return new FindEventCommand(predicate);
    }

    private boolean isValid(ArgumentMultimap argumentMultimap, String searchTypeString) throws ParseException {
        boolean namePresent = argumentMultimap.getValue(PREFIX_NAME).isPresent();
        boolean companyNamePresent = argumentMultimap.getValue(PREFIX_COMPANY).isPresent();
        boolean startDatePresent = argumentMultimap.getValue(PREFIX_START_DATE).isPresent();
        boolean endDatePresent = argumentMultimap.getValue(PREFIX_END_DATE).isPresent();
        boolean timePresent = argumentMultimap.getValue(PREFIX_TIME).isPresent();
        boolean locationPresent = argumentMultimap.getValue(PREFIX_LOCATION).isPresent();
        boolean tagPresent = argumentMultimap.getValue(PREFIX_TAG).isPresent();
        boolean searchPresent = argumentMultimap.getValue(PREFIX_SEARCH_TYPE).isPresent();

        ParserUtil.parseSearchType(searchTypeString);

        if (namePresent) {
            List<String> dummy = Arrays.asList(argumentMultimap.getValue(PREFIX_NAME).get().split("\\s+"));
            for (String s : dummy) {
                ParserUtil.parseName(s);
            }
        }
        if (companyNamePresent) {
            List<String> dummy = Arrays.asList(argumentMultimap.getValue(PREFIX_COMPANY).get().split("\\s+"));
            for (String s : dummy) {
                ParserUtil.parseName(s);
            }
        }
        if (startDatePresent) {
            String s = argumentMultimap.getValue(PREFIX_START_DATE).get();
            ParserUtil.parseDate(s);
        }
        if (endDatePresent) {
            String s = argumentMultimap.getValue(PREFIX_END_DATE).get();
            ParserUtil.parseDate(s);
        }
        if (timePresent) {
            List<String> dummy = Arrays.asList(argumentMultimap.getValue(PREFIX_TIME).get().split("\\s+"));
            for (String s : dummy) {
                ParserUtil.parseTime(s);
            }
        }
        if (locationPresent) {
            List<String> dummy = Arrays.asList(argumentMultimap.getValue(PREFIX_LOCATION).get().split("\\s+"));
            for (String s : dummy) {
                ParserUtil.parseLocation(s);
            }
        }
        if (tagPresent) {
            List<String> dummy = Arrays.asList(argumentMultimap.getValue(PREFIX_TAG).get().split("\\s+"));
            for (String s : dummy) {
                ParserUtil.parseTag(s);
            }
        }

        return namePresent || companyNamePresent || startDatePresent || endDatePresent || timePresent
                || locationPresent || tagPresent || searchPresent;
    }
}

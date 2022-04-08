package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.SearchTypeUtil;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.predicate.PersonContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindPersonCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonCommand
     * and returns a FindPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COMPANY, PREFIX_TAG, PREFIX_SEARCH_TYPE);

        String searchTypeString = argMultimap.getValue(PREFIX_SEARCH_TYPE).orElse("unarchived");

        if (!isValid(argMultimap, searchTypeString)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindPersonCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).orElse("").split("\\s+");
        String[] companyNameKeywords = argMultimap.getValue(PREFIX_COMPANY).orElse("").split("\\s+");
        String[] tagKeywords = argMultimap.getValue(PREFIX_TAG).orElse("").split("\\s+");

        SearchTypeUtil.SearchType searchType = ParserUtil.parseSearchType(searchTypeString);

        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(Arrays.asList(nameKeywords),
                Arrays.asList(companyNameKeywords), Arrays.asList(tagKeywords),
                SearchTypeUtil.getPredicate(searchType));


        return new FindPersonCommand(predicate);
    }

    private boolean isValid(ArgumentMultimap argumentMultimap, String searchTypeString) throws ParseException {
        boolean namePresent = argumentMultimap.getValue(PREFIX_NAME).isPresent();
        boolean companyNamePresent = argumentMultimap.getValue(PREFIX_COMPANY).isPresent();
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
        if (tagPresent) {
            List<String> dummy = Arrays.asList(argumentMultimap.getValue(PREFIX_TAG).get().split("\\s+"));
            for (String s : dummy) {
                ParserUtil.parseTag(s);
            }
        }

        return namePresent || companyNamePresent || tagPresent || searchPresent;
    }
}


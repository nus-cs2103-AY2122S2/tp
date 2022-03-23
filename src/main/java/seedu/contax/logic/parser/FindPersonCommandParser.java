package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;

import java.util.Arrays;

import seedu.contax.logic.commands.FindPersonCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.person.AddressContainsKeywordsPredicate;
import seedu.contax.model.person.EmailContainsKeywordsPredicate;
import seedu.contax.model.person.NameContainsKeywordsPredicate;
import seedu.contax.model.person.PhoneContainsKeywordsPredicate;
import seedu.contax.model.util.SearchType;

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
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_SEARCH_TYPE);
        String[] outputKeywords = argMultimap.getPreamble().trim().split("\\s+");

        if (argMultimap.getValue(PREFIX_SEARCH_TYPE).isEmpty()) {
            return new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
        } else {
            SearchType type = ParserUtil.parseSearchType(argMultimap.getValue(PREFIX_SEARCH_TYPE).get());

            switch (type.searchType) {
            case SearchType.TYPE_PHONE:
                return new FindPersonCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
            case SearchType.TYPE_EMAIL:
                return new FindPersonCommand(new EmailContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
            case SearchType.TYPE_ADDRESS:
                return new FindPersonCommand(new AddressContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
            default:
                return new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
            }
        }
    }
}

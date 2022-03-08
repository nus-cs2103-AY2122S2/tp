package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.contax.logic.commands.FindCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.person.AddressContainsKeywordsPredicate;
import seedu.contax.model.person.EmailContainsKeywordsPredicate;
import seedu.contax.model.person.NameContainsKeywordsPredicate;
import seedu.contax.model.person.PhoneContainsKeywordsPredicate;
import seedu.contax.model.util.SearchType;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_SEARCH_TYPE);
        String[] outputKeywords = args.trim().split("\\s+");
        if (!arePrefixesPresent(argMultimap, PREFIX_SEARCH_TYPE)) {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
        } else {
            SearchType type = ParserUtil.parseSearchType(argMultimap.getValue(PREFIX_SEARCH_TYPE).get());
            switch (type.toString()) {
            case "phone":
                return new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
            case "email":
                return new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
            case "address":
                return new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
            default:
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
            }
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

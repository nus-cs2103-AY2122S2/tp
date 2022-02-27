package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

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

        String[] keywords = trimmedArgs.split("\\s+");

        String prefix = keywords[0];
        if (checkPrefix(prefix, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return findWithPrefix(prefix, Arrays.copyOfRange(keywords, 1, keywords.length));
    }

    public boolean checkPrefix(String p, Prefix ... knownPrefixes) {
        return Arrays.stream(knownPrefixes).noneMatch(s -> s.toString().equals(p));
    }

    public FindCommand findWithPrefix(String prefix, String[] keywords) throws ParseException {
        switch(prefix) {
        case "n/":
            System.out.println("1");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "p/":
            return new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "e/":
            return new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "a/":
            return new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}

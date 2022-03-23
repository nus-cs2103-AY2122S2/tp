package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.predicates.PersonContainsKeywordsPredicate;
import seedu.address.model.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.predicates.PreferenceContainsKeywordsPredicate;
import seedu.address.model.predicates.PropertiesContainsKeywordsPredicate;
import seedu.address.model.predicates.UserTypeContainsKeywordsPredicate;

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

        String[] arguments = trimmedArgs.split("\\s+");
        String commandWord = arguments[0];
        List<String> keywords = Arrays.asList(Arrays.copyOfRange(arguments, 1, arguments.length));

        switch (commandWord) {

        case PersonContainsKeywordsPredicate.COMMAND_WORD:
            return new FindCommand(new PersonContainsKeywordsPredicate(keywords));

        case NameContainsKeywordsPredicate.COMMAND_WORD:
            return new FindCommand(new NameContainsKeywordsPredicate(keywords));

        case PhoneContainsKeywordsPredicate.COMMAND_WORD:
            return new FindCommand(new PhoneContainsKeywordsPredicate(keywords));

        case EmailContainsKeywordsPredicate.COMMAND_WORD:
            return new FindCommand(new EmailContainsKeywordsPredicate(keywords));

        case AddressContainsKeywordsPredicate.COMMAND_WORD:
            return new FindCommand(new AddressContainsKeywordsPredicate(keywords));

        case PropertiesContainsKeywordsPredicate.COMMAND_WORD:
            return new FindCommand(new PropertiesContainsKeywordsPredicate(keywords));

        case PreferenceContainsKeywordsPredicate.COMMAND_WORD:
            return new FindCommand(new PreferenceContainsKeywordsPredicate(keywords));

        case UserTypeContainsKeywordsPredicate.COMMAND_WORD:
            return new FindCommand(new UserTypeContainsKeywordsPredicate(keywords));

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }

}

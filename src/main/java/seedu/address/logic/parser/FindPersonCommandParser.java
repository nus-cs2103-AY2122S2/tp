package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.ParserUtil.isOnlyOnePrefixPresent;

import java.util.Arrays;

import seedu.address.logic.commands.FindPersonAddressCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.FindPersonEmailCommand;
import seedu.address.logic.commands.FindPersonNameCommand;
import seedu.address.logic.commands.FindPersonPhoneCommand;
import seedu.address.logic.commands.FindPersonRemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.RemarkContainsKeywordsPredicate;

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

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_NAME, PREFIX_EMAIL,
                        PREFIX_REMARK, PREFIX_ADDRESS);

        if (isOnlyOnePrefixPresent(argMultimap, PREFIX_NAME)) {
            String[] nameKeywords = getKeywords(argMultimap, PREFIX_NAME);
            return new FindPersonNameCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (isOnlyOnePrefixPresent(argMultimap, PREFIX_PHONE)) {
            String[] phoneKeywords = getKeywords(argMultimap, PREFIX_PHONE);
            return new FindPersonPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
        } else if (isOnlyOnePrefixPresent(argMultimap, PREFIX_EMAIL)) {
            String[] emailKeywords = getKeywords(argMultimap, PREFIX_EMAIL);
            return new FindPersonEmailCommand(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
        } else if (isOnlyOnePrefixPresent(argMultimap, PREFIX_REMARK)) {
            String[] remarkKeywords = getKeywords(argMultimap, PREFIX_REMARK);
            return new FindPersonRemarkCommand(new RemarkContainsKeywordsPredicate(Arrays.asList(remarkKeywords)));
        } else if (isOnlyOnePrefixPresent(argMultimap, PREFIX_ADDRESS)) {
            String[] addressKeywords = getKeywords(argMultimap, PREFIX_ADDRESS);
            return new FindPersonAddressCommand(new AddressContainsKeywordsPredicate(Arrays.asList(addressKeywords)));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }
    }

    private static String[] getKeywords(ArgumentMultimap argMultiMap, Prefix findPrefix) throws ParseException {
        String keywordString = argMultiMap.getValue(findPrefix).get().trim();
        if (keywordString.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }
        return keywordString.split("\\s+");
    }
}

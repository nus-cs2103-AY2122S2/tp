package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FindSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.seller.SellerHouseTypeContainsKeywordsPredicate;
import seedu.address.model.seller.SellerLocationContainsKeywordsPredicate;
import seedu.address.model.seller.SellerNameContainsKeywordsPredicate;
import seedu.address.model.seller.SellerPhoneContainsKeywordsPredicate;
import seedu.address.model.seller.SellerTagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindSellerCommandParser implements Parser<FindSellerCommand> {

    private static final String PREFIX_DELIMITER = "/";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindSellerCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindSellerCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        String firstArg = keywords[0];
        if (firstArg.length() < 3) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindSellerCommand.MESSAGE_USAGE));
        }
        int delimiterIndex = firstArg.indexOf(PREFIX_DELIMITER);
        if (delimiterIndex != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindSellerCommand.MESSAGE_USAGE));
        }
        String prefix = firstArg.substring(0, 2);
        if (checkPrefix(prefix, PREFIX_NAME, PREFIX_PHONE, PREFIX_HOUSE_TYPE, PREFIX_TAG, PREFIX_LOCATION)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindSellerCommand.MESSAGE_USAGE));
        }
        keywords[0] = firstArg.substring(2);
        return findWithPrefix(prefix, keywords);
    }

    public boolean checkPrefix(String p, Prefix ... knownPrefixes) {
        return Arrays.stream(knownPrefixes).noneMatch(s -> s.toString().equals(p));
    }

    /**
     * Executes the respective FindCommands with different signatures, depending on the prefix arguments.
     *
     * @param prefix The prefix that the user inputs, to find a Seller.
     * @param keywords Keywords that come after the prefix.
     * @return The desired FindCommand, with the correct signature.
     * @throws ParseException ParseException thrown if no valid prefix provided.
     */
    public FindSellerCommand findWithPrefix(String prefix, String[] keywords) throws ParseException {
        switch(prefix) {
        case "n/":
            return new FindSellerCommand(new SellerNameContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "p/":
            return new FindSellerCommand(new SellerPhoneContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "t/":
            return new FindSellerCommand(new SellerTagsContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "h/":
            return new FindSellerCommand(new SellerHouseTypeContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "l/":
            return new FindSellerCommand(new SellerLocationContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindSellerCommand.MESSAGE_USAGE));
        }
    }
}

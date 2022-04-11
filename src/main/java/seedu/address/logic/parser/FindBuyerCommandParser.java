package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FindBuyerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.buyer.BuyerHouseTypeContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerLocationContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerNameContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerPhoneContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerTagsContainsKeywordsPredicate;

public class FindBuyerCommandParser implements Parser<FindBuyerCommand> {

    private static final String PREFIX_DELIMITER = "/";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindBuyerCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBuyerCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        String firstArg = keywords[0];
        if (firstArg.length() < 3) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBuyerCommand.MESSAGE_USAGE));
        }
        int delimiterIndex = firstArg.indexOf(PREFIX_DELIMITER);
        if (delimiterIndex != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBuyerCommand.MESSAGE_USAGE));
        }
        String prefix = firstArg.substring(0, 2);
        if (checkPrefix(prefix, PREFIX_NAME, PREFIX_PHONE, PREFIX_TAG, PREFIX_HOUSE_TYPE, PREFIX_LOCATION)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBuyerCommand.MESSAGE_USAGE));
        }
        keywords[0] = firstArg.substring(2);
        return findWithPrefix(prefix, keywords);
    }

    public boolean checkPrefix(String p, Prefix... knownPrefixes) {
        return Arrays.stream(knownPrefixes).noneMatch(s -> s.toString().equals(p));
    }

    /**
     * Executes the respective FindCommands with different signatures, depending on the prefix arguments.
     *
     * @param prefix The prefix that the user inputs, to find a Buyer.
     * @param keywords Keywords that come after the prefix.
     * @return The desired FindCommand, with the correct signature.
     * @throws ParseException ParseException thrown if no valid prefix provided.
     */
    public FindBuyerCommand findWithPrefix(String prefix, String[] keywords) throws ParseException {
        switch(prefix) {
        case "n/":
            return new FindBuyerCommand(new BuyerNameContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "p/":
            System.out.println("print");
            return new FindBuyerCommand(new BuyerPhoneContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "t/":
            return new FindBuyerCommand(new BuyerTagsContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "h/":
            return new FindBuyerCommand(new BuyerHouseTypeContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        case "l/":
            return new FindBuyerCommand(new BuyerLocationContainsKeywordsPredicate(Arrays.asList(keywords)));
            // Fallthrough
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBuyerCommand.MESSAGE_USAGE));
        }
    }
}

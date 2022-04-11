package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLLECTION_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.ParserUtil.isOnlyOnePrefixPresent;

import java.util.Arrays;

import seedu.address.logic.commands.FindOrderCollectionTypeCommand;
import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.commands.FindOrderDetailsCommand;
import seedu.address.logic.commands.FindOrderNameCommand;
import seedu.address.logic.commands.FindOrderPhoneCommand;
import seedu.address.logic.commands.FindOrderRemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.CollectionTypeContainsKeywordsPredicate;
import seedu.address.model.order.Details;
import seedu.address.model.order.DetailsContainsKeywordsPredicate;
import seedu.address.model.order.RemarkContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindOrderCommand object
 */
public class FindOrderCommandParser implements Parser<FindOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindOrderCommand
     * and returns a FindOrderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindOrderCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_NAME,
                        PREFIX_REMARK, PREFIX_DETAILS, PREFIX_COLLECTION_TYPE);

        if (isOnlyOnePrefixPresent(argMultimap, PREFIX_NAME)) {
            String[] nameKeywords = getKeywords(argMultimap, PREFIX_NAME);
            return new FindOrderNameCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (isOnlyOnePrefixPresent(argMultimap, PREFIX_PHONE)) {
            String[] phoneKeywords = getKeywords(argMultimap, PREFIX_PHONE);
            return new FindOrderPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
        } else if (isOnlyOnePrefixPresent(argMultimap, PREFIX_REMARK)) {
            String[] remarkKeywords = getKeywords(argMultimap, PREFIX_REMARK);
            return new FindOrderRemarkCommand(new RemarkContainsKeywordsPredicate(Arrays.asList(remarkKeywords)));
        } else if (isOnlyOnePrefixPresent(argMultimap, PREFIX_DETAILS)) {
            checkDetailsItemKeyword(argMultimap, PREFIX_DETAILS);
            String[] detailsKeywords = getKeywords(argMultimap, PREFIX_DETAILS);
            return new FindOrderDetailsCommand(new DetailsContainsKeywordsPredicate(Arrays.asList(detailsKeywords)));
        } else if (isOnlyOnePrefixPresent(argMultimap, PREFIX_COLLECTION_TYPE)) {
            String collectionTypeKeyword = argMultimap.getValue(PREFIX_COLLECTION_TYPE).get().trim();
            return new FindOrderCollectionTypeCommand(
                    new CollectionTypeContainsKeywordsPredicate(collectionTypeKeyword));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }
    }

    private static String[] getKeywords(ArgumentMultimap argMultiMap, Prefix findPrefix) throws ParseException {
        String keywordString = argMultiMap.getValue(findPrefix).get().trim();
        if (keywordString.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }
        return keywordString.split("\\s+");
    }

    private static void checkDetailsItemKeyword(ArgumentMultimap argMultiMap, Prefix findPrefix) throws ParseException {
        if (!Details.isValidFindDetailsItem(argMultiMap.getValue(findPrefix).get().trim())) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, Details.FIND_ITEM_MESSAGE_CONSTRAINTS));
        }
    }
}


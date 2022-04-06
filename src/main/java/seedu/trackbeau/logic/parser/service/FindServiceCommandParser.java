package seedu.trackbeau.logic.parser.service;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.trackbeau.model.service.ServiceSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.trackbeau.logic.commands.service.FindServiceCommand;
import seedu.trackbeau.logic.parser.ArgumentMultimap;
import seedu.trackbeau.logic.parser.ArgumentTokenizer;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.Prefix;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.service.ServiceSearchContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindServiceCommand object
 */
public class FindServiceCommandParser implements Parser<FindServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindServiceCommand
     * and returns a FindServiceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindServiceCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PRICE, PREFIX_DURATION);
        Prefix[] prefixList = { PREFIX_NAME, PREFIX_PRICE, PREFIX_DURATION };

        if (userInput.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindServiceCommand.MESSAGE_USAGE));
        }

        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));

        for (int i = 0; i < FIND_ATTRIBUTE_COUNT; i++) {
            if (argMultimap.getValue(prefixList[i]).isPresent() && argMultimap.getPreamble().isEmpty()) {
                prefixArr.set(i,
                        Arrays.asList(ParserUtil
                                .parseFindValues(argMultimap.getValue(prefixList[i]).get()).toString().split(" ")));
            }
        }

        return new FindServiceCommand(new ServiceSearchContainsKeywordsPredicate(prefixArr));

    }
}

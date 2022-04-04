package seedu.trackbeau.logic.parser.booking;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.trackbeau.model.booking.BookingSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT;
import static seedu.trackbeau.model.customer.Address.VALIDATION_REGEX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.trackbeau.logic.commands.booking.FindBookingCommand;
import seedu.trackbeau.logic.parser.ArgumentMultimap;
import seedu.trackbeau.logic.parser.ArgumentTokenizer;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.Prefix;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.booking.BookingSearchContainsKeywordsPredicate;
import seedu.trackbeau.model.customer.Address;

/**
 * Parses input arguments and creates a new FindBookingCommand object
 */
public class FindBookingCommandParser implements Parser<FindBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindBookingCommand
     * and returns a FindBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindBookingCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_STARTTIME);
        Prefix[] prefixList = { PREFIX_NAME, PREFIX_STARTTIME };

        if (userInput.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookingCommand.MESSAGE_USAGE));
        }

        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));

        for (int i = 0; i < FIND_ATTRIBUTE_COUNT; i++) {
            if (argMultimap.getValue(prefixList[i]).isPresent() && argMultimap.getPreamble().isEmpty()) {
                //using add will cause the size of the list to be wrong
                prefixArr.set(i,
                        Arrays.asList(ParserUtil
                                .parseFindValues(argMultimap.getValue(prefixList[i]).get()).toString().split(" ")));
            }
        }

        return new FindBookingCommand(new BookingSearchContainsKeywordsPredicate(prefixArr));

    }

}

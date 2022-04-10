package seedu.trackbeau.logic.parser.booking;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_FEEDBACK;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

/**
 * Parses input arguments and creates a new FindBookingCommand object
 */
public class FindBookingCommandParser implements Parser<FindBookingCommand> {
    private Prefix[] prefixList = { PREFIX_NAME, PREFIX_STARTTIME, PREFIX_FEEDBACK };
    private String[] parse = {"parseName", "parseStartTime", "parseFeedback"};

    /**
     * Parses the given {@code String} of arguments in the context of the FindBookingCommand
     * and returns a FindBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindBookingCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, prefixList[0], prefixList[1], prefixList[2]);

        prefixParser(argMultimap);

        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections
                .nCopies(BookingSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT, null));
        try {
            for (int i = 0; i < BookingSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT; i++) {
                if (!argMultimap.getValue(prefixList[i]).isPresent()) {
                    continue;
                }
                if (i == 1) {
                    ParserUtil.parseBirthdate(argMultimap.getValue(prefixList[i]).get());
                } else {
                    for (String value : argMultimap.getValue(prefixList[i]).get().split(" ")) {
                        Method m = ParserUtil.class.getDeclaredMethod(parse[i], String.class);
                        m.invoke(null, value).toString();
                    }
                }
                prefixArr.set(i, Arrays.asList(ParserUtil.parseFindValues(argMultimap
                        .getValue(prefixList[i]).get()).toString().split(" ")));

            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException ie) {
            if (ie.getCause() instanceof ParseException) {
                throw (ParseException) ie.getCause();
            }
        }
        return new FindBookingCommand(new BookingSearchContainsKeywordsPredicate(prefixArr));

    }

    /**
     * Parses the given {@code ArgumentMultimap} to check if any of the valid prefix is present.
     * @throws ParseException if the user input does not contain any valid prefix.
     */
    public void prefixParser(ArgumentMultimap argMultimap) throws ParseException {
        Boolean hasPrefix = false;
        for (Prefix prefix : prefixList) {
            if (ParserUtil.arePrefixesPresent(argMultimap, prefix)) {
                hasPrefix = true;
                break;
            }
        }

        if (!hasPrefix || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookingCommand.MESSAGE_USAGE));
        }
    }

}

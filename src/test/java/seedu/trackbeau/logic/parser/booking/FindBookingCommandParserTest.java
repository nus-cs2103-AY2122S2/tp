package seedu.trackbeau.logic.parser.booking;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.trackbeau.model.booking.BookingSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.booking.FindBookingCommand;
import seedu.trackbeau.model.booking.BookingSearchContainsKeywordsPredicate;

public class FindBookingCommandParserTest {

    private FindBookingCommandParser parser = new FindBookingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBookingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Find bookings with no leading and trailing whitespaces
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));
        prefixArr.set(0, Arrays.asList(new String[]{"alex", "facial"}));
        prefixArr.set(1, Arrays.asList(new String[]{"02-02-2022"}));
        prefixArr.set(2, Arrays.asList(new String[]{"Good"}));
        FindBookingCommand expectedFindBookingCommand =
                new FindBookingCommand(new BookingSearchContainsKeywordsPredicate(prefixArr));
        assertParseSuccess(parser, " n/alex facial st/02-02-2022 f/Good", expectedFindBookingCommand);

        // Find bookings with multiple whitespaces between keywords
        assertParseSuccess(parser, " n/alex facial \n \t st/02-02-2022 f/Good\t" , expectedFindBookingCommand);

    }

}

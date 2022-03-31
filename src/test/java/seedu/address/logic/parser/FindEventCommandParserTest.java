package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DATE_END_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DATE_START_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DATE_START_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_FRIEND_NAME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_FRIEND_NAME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DATE_END_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DATE_START_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AFTER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BEFORE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.FindEventCommandParser.MESSAGE_INVALID_DATE_RANGE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindEventCommand;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventPredicateListBuilder;

public class FindEventCommandParserTest {

    private FindEventCommandParser parser = new FindEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        List<Predicate<Event>> expectedPredicates = new EventPredicateListBuilder().withNameSubstring(VALID_EVENT_NAME)
                .withStartDate(VALID_DATE_BEFORE).withEndDate(VALID_DATE_AFTER)
                .withFriendNameSubstrings(VALID_NAME_AMY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EVENT_NAME_DESC_A + EVENT_DATE_START_DESC_A
                + EVENT_DATE_END_DESC_A + EVENT_FRIEND_NAME_DESC_A, new FindEventCommand(expectedPredicates));

        // multiple names - last name accepted
        assertParseSuccess(parser, EVENT_NAME_DESC_B + EVENT_NAME_DESC_A + EVENT_DATE_START_DESC_A
                + EVENT_DATE_END_DESC_A + EVENT_FRIEND_NAME_DESC_A, new FindEventCommand(expectedPredicates));

        // multiple dates - last date accepted
        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATE_START_DESC_B + EVENT_DATE_START_DESC_A
                + EVENT_DATE_END_DESC_A + EVENT_FRIEND_NAME_DESC_A, new FindEventCommand(expectedPredicates));

        // multiple friend names - all accepted
        List<Predicate<Event>> expectedPredicatesMultipleFriendNames = new EventPredicateListBuilder()
                .withNameSubstring(VALID_EVENT_NAME).withStartDate(VALID_DATE_BEFORE).withEndDate(VALID_DATE_AFTER)
                .withFriendNameSubstrings(VALID_NAME_AMY, VALID_NAME_BOB).build();

        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATE_START_DESC_A
                        + EVENT_DATE_END_DESC_A + EVENT_FRIEND_NAME_DESC_A + EVENT_FRIEND_NAME_DESC_B,
                new FindEventCommand(expectedPredicatesMultipleFriendNames));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        List<Predicate<Event>> expectedPredicatesNoDate = new EventPredicateListBuilder()
                .withNameSubstring(VALID_EVENT_NAME).clearStartDate().clearEndDate()
                .withFriendNameSubstrings(VALID_NAME_AMY).build();

        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_FRIEND_NAME_DESC_A,
                new FindEventCommand(expectedPredicatesNoDate));

        List<Predicate<Event>> expectedPredicatesNoName = new EventPredicateListBuilder()
                .clearNameSubstring().withStartDate(VALID_DATE_BEFORE).withEndDate(VALID_DATE_AFTER)
                .withFriendNameSubstrings(VALID_NAME_AMY).build();

        assertParseSuccess(parser, EVENT_DATE_START_DESC_A + EVENT_DATE_END_DESC_A
                        + EVENT_FRIEND_NAME_DESC_A, new FindEventCommand(expectedPredicatesNoName));

        List<Predicate<Event>> expectedPredicatesNoFriendNames = new EventPredicateListBuilder()
                .withNameSubstring(VALID_EVENT_NAME).withStartDate(VALID_DATE_BEFORE).withEndDate(VALID_DATE_AFTER)
                .withFriendNameSubstrings().build();

        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATE_START_DESC_A + EVENT_DATE_END_DESC_A,
                new FindEventCommand(expectedPredicatesNoFriendNames));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid start date
        assertParseFailure(parser, EVENT_NAME_DESC_A + INVALID_EVENT_DATE_START_DESC
                + EVENT_FRIEND_NAME_DESC_A, MESSAGE_INVALID_DATE);

        // invalid end date
        assertParseFailure(parser, EVENT_NAME_DESC_A + INVALID_EVENT_DATE_END_DESC
                + EVENT_FRIEND_NAME_DESC_A, MESSAGE_INVALID_DATE);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + EVENT_NAME_DESC_A + EVENT_DATE_START_DESC_A
                        + EVENT_FRIEND_NAME_DESC_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));

        // invalid date range
        String invalidDateRangeInput = " " + PREFIX_DATE_START + VALID_DATE_AFTER
                + " " + PREFIX_DATE_END + VALID_DATE_BEFORE;
        assertParseFailure(parser, invalidDateRangeInput, MESSAGE_INVALID_DATE_RANGE);
    }
}

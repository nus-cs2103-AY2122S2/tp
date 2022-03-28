package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DATE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DATE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_FRIEND_NAME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_FRIEND_NAME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindEventCommand;
import seedu.address.model.event.EventFilterPredicate;
import seedu.address.testutil.EventFilterPredicateBuilder;

public class FindEventCommandParserTest {

    private FindEventCommandParser parser = new FindEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        EventFilterPredicate expectedPredicate = new EventFilterPredicateBuilder().withNameSubstring(VALID_EVENT_NAME)
                .withDate(VALID_DATE).withFriendNameSubstrings(VALID_NAME_AMY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EVENT_NAME_DESC_A + EVENT_DATE_DESC_A
                + EVENT_FRIEND_NAME_DESC_A, new FindEventCommand(expectedPredicate));

        // multiple names - last name accepted
        assertParseSuccess(parser, EVENT_NAME_DESC_B + EVENT_NAME_DESC_A + EVENT_DATE_DESC_A
                + EVENT_FRIEND_NAME_DESC_A, new FindEventCommand(expectedPredicate));

        // multiple dates - last date accepted
        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATE_DESC_B + EVENT_DATE_DESC_A
                + EVENT_FRIEND_NAME_DESC_A, new FindEventCommand(expectedPredicate));

        // multiple friend names - all accepted
        EventFilterPredicate expectedPredicateMultipleFriendNames = new EventFilterPredicateBuilder()
                .withNameSubstring(VALID_EVENT_NAME).withDate(VALID_DATE)
                .withFriendNameSubstrings(VALID_NAME_AMY, VALID_NAME_BOB).build();

        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATE_DESC_B + EVENT_DATE_DESC_A
                        + EVENT_FRIEND_NAME_DESC_A + EVENT_FRIEND_NAME_DESC_B,
                new FindEventCommand(expectedPredicateMultipleFriendNames));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        EventFilterPredicate expectedPredicateNoDate = new EventFilterPredicateBuilder()
                .withNameSubstring(VALID_EVENT_NAME).clearDate().withFriendNameSubstrings(VALID_NAME_AMY).build();

        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_FRIEND_NAME_DESC_A,
                new FindEventCommand(expectedPredicateNoDate));

        EventFilterPredicate expectedPredicateNoName = new EventFilterPredicateBuilder()
                .withNameSubstring("").withDate(VALID_DATE).withFriendNameSubstrings(VALID_NAME_AMY).build();

        assertParseSuccess(parser, EVENT_DATE_DESC_A + EVENT_FRIEND_NAME_DESC_A,
                new FindEventCommand(expectedPredicateNoName));

        EventFilterPredicate expectedPredicateNoFriendNames = new EventFilterPredicateBuilder()
                .withNameSubstring(VALID_EVENT_NAME).withDate(VALID_DATE).withFriendNameSubstrings().build();

        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATE_DESC_A,
                new FindEventCommand(expectedPredicateNoFriendNames));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date
        assertParseFailure(parser, EVENT_NAME_DESC_A + INVALID_EVENT_DATE_DESC
                + EVENT_FRIEND_NAME_DESC_A, MESSAGE_INVALID_DATE);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + EVENT_NAME_DESC_A + EVENT_DATE_DESC_A
                        + EVENT_FRIEND_NAME_DESC_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
    }
}

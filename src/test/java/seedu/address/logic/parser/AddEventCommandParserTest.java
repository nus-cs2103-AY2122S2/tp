package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DATETIME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DATETIME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DESCRIPTION_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DESCRIPTION_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_FRIEND_NAME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_FRIEND_NAME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_FRIENDNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.common.Description;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.person.FriendName;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder().withName(VALID_EVENT_NAME).withDateTime(VALID_EVENT_DATETIME)
                .withDescription(VALID_EVENT_DESCRIPTION).withNames(VALID_NAME_AMY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EVENT_NAME_DESC_A + EVENT_DATETIME_DESC_A
                + EVENT_DESCRIPTION_DESC_A + EVENT_FRIEND_NAME_DESC_A, new AddEventCommand(expectedEvent));

        // multiple names - last name accepted
        assertParseSuccess(parser, EVENT_NAME_DESC_B + EVENT_NAME_DESC_A + EVENT_DATETIME_DESC_A
                + EVENT_DESCRIPTION_DESC_A + EVENT_FRIEND_NAME_DESC_A, new AddEventCommand(expectedEvent));

        // multiple datetimes - last datetime accepted
        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATETIME_DESC_B + EVENT_DATETIME_DESC_A
                + EVENT_DESCRIPTION_DESC_A + EVENT_FRIEND_NAME_DESC_A, new AddEventCommand(expectedEvent));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATETIME_DESC_A + EVENT_DESCRIPTION_DESC_B
                + EVENT_DESCRIPTION_DESC_A + EVENT_FRIEND_NAME_DESC_A, new AddEventCommand(expectedEvent));

        // multiple friend names - all accepted
        Event expectedEventMultipleFriendNames = new EventBuilder(expectedEvent)
                .withNames(VALID_NAME_AMY, VALID_NAME_BOB).build();

        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATETIME_DESC_A + EVENT_DESCRIPTION_DESC_A
                + EVENT_FRIEND_NAME_DESC_A + EVENT_FRIEND_NAME_DESC_B,
                new AddEventCommand(expectedEventMultipleFriendNames));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Event expectedEventNoTags = new EventBuilder().withName(VALID_EVENT_NAME).withDateTime(VALID_EVENT_DATETIME)
                .withDescription(VALID_EVENT_DESCRIPTION).withNames().build();
        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATETIME_DESC_A + EVENT_DESCRIPTION_DESC_A,
                new AddEventCommand(expectedEventNoTags));

        //no description
        Event expectedEventNoDescription = new EventBuilder().withName(VALID_EVENT_NAME)
                .withDateTime(VALID_EVENT_DATETIME).withDescription(null).withNames(VALID_NAME_AMY).build();
        assertParseSuccess(parser, EVENT_NAME_DESC_A + EVENT_DATETIME_DESC_A + EVENT_FRIEND_NAME_DESC_A,
                new AddEventCommand(expectedEventNoDescription));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_AMY + EVENT_DATETIME_DESC_A, expectedMessage);

        // missing datetime prefix
        assertParseFailure(parser, EVENT_NAME_DESC_A + VALID_EVENT_DATETIME, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_EVENT_NAME + VALID_EVENT_DATETIME, expectedMessage);
    }

    // TODO Update with new constraints for event name and description
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_EVENT_NAME_DESC + EVENT_DATETIME_DESC_A
                + EVENT_DESCRIPTION_DESC_A + EVENT_FRIEND_NAME_DESC_A, EventName.MESSAGE_CONSTRAINTS);

        // invalid datetime
        assertParseFailure(parser, EVENT_NAME_DESC_A + INVALID_EVENT_DATETIME_DESC
                + EVENT_DESCRIPTION_DESC_A + EVENT_FRIEND_NAME_DESC_A, DateTime.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, EVENT_NAME_DESC_A + EVENT_DATETIME_DESC_A
                + INVALID_EVENT_DESCRIPTION_DESC + EVENT_FRIEND_NAME_DESC_A, Description.MESSAGE_CONSTRAINTS);

        // invalid friend name
        assertParseFailure(parser, EVENT_NAME_DESC_A + EVENT_DATETIME_DESC_A
                + EVENT_DESCRIPTION_DESC_A + INVALID_EVENT_FRIENDNAME_DESC + EVENT_FRIEND_NAME_DESC_A,
                FriendName.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_EVENT_NAME_DESC + INVALID_EVENT_DATETIME_DESC
                + EVENT_DESCRIPTION_DESC_A + EVENT_FRIEND_NAME_DESC_A, EventName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + EVENT_NAME_DESC_A + EVENT_DATETIME_DESC_A
                        + EVENT_DESCRIPTION_DESC_A + EVENT_FRIEND_NAME_DESC_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
    }
}

package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECURRING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_URL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RECURRING_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.RECURRING_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.URL_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.URL_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LECTURE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.typical.TypicalMeetings.CS2101;
import static seedu.address.testutil.typical.TypicalMeetings.CS2103;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.AddMeetingCommand;
import seedu.address.logic.commands.meeting.AddMeetingCommand.AddMeetingDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.meeting.IsRecurring;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDateTime;
import seedu.address.model.meeting.MeetingDuration;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingUrl;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.meeting.AddMeetingDescriptorBuilder;
import seedu.address.testutil.meeting.MeetingBuilder;

public class AddMeetingCommandParserTest {
    private static final String MODULE_DESC_LECTURE = " m/2";
    private static final String MODULE_DESC_TUTORIAL = " m/3";
    private static final String INVALID_MODULE_DESC = " m/a";
    private final AddMeetingCommandParser parser = new AddMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        final Meeting expectedMeeting = new MeetingBuilder(CS2103).build();
        AddMeetingDescriptor addMeetingDescriptor =
                new AddMeetingDescriptorBuilder(expectedMeeting).withModule(Index.fromZeroBased(1)).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_LECTURE + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, new AddMeetingCommand(addMeetingDescriptor));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_TUTORIAL + NAME_DESC_LECTURE + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, new AddMeetingCommand(addMeetingDescriptor));

        // multiple urls - last url accepted
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_TUTORIAL + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, new AddMeetingCommand(addMeetingDescriptor));

        // multiple datetime - last datetime accepted
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_TUTORIAL
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, new AddMeetingCommand(addMeetingDescriptor));

        // multiple duration - last duration accepted
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                + DURATION_DESC_TUTORIAL + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, new AddMeetingCommand(addMeetingDescriptor));

        // multiple module - last module accepted
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                + DURATION_DESC_LECTURE + MODULE_DESC_TUTORIAL + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE
                + TAG_DESC_LECTURE, new AddMeetingCommand(addMeetingDescriptor));

        // multiple recurrence - last recurrence accepted
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_TUTORIAL + RECURRING_DESC_LECTURE
                + TAG_DESC_LECTURE, new AddMeetingCommand(addMeetingDescriptor));

        // multiple tags - all accepted
        final Meeting expectedMeetingMultipleTags = new MeetingBuilder(CS2103)
                .withTags(VALID_TAG_LECTURE, VALID_TAG_TUTORIAL).build();
        addMeetingDescriptor = new AddMeetingDescriptorBuilder(expectedMeetingMultipleTags)
                .withModule(Index.fromZeroBased(1)).build();
        assertParseSuccess(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE + TAG_DESC_TUTORIAL
                + TAG_DESC_LECTURE, new AddMeetingCommand(addMeetingDescriptor));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        final Meeting expectedMeeting = new MeetingBuilder(CS2101).withTags().build();
        AddMeetingDescriptor addMeetingDescriptor =
                new AddMeetingDescriptorBuilder(expectedMeeting).withModule(Index.fromZeroBased(2)).build();
        assertParseSuccess(parser, NAME_DESC_TUTORIAL + URL_DESC_TUTORIAL + DATETIME_DESC_TUTORIAL
                        + DURATION_DESC_TUTORIAL + MODULE_DESC_TUTORIAL + RECURRING_DESC_TUTORIAL,
                new AddMeetingCommand(addMeetingDescriptor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        final String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                        + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE,
                expectedMessage);

        // missing url prefix
        assertParseFailure(parser, NAME_DESC_LECTURE + VALID_URL_LECTURE + DATETIME_DESC_LECTURE
                        + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE,
                expectedMessage);

        // missing datetime prefix
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + VALID_DATETIME_LECTURE
                        + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE,
                expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                        + VALID_DURATION_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE,
                expectedMessage);

        // missing module prefix
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                        + DURATION_DESC_LECTURE + VALID_MODULE_LECTURE + RECURRING_DESC_LECTURE,
                expectedMessage);

        // missing recurrence prefix
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + DATETIME_DESC_LECTURE
                        + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + VALID_RECURRING_LECTURE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_LECTURE + VALID_URL_LECTURE + VALID_DATETIME_LECTURE
                        + VALID_DURATION_LECTURE + MODULE_DESC_LECTURE + VALID_RECURRING_LECTURE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, MeetingName.MESSAGE_CONSTRAINTS);

        // invalid url
        assertParseFailure(parser, NAME_DESC_LECTURE + INVALID_URL_DESC + DATETIME_DESC_LECTURE
                + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, MeetingUrl.MESSAGE_CONSTRAINTS);

        // invalid datetime
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE + INVALID_DATETIME_DESC
                + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE + RECURRING_DESC_LECTURE
                + TAG_DESC_LECTURE, MeetingDateTime.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + INVALID_DURATION_DESC + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, MeetingDuration.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + INVALID_MODULE_DESC
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, "Module " + ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid isRecurring
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + INVALID_RECURRING_DESC + TAG_DESC_LECTURE, IsRecurring.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_LECTURE + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + INVALID_DURATION_DESC + MODULE_DESC_LECTURE
                + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE, MeetingName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_LECTURE + URL_DESC_LECTURE
                        + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_LECTURE
                        + RECURRING_DESC_LECTURE + TAG_DESC_LECTURE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
    }
}

package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RECURRING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_URL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_INDEX_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.RECURRING_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.URL_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.URL_DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_INDEX_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_THIRD_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.EditMeetingCommand;
import seedu.address.logic.commands.meeting.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.meeting.IsRecurring;
import seedu.address.model.meeting.MeetingDateTime;
import seedu.address.model.meeting.MeetingDuration;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingUrl;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.meeting.EditMeetingDescriptorBuilder;

public class EditMeetingCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE);
    private final EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_LECTURE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditMeetingCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_LECTURE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_LECTURE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, MeetingName.MESSAGE_CONSTRAINTS);

        // invalid url
        assertParseFailure(parser, "1" + INVALID_URL_DESC, MeetingUrl.MESSAGE_CONSTRAINTS);

        // invalid datetime
        assertParseFailure(parser, "1" + INVALID_DATETIME_DESC, MeetingDateTime.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, "1" + INVALID_DURATION_DESC, MeetingDuration.MESSAGE_CONSTRAINTS);

        // invalid module
        assertParseFailure(parser, "1" + INVALID_MODULE_INDEX_DESC,
                String.format("%s %s", "Module", ParserUtil.MESSAGE_INVALID_INDEX));

        // invalid isRecurring
        assertParseFailure(parser, "1" + INVALID_RECURRING_DESC, IsRecurring.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        final Index targetIndex = INDEX_SECOND_MEETING;
        final String userInput = targetIndex.getOneBased() + NAME_DESC_LECTURE + URL_DESC_TUTORIAL
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + MODULE_DESC_INDEX_LECTURE + RECURRING_DESC_LECTURE
                + TAG_DESC_LECTURE;

        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE)
                .withUrl(VALID_URL_TUTORIAL).withDateTime(VALID_DATETIME_LECTURE).withDuration(VALID_DURATION_LECTURE)
                .withModule(VALID_MODULE_INDEX_LECTURE).withIsRecurring(VALID_RECURRING_LECTURE)
                .withTags(VALID_TAG_LECTURE).build();
        final EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        final Index targetIndex = INDEX_FIRST_MEETING;
        final String userInput = targetIndex.getOneBased() + URL_DESC_TUTORIAL + DATETIME_DESC_LECTURE;

        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withUrl(VALID_URL_TUTORIAL)
                .withDateTime(VALID_DATETIME_LECTURE).build();
        final EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        final Index targetIndex = INDEX_THIRD_MEETING;
        String userInput = targetIndex.getOneBased() + NAME_DESC_LECTURE;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // url
        userInput = targetIndex.getOneBased() + URL_DESC_LECTURE;
        descriptor = new EditMeetingDescriptorBuilder().withUrl(VALID_URL_LECTURE).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // datetime
        userInput = targetIndex.getOneBased() + DATETIME_DESC_LECTURE;
        descriptor = new EditMeetingDescriptorBuilder().withDateTime(VALID_DATETIME_LECTURE).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = targetIndex.getOneBased() + DURATION_DESC_LECTURE;
        descriptor = new EditMeetingDescriptorBuilder().withDuration(VALID_DURATION_LECTURE).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // module
        userInput = targetIndex.getOneBased() + MODULE_DESC_INDEX_LECTURE;
        descriptor = new EditMeetingDescriptorBuilder().withModule(VALID_MODULE_INDEX_LECTURE).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // isRecurring
        userInput = targetIndex.getOneBased() + RECURRING_DESC_LECTURE;
        descriptor = new EditMeetingDescriptorBuilder().withIsRecurring(VALID_RECURRING_LECTURE).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_LECTURE;
        descriptor = new EditMeetingDescriptorBuilder().withTags(VALID_TAG_LECTURE).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        final Index targetIndex = INDEX_FIRST_MEETING;
        final String userInput = targetIndex.getOneBased() + NAME_DESC_LECTURE + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + DURATION_DESC_LECTURE + NAME_DESC_LECTURE + URL_DESC_LECTURE
                + DATETIME_DESC_LECTURE + TAG_DESC_LECTURE + NAME_DESC_TUTORIAL + URL_DESC_TUTORIAL
                + DATETIME_DESC_TUTORIAL + DURATION_DESC_TUTORIAL + TAG_DESC_TUTORIAL;

        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withName(VALID_NAME_TUTORIAL)
                .withUrl(VALID_URL_TUTORIAL).withDateTime(VALID_DATETIME_TUTORIAL)
                .withDuration(VALID_DURATION_TUTORIAL).withTags(VALID_TAG_TUTORIAL, VALID_TAG_LECTURE).build();
        final EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        final Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + INVALID_DURATION_DESC + DURATION_DESC_LECTURE;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withDuration(VALID_DURATION_LECTURE)
                .build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + NAME_DESC_LECTURE + INVALID_DURATION_DESC + DATETIME_DESC_LECTURE
                + DURATION_DESC_LECTURE;
        descriptor = new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE)
                .withDateTime(VALID_DATETIME_LECTURE).withDuration(VALID_DURATION_LECTURE).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        final Index targetIndex = INDEX_THIRD_MEETING;
        final String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTags().build();
        final EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

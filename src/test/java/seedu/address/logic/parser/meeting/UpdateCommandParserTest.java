package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AGENDA_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.AGENDA_DESC_QUARTERLY;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PLACE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AGENDA_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PLACE_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.PLACE_DESC_QUARTERLY;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_QUARTERLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGENDA_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGENDA_QUARTERLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLACE_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLACE_QUARTERLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_QUARTERLY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.UpdateCommand;
import seedu.address.model.meeting.Agenda;
import seedu.address.model.meeting.MeetingPlace;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.UpdateMeetingDescriptor;
import seedu.address.testutil.UpdateMeetingDescriptorBuilder;

public class UpdateCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE);

    private UpdateCommandParser parser = new UpdateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_AGENDA_QUARTERLY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", UpdateCommand.MESSAGE_NOT_UPDATED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_AGENDA_QUARTERLY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_AGENDA_QUARTERLY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_AGENDA_DESC, Agenda.MESSAGE_CONSTRAINTS); // invalid agenda
        assertParseFailure(parser, "1" + EMPTY_PLACE_DESC, MeetingPlace.MESSAGE_CONSTRAINTS); // invalid place
        assertParseFailure(parser, "1" + INVALID_TIME_DESC, MeetingTime.MESSAGE_CONSTRAINTS); // invalid time

        // invalid agenda followed by valid meeting place
        assertParseFailure(parser, "1" + INVALID_TIME_DESC + PLACE_DESC_QUARTERLY, MeetingTime.MESSAGE_CONSTRAINTS);

        // valid place followed by invalid agenda.
        assertParseFailure(parser, "1" + PLACE_DESC_QUARTERLY + INVALID_TIME_DESC, MeetingTime.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TIME_DESC + EMPTY_PLACE_DESC + AGENDA_DESC_QUARTERLY
                        + AGENDA_DESC_PROJECT,
                MeetingTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MEETING;
        String userInput = targetIndex.getOneBased() + AGENDA_DESC_PROJECT
                + PLACE_DESC_PROJECT + TIME_DESC_PROJECT;

        UpdateMeetingDescriptor descriptor = new UpdateMeetingDescriptorBuilder().withAgenda(VALID_AGENDA_PROJECT)
                .withPlace(VALID_PLACE_PROJECT).withTime(VALID_TIME_PROJECT).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + TIME_DESC_QUARTERLY + PLACE_DESC_QUARTERLY;

        UpdateMeetingDescriptor descriptor = new UpdateMeetingDescriptorBuilder().withTime(VALID_TIME_QUARTERLY)
                .withPlace(VALID_PLACE_QUARTERLY).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // agenda
        Index targetIndex = INDEX_THIRD_MEETING;
        String userInput = targetIndex.getOneBased() + AGENDA_DESC_PROJECT;
        UpdateMeetingDescriptor descriptor = new UpdateMeetingDescriptorBuilder().withAgenda(VALID_AGENDA_PROJECT)
                .build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // place
        userInput = targetIndex.getOneBased() + PLACE_DESC_PROJECT;
        descriptor = new UpdateMeetingDescriptorBuilder().withPlace(VALID_PLACE_PROJECT).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // time
        userInput = targetIndex.getOneBased() + TIME_DESC_QUARTERLY;
        descriptor = new UpdateMeetingDescriptorBuilder().withTime(VALID_TIME_QUARTERLY).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + AGENDA_DESC_PROJECT + AGENDA_DESC_QUARTERLY + TIME_DESC_PROJECT
                + TIME_DESC_QUARTERLY + PLACE_DESC_PROJECT + PLACE_DESC_QUARTERLY;

        UpdateMeetingDescriptor descriptor = new UpdateMeetingDescriptorBuilder().withAgenda(VALID_AGENDA_QUARTERLY)
                .withPlace(VALID_PLACE_QUARTERLY).withTime(VALID_TIME_QUARTERLY)
                .build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + INVALID_AGENDA_DESC + AGENDA_DESC_QUARTERLY;
        UpdateMeetingDescriptor descriptor = new UpdateMeetingDescriptorBuilder().withAgenda(VALID_AGENDA_QUARTERLY)
                .build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TIME_DESC_QUARTERLY + EMPTY_PLACE_DESC + AGENDA_DESC_QUARTERLY
                + PLACE_DESC_QUARTERLY;
        descriptor = new UpdateMeetingDescriptorBuilder().withTime(VALID_TIME_QUARTERLY)
                .withAgenda(VALID_AGENDA_QUARTERLY)
                .withPlace(VALID_PLACE_QUARTERLY).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}

package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AGENDA_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.ATTENDEE_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_ATTENDEE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_PLACE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AGENDA_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PAST_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PLACE_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGENDA_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLACE_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_PROJECT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.MeetCommand;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingPlace;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.testutil.MeetingBuilder;

public class MeetCommandParserTest {
    private MeetCommandParser meetCommandParser = new MeetCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Meeting expectedMeeting = new MeetingBuilder()
                .withAgenda(VALID_AGENDA_PROJECT)
                .withTime(VALID_TIME_PROJECT)
                .withPlace(VALID_PLACE_PROJECT)
                .build();
        assertParseSuccess(meetCommandParser, ATTENDEE_DESC_PROJECT
                + AGENDA_DESC_PROJECT + PLACE_DESC_PROJECT + TIME_DESC_PROJECT, new MeetCommand(expectedMeeting));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // Missing agenda
        assertParseFailure(meetCommandParser, ATTENDEE_DESC_PROJECT + INVALID_AGENDA_DESC
                        + PLACE_DESC_PROJECT + TIME_DESC_PROJECT, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MeetCommand.AGENDA_CANNOT_BE_EMPTY_MESSAGE));

        // Missing time
        assertParseFailure(meetCommandParser, ATTENDEE_DESC_PROJECT + AGENDA_DESC_PROJECT
                + PLACE_DESC_PROJECT + EMPTY_TIME_DESC, MeetingTime.MESSAGE_CONSTRAINTS);

        // Missing place
        assertParseFailure(meetCommandParser, ATTENDEE_DESC_PROJECT + AGENDA_DESC_PROJECT
                + EMPTY_PLACE_DESC + TIME_DESC_PROJECT, MeetingPlace.MESSAGE_CONSTRAINTS);

        // Missing attendees
        assertParseFailure(meetCommandParser, EMPTY_ATTENDEE_DESC + AGENDA_DESC_PROJECT
                + PLACE_DESC_PROJECT + TIME_DESC_PROJECT, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MeetCommand.INDEX_CANNOT_BE_EMPTY_MESSAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid time
        assertParseFailure(meetCommandParser, ATTENDEE_DESC_PROJECT + AGENDA_DESC_PROJECT
                        + PLACE_DESC_PROJECT + INVALID_TIME_DESC,
                MeetingTime.MESSAGE_CONSTRAINTS);

        // Past time
        assertParseFailure(meetCommandParser, ATTENDEE_DESC_PROJECT + AGENDA_DESC_PROJECT
                        + PLACE_DESC_PROJECT + PAST_TIME_DESC,
                MeetingTime.MESSAGE_FUTURE_CONSTRAINT);

    }
}

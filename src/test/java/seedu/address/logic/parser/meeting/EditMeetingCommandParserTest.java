package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATETIME_INPUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATETIME_INPUT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.EditMeetingCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditMeetingCommandParserTest {

    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor = new EditMeetingCommand.EditMeetingDescriptor();
        editMeetingDescriptor.setStartDateTime(ParserUtil.parseDateTime(VALID_START_DATETIME_INPUT));
        editMeetingDescriptor.setEndDateTime(ParserUtil.parseDateTime(VALID_END_DATETIME_INPUT));
        editMeetingDescriptor.setLabel(VALID_LABEL);

        assertParseSuccess(parser,
                VALID_MEETING_INDEX
                        + " " + PREFIX_START_DATETIME + VALID_START_DATETIME_INPUT
                        + " " + PREFIX_END_DATETIME + VALID_END_DATETIME_INPUT
                        + " " + PREFIX_LABEL + VALID_LABEL,
                new EditMeetingCommand(INDEX_FIRST_MEETING, editMeetingDescriptor));
    }

    @Test
    public void parse_invalidArgs_returnsEditMeetingCommand() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE));
    }
}

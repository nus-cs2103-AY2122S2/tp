package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalInterviews.TYPICAL_INTERVIEW_DATE_TIME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedule.EditScheduleCommand;
import seedu.address.logic.commands.schedule.ScheduleCommand;
import seedu.address.logic.parser.schedule.EditScheduleCommandParser;

public class EditScheduleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditScheduleCommand.MESSAGE_USAGE);
    private EditScheduleCommandParser parser = new EditScheduleCommandParser();


    @Test
    public void parse_validArgs_returnsEditScheduleCommand() {
        assertParseSuccess(parser, "1" + VALID_INTERVIEW_DATE_TIME,
                new EditScheduleCommand(INDEX_FIRST_INTERVIEW, TYPICAL_INTERVIEW_DATE_TIME));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_INTERVIEW_DATE_TIME, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", String.format(EditScheduleCommand.MESSAGE_NOT_EDITED));

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingPrefix_throwsParseException() {
        assertParseFailure(parser, "1", String.format(EditScheduleCommand.MESSAGE_NOT_EDITED));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_INTERVIEW_DATE_TIME, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + "20/02/2022 6:00", ScheduleCommand.MESSAGE_INVALID_FORMAT_DATETIME);
    }
}

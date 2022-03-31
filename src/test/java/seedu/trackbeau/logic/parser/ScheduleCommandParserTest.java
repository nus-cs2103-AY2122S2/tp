package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.trackbeau.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_DATE1;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_DATE2;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_REG_DATE_AMY;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.ScheduleCommand;

public class ScheduleCommandParserTest {
    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_validDate_success() {
        LocalDate expectedDate = LocalDate.parse(VALID_REG_DATE_AMY, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_DATE1, new ScheduleCommand(expectedDate));

        // multiple dates - last date accepted
        assertParseSuccess(parser, VALID_DATE2 + VALID_DATE1, new ScheduleCommand(expectedDate));
    }

    @Test
    public void parse_missingDate_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " ", expectedMessage);
    }

    @Test
    public void parse_invalidDate_failure() {
        assertParseFailure(parser, INVALID_DATE, parser.MESSAGE_CONSTRAINT);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_DATE1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
    }
}

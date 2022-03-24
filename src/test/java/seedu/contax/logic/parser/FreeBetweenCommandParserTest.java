package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_NAME_ALONE;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DURATION;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_TIME;
import static seedu.contax.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DURATION_HOUR;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DURATION_MINUTE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_DATE2;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TIME2;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_START;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.util.DateUtil;
import seedu.contax.logic.commands.FreeBetweenCommand;
import seedu.contax.model.appointment.Duration;

public class FreeBetweenCommandParserTest {

    private static final String INPUT_START_DATE = " " + PREFIX_DATE_START + VALID_DATE2;
    private static final String INPUT_END_DATE = " " + PREFIX_DATE_END + VALID_DATE;
    private static final String INPUT_START_TIME = " " + PREFIX_TIME_START + VALID_TIME2;
    private static final String INPUT_END_TIME = " " + PREFIX_TIME_END + VALID_TIME;
    private static final String INPUT_DURATION = " " + PREFIX_DURATION + VALID_APPOINTMENT_DURATION_HOUR;

    private FreeBetweenCommandParser parser = new FreeBetweenCommandParser();

    @Test
    public void parse_compulsoryFieldsPresent_success() {
        LocalDateTime expectedStart = DateUtil.combineDateTime(
                DateUtil.parseDate(VALID_DATE2).get(), DateUtil.parseTime(VALID_TIME2).get());
        LocalDateTime expectedEnd = DateUtil.combineDateTime(
                DateUtil.parseDate(VALID_DATE).get(), DateUtil.parseTime(VALID_TIME).get());
        FreeBetweenCommand expectedCommand = new FreeBetweenCommand(expectedStart, expectedEnd, 60);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INPUT_START_DATE + INPUT_START_TIME
                + INPUT_END_DATE + INPUT_END_TIME + INPUT_DURATION, expectedCommand);

        // duplicate start date
        assertParseSuccess(parser, " " + PREFIX_DATE_START + VALID_DATE + INPUT_START_DATE
                        + INPUT_START_TIME + INPUT_END_DATE + INPUT_END_TIME + INPUT_DURATION, expectedCommand);

        // duplicate start time
        assertParseSuccess(parser, INPUT_START_DATE + " " + PREFIX_TIME_START + VALID_TIME
                        + INPUT_START_TIME + INPUT_END_DATE + INPUT_END_TIME + INPUT_DURATION, expectedCommand);

        // duplicate end date
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_START_TIME
                        + " " + PREFIX_DATE_END + VALID_DATE2 + INPUT_END_DATE + INPUT_END_TIME
                        + INPUT_DURATION, expectedCommand);

        // duplicate end time
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_START_TIME
                        + INPUT_END_DATE + " " + PREFIX_TIME_END + VALID_TIME2 + INPUT_END_TIME
                        + INPUT_DURATION, expectedCommand);

        // duplicate duration
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_START_TIME
                + INPUT_END_DATE + INPUT_END_TIME + " " + PREFIX_DURATION + VALID_APPOINTMENT_DURATION_MINUTE
                + INPUT_DURATION, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FreeBetweenCommand.MESSAGE_USAGE);

        // missing fields
        assertParseFailure(parser, INPUT_START_TIME + INPUT_END_DATE + INPUT_END_TIME
                + INPUT_DURATION, expectedMessage);
        assertParseFailure(parser, INPUT_START_DATE + INPUT_END_DATE + INPUT_END_TIME
                + INPUT_DURATION, expectedMessage);
        assertParseFailure(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_TIME
                + INPUT_DURATION, expectedMessage);
        assertParseFailure(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE
                + INPUT_DURATION, expectedMessage);
        assertParseFailure(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE
                + INPUT_END_TIME, expectedMessage);

        // all fields missing
        assertParseFailure(parser, APPOINTMENT_NAME_ALONE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid start date
        assertParseFailure(parser, " " + PREFIX_DATE_START + INVALID_DATE + INPUT_START_TIME
                + INPUT_END_DATE + INPUT_END_TIME + INPUT_DURATION,
                FreeBetweenCommand.MESSAGE_START_DATE_INVALID);

        // invalid start time
        assertParseFailure(parser, INPUT_START_DATE + " " + PREFIX_TIME_START + INVALID_TIME
                + INPUT_END_DATE + INPUT_END_TIME + INPUT_DURATION,
                FreeBetweenCommand.MESSAGE_START_TIME_INVALID);

        // invalid end date
        assertParseFailure(parser, INPUT_START_DATE + INPUT_START_TIME + " " + PREFIX_DATE_END
                + INVALID_DATE + " " + INPUT_END_TIME + INPUT_DURATION,
                FreeBetweenCommand.MESSAGE_END_DATE_INVALID);

        // invalid start time
        assertParseFailure(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE
                + " " + PREFIX_TIME_END + INVALID_TIME + INPUT_DURATION,
                FreeBetweenCommand.MESSAGE_END_TIME_INVALID);

        // invalid duration
        assertParseFailure(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE
                + INPUT_END_TIME + " " + PREFIX_DURATION + INVALID_APPOINTMENT_DURATION,
                Duration.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_duplicatedInvalidValid_success() {
        LocalDateTime expectedStart = DateUtil.combineDateTime(
                DateUtil.parseDate(VALID_DATE2).get(), DateUtil.parseTime(VALID_TIME2).get());
        LocalDateTime expectedEnd = DateUtil.combineDateTime(
                DateUtil.parseDate(VALID_DATE).get(), DateUtil.parseTime(VALID_TIME).get());
        FreeBetweenCommand expectedCommand = new FreeBetweenCommand(expectedStart, expectedEnd, 60);

        assertParseSuccess(parser, " " + PREFIX_DATE_START + INVALID_DATE + INPUT_START_DATE
                + INPUT_START_TIME + INPUT_END_DATE + INPUT_END_TIME + INPUT_DURATION, expectedCommand);
        assertParseSuccess(parser, INPUT_START_DATE + " " + PREFIX_TIME_START + INVALID_TIME
                + INPUT_START_TIME + INPUT_END_DATE + INPUT_END_TIME + INPUT_DURATION, expectedCommand);
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_START_TIME + " " + PREFIX_DATE_END
                + INVALID_DATE + INPUT_END_DATE + INPUT_END_TIME + INPUT_DURATION, expectedCommand);
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE
                + " " + PREFIX_TIME_END + INVALID_TIME + INPUT_END_TIME + INPUT_DURATION, expectedCommand);
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE
                + INPUT_END_TIME + " " + PREFIX_DURATION + INVALID_APPOINTMENT_DURATION + INPUT_DURATION,
                expectedCommand);
    }

    @Test
    public void parse_duplicatedValidInvalid_failure() {
        assertParseFailure(parser, INPUT_START_DATE + PREFIX_DATE_START + INVALID_DATE + " "
                + INPUT_START_TIME + INPUT_END_DATE + INPUT_END_TIME + INPUT_DURATION,
                FreeBetweenCommand.MESSAGE_START_DATE_INVALID);

        assertParseFailure(parser, INPUT_START_DATE + INPUT_START_TIME
                + " " + PREFIX_TIME_START + INVALID_TIME + INPUT_END_DATE + INPUT_END_TIME + INPUT_DURATION,
                FreeBetweenCommand.MESSAGE_START_TIME_INVALID);

        assertParseFailure(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE
                + " " + PREFIX_DATE_END + INVALID_DATE + " " + INPUT_END_TIME + INPUT_DURATION,
                FreeBetweenCommand.MESSAGE_END_DATE_INVALID);

        assertParseFailure(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE
                + INPUT_END_TIME + " " + PREFIX_TIME_END + INVALID_TIME + INPUT_DURATION,
                FreeBetweenCommand.MESSAGE_END_TIME_INVALID);

        assertParseFailure(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE
                + INPUT_END_TIME + INPUT_DURATION + " " + PREFIX_DURATION + INVALID_APPOINTMENT_DURATION,
                Duration.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_endBeforeStart_failure() {
        String startDate = " " + PREFIX_DATE_START + VALID_DATE;
        String endDate = " " + PREFIX_DATE_END + VALID_DATE2;
        String startTime = " " + PREFIX_TIME_START + VALID_TIME;
        String endTime = " " + PREFIX_TIME_END + VALID_TIME2;

        assertParseFailure(parser, startDate + startTime + endDate + endTime + INPUT_DURATION,
                FreeBetweenCommand.MESSAGE_END_BEFORE_START);
    }

    @Test
    public void parse_sameStartAndEnd_success() {
        String startDate = " " + PREFIX_DATE_START + VALID_DATE;
        String endDate = " " + PREFIX_DATE_END + VALID_DATE;
        String startTime = " " + PREFIX_TIME_START + VALID_TIME;
        String endTime = " " + PREFIX_TIME_END + VALID_TIME;
        LocalDateTime expectedStartEnd = DateUtil.combineDateTime(
                DateUtil.parseDate(VALID_DATE).get(), DateUtil.parseTime(VALID_TIME).get());

        assertParseSuccess(parser, startDate + startTime + endDate + endTime + INPUT_DURATION,
                new FreeBetweenCommand(expectedStartEnd, expectedStartEnd, 60));
    }
}

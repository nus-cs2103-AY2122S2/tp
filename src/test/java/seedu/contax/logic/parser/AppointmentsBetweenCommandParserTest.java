package seedu.contax.logic.parser;

import static seedu.contax.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_TIME;
import static seedu.contax.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_DATE2;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TIME2;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_START;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.util.DateUtil;
import seedu.contax.logic.commands.AppointmentsBetweenCommand;

public class AppointmentsBetweenCommandParserTest {

    private static final String INPUT_START_DATE = " " + PREFIX_DATE_START + VALID_DATE2;
    private static final String INPUT_END_DATE = " " + PREFIX_DATE_END + VALID_DATE;
    private static final String INPUT_START_TIME = " " + PREFIX_TIME_START + VALID_TIME2;
    private static final String INPUT_END_TIME = " " + PREFIX_TIME_END + VALID_TIME;

    private AppointmentsBetweenCommandParser parser = new AppointmentsBetweenCommandParser();

    @Test
    public void parse_defaultValuesOnly_success() {
        LocalDateTime expectedStart = LocalDate.now().atTime(0, 0);
        LocalDateTime expectedEnd = LocalDate.MAX.atTime(23, 59);
        AppointmentsBetweenCommand expectedCommand = new AppointmentsBetweenCommand(expectedStart, expectedEnd);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE, expectedCommand);

        // No Arguments
        assertParseSuccess(parser, "", expectedCommand);
    }

    @Test
    public void parse_optionalFieldsPresent_success() {
        LocalDate defaultStartDate = LocalDate.now();
        LocalDate defaultEndDate = LocalDate.MAX;
        LocalTime defaultEndTime = LocalTime.of(23, 59);
        LocalDateTime defaultEnd = DateUtil.combineDateTime(defaultEndDate, defaultEndTime);
        LocalDate modifiedStartDate = DateUtil.parseDate(VALID_DATE2).get();
        LocalTime modifiedStartTime = DateUtil.parseTime(VALID_TIME2).get();
        LocalDate modifiedEndDate = DateUtil.parseDate(VALID_DATE).get();
        LocalTime modifiedEndTime = DateUtil.parseTime(VALID_TIME).get();

        // Change start. Default values for start date/time tested in compulsory field test
        assertParseSuccess(parser, INPUT_START_DATE,
                new AppointmentsBetweenCommand(modifiedStartDate.atStartOfDay(), defaultEnd));
        assertParseSuccess(parser, INPUT_START_TIME,
                new AppointmentsBetweenCommand(DateUtil.combineDateTime(defaultStartDate, modifiedStartTime),
                        defaultEnd));
        assertParseSuccess(parser, INPUT_START_TIME + INPUT_START_DATE,
                new AppointmentsBetweenCommand(DateUtil.combineDateTime(modifiedStartDate, modifiedStartTime),
                        defaultEnd));

        // Change end
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_END_DATE,
                new AppointmentsBetweenCommand(modifiedStartDate.atStartOfDay(),
                        DateUtil.combineDateTime(modifiedEndDate, defaultEndTime)));
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_END_DATE + INPUT_END_TIME,
                new AppointmentsBetweenCommand(modifiedStartDate.atStartOfDay(),
                        DateUtil.combineDateTime(modifiedEndDate, modifiedEndTime)));

        // All fields present
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE + INPUT_END_TIME,
                new AppointmentsBetweenCommand(DateUtil.combineDateTime(modifiedStartDate, modifiedStartTime),
                        DateUtil.combineDateTime(modifiedEndDate, modifiedEndTime)));
    }

    @Test
    public void parse_endTimeWithoutEndDate_throwsParseException() {
        String expectedMessage = AppointmentsBetweenCommand.MESSAGE_END_TIME_WITHOUT_DATE;
        assertParseFailure(parser, INPUT_END_TIME, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid start date
        assertParseFailure(parser, " " + PREFIX_DATE_START + INVALID_DATE,
                AppointmentsBetweenCommand.MESSAGE_START_DATE_INVALID);

        // invalid start time
        assertParseFailure(parser, " " + PREFIX_TIME_START + INVALID_TIME,
                AppointmentsBetweenCommand.MESSAGE_START_TIME_INVALID);

        // invalid end date
        assertParseFailure(parser, " " + PREFIX_DATE_END + INVALID_DATE,
                AppointmentsBetweenCommand.MESSAGE_END_DATE_INVALID);

        // invalid end time, which must be specified with some end date
        assertParseFailure(parser, INPUT_END_DATE + " " + PREFIX_TIME_END + INVALID_TIME,
                AppointmentsBetweenCommand.MESSAGE_END_TIME_INVALID);
    }

    @Test
    public void parse_duplicatedInvalidValid_success() {
        LocalDateTime expectedStart = DateUtil.combineDateTime(
                DateUtil.parseDate(VALID_DATE2).get(), DateUtil.parseTime(VALID_TIME2).get());
        LocalDateTime expectedEnd = DateUtil.combineDateTime(
                DateUtil.parseDate(VALID_DATE).get(), DateUtil.parseTime(VALID_TIME).get());
        AppointmentsBetweenCommand expectedCommand = new AppointmentsBetweenCommand(expectedStart, expectedEnd);

        assertParseSuccess(parser, " " + PREFIX_DATE_START + INVALID_DATE + INPUT_START_DATE
                + INPUT_START_TIME + INPUT_END_DATE + INPUT_END_TIME, expectedCommand);
        assertParseSuccess(parser, INPUT_START_DATE + " " + PREFIX_TIME_START + INVALID_TIME
                + INPUT_START_TIME + INPUT_END_DATE + INPUT_END_TIME, expectedCommand);
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_START_TIME + " " + PREFIX_DATE_END
                + INVALID_DATE + INPUT_END_DATE + INPUT_END_TIME, expectedCommand);
        assertParseSuccess(parser, INPUT_START_DATE + INPUT_START_TIME + INPUT_END_DATE
                + " " + PREFIX_TIME_END + INVALID_TIME + INPUT_END_TIME, expectedCommand);
    }

    @Test
    public void parse_duplicatedValidInvalid_failure() {
        assertParseFailure(parser, INPUT_START_DATE + " " + PREFIX_DATE_START + INVALID_DATE,
                AppointmentsBetweenCommand.MESSAGE_START_DATE_INVALID);

        assertParseFailure(parser, INPUT_START_TIME + " " + PREFIX_TIME_START + INVALID_TIME,
                AppointmentsBetweenCommand.MESSAGE_START_TIME_INVALID);

        assertParseFailure(parser, INPUT_END_DATE + " " + PREFIX_DATE_END + INVALID_DATE,
                AppointmentsBetweenCommand.MESSAGE_END_DATE_INVALID);

        // End time must be specified with some end date
        assertParseFailure(parser, INPUT_END_DATE + INPUT_END_TIME + " " + PREFIX_TIME_END + INVALID_TIME,
                AppointmentsBetweenCommand.MESSAGE_END_TIME_INVALID);
    }

    @Test
    public void parse_endBeforeStart_failure() {
        String startDate = " " + PREFIX_DATE_START + VALID_DATE;
        String endDate = " " + PREFIX_DATE_END + VALID_DATE2;
        String startTime = " " + PREFIX_TIME_START + VALID_TIME;
        String endTime = " " + PREFIX_TIME_END + VALID_TIME2;

        assertParseFailure(parser, startDate + startTime + endDate + endTime,
                AppointmentsBetweenCommand.MESSAGE_END_BEFORE_START);
    }

    @Test
    public void parse_sameStartAndEnd_success() {
        String startDate = " " + PREFIX_DATE_START + VALID_DATE;
        String endDate = " " + PREFIX_DATE_END + VALID_DATE;
        String startTime = " " + PREFIX_TIME_START + VALID_TIME;
        String endTime = " " + PREFIX_TIME_END + VALID_TIME;
        LocalDateTime expectedStartEnd = DateUtil.combineDateTime(
                DateUtil.parseDate(VALID_DATE).get(), DateUtil.parseTime(VALID_TIME).get());

        assertParseSuccess(parser, startDate + startTime + endDate + endTime,
                new AppointmentsBetweenCommand(expectedStartEnd, expectedStartEnd));
    }
}

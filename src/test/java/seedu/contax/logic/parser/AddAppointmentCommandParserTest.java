package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_DATE;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_DATE2;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_DURATION;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_DURATION2;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_FIRST_PERSON;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_NAME_ALONE;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_NAME_AMELIA;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_SECOND_PERSON;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_TIME;
import static seedu.contax.logic.commands.CommandTestUtil.APPOINTMENT_TIME2;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DURATION_DESC;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_NAME_DESC;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_PERSON;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.contax.logic.commands.CommandTestUtil.INVALID_TIME_DESC;
import static seedu.contax.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DURATION_HOUR;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME_ALONE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.AddAppointmentCommand;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.Name;
import seedu.contax.model.appointment.StartDateTime;
import seedu.contax.testutil.AppointmentBuilder;
import seedu.contax.testutil.TypicalIndexes;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_compulsoryFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder(APPOINTMENT_ALONE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE
                + APPOINTMENT_TIME + APPOINTMENT_DURATION,
                new AddAppointmentCommand(expectedAppointment, null));

        // multiple names - last name accepted
        assertParseSuccess(parser, APPOINTMENT_NAME_AMELIA + APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE
                + APPOINTMENT_TIME + APPOINTMENT_DURATION,
                new AddAppointmentCommand(expectedAppointment, null));

        // multiple dates - last date accepted
        assertParseSuccess(parser, APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE2 + APPOINTMENT_DATE
                + APPOINTMENT_TIME + APPOINTMENT_DURATION,
                new AddAppointmentCommand(expectedAppointment, null));

        // multiple times - last time accepted
        assertParseSuccess(parser, APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE + APPOINTMENT_TIME2
                        + APPOINTMENT_TIME + APPOINTMENT_DURATION,
                new AddAppointmentCommand(expectedAppointment, null));

        // multiple durations - last duration accepted
        assertParseSuccess(parser, APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE + APPOINTMENT_TIME
                        + APPOINTMENT_DURATION2 + APPOINTMENT_DURATION,
                new AddAppointmentCommand(expectedAppointment, null));

    }

    @Test
    public void parse_optionalFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder(APPOINTMENT_ALONE).build();

        // include a person index
        assertParseSuccess(parser, APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE
                        + APPOINTMENT_TIME + APPOINTMENT_DURATION + APPOINTMENT_FIRST_PERSON,
                new AddAppointmentCommand(expectedAppointment, TypicalIndexes.INDEX_FIRST_PERSON));

        // multiple persons - last person accepted
        assertParseSuccess(parser, APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE + APPOINTMENT_TIME
                        + APPOINTMENT_DURATION + APPOINTMENT_FIRST_PERSON + APPOINTMENT_SECOND_PERSON,
                new AddAppointmentCommand(expectedAppointment, TypicalIndexes.INDEX_SECOND_PERSON));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE + APPOINTMENT_TIME
                        + APPOINTMENT_DURATION, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, APPOINTMENT_NAME_ALONE + VALID_DATE + APPOINTMENT_TIME
                + APPOINTMENT_DURATION, expectedMessage);

        // missing time prefix
        assertParseFailure(parser, APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE + VALID_TIME
                + APPOINTMENT_DURATION, expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE + APPOINTMENT_TIME
                + VALID_APPOINTMENT_DURATION_HOUR, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_APPOINTMENT_NAME_ALONE + VALID_DATE
                + VALID_TIME + VALID_APPOINTMENT_DURATION_HOUR, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_APPOINTMENT_NAME_DESC + APPOINTMENT_DATE + APPOINTMENT_TIME
                + APPOINTMENT_DURATION, Name.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, APPOINTMENT_NAME_ALONE + INVALID_DATE_DESC + APPOINTMENT_TIME
                + APPOINTMENT_DURATION, StartDateTime.MESSAGE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE + INVALID_TIME_DESC
                + APPOINTMENT_DURATION, StartDateTime.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE + APPOINTMENT_TIME
                + INVALID_APPOINTMENT_DURATION_DESC, Duration.MESSAGE_CONSTRAINTS);

        // invalid person index
        assertParseFailure(parser, APPOINTMENT_NAME_ALONE + APPOINTMENT_DATE + APPOINTMENT_TIME
                + APPOINTMENT_DURATION + INVALID_APPOINTMENT_PERSON, ParserUtil.MESSAGE_INVALID_INDEX);
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_APPOINTMENT1;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_APPOINTMENT2;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPOINTMENT2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Description;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppointment = new AppointmentBuilder(APPOINTMENT1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DESC_APPOINTMENT1 + DATETIME_APPOINTMENT1,
                new AddAppointmentCommand(expectedAppointment));

        // multiple description - last description accepted
        assertParseSuccess(parser,
                DESC_APPOINTMENT2 + DESC_APPOINTMENT1 + DATETIME_APPOINTMENT1,
                new AddAppointmentCommand(expectedAppointment));

        // multiple datetime - last datetime accepted
        assertParseSuccess(parser, DESC_APPOINTMENT1 + DATETIME_APPOINTMENT2 + DATETIME_APPOINTMENT1,
                new AddAppointmentCommand(expectedAppointment));
    }
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // missing description
        assertParseFailure(parser, DATETIME_APPOINTMENT1, expectedMessage);

        // missing datetime
        assertParseFailure(parser, DESC_APPOINTMENT1, expectedMessage);
    }
    @Test
    public void parse_invalidValue_failure() {
        // invalid datetime
        assertParseFailure(parser, DESC_APPOINTMENT1 + INVALID_DATETIME_APPOINTMENT,
                DateTime.MESSAGE_CONSTRAINTS);
        // invalid description
        assertParseFailure(parser, INVALID_DESC_APPOINTMENT + DATETIME_APPOINTMENT1,
                Description.MESSAGE_CONSTRAINTS);
    }
}

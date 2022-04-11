package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AppointmentBuyerCommand.MESSAGE_TIME_IN_PAST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AppointmentBuyerCommand;
import seedu.address.model.client.Appointment;

public class AppointmentBuyerCommandParserTest {

    private static final String VALID_APPOINTMENT_STRING = "2025-09-09-09-09";
    private static final String PAST_APPOINTMENT_STRING = "2019-09-09-09-09";
    private static final String APPOINTMENT_RESET_STRING = "reset";
    private static final Appointment VALID_APPOINTMENT = new Appointment(VALID_APPOINTMENT_STRING);
    private static final Appointment VALID_EMPTY_APPOINTMENT = new Appointment("");

    private AppointmentBuyerCommandParser parser = new AppointmentBuyerCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppointmentBuyerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppointmentBuyerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String index = "1 ";
        //Missing date input
        assertParseFailure(parser, index + PREFIX_APPOINTMENT, Appointment.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        String index = "1 ";
        Index stubIndex = Index.fromOneBased(1);
        assertParseSuccess(parser, index + PREFIX_APPOINTMENT + VALID_APPOINTMENT_STRING,
                new AppointmentBuyerCommand(stubIndex, VALID_APPOINTMENT));
    }

    @Test
    public void parse_reset_success() {
        String index = "1 ";
        Index stubIndex = Index.fromOneBased(1);
        assertParseSuccess(parser, index + PREFIX_APPOINTMENT + APPOINTMENT_RESET_STRING,
                new AppointmentBuyerCommand(stubIndex, VALID_EMPTY_APPOINTMENT));
    }


    @Test
    public void parse_dateInPast_failure() {
        String index = "1 ";
        Index stubIndex = Index.fromOneBased(1);
        assertParseFailure(parser, index + PREFIX_APPOINTMENT + PAST_APPOINTMENT_STRING,
                MESSAGE_TIME_IN_PAST);
    }
}

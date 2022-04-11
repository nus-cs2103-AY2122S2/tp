package woofareyou.logic.parser;

import static woofareyou.commons.core.Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX;
import static woofareyou.logic.commands.CommandTestUtil.APPT_DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_APPT_DATE_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_APPT_TIME_AMY;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseFailure;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.junit.jupiter.api.Test;

import woofareyou.logic.commands.AppointmentCommand;
import woofareyou.model.pet.Appointment;

public class AppointmentCommandParserTest {

    private static final String NEG_INTEGER_MAX = String.valueOf(-(Integer.MAX_VALUE + 1));
    private static final String POS_INTEGER_MAX = String.valueOf(Integer.MAX_VALUE + 1);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    private AppointmentCommandParser parser = new AppointmentCommandParser();


    @Test
    public void parse_indexOutOfBoundsIntegerOverflow_throwsParseException() {
        // large positive number
        assertParseFailure(parser, POS_INTEGER_MAX + APPT_DESC_AMY, MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        // large negative number
        assertParseFailure(parser, NEG_INTEGER_MAX + APPT_DESC_AMY, MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }


    @Test
    public void parse_validArgs_returnsApptCommand() {
        String userInput = INDEX_FIRST_PET.getOneBased() + APPT_DESC_AMY;
        LocalDateTime dateTime = LocalDateTime.parse(VALID_APPT_DATE_AMY + " " + VALID_APPT_TIME_AMY,
                dateFormatter);
        Appointment newApp = new Appointment(dateTime, VALID_ADDRESS_AMY);
        assertParseSuccess(parser, userInput, new AppointmentCommand(INDEX_FIRST_PET, newApp));
    }
}

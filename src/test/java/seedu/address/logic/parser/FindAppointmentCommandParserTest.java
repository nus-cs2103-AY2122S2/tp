package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAppointmentCommand;
import seedu.address.model.appointment.DescriptionContainsKeywordsPredicate;

public class FindAppointmentCommandParserTest {
    private FindAppointmentCommandParser parser = new FindAppointmentCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindAppointmentCommand expectedFindAppointmentCommand =
                new FindAppointmentCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList("Meet", "UTown")));
        assertParseSuccess(parser, "Meet UTown", expectedFindAppointmentCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Meet \n \t UTown  \t", expectedFindAppointmentCommand);
    }
}

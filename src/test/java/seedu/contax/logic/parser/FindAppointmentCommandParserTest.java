package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.FindAppointmentCommand;
import seedu.contax.model.appointment.NameContainsKeywordsPredicate;
import seedu.contax.model.appointment.PersonNameContainsKeywordsPredicate;


public class FindAppointmentCommandParserTest {
    private FindAppointmentCommandParser parser = new FindAppointmentCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindAppointmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSearchType_throwsParseException() {
        // multiple whitespaces between keywords
        assertParseFailure(parser, "\n John \n \t Bob  \t by/" + "phone", "Search type should only be person");
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindAppointmentCommand expectedFindPersonCommand =
                new FindAppointmentCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindPersonCommand);
    }

    @Test
    public void parse_findByName_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindAppointmentCommand expectedFindPersonCommand =
                new FindAppointmentCommand(new NameContainsKeywordsPredicate(Arrays.asList("John", "Bob")));
        assertParseSuccess(parser, "John Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n John \n \t Bob  \t", expectedFindPersonCommand);
    }

    @Test
    public void parse_findByClientName_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindAppointmentCommand expectedFindPersonCommand =
                new FindAppointmentCommand(new PersonNameContainsKeywordsPredicate(Arrays.asList("John", "Bob")));
        assertParseSuccess(parser, "John Bob by/" + "person", expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n John \n \t Bob  \t by/" + "person", expectedFindPersonCommand);
    }
}

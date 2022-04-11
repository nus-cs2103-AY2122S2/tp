package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.datewithouttime.DateWithoutTime;
import seedu.address.logic.commands.ListProcOnCommand;

public class ListProcOnCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DateWithoutTime.MESSAGE_CONSTRAINTS);

    private ListProcOnCommandParser parser = new ListProcOnCommandParser();

    @Test
    public void parse_validArgs_success() {
        // A valid date format
        String userInput = "30/03/2022";
        ListProcOnCommand expectedCommand = new ListProcOnCommand(new DateWithoutTime("30/03/2022"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_inValidArgs_failure() {
        // EP: an empty string
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        // EP: a blank string
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
        // EP: a string filled with texts
        assertParseFailure(parser, "texts", MESSAGE_INVALID_FORMAT);
        // EP: a string of integers
        assertParseFailure(parser, "123", MESSAGE_INVALID_FORMAT);
        // EP: an abnormal date
        assertParseFailure(parser, "32/03/2022", MESSAGE_INVALID_FORMAT);
        // EP: a date with time
        assertParseFailure(parser, "32/03/2022 11:00", MESSAGE_INVALID_FORMAT);
    }
}

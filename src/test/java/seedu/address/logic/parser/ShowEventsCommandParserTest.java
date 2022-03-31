package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowEventsCommand;

public class ShowEventsCommandParserTest {
    private ShowEventsCommandParser parser = new ShowEventsCommandParser();

    @Test
    public void parse_validArgument_returnsShowEventsCommand() {
        ShowEventsCommand showEventsCommand = new ShowEventsCommand(true);

        assertParseSuccess(parser, " -a", showEventsCommand);
    }

    @Test
    public void parse_validNoArgument_returnsShowEventsCommand() {
        ShowEventsCommand showEventsCommand = new ShowEventsCommand(false);

        assertParseSuccess(parser, "", showEventsCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //random input of special characters
        assertParseFailure(parser, "@%^", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowEventsCommand.MESSAGE_USAGE));
    }
}

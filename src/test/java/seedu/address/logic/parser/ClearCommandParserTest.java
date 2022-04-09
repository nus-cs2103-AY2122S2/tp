package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;

public class ClearCommandParserTest {

    private ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_emptyArg_returnsClearCommand() {
        ClearCommand expectedCommand = new ClearCommand();
        assertParseSuccess(parser, "clear", expectedCommand);
    }

    @Test
    public void parse_notEmptyArg_throwsParseException() {
        assertParseFailure(parser, "clear ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "clear  abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "  clear",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
    }

}

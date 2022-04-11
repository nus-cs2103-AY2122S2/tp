package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindOrderCommand;

public class FindOrderCommandParserTest {

    private FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleAttributes_throwsParseException() {
        assertParseFailure(parser, " n/Alice p/12345678", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindOrderCommand.MESSAGE_USAGE));
    }

}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewImageCommand;


public class ViewImageCommandParserTest {

    private ViewImageCommandParser parser = new ViewImageCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "  ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewImageCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewImageCommand() {
        assertParseSuccess(parser, "1", new ViewImageCommand(INDEX_FIRST_PERSON));
    }
}

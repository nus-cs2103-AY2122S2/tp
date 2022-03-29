package manageezpz.logic.parser;

import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.ListCommand;

class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();
    private ListCommand listCommand = new ListCommand();

    @Test
    void listCommandParser_noOptions_listCommand() {
        assertParseSuccess(parser, "", listCommand);
    }

    @Test
    void listCommandParser_haveOptions_listCommand() {
        assertParseSuccess(parser, "1", listCommand);
    }
}

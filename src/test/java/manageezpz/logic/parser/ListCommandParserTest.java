package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_LIST_MULTIPLE_ARGUMENTS;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_NAME;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import manageezpz.logic.commands.ListCommand;

class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();
    private String expectedInvalidArgumentMessage
            = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_INVALID_ARGUMENTS);

    @Test
    void listCommandParser_multipleArguments_parseExceptionThrown() {
        assertParseFailure(parser, INVALID_LIST_MULTIPLE_ARGUMENTS, expectedInvalidArgumentMessage);
    }

    @Test
    void listCommandParser_invalidArgument_parseExceptionThrown() {
        assertParseFailure(parser, PREFIX_NAME.toString(), expectedInvalidArgumentMessage);
    }

    @Test
    void listCommandParser_argumentWithValues_parseExceptionThrown() {
        assertParseFailure(parser, PREFIX_EVENT + "Some Value", expectedInvalidArgumentMessage);
    }
}
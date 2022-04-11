package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewStudentInfoCommand;

/**
 * Test cases for the parser ViewStudentInfoCommandParser
 */
public class ViewStudentInfoCommandParserTest {

    private ViewStudentInfoCommandParser parser = new ViewStudentInfoCommandParser();

    @Test
    public void parse_validArgs_returnsViewStudentInfoCommand() {
        assertParseSuccess(parser, "1", new ViewStudentInfoCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "aaa", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentInfoCommand.MESSAGE_USAGE));
    }
}

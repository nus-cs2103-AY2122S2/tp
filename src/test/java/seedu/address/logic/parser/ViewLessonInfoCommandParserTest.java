package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewLessonInfoCommand;

/**
 * Test cases for the parser ViewLessonInfoCommandParser
 */
public class ViewLessonInfoCommandParserTest {

    private ViewLessonInfoCommandParser parser = new ViewLessonInfoCommandParser();

    @Test
    public void parse_validArgs_returnsViewLessonInfoCommand() {
        assertParseSuccess(parser, "1", new ViewLessonInfoCommand(INDEX_FIRST_STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "aaa", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ViewLessonInfoCommand.MESSAGE_USAGE));
    }
}

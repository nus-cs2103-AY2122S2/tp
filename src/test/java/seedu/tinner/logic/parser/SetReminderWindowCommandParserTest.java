package seedu.tinner.logic.parser;

import static seedu.tinner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tinner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tinner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.tinner.logic.commands.SetReminderWindowCommand;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the SetReminderWindowCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the SetReminderWindowCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class SetReminderWindowCommandParserTest {

    private SetReminderWindowCommandParser parser = new SetReminderWindowCommandParser();

    @Test
    public void parse_validArgs_returnsSetReminderWindowCommand() {
        assertParseSuccess(parser, "7", new SetReminderWindowCommand(7));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetReminderWindowCommand.MESSAGE_USAGE));
    }
}

package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.OpenMeetingCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the OpenMeetingCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the OpenMeetingCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class OpenMeetingCommandParserTest {

    private final OpenMeetingCommandParser parser = new OpenMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsOpenMeetingCommand() {
        assertParseSuccess(parser, "1", new OpenMeetingCommand(INDEX_FIRST_MEETING));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                OpenMeetingCommand.MESSAGE_USAGE));
    }
}

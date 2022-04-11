package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.CloseMeetingCommand;

public class CloseMeetingCommandParserTest {

    private CloseMeetingCommandParser parser = new CloseMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsCloseMeetingCommand() {
        assertParseSuccess(parser, "1", new CloseMeetingCommand(INDEX_FIRST_MEETING));
    }

    @Test
    public void parse_invalidArgs_returnsCloseMeetingCommand() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CloseMeetingCommand.MESSAGE_USAGE));
    }
}

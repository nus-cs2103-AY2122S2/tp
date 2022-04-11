package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.FindMeetingCommand;
import seedu.address.model.meeting.MeetingContainsAllKeywordsPredicate;

public class FindMeetingCommandParserTest {
    private final FindMeetingCommandParser parser = new FindMeetingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindMeetingCommand() {
        // no leading and trailing whitespaces
        FindMeetingCommand expectedFindMeetingCommand =
                new FindMeetingCommand(new MeetingContainsAllKeywordsPredicate(Arrays.asList("CS", "Tutorial")));
        assertParseSuccess(parser, "CS Tutorial", expectedFindMeetingCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS \n \t Tutorial  \t", expectedFindMeetingCommand);
    }
}

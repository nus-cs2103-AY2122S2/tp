package seedu.address.logic.parser.meeting;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETINGS_SHOW_ALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.ListMeetingCommand;

public class ListMeetingCommandParserTest {

    private ListMeetingCommandParser parser = new ListMeetingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser,
                VALID_MEETING_INDEX + " " + PREFIX_MEETINGS_SHOW_ALL,
                new ListMeetingCommand(INDEX_FIRST_MEETING, true));
    }

    @Test
    public void parse_onlyIndexPresent_success() {
        assertParseSuccess(parser,
                VALID_MEETING_INDEX,
                new ListMeetingCommand(INDEX_FIRST_MEETING, false));
    }

    @Test
    public void parse_onlyAllPresent_success() {
        assertParseSuccess(parser,
                " " + PREFIX_MEETINGS_SHOW_ALL,
                new ListMeetingCommand(null, true));
    }

    @Test
    public void parse_noFieldsPresent_success() {
        assertParseSuccess(parser,
                "",
                new ListMeetingCommand(null, false));
    }
}

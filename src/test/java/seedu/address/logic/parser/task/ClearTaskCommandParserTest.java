package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.ClearTaskCommand;
import seedu.address.model.task.Date;

/**
 * Contains tests for
 * {@code ClearTaskCommandParser}.
 */
public class ClearTaskCommandParserTest {

    private final ClearTaskCommandParser parser = new ClearTaskCommandParser();
    private final String dateString = "03-03-2000";

    @Test
    public void parse_validArgs_returnsClearTaskCommand() {
        assertParseSuccess(parser, " d/" + dateString, new ClearTaskCommand(new Date(dateString)));
    }

    @Test
    public void parse_validArgsBlank_returnsClearTaskCommand() {
        assertParseSuccess(parser, "", new ClearTaskCommand());
    }

    @Test
    public void parse_validArgsWithWhitespace_returnsClearTaskCommand() {
        assertParseSuccess(parser, "    ", new ClearTaskCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ClearTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsNoPrefix_throwsParseException() {
        assertParseFailure(parser, dateString, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ClearTaskCommand.MESSAGE_USAGE));
    }

}

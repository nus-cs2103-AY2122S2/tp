package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_PAST_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_UPCOMING_EVENTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowEventsCommand;

public class ShowEventsCommandParserTest {
    private ShowEventsCommandParser parser = new ShowEventsCommandParser();

    @Test
    public void parse_validCommand_success() {
        ShowEventsCommand expectedCommand = new ShowEventsCommand(PREDICATE_SHOW_PAST_EVENTS);
        assertParseSuccess(parser, " -past", expectedCommand);

        expectedCommand = new ShowEventsCommand(PREDICATE_SHOW_ALL_EVENTS);
        assertParseSuccess(parser, "  ", expectedCommand);

        expectedCommand = new ShowEventsCommand(PREDICATE_SHOW_UPCOMING_EVENTS);
        assertParseSuccess(parser, "  -upcoming  ", expectedCommand);
    }

    @Test
    public void parse_invalidCommand_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowEventsCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " invalid", expectedMessage);
    }

    @Test
    public void parse_validEmptyCommand_success() {
        ShowEventsCommand expectedCommand = new ShowEventsCommand(PREDICATE_SHOW_ALL_EVENTS);

        assertParseSuccess(parser, "", expectedCommand);
    }
}

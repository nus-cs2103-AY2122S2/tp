package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ListCommandParser.PREDICATE_SHOW_FLAGGED_PERSONS;
import static seedu.address.logic.parser.ListCommandParser.PREDICATE_SHOW_UNFLAGGED_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;


class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_empty_returnsListCommand() {
        ListCommand expected = new ListCommand(PREDICATE_SHOW_ALL_PERSONS);

        // no additional input should list all clients
        assertParseSuccess(parser, "", expected);

        // test with whitespace to see if trim works
        assertParseSuccess(parser, "\t", expected);
    }

    @Test
    public void parse_flag_returnsListCommand() {
        ListCommand expected = new ListCommand(PREDICATE_SHOW_FLAGGED_PERSONS);

        // ensure normal input works
        assertParseSuccess(parser, "flag", expected);

        // ensure input is not case sensitive
        assertParseSuccess(parser, "FlAg", expected);

        // ensure input works with spaces around it
        assertParseSuccess(parser, " flag ", expected);
    }

    @Test
    public void parse_unflag_returnsListCommand() {
        ListCommand expected = new ListCommand(PREDICATE_SHOW_UNFLAGGED_PERSONS);

        // ensure normal input works
        assertParseSuccess(parser, "unflag", expected);

        // ensure input is not case sensitive
        assertParseSuccess(parser, "unFlAg", expected);

        // ensure input works with spaces around it
        assertParseSuccess(parser, " unflag ", expected);
    }

    @Test
    public void parse_invalidString_throwsParseException() {
        // ensure misspellings do not work
        assertParseFailure(parser, "falg",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "flagged",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        // ensure additional invalid input is disallowed
        assertParseFailure(parser, "3 flag",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        // ensure it does not work if both options are inputted
        assertParseFailure(parser, "flag unflag",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}

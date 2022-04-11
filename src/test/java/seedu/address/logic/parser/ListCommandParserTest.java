package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.sort.ApplicationStatusComparator;
import seedu.address.logic.sort.InterviewSlotComparator;
import seedu.address.logic.sort.NameComparator;
import seedu.address.logic.sort.PriorityComparator;

public class ListCommandParserTest {
    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_invalidInput_throwsParseException() {
        assertParseFailure(parser, "abc desc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "interview acb",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListCommand() {

        // Default list command that shows all applications w/o sorting
        assertParseSuccess(parser, "", ListCommandParser.SORT_NONE);

        // Sorting by the 4 different fields
        ListCommand expectedListCommand = new ListCommand(new NameComparator(), "ASC");
        assertParseSuccess(parser, "name asc", expectedListCommand);

        expectedListCommand = new ListCommand(new InterviewSlotComparator(), "ASC");
        assertParseSuccess(parser, "interview asc", expectedListCommand);

        expectedListCommand = new ListCommand(new PriorityComparator(), "ASC");
        assertParseSuccess(parser, "priority asc", expectedListCommand);

        expectedListCommand = new ListCommand(new ApplicationStatusComparator(), "ASC");
        assertParseSuccess(parser, "status asc", expectedListCommand);

        // additional text
        expectedListCommand = new ListCommand(new InterviewSlotComparator(), "DESC");
        assertParseSuccess(parser, "interview DESC abc", expectedListCommand);

        // additional spaces
        expectedListCommand = new ListCommand(new InterviewSlotComparator(), "DESC");
        assertParseSuccess(parser, "interview    DESC", expectedListCommand);

        // whitespace only preamble
        expectedListCommand = new ListCommand(new InterviewSlotComparator(), "DESC");
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "interview DESC", expectedListCommand);

        // multiple input text - only first set accepted
        expectedListCommand = new ListCommand(new InterviewSlotComparator(), "DESC");
        assertParseSuccess(parser, "interview DESC name ASC", expectedListCommand);
    }
}

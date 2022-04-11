package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.comparators.PersonMeetingComparator;
import seedu.address.model.person.comparators.PersonNameComparator;
import seedu.address.model.person.comparators.PersonPrevDateMetComparator;
import seedu.address.model.person.comparators.PersonSalaryComparator;


class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_name_returnsSortCommand() {
        SortCommand expected = new SortCommand(new PersonNameComparator());

        // ensure normal input works
        assertParseSuccess(parser, "name", expected);

        // ensure input is not case sensitive
        assertParseSuccess(parser, "NaMe", expected);

        // ensure input works with spaces around it
        assertParseSuccess(parser, " name ", expected);
    }

    @Test
    public void parse_prev_returnsSortCommand() {
        SortCommand expected = new SortCommand(new PersonPrevDateMetComparator());

        // ensure normal input works
        assertParseSuccess(parser, "prev", expected);

        // ensure input is not case sensitive
        assertParseSuccess(parser, "PrEv", expected);

        // ensure input works with spaces around it
        assertParseSuccess(parser, " prev ", expected);
    }

    @Test
    public void parse_meeting_returnsSortCommand() {
        SortCommand expected = new SortCommand(new PersonMeetingComparator());

        // ensure normal input works
        assertParseSuccess(parser, "meeting", expected);

        // ensure input is not case sensitive
        assertParseSuccess(parser, "MeEting", expected);

        // ensure input works with spaces around it
        assertParseSuccess(parser, " meeting ", expected);
    }

    @Test
    public void parse_salary_returnsSortCommand() {
        SortCommand expected = new SortCommand(new PersonSalaryComparator());

        // ensure normal input works
        assertParseSuccess(parser, "salary", expected);

        // ensure input is not case sensitive
        assertParseSuccess(parser, "SaLarY", expected);

        // ensure input works with spaces around it
        assertParseSuccess(parser, " salary ", expected);
    }

    @Test
    public void parse_invalidString_throwsParseException() {
        // ensure misspellings do not work
        assertParseFailure(parser, "prve",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "meting",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // ensure additional invalid input is disallowed
        assertParseFailure(parser, "3 name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // ensure it does not work if both options are inputted
        assertParseFailure(parser, "salary meeting",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}

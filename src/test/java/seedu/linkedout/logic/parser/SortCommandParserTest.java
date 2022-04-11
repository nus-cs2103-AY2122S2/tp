package seedu.linkedout.logic.parser;

import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.linkedout.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.linkedout.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.linkedout.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.linkedout.logic.commands.SortCommand;
import seedu.linkedout.model.applicant.util.sort.Field;
import seedu.linkedout.model.applicant.util.sort.Order;
import seedu.linkedout.model.applicant.util.sort.SortComparator;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        //missing field prefix
        assertParseFailure(parser, "NAME", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        //missing order prefix
        assertParseFailure(parser, "ASC", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        // wrong prefix
        assertParseFailure(parser, "q/Name", String.format(
                MESSAGE_INVALID_PREFIX, SortCommand.MESSAGE_USAGE));

        // first prefix correct but second prefix wrong
        assertParseFailure(parser, "f/Name q/Asc", String.format(
                MESSAGE_INVALID_PREFIX, SortCommand.MESSAGE_USAGE));

        // wrong prefix between correct prefixes
        assertParseFailure(parser, "f/Name d/Desc o/Asc", String.format(
                MESSAGE_INVALID_PREFIX, SortCommand.MESSAGE_USAGE));

        // spaces between prefixes
        assertParseFailure(parser, "f/   Name      d/  Desc o/Asc", String.format(
                MESSAGE_INVALID_PREFIX, SortCommand.MESSAGE_USAGE));

        // wrong prefix of any length
        assertParseFailure(parser, "f/   Name      ddddd/  Desc o/Asc", String.format(
                MESSAGE_INVALID_PREFIX, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_keywordMissing_throwsParseException() {
        // missing field keyword
        assertParseFailure(parser, " f/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // missing order keyword
        assertParseFailure(parser, " o/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // missing order keyword
        assertParseFailure(parser, " f/NAME o/", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        // missing field  keyword
        assertParseFailure(parser, " f/ o/ASC", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        SortCommand expectedNameSortCommand =
                new SortCommand(new SortComparator(new Field("NAME"), new Order("ASC")));
        SortCommand expectedJobSortCommand =
                new SortCommand(new SortComparator(new Field("JOB"), new Order("DESC")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " f/NAME o/ASC", expectedNameSortCommand);
        assertParseSuccess(parser, " f/JOB o/DESC", expectedJobSortCommand);

        //leading whitespace after prefix
        assertParseSuccess(parser, " f/   NAME o/  ASC", expectedNameSortCommand);
        assertParseSuccess(parser, " f/\nNAME o/ \t ASC", expectedNameSortCommand);

        //case insensitive
        assertParseSuccess(parser, " f/NAmE o/asc", expectedNameSortCommand);
        assertParseSuccess(parser, " f/jOB o/DeSc", expectedJobSortCommand);
    }

}

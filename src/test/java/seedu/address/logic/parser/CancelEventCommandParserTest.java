package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CancelEventCommand;

public class CancelEventCommandParserTest {

    private CancelEventCommandParser parser = new CancelEventCommandParser();

    @Test
    public void parse_validArgs_returnsCancelEventCommand() {
        assertParseSuccess(parser, "1", new CancelEventCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, " 1 ", new CancelEventCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, "  1 2 3   ", new CancelEventCommand(
                new Index[]{INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON}));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Contains non-zero integer -> Fail
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CancelEventCommand.MESSAGE_USAGE));
        // "+1" can be parsed into 1, should be detected and rejected -> Fail
        assertParseFailure(parser, "+1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CancelEventCommand.MESSAGE_USAGE));
        // Contains non-zero integer in the multiple case -> Fail
        assertParseFailure(parser, "1 a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CancelEventCommand.MESSAGE_USAGE));
        // Contains non-zero integer in the multiple case -> Fail
        assertParseFailure(parser, "1 -1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CancelEventCommand.MESSAGE_USAGE));
        // Contains than 1 white space between 2 integers -> Fail
        assertParseFailure(parser, "1  2 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CancelEventCommand.MESSAGE_USAGE));
        // Contains zero -> Fail
        assertParseFailure(parser, "1 0 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CancelEventCommand.MESSAGE_USAGE));
        // Contains duplicate integer -> Fail
        assertParseFailure(parser, "1 1 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CancelEventCommand.MESSAGE_USAGE));

    }
}

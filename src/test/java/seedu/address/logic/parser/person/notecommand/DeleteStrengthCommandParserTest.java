package seedu.address.logic.parser.person.notecommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.NOTE_FIRST_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.notecommands.DeleteStrengthCommand;
import seedu.address.logic.parser.person.notecommands.DeleteStrengthCommandParser;

/**
 * Contains tests for
 * {@code DeleteStrengthCommandParser}.
 */
public class DeleteStrengthCommandParserTest {
    private final DeleteStrengthCommandParser parser = new DeleteStrengthCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "2 1", new DeleteStrengthCommand(INDEX_SECOND_PERSON, NOTE_FIRST_INDEX));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteStrengthCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteStrengthCommand.MESSAGE_USAGE));
    }
}

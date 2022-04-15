package seedu.address.logic.parser.person.notecommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.notecommands.AddStrengthCommand;
import seedu.address.logic.parser.person.notecommands.AddStrengthCommandParser;
import seedu.address.model.note.Note;

class AddStrengthCommandParserTest {

    private AddStrengthCommandParser parser = new AddStrengthCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStrengthCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAddStrengthCommand() {
        // no leading and trailing whitespaces
        AddStrengthCommand expectedAddStrengthCommand =
                new AddStrengthCommand(INDEX_FIRST_PERSON, new Note(VALID_NOTE_AMY));
        assertParseSuccess(parser, "1 Amy", expectedAddStrengthCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 1 \n \t Amy  \t", expectedAddStrengthCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "2           ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStrengthCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "         Amy",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStrengthCommand.MESSAGE_USAGE));
    }
}

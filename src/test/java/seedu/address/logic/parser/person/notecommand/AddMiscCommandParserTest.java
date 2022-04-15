package seedu.address.logic.parser.person.notecommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.notecommands.AddMiscCommand;
import seedu.address.logic.parser.person.notecommands.AddMiscCommandParser;
import seedu.address.model.note.Note;

class AddMiscCommandParserTest {

    private AddMiscCommandParser parser = new AddMiscCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMiscCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAddMiscCommand() {
        // no leading and trailing whitespaces
        AddMiscCommand expectedAddMiscCommand =
                new AddMiscCommand(INDEX_FIRST_PERSON, new Note(VALID_NOTE_AMY));
        assertParseSuccess(parser, "1 Amy", expectedAddMiscCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 1 \n \t Amy  \t", expectedAddMiscCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "2           ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMiscCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "         Amy",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMiscCommand.MESSAGE_USAGE));
    }
}

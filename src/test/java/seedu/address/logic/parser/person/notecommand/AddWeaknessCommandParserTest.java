package seedu.address.logic.parser.person.notecommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.notecommands.AddWeaknessCommand;
import seedu.address.logic.parser.person.notecommands.AddWeaknessCommandParser;
import seedu.address.model.note.Note;

class AddWeaknessCommandParserTest {

    private AddWeaknessCommandParser parser = new AddWeaknessCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWeaknessCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAddWeaknessCommand() {
        // no leading and trailing whitespaces
        AddWeaknessCommand expectedAddWeaknessCommand =
                new AddWeaknessCommand(INDEX_FIRST_PERSON, new Note(VALID_NOTE_AMY));
        assertParseSuccess(parser, "1 Amy", expectedAddWeaknessCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 1 \n \t Amy  \t", expectedAddWeaknessCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "2           ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWeaknessCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "         Amy",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddWeaknessCommand.MESSAGE_USAGE));
    }
}

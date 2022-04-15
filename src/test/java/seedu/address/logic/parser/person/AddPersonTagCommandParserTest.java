package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.AddPersonTagCommand;

class AddPersonTagCommandParserTest {

    private static final String TAG1 = "TAG1";
    private AddPersonTagCommandParser parser = new AddPersonTagCommandParser();


    @Test
    void parse_validArgs_returnsAddTagCommand() {
        AddPersonTagCommand expectedAddTagCommand = new AddPersonTagCommand(INDEX_FIRST_PERSON, TAG1);
        assertParseSuccess(parser, " 1 TAG1", expectedAddTagCommand);
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                " 1 t/friend",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonTagCommand.MESSAGE_USAGE));
    }
}

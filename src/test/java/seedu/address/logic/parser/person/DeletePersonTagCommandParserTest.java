package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.DeletePersonTagCommand;

class DeletePersonTagCommandParserTest {

    private static final String TAG1 = "friends";
    private DeletePersonTagCommandParser parser = new DeletePersonTagCommandParser();


    @Test
    void parse_validArgs_returnsDeleteTagCommand() {
        DeletePersonTagCommand expectedDeleteTagCommand = new DeletePersonTagCommand(INDEX_FIRST_PERSON, TAG1);
        assertParseSuccess(parser, " 1 friends", expectedDeleteTagCommand);
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser,
                " 1 t/friend",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonTagCommand.MESSAGE_USAGE));
    }
}

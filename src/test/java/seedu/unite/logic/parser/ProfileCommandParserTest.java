package seedu.unite.logic.parser;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unite.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.unite.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.unite.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.unite.logic.commands.ProfileCommand;

public class ProfileCommandParserTest {
    private final ProfileCommandParser parser = new ProfileCommandParser();

    @Test
    public void parse_validArgs_returnsParseCommand() {
        assertParseSuccess(parser, "1", new ProfileCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ProfileCommand.MESSAGE_USAGE));
    }
}

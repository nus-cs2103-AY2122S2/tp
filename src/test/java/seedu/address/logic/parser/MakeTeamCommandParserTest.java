package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MakeTeamCommand;

class MakeTeamCommandParserTest {

    private final MakeTeamCommandParser addCommandParser = new MakeTeamCommandParser(MakeTeamCommand.TeamAction.ADD);
    private final MakeTeamCommandParser removeCommandParser =
            new MakeTeamCommandParser(MakeTeamCommand.TeamAction.REMOVE);

    @Test
    public void parse_makeTeamCommandAddValidArgs_success() throws Exception {
        String validIndex = "1";
        assertParseSuccess(addCommandParser, validIndex,
                new MakeTeamCommand(INDEX_FIRST_PERSON, MakeTeamCommand.TeamAction.ADD));
    }

    @Test
    public void parse_makeTeamCommandRemoveValidArgs_success() throws Exception {
        String validIndex = "1";
        assertParseSuccess(removeCommandParser, validIndex,
                new MakeTeamCommand(INDEX_FIRST_PERSON, MakeTeamCommand.TeamAction.REMOVE));
    }

    @Test
    public void parse_makeTeamCommandInvalidArgs_throwsParseException() {
        String emptyIndex = "";
        String invalidIndex = "a";
        assertParseFailure(addCommandParser, emptyIndex,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeTeamCommand.MESSAGE_USAGE));
        assertParseFailure(removeCommandParser, invalidIndex,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeTeamCommand.MESSAGE_USAGE));
    }
}

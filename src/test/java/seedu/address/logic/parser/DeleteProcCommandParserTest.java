package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROCEDURE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteProcCommand;

public class DeleteProcCommandParserTest {
    private static final String PREAMBLE = "%1$s %2$s";
    private static final String VALID_PREAMBLE = String.format(PREAMBLE, INDEX_FIRST_CLIENT.getOneBased(),
        INDEX_FIRST_PROCEDURE.getOneBased());

    private DeleteProcCommandParser parser = new DeleteProcCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        Index targetProcedure = INDEX_FIRST_PROCEDURE;

        String userInput = VALID_PREAMBLE;
        assertParseSuccess(parser, userInput,
            new DeleteProcCommand(targetIndex, targetProcedure));
    }

    @Test
    public void parse_validArgs_returnsDeleteClientCommand() {
        assertParseSuccess(parser, "1 1", new DeleteProcCommand(INDEX_FIRST_CLIENT,
                INDEX_FIRST_PROCEDURE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProcCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "a 1", expectedMessage);

        // Negative client index
        assertParseFailure(parser, String.format(PREAMBLE, -5, 1), expectedMessage);

        // Zero-ed client index
        assertParseFailure(parser, String.format(PREAMBLE, 0, 1), expectedMessage);

        // Invalid arguments being parsed in client index as preamble
        assertParseFailure(parser, String.format(PREAMBLE, "1 some Strings", 1), expectedMessage);

        // invalid prefix being parsed as preamble for client index
        assertParseFailure(parser, String.format(PREAMBLE, "1 n/notification", 1), expectedMessage);

        // Negative procedure index
        assertParseFailure(parser, String.format(PREAMBLE, 1, -1), expectedMessage);

        // Zero-ed procedure index
        assertParseFailure(parser, String.format(PREAMBLE, 1, 0), expectedMessage);

        // Invalid arguments being parsed in procedure index as preamble
        assertParseFailure(parser, String.format(PREAMBLE, 1, "1 some Strings"), expectedMessage);

        // invalid prefix being parsed as preamble for procedure index
        assertParseFailure(parser, String.format(PREAMBLE, 1, "1 n/notification"), expectedMessage);
    }
}

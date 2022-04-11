package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERENCE_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE_KEY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeletePreferenceCommand;

public class DeletePreferenceCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePreferenceCommand.MESSAGE_USAGE);

    private DeletePreferenceCommandParser parser = new DeletePreferenceCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PREFIX_PREFERENCE_KEY + VALID_PREFERENCE_CATEGORY, MESSAGE_INVALID_FORMAT);

        // no category specified
        assertParseFailure(parser, String.valueOf(INDEX_FIRST_CLIENT.getOneBased()), MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 " + PREFIX_PREFERENCE_KEY + VALID_PREFERENCE_CATEGORY,
                MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "-5 " + PREFIX_PREFERENCE_KEY + VALID_PREFERENCE_CATEGORY,
                MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string " + PREFIX_PREFERENCE_KEY + VALID_PREFERENCE_CATEGORY,
                MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput = (INDEX_FIRST_CLIENT.getOneBased()) + " " + PREFIX_PREFERENCE_KEY + VALID_PREFERENCE_CATEGORY;

        DeletePreferenceCommand expectedCommand = new DeletePreferenceCommand(targetIndex, VALID_PREFERENCE_CATEGORY);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

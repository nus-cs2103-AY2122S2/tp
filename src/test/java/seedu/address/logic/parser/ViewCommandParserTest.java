package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalRecipes.AGLIO_OLIO;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;

/**
 * Contains integration tests and unit tests for {@code ViewCommandParser}.
 */
public class ViewCommandParserTest {

    private final ViewCommandParser parser = new ViewCommandParser();

    /**
     * Tests parse method with valid inputs.
     * Expects appropriate ViewCommand Object to be returned.
     */
    @Test
    public void parse_validArgs_returnsViewCommand() {
        assertParseSuccess(parser, " -x 1", new ViewCommand(INDEX_FIRST_RECIPE));
        assertParseSuccess(parser, " Aglio Olio\n", new ViewCommand(AGLIO_OLIO.getName()));
        assertParseSuccess(parser, " aglio olio\n", new ViewCommand(AGLIO_OLIO.getName()));
    }

    /**
     * Tests parse method with invalid inputs.
     * Expects ParseException to be thrown.
     */
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " -x -1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " -x 0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    /**
     * Tests parse method with a null input.
     * Expects NullPointerException to be thrown.
     */
    @Test
    public void parse_nullStringInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }
}

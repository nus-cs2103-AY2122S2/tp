package seedu.address.logic.parser;

import static seedu.address.logic.commands.DeleteCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalRecipes.AGLIO_OLIO;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private final DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, COMMAND_WORD + " -x 1", new DeleteCommand(INDEX_FIRST_RECIPE));

        assertParseSuccess(parser, " Aglio Olio\n", new DeleteCommand(AGLIO_OLIO.getName()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, COMMAND_WORD + " -x -1",
                Messages.MESSAGE_INVALID_RECIPE_INDEX);
    }
}

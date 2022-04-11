package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.RecipeContainsKeywordPredicate;

class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() throws ParseException {
        RecipeContainsKeywordPredicate pred = new RecipeContainsKeywordPredicate(List.of("Western", "Bacon"));
        FindCommand expected = new FindCommand(pred);

        assertParseSuccess(parser, "Western| Bacon", expected);
        assertParseSuccess(parser, "      Western \r\n\t\t|    Bacon", expected);
    }
}

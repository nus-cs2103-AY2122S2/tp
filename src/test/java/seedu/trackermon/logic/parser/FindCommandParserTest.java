package seedu.trackermon.logic.parser;

import static seedu.trackermon.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackermon.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackermon.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.trackermon.logic.commands.FindCommand;
import seedu.trackermon.model.show.ShowContainsKeywordsPredicate;

/**
 * Contains unit tests for {@code FindCommandParser}.
 */
public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    /**
     * Tests the parsing of empty argument from the execution of {@code FindCommandParser}.
     */
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    /**
     * Tests the parsing of valid arguments from the execution of {@code FindCommandParser}.
     */
    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ShowContainsKeywordsPredicate(Arrays.asList("Gone")));
        assertParseSuccess(parser, "Gone", expectedFindCommand);
        assertParseSuccess(parser, " Gone ", expectedFindCommand);
    }
}

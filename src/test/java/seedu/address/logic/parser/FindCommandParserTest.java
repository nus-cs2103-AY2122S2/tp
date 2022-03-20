package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.AttributeContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        AttributeContainsKeywordsPredicate testName = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        FindCommand expectedFindCommand = new FindCommand(testName);
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommand);

        // This one might not be too relevant in our case cause they need to enter a solid name for it
        // multiple whitespaces between keywords
        // assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}

package seedu.tinner.logic.parser;

import static seedu.tinner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tinner.commons.core.Messages.MESSAGE_NO_VALUE_AFTER_PREFIX;
import static seedu.tinner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tinner.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.tinner.logic.commands.FindCommand;
import seedu.tinner.model.company.CompanyNameContainsKeywordsPredicate;
import seedu.tinner.model.role.RoleNameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " c/ ",
                String.format(MESSAGE_NO_VALUE_AFTER_PREFIX, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " r/ ",
                String.format(MESSAGE_NO_VALUE_AFTER_PREFIX, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " c/meta r/",
                String.format(MESSAGE_NO_VALUE_AFTER_PREFIX, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " c/meta r/",
                String.format(MESSAGE_NO_VALUE_AFTER_PREFIX, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompanyNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineer"),
                        Arrays.asList("Square", "Enix")),
                        new RoleNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineer")));
        assertParseSuccess(parser, " c/Square Enix r/Software Engineer", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n c/Square Enix \n \t r/Software Engineer \t", expectedFindCommand);

        // no company keywords
        expectedFindCommand =
                new FindCommand(new CompanyNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineer"),
                        Arrays.asList()), new RoleNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineer")));
        assertParseSuccess(parser, " r/Software Engineer", expectedFindCommand);

        // no role keywords
        expectedFindCommand =
                new FindCommand(new CompanyNameContainsKeywordsPredicate(Arrays.asList(),
                        Arrays.asList("Square", "Enix")), new RoleNameContainsKeywordsPredicate(Arrays.asList()));
        assertParseSuccess(parser, " c/ Square Enix", expectedFindCommand);
    }
}

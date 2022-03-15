package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.FindCommand;
import seedu.contax.model.person.AddressContainsKeywordsPredicate;
import seedu.contax.model.person.EmailContainsKeywordsPredicate;
import seedu.contax.model.person.NameContainsKeywordsPredicate;
import seedu.contax.model.person.PhoneContainsKeywordsPredicate;
import seedu.contax.model.util.SearchType;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_findByPhone_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("123", "456")));
        assertParseSuccess(parser, "123 456 by/" + SearchType.TYPE_PHONE, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n 123 \n \t 456  \t by/" + SearchType.TYPE_PHONE, expectedFindCommand);
    }

    @Test
    public void parse_findByAddress_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList("abc", "bcd")));
        assertParseSuccess(parser, "abc bcd by/" + SearchType.TYPE_ADDRESS, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n abc \n \t bcd  \t by/" + SearchType.TYPE_ADDRESS, expectedFindCommand);
    }

    @Test
    public void parse_findByEmail_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(
                new EmailContainsKeywordsPredicate(Arrays.asList("test@abc.com", "test2@example.com")));
        assertParseSuccess(parser, "test@abc.com test2@example.com by/" + SearchType.TYPE_EMAIL,
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n test@abc.com \n \t test2@example.com  \t by/" + SearchType.TYPE_EMAIL,
                expectedFindCommand);
    }

    @Test
    public void parse_findByName_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("John", "Bob")));
        assertParseSuccess(parser, "John Bob by/" + SearchType.TYPE_NAME, expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n John \n \t Bob  \t by/" + SearchType.TYPE_NAME, expectedFindCommand);
    }

}

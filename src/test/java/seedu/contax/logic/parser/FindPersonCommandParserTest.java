package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.FindPersonCommand;
import seedu.contax.model.person.AddressContainsKeywordsPredicate;
import seedu.contax.model.person.EmailContainsKeywordsPredicate;
import seedu.contax.model.person.NameContainsKeywordsPredicate;
import seedu.contax.model.person.PhoneContainsKeywordsPredicate;
import seedu.contax.model.util.SearchType;

public class FindPersonCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindPersonCommand);
    }

    @Test
    public void parse_findByPhone_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("123", "456")));
        assertParseSuccess(parser, "123 456 by/" + SearchType.TYPE_PHONE, expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n 123 \n \t 456  \t by/" + SearchType.TYPE_PHONE, expectedFindPersonCommand);
    }

    @Test
    public void parse_findByAddress_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(new AddressContainsKeywordsPredicate(Arrays.asList("abc", "bcd")));
        assertParseSuccess(parser, "abc bcd by/" + SearchType.TYPE_ADDRESS, expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n abc \n \t bcd  \t by/" + SearchType.TYPE_ADDRESS, expectedFindPersonCommand);
    }

    @Test
    public void parse_findByEmail_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPersonCommand expectedFindPersonCommand = new FindPersonCommand(
                new EmailContainsKeywordsPredicate(Arrays.asList("test@abc.com", "test2@example.com")));
        assertParseSuccess(parser, "test@abc.com test2@example.com by/" + SearchType.TYPE_EMAIL,
                expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n test@abc.com \n \t test2@example.com  \t by/" + SearchType.TYPE_EMAIL,
                expectedFindPersonCommand);
    }

    @Test
    public void parse_findByName_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("John", "Bob")));
        assertParseSuccess(parser, "John Bob by/" + SearchType.TYPE_NAME, expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n John \n \t Bob  \t by/" + SearchType.TYPE_NAME, expectedFindPersonCommand);
    }

}

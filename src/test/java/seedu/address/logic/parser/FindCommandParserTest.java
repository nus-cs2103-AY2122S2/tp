package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.predicates.PersonContainsKeywordsPredicate;
import seedu.address.model.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.predicates.PreferenceContainsKeywordsPredicate;
import seedu.address.model.predicates.PropertiesContainsKeywordsPredicate;
import seedu.address.model.predicates.UserTypeContainsKeywordsPredicate;

public class FindCommandParserTest {

    private static final String expectedMessage =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", expectedMessage);
    }

    @Test
    public void parse_invalidCommand_throwsParseException() {
        assertParseFailure(parser, "invalidCommand Alice Bob", expectedMessage);
    }

    @Test
    public void parse_emptyKeywords_throwsParseException() {
        assertParseFailure(parser, "all", expectedMessage);
    }

    @Test
    public void parse_validPersonArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser,
                PersonContainsKeywordsPredicate.COMMAND_WORD + " Alice Bob",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n" + PersonContainsKeywordsPredicate.COMMAND_WORD + " \n Alice \n \t Bob  \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser,
                NameContainsKeywordsPredicate.COMMAND_WORD + " Alice Bob",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n" + NameContainsKeywordsPredicate.COMMAND_WORD + " \n Alice \n \t Bob  \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validPhoneArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser,
                PhoneContainsKeywordsPredicate.COMMAND_WORD + " Alice Bob",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n" + PhoneContainsKeywordsPredicate.COMMAND_WORD + " \n Alice \n \t Bob  \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validEmailArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser,
                EmailContainsKeywordsPredicate.COMMAND_WORD + " Alice Bob",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n" + EmailContainsKeywordsPredicate.COMMAND_WORD + " \n Alice \n \t Bob  \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validAddressArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new AddressContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser,
                AddressContainsKeywordsPredicate.COMMAND_WORD + " Alice Bob",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n" + AddressContainsKeywordsPredicate.COMMAND_WORD + " \n Alice \n \t Bob  \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validPropertiesArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PropertiesContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser,
                PropertiesContainsKeywordsPredicate.COMMAND_WORD + " Alice Bob",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n" + PropertiesContainsKeywordsPredicate.COMMAND_WORD + " \n Alice \n \t Bob  \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validPreferenceArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PreferenceContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser,
                PreferenceContainsKeywordsPredicate.COMMAND_WORD + " Alice Bob",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n" + PreferenceContainsKeywordsPredicate.COMMAND_WORD + " \n Alice \n \t Bob  \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validUserTypeArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new UserTypeContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser,
                UserTypeContainsKeywordsPredicate.COMMAND_WORD + " Alice Bob",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n" + UserTypeContainsKeywordsPredicate.COMMAND_WORD + " \n Alice \n \t Bob  \t",
                expectedFindCommand);
    }
}

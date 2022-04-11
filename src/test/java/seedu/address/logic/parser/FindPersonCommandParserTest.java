package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPersonAddressCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.FindPersonEmailCommand;
import seedu.address.logic.commands.FindPersonNameCommand;
import seedu.address.logic.commands.FindPersonPhoneCommand;
import seedu.address.logic.commands.FindPersonRemarkCommand;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.RemarkContainsKeywordsPredicate;

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
                new FindPersonNameCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindPersonCommand);

        // find based on phones
        expectedFindPersonCommand =
                new FindPersonPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("93120283", "93214134")));
        assertParseSuccess(parser, " " + PREFIX_PHONE + "93120283 93214134", expectedFindPersonCommand);

        // find based on emails
        expectedFindPersonCommand =
                new FindPersonEmailCommand(
                        new EmailContainsKeywordsPredicate(Arrays.asList("alex@abc.com", "ben@xyz.com")));
        assertParseSuccess(parser, " " + PREFIX_EMAIL + "alex@abc.com ben@xyz.com", expectedFindPersonCommand);

        // find based on remarks
        expectedFindPersonCommand =
                new FindPersonRemarkCommand(new RemarkContainsKeywordsPredicate(Arrays.asList("Allergic", "Hates")));
        assertParseSuccess(parser, " " + PREFIX_REMARK + "Allergic Hates", expectedFindPersonCommand);

        // find based on address
        expectedFindPersonCommand =
                new FindPersonAddressCommand(
                        new AddressContainsKeywordsPredicate(Arrays.asList("Serangoon", "Geylang")));
        assertParseSuccess(parser, " " + PREFIX_ADDRESS + "Serangoon Geylang", expectedFindPersonCommand);

    }

    @Test
    public void parse_multipleAttributes_throwsParseException() {
        // multiple attribute search
        assertParseFailure(parser, " n/Alice p/12345678", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPersonCommand.MESSAGE_USAGE));

        // multiple attribute search with different order
        assertParseFailure(parser, " p/12345678 n/Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

}

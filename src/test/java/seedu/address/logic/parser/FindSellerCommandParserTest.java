package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindSellerCommand;
import seedu.address.model.seller.SellerHouseTypeContainsKeywordsPredicate;
import seedu.address.model.seller.SellerLocationContainsKeywordsPredicate;
import seedu.address.model.seller.SellerNameContainsKeywordsPredicate;
import seedu.address.model.seller.SellerPhoneContainsKeywordsPredicate;
import seedu.address.model.seller.SellerTagsContainsKeywordsPredicate;

class FindSellerCommandParserTest {

    public final FindSellerCommandParser parser = new FindSellerCommandParser();

    @Test
    public void parse_validArgs_returnsFindSellerCommand() {
        assertParseSuccess(parser, "h/hdb",
                new FindSellerCommand(new SellerHouseTypeContainsKeywordsPredicate(List.of("hdb"))));
        assertParseSuccess(parser, "l/bishan",
                new FindSellerCommand(new SellerLocationContainsKeywordsPredicate(List.of("bishan"))));
        assertParseSuccess(parser, "p/123",
                new FindSellerCommand(new SellerPhoneContainsKeywordsPredicate(List.of("123"))));
        assertParseSuccess(parser, "t/pepe",
                new FindSellerCommand(new SellerTagsContainsKeywordsPredicate(List.of("pepe"))));
        assertParseSuccess(parser, "n/wokege",
                new FindSellerCommand(new SellerNameContainsKeywordsPredicate(List.of("wokege"))));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindSellerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "%", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindSellerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1b", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindSellerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindSellerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindSellerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindSellerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "2147483648", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindSellerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindSellerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "q/troll", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindSellerCommand.MESSAGE_USAGE));
    }
}

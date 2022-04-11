package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindBuyerCommand;
import seedu.address.model.buyer.BuyerHouseTypeContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerLocationContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerNameContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerPhoneContainsKeywordsPredicate;
import seedu.address.model.buyer.BuyerTagsContainsKeywordsPredicate;

class FindBuyerCommandParserTest {

    public final FindBuyerCommandParser parser = new FindBuyerCommandParser();

    @Test
    public void parse_validArgs_returnsFindBuyerCommand() {
        assertParseSuccess(parser, "h/hdb",
                new FindBuyerCommand(new BuyerHouseTypeContainsKeywordsPredicate(List.of("hdb"))));
        assertParseSuccess(parser, "l/bishan",
                new FindBuyerCommand(new BuyerLocationContainsKeywordsPredicate(List.of("bishan"))));
        assertParseSuccess(parser, "p/123",
                new FindBuyerCommand(new BuyerPhoneContainsKeywordsPredicate(List.of("123"))));
        assertParseSuccess(parser, "t/pepe",
                new FindBuyerCommand(new BuyerTagsContainsKeywordsPredicate(List.of("pepe"))));
        assertParseSuccess(parser, "n/wokege",
                new FindBuyerCommand(new BuyerNameContainsKeywordsPredicate(List.of("wokege"))));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBuyerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "%", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBuyerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1b", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBuyerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBuyerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBuyerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBuyerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "2147483648", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBuyerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBuyerCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "q/troll", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindBuyerCommand.MESSAGE_USAGE));
    }
}

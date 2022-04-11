package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListOrderCommand;
import seedu.address.model.order.OrderStatusContainsKeywordsPredicate;

public class ListOrderCommandParserTest {

    private final ListOrderCommandParser parser = new ListOrderCommandParser();

    @Test
    public void parse_invalidArg_throwsParseException() {
        // empty arg
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListOrderCommand.MESSAGE_USAGE));

        // more than one arg
        assertParseFailure(parser, "cancelled delivered", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListOrderCommand.MESSAGE_USAGE));

        // invalid arg
        assertParseFailure(parser, "random", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListOrderCommand() {
        // in progress
        ListOrderCommand expectedListOrderCommand =
                new ListOrderCommand(new OrderStatusContainsKeywordsPredicate(List.of("in_progress")));
        assertParseSuccess(parser, "in progress", expectedListOrderCommand);
        assertParseSuccess(parser, "In ProgrEss", expectedListOrderCommand);
        assertParseSuccess(parser, "In_ProgrEss", expectedListOrderCommand);

        // delivered
        expectedListOrderCommand = new ListOrderCommand(new OrderStatusContainsKeywordsPredicate(List.of("delivered")));
        assertParseSuccess(parser, "dElivEred", expectedListOrderCommand);

        // cancelled
        expectedListOrderCommand = new ListOrderCommand(new OrderStatusContainsKeywordsPredicate(List.of("cancelled")));
        assertParseSuccess(parser, "canCElled", expectedListOrderCommand);

        // all
        expectedListOrderCommand = new ListOrderCommand(new OrderStatusContainsKeywordsPredicate(List.of("all")));
        assertParseSuccess(parser, "aLl", expectedListOrderCommand);
    }

}

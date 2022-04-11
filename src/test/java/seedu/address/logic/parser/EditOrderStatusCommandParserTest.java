package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditOrderStatusCommand;
import seedu.address.model.order.OrderStatus;

public class EditOrderStatusCommandParserTest {
    private final EditOrderStatusCommandParser parser = new EditOrderStatusCommandParser();
    @Test
    public void parse_validStatus_success() {
        assertParseSuccess(parser, "1 " + PREFIX_STATUS.toString() + OrderStatus.IN_PROGRESS.name(),
                new EditOrderStatusCommand(INDEX_FIRST_ORDER, OrderStatus.IN_PROGRESS.name()));

        assertParseSuccess(parser, "1 " + PREFIX_STATUS.toString() + OrderStatus.DELIVERED.name(),
                new EditOrderStatusCommand(INDEX_FIRST_ORDER, OrderStatus.DELIVERED.name()));
        assertParseSuccess(parser, "1 " + PREFIX_STATUS.toString() + OrderStatus.CANCELLED.name(),
                new EditOrderStatusCommand(INDEX_FIRST_ORDER, OrderStatus.CANCELLED.name()));
    }

    @Test
    public void parse_noPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderStatusCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1 " + OrderStatus.DELIVERED.toString(), expectedMessage);
    }
}

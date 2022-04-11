package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.model.order.OrderContainsKeywordsPredicate;

public class FindOrderCommandParserTest {

    private final FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_validArgs_success() {
        FindOrderCommand expectedCommand =
                new FindOrderCommand(new OrderContainsKeywordsPredicate(Arrays.asList("98765432")));
        assertParseSuccess(parser, "98765432", expectedCommand);
    }

    @Test
    public void parse_multipleArgs_success() {
        FindOrderCommand expectedCommand =
                new FindOrderCommand(new OrderContainsKeywordsPredicate(Arrays.asList("98765432", "88888888")));
        assertParseSuccess(parser, "98765432 88888888", expectedCommand);
    }
}

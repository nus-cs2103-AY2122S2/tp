package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.customer.DeleteCustomerCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCustomerCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCustomerCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCustomerCommandParserTest {

    private DeleteCustomerCommandParser parser = new DeleteCustomerCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        ArrayList<Index> firstCustomer = new ArrayList<>() {
            {
                add(INDEX_FIRST_CUSTOMER);
                add(INDEX_SECOND_CUSTOMER);
            }
        };
        assertParseSuccess(parser, "1", new DeleteCustomerCommand(firstCustomer));
    }

    @Test
    public void parse_validMultipleArgs_returnsDeleteCommand() {
        ArrayList<Index> firstCustomer = new ArrayList<>() {
            {
                add(INDEX_FIRST_CUSTOMER);
                add(INDEX_SECOND_CUSTOMER);
            }
        };
        assertParseSuccess(parser, "1,2", new DeleteCustomerCommand(firstCustomer));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMultipleArgs_throwsParseException() {
        assertParseFailure(parser, "1,a",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCustomerCommand.MESSAGE_USAGE));
    }
}

package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.FindTransactionCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class FindTransactionCommandParserTest {

    private FindTransactionCommandParser parser = new FindTransactionCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "e", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTransactionCommand.MESSAGE_USAGE));
    }
}

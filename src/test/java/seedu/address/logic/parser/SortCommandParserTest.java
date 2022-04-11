package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_nullInput_failure() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidDirection_failure() {
        assertThrows(ParseException.class, () -> parser.parse("premium dir/Wrong"));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = "";
        SortCommand expectedCommand = new SortCommand("", false);
        assertParseSuccess(parser, userInput, expectedCommand);

        userInput = "premium dir/asc";
        expectedCommand = new SortCommand("premium", true);
        assertParseSuccess(parser, userInput, expectedCommand);

        userInput = "premium";
        expectedCommand = new SortCommand("premium", false);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = "premium dir/asc dir/desc";
        SortCommand expectedCommand = new SortCommand("premium", false);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}

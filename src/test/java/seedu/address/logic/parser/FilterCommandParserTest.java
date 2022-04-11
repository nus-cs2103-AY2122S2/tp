package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FilterCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no attribute specified
        assertParseFailure(parser, " op/equal v/february", MESSAGE_INVALID_FORMAT);
        assertThrows(ParseException.class, () -> new FilterCommandParser()
                .parse(" op/equal v/february"));

        // no operator specified
        assertParseFailure(parser, "birthMonth v/february", MESSAGE_INVALID_FORMAT);

        // no value specified
        assertParseFailure(parser, "birthMonth op/equal", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = "birthMonth op/equal v/february";
        FilterCommand expectedCommand = new FilterCommand("birthMonth", "equal", "february");

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = "birthMonth op/greater op/equal v/march v/february";

        FilterCommand expectedCommand = new FilterCommand("birthMonth", "equal", "february");

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

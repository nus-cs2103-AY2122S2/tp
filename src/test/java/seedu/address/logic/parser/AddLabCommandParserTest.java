package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddLabCommand;
import seedu.address.model.lab.Lab;

public class AddLabCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLabCommand.MESSAGE_USAGE);
    private AddLabCommandParser parser = new AddLabCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no lab specified
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid labs
        assertParseFailure(parser, " l/a ", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " l/-1 ", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " l/-1", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " l/21", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        AddLabCommand expectedCommand =
                new AddLabCommand(new Lab("1"));

        assertParseSuccess(parser, " l/1", expectedCommand);
    }
}

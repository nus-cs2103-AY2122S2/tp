package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveLabCommand;
import seedu.address.model.lab.Lab;

public class RemoveLabCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveLabCommand.MESSAGE_USAGE);
    private RemoveLabCommandParser parser = new RemoveLabCommandParser();

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
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        RemoveLabCommand expectedCommand =
                new RemoveLabCommand(new Lab("1"));

        assertParseSuccess(parser, " l/1", expectedCommand);
    }
}

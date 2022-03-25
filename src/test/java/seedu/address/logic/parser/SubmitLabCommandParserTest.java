package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SubmitLabCommand;

public class SubmitLabCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubmitLabCommand.MESSAGE_USAGE);
    private static final String VALID_LABNUMBER = "1 ";
    private static final String VALID_LAB_DESC = PREFIX_LAB + VALID_LABNUMBER;
    private static final String VALID_INDEX = "1 ";

    private SubmitLabCommandParser parser = new SubmitLabCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // nothing specified
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);

        // no index specified
        assertParseFailure(parser, VALID_LAB_DESC, MESSAGE_INVALID_FORMAT);

        // no lab specified
        assertParseFailure(parser, VALID_INDEX, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser, "0 " + VALID_LAB_DESC, MESSAGE_INVALID_FORMAT);

        // invalid lab
        assertParseFailure(parser, VALID_INDEX + "l/a ", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_INDEX + "l/-1 ", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_INDEX + "l/0001 ", MESSAGE_INVALID_FORMAT);

        // invalid index and lab
        assertParseFailure(parser, "-1 " + "l/a ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        SubmitLabCommand expectedCommand = new SubmitLabCommand(
                Index.fromOneBased(Integer.parseInt(VALID_INDEX.trim())), Integer.parseInt(VALID_LABNUMBER.trim()));

        assertParseSuccess(parser, VALID_INDEX + VALID_LAB_DESC, expectedCommand);
    }
}

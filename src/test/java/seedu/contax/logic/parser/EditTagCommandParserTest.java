package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.contax.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.EditTagCommand;
import seedu.contax.logic.commands.EditTagCommand.EditTagDescriptor;
import seedu.contax.testutil.EditTagDescriptorBuilder;

/**
 * Parses input arguments and creates a new EditTagCommand object.
 */
public class EditTagCommandParserTest {

    private EditTagCommandParser parser = new EditTagCommandParser();
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditTagCommand.MESSAGE_USAGE);

    @Test
    public void parse_invalidFields_failure() {
        // no index specified
        assertParseFailure(parser, "t/family", MESSAGE_INVALID_FORMAT);

        // no tag name specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // not tag name and no index specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);

        // invalid arguments as preamble
        assertParseFailure(parser, "1 some random text", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/ random string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTagName_failure() {
    }

    @Test
    public void parser_validInput_success() {
        EditTagDescriptor descriptor = new EditTagDescriptorBuilder().withName("husband").build();
        assertParseSuccess(parser, "1 t/husband", new EditTagCommand(Index.fromOneBased(1), descriptor));
    }
}

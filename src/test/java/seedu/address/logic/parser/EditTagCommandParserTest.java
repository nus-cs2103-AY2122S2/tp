package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COMMAND_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class EditTagCommandParserTest {
    private EditTagCommandParser parser = new EditTagCommandParser();

    @Test
    public void parse_validCommand_success() throws ParseException {
        // single tag - accepted
        assertParseSuccess(parser, "1 " + "2 " + VALID_TAG_COMMAND_FRIEND,
            new EditTagCommand(Index.fromOneBased(1), 2, VALID_TAG_FRIEND.get(0)));
    }

    @Test
    public void parse_noTag_failure() {
        assertParseFailure(parser, "1 " + "2" + VALID_TAG_COMMAND_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE)); // no tag prefix
        assertParseFailure(parser, "1 " + "2" + PREFIX_TAG,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE)); // no tag description
    }

    @Test
    public void parse_invalidTag_failure() {
        assertParseFailure(parser, "1 " + "2" + INVALID_TAG_DESC,
            Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_noIndexAndTagNumber_failure() {
        assertParseFailure(parser, "1 " + VALID_TAG_COMMAND_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
        assertParseFailure(parser, VALID_TAG_COMMAND_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "one 2 " + VALID_TAG_COMMAND_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidTagNumber_failure() {
        assertParseFailure(parser, "1 two " + TAG_DESC_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
    }
}

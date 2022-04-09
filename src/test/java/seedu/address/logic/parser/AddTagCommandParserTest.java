package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COMMAND_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COMMAND_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class AddTagCommandParserTest {
    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        assertParseSuccess(parser, "1 " + VALID_TAG_COMMAND_FRIEND,
            new AddTagCommand(Index.fromOneBased(1), VALID_TAG_FRIEND.get(0)));
    }

    @Test
    public void parse_invalidTag_failure() {
        assertParseFailure(parser, "1 " + INVALID_TAG_COMMAND,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_noIndex_failure() {
        assertParseFailure(parser, VALID_TAG_COMMAND_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "one " + VALID_TAG_COMMAND_HUSBAND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }
}

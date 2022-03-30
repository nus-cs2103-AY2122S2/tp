package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveMembershipCommand;

public class RemoveMembershipParserTest {
    private RemoveMembershipParser parser = new RemoveMembershipParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // valid index
        assertParseSuccess(parser, "1",
                new RemoveMembershipCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveMembershipCommand.MESSAGE_USAGE));

        // invalid index string
        assertParseFailure(parser, "hello", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveMembershipCommand.MESSAGE_USAGE));
    }
}

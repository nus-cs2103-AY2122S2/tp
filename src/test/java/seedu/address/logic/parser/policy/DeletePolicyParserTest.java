package seedu.address.logic.parser.policy;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_INDEX_DESC_INSURANCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.policy.DeletePolicyCommand;

public class DeletePolicyParserTest {
    private DeletePolicyCommandParser parser = new DeletePolicyCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1" + POLICY_INDEX_DESC_INSURANCE,
                new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletePolicyCommand.MESSAGE_USAGE));
    }
}

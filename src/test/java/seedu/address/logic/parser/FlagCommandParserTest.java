package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.FlagCommand;
import seedu.address.logic.commands.FlagCommandTest;
import seedu.address.model.person.Flag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalNames.FULL_NAME_FIRST_PERSON;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the FlagCommand code. The path variation occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class FlagCommandParserTest {

    private FlagCommandParser parser = new FlagCommandParser();

    @Test
    public void parseFlag_validArgs_returnsDeleteCommand() {
        parser.setCommand("flag");
        assertParseSuccess(parser, "Alice Pauline",
                new FlagCommand(FULL_NAME_FIRST_PERSON, new Flag("true")));
    }

    @Test
    public void parseFlag_invalidArgs_throwsParseException() {
        parser.setCommand("flag");
        assertParseFailure(parser, "!",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FlagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseUnflag_validArgs_returnsDeleteCommand() {
        parser.setCommand("unflag");
        assertParseSuccess(parser, "Alice Pauline",
                new FlagCommand(FULL_NAME_FIRST_PERSON, new Flag("false")));
    }

    @Test
    public void parseUnflag_invalidArgs_throwsParseException() {
        parser.setCommand("unflag");
        assertParseFailure(parser, "!",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FlagCommand.MESSAGE_USAGE));
    }
}

package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FlagCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.model.person.Flag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalNames.FULL_NAME_FIRST_PERSON;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the HelpCommand code. The path variation occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_noArgs_returnsEmptyHelpCommand() {
        assertParseSuccess(parser, "", new HelpCommand());
    }

    @Test
    public void parseFlag_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "NotACommand",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }
}

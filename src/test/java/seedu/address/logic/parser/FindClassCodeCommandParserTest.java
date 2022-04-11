package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindClassCodeCommand;
import seedu.address.model.person.ClassCodeContainsKeywordsPredicate;

public class FindClassCodeCommandParserTest {
    private FindClassCodeCommandParser parser = new FindClassCodeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindClassCodeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindClassCodeCommand expectedFindClassCodeCommand =
                new FindClassCodeCommand(new ClassCodeContainsKeywordsPredicate(Arrays.asList("4A")));
        assertParseSuccess(parser, "4A", expectedFindClassCodeCommand);

    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindActivityCommand;
import seedu.address.model.activity.ActivityContainsKeywordsPredicate;

public class FindActivityCommandParserTest {
    private FindActivityCommandParser parser = new FindActivityCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindActivityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindActivityCommand expectedFindActivityCommand =
                new FindActivityCommand(new ActivityContainsKeywordsPredicate(Arrays.asList("4A")));
        assertParseSuccess(parser, "4A", expectedFindActivityCommand);
    }
}

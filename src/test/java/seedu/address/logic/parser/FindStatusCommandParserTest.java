package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindStatusCommand;
import seedu.address.model.person.StatusContainsKeywordsPredicate;

public class FindStatusCommandParserTest {
    private FindStatusCommandParser parser = new FindStatusCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindStatusCommand.MESSAGE_USAGE));

        // multiple whitespaces between keywords
        assertParseFailure(parser, " \n Positive \n \t Negative  \t", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindStatusCommand.ERRMSG_STATUS));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindStatusCommand expectedFindStatusCommand =
                new FindStatusCommand(new StatusContainsKeywordsPredicate(Arrays.asList("Positive")));
        assertParseSuccess(parser, "Positive", expectedFindStatusCommand);

    }
}

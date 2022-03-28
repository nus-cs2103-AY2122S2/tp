package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPersonCommand;

public class FindPersonCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        ArgumentMultimap dummy = new ArgumentMultimap();
        dummy.put(PREFIX_NAME, "Alice Bob");
        dummy.put(new Prefix(""), "");
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(dummy);
        assertParseSuccess(parser, " n/ Alice Bob", expectedFindPersonCommand);

        // multiple whitespaces between keywords
        // multiple whitespaces between keywords cannot be tested properly
        //assertParseSuccess(parser, " \n n/  Alice \n \t Bob  \t", expectedFindPersonCommand);
    }

}

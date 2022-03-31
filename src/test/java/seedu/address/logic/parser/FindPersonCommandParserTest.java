package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.SearchTypeUtil;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.model.entry.predicate.PersonContainsKeywordsPredicate;


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
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(
                List.<String>of("Alice", "Bob"), List.<String>of(""),
                List.<String>of(""), SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));
        FindPersonCommand expectedFindPersonCommand =
                new FindPersonCommand(predicate);
        assertParseSuccess(parser, " n/ Alice Bob", expectedFindPersonCommand);

        //multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/  Alice \n \t Bob  \t", expectedFindPersonCommand);
    }

}

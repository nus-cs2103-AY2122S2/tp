package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.SearchTypeUtil;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.model.entry.predicate.EventContainsKeywordsPredicate;


public class FindEventCommandParserTest {

    private FindEventCommandParser parser = new FindEventCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        EventContainsKeywordsPredicate predicate = new EventContainsKeywordsPredicate(
                List.<String>of("interview", "assessment"), List.<String>of(""), null, null,
                List.<String>of(""), List.<String>of(""), List.<String>of(""),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));
        FindEventCommand expectedFindEventCommand =
                new FindEventCommand(predicate);
        assertParseSuccess(parser, " n/ interview assessment ", expectedFindEventCommand);

        //multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/  interview \n \t assessment  \t", expectedFindEventCommand);
    }

}

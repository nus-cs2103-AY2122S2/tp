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
                List.of("Alice", "Bob"), List.of(""),
                List.of(""), SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));
        FindPersonCommand expectedFindPersonCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, " n/ Alice Bob", expectedFindPersonCommand);

        //multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/  Alice \n \t Bob  \t", expectedFindPersonCommand);

        predicate = new PersonContainsKeywordsPredicate(List.of("Carl"), List.of("DBSSS"),
                List.of("hr"), SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.ARCHIVED_ONLY));
        expectedFindPersonCommand = new FindPersonCommand(predicate);

        assertParseSuccess(parser, " n/Carl s/archived c/DBSSS t/hr", expectedFindPersonCommand);
    }

}

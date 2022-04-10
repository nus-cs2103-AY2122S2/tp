package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.SearchTypeUtil;
import seedu.address.logic.commands.FindCompanyCommand;
import seedu.address.model.entry.predicate.CompanyContainsKeywordsPredicate;


public class FindCompanyCommandParserTest {

    private FindCompanyCommandParser parser = new FindCompanyCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCompanyCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(
                List.of("dbsss", "shopsg"), List.of(""),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.UNARCHIVED_ONLY));
        FindCompanyCommand expectedFindCompanyCommand = new FindCompanyCommand(predicate);
        assertParseSuccess(parser, " n/ dbsss shopsg", expectedFindCompanyCommand);

        //multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/  dbsss \n \t shopsg  \t", expectedFindCompanyCommand);

        predicate = new CompanyContainsKeywordsPredicate(
                List.of("DBSSS"), List.of("fintech"),
                SearchTypeUtil.getPredicate(SearchTypeUtil.SearchType.ALL));
        expectedFindCompanyCommand = new FindCompanyCommand(predicate);

        assertParseSuccess(parser, " n/DBSSS t/fintech s/all", expectedFindCompanyCommand);
    }

}

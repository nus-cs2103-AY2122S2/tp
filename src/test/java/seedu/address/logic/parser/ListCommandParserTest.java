package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.ListType;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.logic.commands.ListCompanyCommand;
import seedu.address.logic.commands.ListEventCommand;
import seedu.address.logic.commands.ListPersonCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ListCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ListCommandParserTest {
    private static final String UNARCHIVED_INPUT = " " + PREFIX_SEARCH_TYPE + "unarchived";
    private static final String ARCHIVED_INPUT = " " + PREFIX_SEARCH_TYPE + "archived";
    private static final String ALL_INPUT = " " + PREFIX_SEARCH_TYPE + "all";

    private final ListCommandParser listCompanyCommandParser = new ListCommandParser(ListType.COMPANY);
    private final ListCommandParser listPersonCommandParser = new ListCommandParser(ListType.PERSON);
    private final ListCommandParser listEventCommandParser = new ListCommandParser(ListType.EVENT);

    @Test
    public void parse_validArgs_returnsListCommand() {
        assertParseSuccess(listCompanyCommandParser, UNARCHIVED_INPUT,
                new ListCompanyCommand(SearchType.UNARCHIVED_ONLY));

        assertParseSuccess(listCompanyCommandParser, ARCHIVED_INPUT,
                new ListCompanyCommand(SearchType.ARCHIVED_ONLY));

        assertParseSuccess(listCompanyCommandParser, ALL_INPUT,
                new ListCompanyCommand(SearchType.ALL));

        assertParseSuccess(listPersonCommandParser, UNARCHIVED_INPUT,
                new ListPersonCommand(SearchType.UNARCHIVED_ONLY));

        assertParseSuccess(listPersonCommandParser, ARCHIVED_INPUT,
                new ListPersonCommand(SearchType.ARCHIVED_ONLY));

        assertParseSuccess(listPersonCommandParser, ALL_INPUT,
                new ListPersonCommand(SearchType.ALL));

        assertParseSuccess(listEventCommandParser, UNARCHIVED_INPUT,
                new ListEventCommand(SearchType.UNARCHIVED_ONLY));

        assertParseSuccess(listEventCommandParser, ARCHIVED_INPUT,
                new ListEventCommand(SearchType.ARCHIVED_ONLY));

        assertParseSuccess(listEventCommandParser, ALL_INPUT,
                new ListEventCommand(SearchType.ALL));
    }

    @Test
    public void parse_noSearchTypeArgs_returnsListCommandUnarchived() {
        assertParseSuccess(listCompanyCommandParser, " ", new ListCompanyCommand(SearchType.UNARCHIVED_ONLY));

        assertParseSuccess(listPersonCommandParser, " ", new ListPersonCommand(SearchType.UNARCHIVED_ONLY));

        assertParseSuccess(listEventCommandParser, " ", new ListEventCommand(SearchType.UNARCHIVED_ONLY));
    }

    @Test
    public void parse_invalidSearchTypeArgs_returnsListCommandUnarchived() {
        assertParseFailure(listCompanyCommandParser, " s/a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCompanyCommand.MESSAGE_USAGE));

        assertParseFailure(listPersonCommandParser, " s/a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListPersonCommand.MESSAGE_USAGE));

        assertParseFailure(listEventCommandParser, " s/a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEventCommand.MESSAGE_USAGE));
    }
}

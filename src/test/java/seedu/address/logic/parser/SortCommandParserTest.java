package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDERING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.ListType;
import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.logic.commands.SortCompanyCommand;
import seedu.address.logic.commands.SortEventCommand;
import seedu.address.logic.commands.SortPersonCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ListCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class SortCommandParserTest {
    private static final String UNARCHIVED_ASCENDING_INPUT = " " + PREFIX_SEARCH_TYPE + "unarchived "
            + PREFIX_ORDERING + "ascending";
    private static final String UNARCHIVED_DESCENDING_INPUT = " " + PREFIX_SEARCH_TYPE + "unarchived "
            + PREFIX_ORDERING + "descending";
    private static final String ARCHIVED_ASCENDING_INPUT = " " + PREFIX_SEARCH_TYPE + "archived "
            + PREFIX_ORDERING + "ascending";
    private static final String ARCHIVED_DESCENDING_INPUT = " " + PREFIX_SEARCH_TYPE + "archived "
            + PREFIX_ORDERING + "descending";
    private static final String ALL_ASCENDING_INPUT = " " + PREFIX_SEARCH_TYPE + "all "
            + PREFIX_ORDERING + "ascending";
    private static final String ALL_DESCENDING_INPUT = " " + PREFIX_SEARCH_TYPE + "all "
            + PREFIX_ORDERING + "descending";

    private final SortCommandParser sortCompanyCommandParser = new SortCommandParser(ListType.COMPANY);
    private final SortCommandParser sortPersonCommandParser = new SortCommandParser(ListType.PERSON);
    private final SortCommandParser sortEventCommandParser = new SortCommandParser(ListType.EVENT);

    @Test
    public void parse_validArgs_returnsCompanyListCommand() {
        assertParseSuccess(sortCompanyCommandParser, UNARCHIVED_ASCENDING_INPUT,
                new SortCompanyCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING));

        assertParseSuccess(sortCompanyCommandParser, UNARCHIVED_DESCENDING_INPUT,
                new SortCompanyCommand(SearchType.UNARCHIVED_ONLY, Ordering.DESCENDING));

        assertParseSuccess(sortCompanyCommandParser, ARCHIVED_ASCENDING_INPUT,
                new SortCompanyCommand(SearchType.ARCHIVED_ONLY, Ordering.ASCENDING));

        assertParseSuccess(sortCompanyCommandParser, ARCHIVED_DESCENDING_INPUT,
                new SortCompanyCommand(SearchType.ARCHIVED_ONLY, Ordering.DESCENDING));

        assertParseSuccess(sortCompanyCommandParser, ALL_ASCENDING_INPUT,
                new SortCompanyCommand(SearchType.ALL, Ordering.ASCENDING));

        assertParseSuccess(sortCompanyCommandParser, ALL_DESCENDING_INPUT,
                new SortCompanyCommand(SearchType.ALL, Ordering.DESCENDING));
    }

    @Test
    public void parse_validArgs_returnsPersonListCommand() {
        assertParseSuccess(sortPersonCommandParser, UNARCHIVED_ASCENDING_INPUT,
                new SortPersonCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING));

        assertParseSuccess(sortPersonCommandParser, UNARCHIVED_DESCENDING_INPUT,
                new SortPersonCommand(SearchType.UNARCHIVED_ONLY, Ordering.DESCENDING));

        assertParseSuccess(sortPersonCommandParser, ARCHIVED_ASCENDING_INPUT,
                new SortPersonCommand(SearchType.ARCHIVED_ONLY, Ordering.ASCENDING));

        assertParseSuccess(sortPersonCommandParser, ARCHIVED_DESCENDING_INPUT,
                new SortPersonCommand(SearchType.ARCHIVED_ONLY, Ordering.DESCENDING));

        assertParseSuccess(sortPersonCommandParser, ALL_ASCENDING_INPUT,
                new SortPersonCommand(SearchType.ALL, Ordering.ASCENDING));

        assertParseSuccess(sortPersonCommandParser, ALL_DESCENDING_INPUT,
                new SortPersonCommand(SearchType.ALL, Ordering.DESCENDING));
    }

    @Test
    public void parse_validArgs_returnsEventListCommand() {
        assertParseSuccess(sortEventCommandParser, UNARCHIVED_ASCENDING_INPUT,
                new SortEventCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING));

        assertParseSuccess(sortEventCommandParser, UNARCHIVED_DESCENDING_INPUT,
                new SortEventCommand(SearchType.UNARCHIVED_ONLY, Ordering.DESCENDING));

        assertParseSuccess(sortEventCommandParser, ARCHIVED_ASCENDING_INPUT,
                new SortEventCommand(SearchType.ARCHIVED_ONLY, Ordering.ASCENDING));

        assertParseSuccess(sortEventCommandParser, ARCHIVED_DESCENDING_INPUT,
                new SortEventCommand(SearchType.ARCHIVED_ONLY, Ordering.DESCENDING));

        assertParseSuccess(sortEventCommandParser, ALL_ASCENDING_INPUT,
                new SortEventCommand(SearchType.ALL, Ordering.ASCENDING));

        assertParseSuccess(sortEventCommandParser, ALL_DESCENDING_INPUT,
                new SortEventCommand(SearchType.ALL, Ordering.DESCENDING));
    }

    @Test
    public void parse_noSearchTypeArgs_returnsListCommandUnarchivedAscending() {
        assertParseSuccess(sortCompanyCommandParser, " ",
            new SortCompanyCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING));

        assertParseSuccess(sortPersonCommandParser, " ",
            new SortPersonCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING));

        assertParseSuccess(sortEventCommandParser, " ",
            new SortEventCommand(SearchType.UNARCHIVED_ONLY, Ordering.ASCENDING));
    }

    @Test
    public void parse_invalidSearchTypeArgs_returnsListCommandUnarchived() {
        assertParseFailure(sortCompanyCommandParser, " s/a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCompanyCommand.MESSAGE_USAGE));

        assertParseFailure(sortPersonCommandParser, " s/a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortPersonCommand.MESSAGE_USAGE));

        assertParseFailure(sortEventCommandParser, " s/a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortEventCommand.MESSAGE_USAGE));
    }
}

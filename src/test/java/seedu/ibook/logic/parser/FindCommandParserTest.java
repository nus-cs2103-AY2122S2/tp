package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.DESCRIPTION_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.END_PRICE_FULL_0;
import static seedu.ibook.logic.commands.CommandTestUtil.END_PRICE_FULL_100;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.PRICE_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.START_PRICE_FULL_0;
import static seedu.ibook.logic.commands.CommandTestUtil.START_PRICE_FULL_100;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ibook.testutil.TypicalProductFilters.CATEGORY_FILTER_B;
import static seedu.ibook.testutil.TypicalProductFilters.DESCRIPTION_FILTER_B;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_B;
import static seedu.ibook.testutil.TypicalProductFilters.PRICE_FILTER_0_100;
import static seedu.ibook.testutil.TypicalProductFilters.PRICE_FILTER_B;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.product.FindCommand;
import seedu.ibook.model.product.PriceRange;
import seedu.ibook.model.product.filters.AttributeFilter;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        List<AttributeFilter> filterList =
            List.of(NAME_FILTER_B, CATEGORY_FILTER_B, DESCRIPTION_FILTER_B, PRICE_FILTER_B);
        FindCommand expectedFindCommand = new FindCommand(filterList);
        assertParseSuccess(parser, NAME_FULL_B + CATEGORY_FULL_B + DESCRIPTION_FULL_B + PRICE_FULL_B,
            expectedFindCommand);
    }

    @Test
    public void parse_someFieldsMissing_success() {
        List<AttributeFilter> filterList = List.of(NAME_FILTER_B, PRICE_FILTER_B);
        FindCommand expectedFindCommand = new FindCommand(filterList);
        assertParseSuccess(parser, NAME_FULL_B + PRICE_FULL_B, expectedFindCommand);
    }

    @Test
    public void parse_noFieldsPresent_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_startPriceWithoutEndPrice_throwsParseException() {
        assertParseFailure(parser, START_PRICE_FULL_0,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_REQUIRE_START_END_PRICE));
    }

    @Test
    public void parse_endPriceWithoutStartPrice_throwsParseException() {
        assertParseFailure(parser, END_PRICE_FULL_100,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_REQUIRE_START_END_PRICE));
    }

    @Test
    public void parse_startPriceSmallerThanEndPrice_throwsParseException() {
        assertParseFailure(parser, START_PRICE_FULL_100 + END_PRICE_FULL_0, PriceRange.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_startPriceWithEndPrice_success() {
        List<AttributeFilter> filterList = List.of(PRICE_FILTER_0_100);
        FindCommand expectedFindCommand = new FindCommand(filterList);
        assertParseSuccess(parser, START_PRICE_FULL_0 + END_PRICE_FULL_100, expectedFindCommand);
    }
}

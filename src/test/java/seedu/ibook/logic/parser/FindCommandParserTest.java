package seedu.ibook.logic.parser;

import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.DESCRIPTION_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.PRICE_FULL_B;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ibook.testutil.TypicalProductFilters.CATEGORY_FILTER_B;
import static seedu.ibook.testutil.TypicalProductFilters.DESCRIPTION_FILTER_B;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_B;
import static seedu.ibook.testutil.TypicalProductFilters.PRICE_FILTER_B;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.FindCommand;
import seedu.ibook.model.product.filters.AttributeFilter;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<AttributeFilter> filterList = new ArrayList<>();
        filterList.add(NAME_FILTER_B);
        filterList.add(CATEGORY_FILTER_B);
        filterList.add(DESCRIPTION_FILTER_B);
        filterList.add(PRICE_FILTER_B);
        FindCommand expectedFindCommand = new FindCommand(filterList);
        assertParseSuccess(parser, NAME_FULL_B + CATEGORY_FULL_B + DESCRIPTION_FULL_B + PRICE_FULL_B,
            expectedFindCommand);

        // with null values
        filterList = new ArrayList<>();
        filterList.add(NAME_FILTER_B);
        filterList.add(PRICE_FILTER_B);

        FindCommand expectedFindCommand2 = new FindCommand(filterList);

        assertParseSuccess(parser, NAME_FULL_B + PRICE_FULL_B, expectedFindCommand2);
    }

}

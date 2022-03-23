package seedu.ibook.logic.parser;

import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.FindCommand;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.filters.NameFilter;
import seedu.ibook.model.product.filters.PriceFilter;
import seedu.ibook.model.product.filters.ProductFulfillsFiltersPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validArgs_returnsFindCommand() {

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ProductFulfillsFiltersPredicate(new Product(
                        new Name("Maggi"),
                        new Category("noodles"),
                        new Description("tasty"),
                        new Price("3.00"))));
        assertParseSuccess(parser, " n~Maggi c~noodles d~tasty p~3.00", expectedFindCommand);

        // with null values
        ProductFulfillsFiltersPredicate predicate = new ProductFulfillsFiltersPredicate();
        predicate.addFilter(new NameFilter(new Name("Maggi")));
        predicate.addFilter(new PriceFilter(new Price("3.00")));

        FindCommand expectedFindCommand2 = new FindCommand(predicate);

        assertParseSuccess(parser, " n~Maggi p~3.00", expectedFindCommand2);
    }

}

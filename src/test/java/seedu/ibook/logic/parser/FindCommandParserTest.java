package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.FindCommand;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.ExpiryDate;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.ProductFulfillsFiltersPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {

        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ProductFulfillsFiltersPredicate(new Product(
                        new Name("Maggi"),
                        new Category("noodles"),
                        new ExpiryDate("2022-01-01"),
                        new Description("tasty"),
                        new Price("3.00"))));
        assertParseSuccess(parser, " n: Maggi c: noodles e: 2022-01-01 d: tasty p: 3.00", expectedFindCommand);

        // with null values
        FindCommand expectedFindCommand2 =
                new FindCommand(new ProductFulfillsFiltersPredicate(new Product (
                        new Name("Maggi"),
                        Category.WILDCATEGORY,
                        new ExpiryDate("2022-01-01"),
                        Description.WILDDESCRIPTION,
                        new Price("3.00")))
                );

        assertParseSuccess(parser, " n: Maggi e: 2022-01-01 p: 3.00", expectedFindCommand2);


    }

}

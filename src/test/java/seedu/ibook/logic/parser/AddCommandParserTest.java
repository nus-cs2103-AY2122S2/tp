package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.DESCRIPTION_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.DESCRIPTION_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_PRICE_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.ibook.logic.commands.CommandTestUtil.PRICE_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.PRICE_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.AddCommand;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ProductBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        Product expectedProduct = new ProductBuilder(PRODUCT_A).build();
        assertParseSuccess(parser,
            NAME_FULL_A + CATEGORY_FULL_A + DESCRIPTION_FULL_A + PRICE_FULL_A,
            new AddCommand(expectedProduct));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
            VALID_NAME_B + CATEGORY_FULL_B + DESCRIPTION_FULL_B + PRICE_FULL_B,
            expectedMessage);

        // missing description prefix
        assertParseFailure(parser,
            NAME_FULL_B + CATEGORY_FULL_B + VALID_DESCRIPTION_B + PRICE_FULL_B,
            expectedMessage);

        // missing price prefix
        assertParseFailure(parser,
            NAME_FULL_B + CATEGORY_FULL_B + VALID_DESCRIPTION_B + VALID_PRICE_B,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
            VALID_NAME_B + VALID_CATEGORY_B + VALID_DESCRIPTION_B + VALID_PRICE_B,
            expectedMessage);
    }

    @Test
    public void parse_setDefaultCategory_success() {
        // missing category
        Product expectedProduct = new ProductBuilder(PRODUCT_A).withCategory(Category.DEFAULT_CATEGORY).build();
        assertParseSuccess(parser, NAME_FULL_A + DESCRIPTION_FULL_A + PRICE_FULL_A,
            new AddCommand(expectedProduct));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + CATEGORY_FULL_B + DESCRIPTION_FULL_B
            + PRICE_FULL_B, Name.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, NAME_FULL_B + INVALID_CATEGORY_DESC + DESCRIPTION_FULL_B
            + PRICE_FULL_B, Category.MESSAGE_CONSTRAINTS);

        // invalid price
        assertParseFailure(parser, NAME_FULL_B + CATEGORY_FULL_B + DESCRIPTION_FULL_B
            + INVALID_PRICE_DESC, Price.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_FULL_B + CATEGORY_FULL_B
                + DESCRIPTION_FULL_B + PRICE_FULL_B,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}

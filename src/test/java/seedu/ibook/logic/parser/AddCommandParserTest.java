package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.DESCRIPTION_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.DESCRIPTION_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.DISCOUNT_RATE_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.DISCOUNT_RATE_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.DISCOUNT_START_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.DISCOUNT_START_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_DISCOUNT_RATE_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_DISCOUNT_START_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_PRICE_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.ibook.logic.commands.CommandTestUtil.PRICE_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.PRICE_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DISCOUNT_RATE_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DISCOUNT_START_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.product.AddCommand;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.DiscountRate;
import seedu.ibook.model.product.DiscountStart;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ProductBuilder;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        Product expectedProduct = new ProductBuilder(PRODUCT_A).build();
        assertParseSuccess(parser,
            NAME_FULL_A + CATEGORY_FULL_A + DESCRIPTION_FULL_A
                    + PRICE_FULL_A + DISCOUNT_RATE_FULL_A + DISCOUNT_START_FULL_A,
            new AddCommand(expectedProduct));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
            VALID_NAME_B + CATEGORY_FULL_B + DESCRIPTION_FULL_B
                    + PRICE_FULL_B + DISCOUNT_RATE_FULL_B + DISCOUNT_START_FULL_B,
            expectedMessage);

        // missing price prefix
        assertParseFailure(parser,
            NAME_FULL_B + CATEGORY_FULL_B + VALID_DESCRIPTION_B
                    + VALID_PRICE_B + DISCOUNT_RATE_FULL_B + DISCOUNT_START_FULL_B,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
            VALID_NAME_B + VALID_CATEGORY_B + VALID_DESCRIPTION_B
                    + VALID_PRICE_B + VALID_DISCOUNT_RATE_B + VALID_DISCOUNT_START_B,
            expectedMessage);
    }

    @Test
    public void parse_setDefaultCategory_success() {
        // missing category
        Product expectedProduct = new ProductBuilder(PRODUCT_A)
                .withCategory(Category.DEFAULT_CATEGORY)
                .withDiscountRate(DiscountRate.DEFAULT_DISCOUNT_RATE)
                .withDiscountStart(DiscountStart.DEFAULT_DISCOUNT_START)
                .build();
        assertParseSuccess(parser, NAME_FULL_A + DESCRIPTION_FULL_A + PRICE_FULL_A,
            new AddCommand(expectedProduct));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid price
        assertParseFailure(parser, NAME_FULL_B + CATEGORY_FULL_B + DESCRIPTION_FULL_B
            + INVALID_PRICE_DESC + DISCOUNT_RATE_FULL_B + DISCOUNT_START_FULL_B, Price.MESSAGE_CONSTRAINTS);

        // invalid discountRate
        assertParseFailure(parser, NAME_FULL_B + CATEGORY_FULL_B + DESCRIPTION_FULL_B
                + PRICE_FULL_B + INVALID_DISCOUNT_RATE_DESC + DISCOUNT_START_FULL_B, DiscountRate.MESSAGE_CONSTRAINTS);

        // invalid discountStart
        assertParseFailure(parser, NAME_FULL_B + CATEGORY_FULL_B + DESCRIPTION_FULL_B
                + PRICE_FULL_B + DISCOUNT_RATE_FULL_B + INVALID_DISCOUNT_START_DESC, DiscountStart.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_FULL_B + CATEGORY_FULL_B
                + DESCRIPTION_FULL_B + PRICE_FULL_B + DISCOUNT_RATE_FULL_B + DISCOUNT_START_FULL_B,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}

package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.DESCRIPTION_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.DESCRIPTION_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.EXPIRY_DATE_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.EXPIRY_DATE_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.PRICE_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.PRICE_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.AddCommand;
import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ProductBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        Product expectedProduct = new ProductBuilder(PRODUCT_A).build();
        assertParseSuccess(parser,
            NAME_FULL_A + CATEGORY_FULL_A + EXPIRY_DATE_FULL_A + DESCRIPTION_FULL_A + PRICE_FULL_A,
            new AddCommand(expectedProduct));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
            VALID_NAME_B + CATEGORY_FULL_B + EXPIRY_DATE_FULL_B + DESCRIPTION_FULL_B + PRICE_FULL_B,
            expectedMessage);

        // missing category prefix
        assertParseFailure(parser,
            NAME_FULL_B + VALID_CATEGORY_B + EXPIRY_DATE_FULL_B + DESCRIPTION_FULL_B + PRICE_FULL_B,
            expectedMessage);

        // missing expiry date prefix
        assertParseFailure(parser,
            NAME_FULL_B + CATEGORY_FULL_B + VALID_EXPIRY_DATE_B + DESCRIPTION_FULL_B + PRICE_FULL_B,
            expectedMessage);

        // missing description prefix
        assertParseFailure(parser,
            NAME_FULL_B + CATEGORY_FULL_B + EXPIRY_DATE_FULL_B + VALID_DESCRIPTION_B + PRICE_FULL_B,
            expectedMessage);

        // missing price prefix
        assertParseFailure(parser,
            NAME_FULL_B + CATEGORY_FULL_B + EXPIRY_DATE_FULL_B + VALID_DESCRIPTION_B + VALID_PRICE_B,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
            VALID_NAME_B + VALID_CATEGORY_B + VALID_EXPIRY_DATE_B + VALID_DESCRIPTION_B + VALID_PRICE_B,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
    }
}

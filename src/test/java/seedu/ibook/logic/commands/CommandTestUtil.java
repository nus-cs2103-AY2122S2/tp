package seedu.ibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DISCOUNT_RATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DISCOUNT_START;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_END_PRICE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_START_PRICE;
import static seedu.ibook.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.ibook.commons.core.index.Index;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.logic.commands.item.UpdateItemCommand.UpdateItemDescriptor;
import seedu.ibook.logic.commands.product.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.model.IBook;
import seedu.ibook.model.Model;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.filters.ProductFilter;
import seedu.ibook.testutil.UpdateItemDescriptorBuilder;
import seedu.ibook.testutil.UpdateProductDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_INDEX_A = "1";
    public static final String INVALID_INDEX_A = "0";
    public static final String INVALID_INDEX_B = "-1";
    public static final String OVERFLOW_INDEX = "11111111111111";

    public static final String VALID_NAME_A = "Item A";
    public static final String VALID_NAME_B = "Item B";
    public static final String VALID_CATEGORY_A = "Category A";
    public static final String VALID_CATEGORY_B = "Category B";
    public static final String VALID_DESCRIPTION_A = "Description A";
    public static final String VALID_DESCRIPTION_B = "Description B";
    public static final String VALID_PRICE_A = "$1.99";
    public static final String VALID_PRICE_B = "$2.99";
    public static final String VALID_PRICE_0 = "$0";
    public static final String VALID_PRICE_100 = "$100";
    public static final String VALID_DISCOUNT_RATE_A = "25";
    public static final String VALID_DISCOUNT_RATE_B = "30";
    public static final String VALID_DISCOUNT_START_A = "1";
    public static final String VALID_DISCOUNT_START_B = "4";
    public static final String VALID_EXPIRY_DATE_A = "2022-03-08";
    public static final String VALID_EXPIRY_DATE_B = "2022-03-18";
    public static final String VALID_QUANTITY_A = "10";
    public static final String VALID_QUANTITY_B = "4";

    public static final String NAME_FULL_A = " " + PREFIX_NAME + VALID_NAME_A;
    public static final String NAME_FULL_B = " " + PREFIX_NAME + VALID_NAME_B;
    public static final String CATEGORY_FULL_A = " " + PREFIX_CATEGORY + VALID_CATEGORY_A;
    public static final String CATEGORY_FULL_B = " " + PREFIX_CATEGORY + VALID_CATEGORY_B;
    public static final String DESCRIPTION_FULL_A = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_A;
    public static final String DESCRIPTION_FULL_B = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_B;
    public static final String PRICE_FULL_A = " " + PREFIX_PRICE + VALID_PRICE_A;
    public static final String PRICE_FULL_B = " " + PREFIX_PRICE + VALID_PRICE_B;
    public static final String START_PRICE_FULL_0 = " " + PREFIX_START_PRICE + VALID_PRICE_0;
    public static final String START_PRICE_FULL_100 = " " + PREFIX_START_PRICE + VALID_PRICE_100;
    public static final String END_PRICE_FULL_100 = " " + PREFIX_END_PRICE + VALID_PRICE_100;
    public static final String END_PRICE_FULL_0 = " " + PREFIX_END_PRICE + VALID_PRICE_0;
    public static final String DISCOUNT_RATE_FULL_A = " " + PREFIX_DISCOUNT_RATE + VALID_DISCOUNT_RATE_A;
    public static final String DISCOUNT_RATE_FULL_B = " " + PREFIX_DISCOUNT_RATE + VALID_DISCOUNT_RATE_B;
    public static final String DISCOUNT_START_FULL_A = " " + PREFIX_DISCOUNT_START + VALID_DISCOUNT_START_A;
    public static final String DISCOUNT_START_FULL_B = " " + PREFIX_DISCOUNT_START + VALID_DISCOUNT_START_B;
    public static final String EXPIRY_DATE_FULL_A = " " + PREFIX_EXPIRY_DATE + VALID_EXPIRY_DATE_A;
    public static final String EXPIRY_DATE_FULL_B = " " + PREFIX_EXPIRY_DATE + VALID_EXPIRY_DATE_B;
    public static final String QUANTITY_FULL_A = " " + PREFIX_QUANTITY + VALID_QUANTITY_A;
    public static final String QUANTITY_FULL_B = " " + PREFIX_QUANTITY + VALID_QUANTITY_B;

    public static final String INVALID_PRICE_DESC = " " + PREFIX_PRICE + "-1"; // price must be a positive amount
    // discount rate must be less than 100;
    public static final String INVALID_DISCOUNT_RATE_DESC = " " + PREFIX_DISCOUNT_RATE + "101";
    // discount start must be a positive number;
    public static final String INVALID_DISCOUNT_START_DESC = " " + PREFIX_DISCOUNT_START + "-1";
    public static final String INVALID_EXPIRY_DATE_DESC = " " + PREFIX_EXPIRY_DATE + "2022 03 08"; // incorrect format
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_QUANTITY + "-2"; // quantity must be non-negative


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final UpdateProductDescriptor PRODUCT_DESCRIPTOR_A;
    public static final UpdateProductDescriptor PRODUCT_DESCRIPTOR_B;
    public static final UpdateItemDescriptor ITEM_DESCRIPTOR_A;
    public static final UpdateItemDescriptor ITEM_DESCRIPTOR_B;

    static {
        PRODUCT_DESCRIPTOR_A = new UpdateProductDescriptorBuilder().withName(VALID_NAME_A)
                .withCategory(VALID_CATEGORY_A)
                .withDescription(VALID_DESCRIPTION_A)
                .withPrice(VALID_PRICE_A)
                .withDiscountRate(VALID_DISCOUNT_RATE_A)
                .withDiscountStart(VALID_DISCOUNT_START_A)
                .build();
        PRODUCT_DESCRIPTOR_B = new UpdateProductDescriptorBuilder().withName(VALID_NAME_B)
                .withCategory(VALID_CATEGORY_B)
                .withDescription(VALID_DESCRIPTION_B)
                .withPrice(VALID_PRICE_B)
                .withDiscountRate(VALID_DISCOUNT_RATE_B)
                .withDiscountStart(VALID_DISCOUNT_START_B)
                .build();
        ITEM_DESCRIPTOR_A = new UpdateItemDescriptorBuilder().withExpiryDate(VALID_EXPIRY_DATE_A)
                .withQuantity(VALID_QUANTITY_A)
                .build();
        ITEM_DESCRIPTOR_B = new UpdateItemDescriptorBuilder().withExpiryDate(VALID_EXPIRY_DATE_B)
                .withQuantity(VALID_QUANTITY_B)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */

    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */

    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the ibook, filtered product list and selected product in {@code actualModel} remain unchanged
     */

    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        IBook expectedIBook = new IBook(actualModel.getIBook());
        List<Product> expectedFilteredList = new ArrayList<>(actualModel.getFilteredProductList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedIBook, actualModel.getIBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredProductList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the product at the given {@code targetIndex} in the
     * {@code model}'s ibook.
     */

    public static void showProductAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProductList().size());

        Product product = model.getFilteredProductList().get(targetIndex.getZeroBased());
        model.updateProductFilters(new ProductFilter(product));

        assertEquals(1, model.getFilteredProductList().size());
    }
}

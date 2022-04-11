package seedu.ibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ibook.storage.JsonAdaptedProduct.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.exceptions.IllegalValueException;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.DiscountRate;
import seedu.ibook.model.product.DiscountStart;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;

public class JsonAdaptedProductTest {

    private static final String INVALID_NAME = " ";
    private static final String INVALID_CATEGORY = " ";
    private static final String INVALID_PRICE = "-42.00";
    private static final String INVALID_DISCOUNT_RATE = "101";
    private static final String INVALID_DISCOUNT_START = "-4";

    private static final String VALID_NAME = KAYA_BREAD.getName().fullName;
    private static final String VALID_CATEGORY = KAYA_BREAD.getCategory().fullCategoryName;
    private static final String VALID_DESCRIPTION = KAYA_BREAD.getDescription().fullDescription;
    private static final String VALID_PRICE = KAYA_BREAD.getPrice().toString();
    private static final String VALID_DISCOUNT_RATE = KAYA_BREAD.getDiscountRate().toString();
    private static final String VALID_DISCOUNT_START = KAYA_BREAD.getDiscountStart().toString();


    @Test
    public void toModelType_validProductDetails_returnsProduct() throws Exception {
        JsonAdaptedProduct product = new JsonAdaptedProduct(KAYA_BREAD);
        assertEquals(KAYA_BREAD, product.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(INVALID_NAME, VALID_CATEGORY, VALID_DESCRIPTION,
                        VALID_PRICE, VALID_DISCOUNT_RATE, VALID_DISCOUNT_START, null);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(null, VALID_CATEGORY, VALID_DESCRIPTION, VALID_PRICE,
                    VALID_DISCOUNT_RATE, VALID_DISCOUNT_START, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_invalidCategory_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, INVALID_CATEGORY, VALID_DESCRIPTION, VALID_PRICE,
                    VALID_DISCOUNT_RATE, VALID_DISCOUNT_START, null);
        String expectedMessage = Category.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullCategory_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, null, VALID_DESCRIPTION, VALID_PRICE,
                    VALID_DISCOUNT_RATE, VALID_DISCOUNT_START, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Category.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, null, VALID_PRICE,
                    VALID_DISCOUNT_RATE, VALID_DISCOUNT_START, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, VALID_DESCRIPTION, INVALID_PRICE,
                    VALID_DISCOUNT_RATE, VALID_DISCOUNT_START, null);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, VALID_DESCRIPTION, null,
                    VALID_DISCOUNT_RATE, VALID_DISCOUNT_START, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_invalidDiscountRate_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, VALID_DESCRIPTION, VALID_PRICE,
                    INVALID_DISCOUNT_RATE, VALID_DISCOUNT_START, null);
        String expectedMessage = DiscountRate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullDiscountRate_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, VALID_DESCRIPTION, VALID_PRICE,
                        null, VALID_DISCOUNT_START, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DiscountRate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_invalidDiscountStart_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, VALID_DESCRIPTION, VALID_PRICE,
                    VALID_DISCOUNT_RATE, INVALID_DISCOUNT_START, null);
        String expectedMessage = DiscountStart.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullDiscountStart_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, VALID_DESCRIPTION, VALID_PRICE,
                    VALID_DISCOUNT_RATE, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DiscountStart.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }
}

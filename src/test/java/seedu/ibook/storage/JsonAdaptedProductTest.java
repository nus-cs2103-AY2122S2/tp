package seedu.ibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ibook.storage.JsonAdaptedProduct.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.exceptions.IllegalValueException;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.ExpiryDate;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;

public class JsonAdaptedProductTest {

    private static final String INVALID_NAME = " ";
    private static final String INVALID_CATEGORY = " ";
    private static final LocalDate INVALID_EXPIRY_DATE = LocalDate.parse("0999-10-10");
    private static final String INVALID_DESCRIPTION = " ";
    private static final Double INVALID_PRICE = -42.00;

    private static final String VALID_NAME = KAYA_BREAD.getName().fullName;
    private static final String VALID_CATEGORY = KAYA_BREAD.getCategory().fullCategoryName;
    private static final LocalDate VALID_EXPIRY_DATE = KAYA_BREAD.getExpiryDate().expiryDate;
    private static final String VALID_DESCRIPTION = KAYA_BREAD.getDescription().fullDescription;
    private static final Double VALID_PRICE = KAYA_BREAD.getPrice().price;

    @Test
    public void toModelType_validProductDetails_returnsProduct() throws Exception {
        JsonAdaptedProduct product = new JsonAdaptedProduct(KAYA_BREAD);
        assertEquals(KAYA_BREAD, product.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(INVALID_NAME, VALID_CATEGORY, VALID_EXPIRY_DATE, VALID_DESCRIPTION, VALID_PRICE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(null, VALID_CATEGORY, VALID_EXPIRY_DATE, VALID_DESCRIPTION, VALID_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_invalidCategory_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, INVALID_CATEGORY, VALID_EXPIRY_DATE, VALID_DESCRIPTION, VALID_PRICE);
        String expectedMessage = Category.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullCategory_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, null, VALID_EXPIRY_DATE, VALID_DESCRIPTION, VALID_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Category.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, INVALID_EXPIRY_DATE, VALID_DESCRIPTION, VALID_PRICE);
        String expectedMessage = ExpiryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, null, VALID_DESCRIPTION, VALID_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpiryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, VALID_EXPIRY_DATE, INVALID_DESCRIPTION, VALID_PRICE);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, VALID_EXPIRY_DATE, null, VALID_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, VALID_EXPIRY_DATE, VALID_DESCRIPTION, INVALID_PRICE);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_NAME, VALID_CATEGORY, VALID_EXPIRY_DATE, VALID_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }
}

package seedu.ibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ibook.storage.JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalItems.Q5_2022_03_01;
import static seedu.ibook.testutil.TypicalItems.Q5_2022_03_01_KAYA;
import static seedu.ibook.testutil.TypicalItems.QUANTITY_5;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.exceptions.IllegalValueException;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.Quantity;

class JsonAdaptedItemTest {

    private static final String INVALID_EXPIRY_DATE = "2022 03 08"; // incorrect format
    private static final String INVALID_QUANTITY = "-2"; // quantity must be non-negative

    private static final String VALID_EXPIRY_DATE = "2022-03-01";
    private static final String VALID_QUANTITY = QUANTITY_5;

    @Test
    void toModelType_validItem_returnsItemDescriptor() throws Exception {
        JsonAdaptedItem itemDesc = new JsonAdaptedItem(Q5_2022_03_01_KAYA);
        assertEquals(Q5_2022_03_01, itemDesc.toModelType());
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(INVALID_EXPIRY_DATE, VALID_QUANTITY);
        String expectedMessage = ExpiryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(null, VALID_QUANTITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpiryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_EXPIRY_DATE, INVALID_QUANTITY);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedItem item =
            new JsonAdaptedItem(VALID_EXPIRY_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }
}

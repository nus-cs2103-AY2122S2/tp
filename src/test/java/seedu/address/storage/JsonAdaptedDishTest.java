package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedDish.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.DONUT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;

public class JsonAdaptedDishTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PRICE = "50.0";

    private static final String VALID_NAME = DONUT.getName().toString();
    private static final String VALID_PRICE = DONUT.getPrice().toString();

    @Test
    public void toModelType_validDishDetails_returnsDish() throws Exception {
        JsonAdaptedDish dish = new JsonAdaptedDish(DONUT);
        assertEquals(DONUT, dish.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDish dish =
                new JsonAdaptedDish(INVALID_NAME, VALID_PRICE);
        String expectedMessage = NameDish.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, dish::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDish dish = new JsonAdaptedDish(null, VALID_PRICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NameDish.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, dish::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedDish dish =
                new JsonAdaptedDish(VALID_NAME, INVALID_PRICE);
        String expectedMessage = PriceDish.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, dish::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedDish dish = new JsonAdaptedDish(VALID_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PriceDish.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, dish::toModelType);
    }
}

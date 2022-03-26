package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.PriceRange;

import static org.junit.jupiter.api.Assertions.*;

class JsonAdaptedPriceRangeTest {

    private final PriceRange priceRange1 = new PriceRange(1, 100);
    private final PriceRange priceRange2 = new PriceRange(50,100);

    JsonAdaptedPriceRange jsonAdaptedPriceRange1 = new JsonAdaptedPriceRange("1", "100");
    JsonAdaptedPriceRange jsonAdaptedPriceRange2 = new JsonAdaptedPriceRange("50", "100");


    @Test
    void toModelType() throws IllegalValueException {
        assertEquals(priceRange1, jsonAdaptedPriceRange1.toModelType());
        assertEquals(priceRange2, jsonAdaptedPriceRange2.toModelType());
    }
}
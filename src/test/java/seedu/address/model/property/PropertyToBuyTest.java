package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.address.commons.exceptions.IllegalValueException;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PropertyToBuyTest {

    House houseStub1 = new House(HouseType.APARTMENT, "Serangoon");
    PriceRange priceRangeStub1 = new PriceRange(0, 100);
    PropertyToBuy toBuy1 = new PropertyToBuy(houseStub1, priceRangeStub1);

    House houseStub2 = new House(HouseType.APARTMENT, "Serangoon");
    PriceRange priceRangeStub2 = new PriceRange(0, 100);
    PropertyToBuy toBuy2 = new PropertyToBuy(houseStub2, priceRangeStub2);

    House houseStub3 = new House(HouseType.APARTMENT, "Hougang");
    PriceRange priceRangeStub3 = new PriceRange(0, 100);
    PropertyToBuy toBuy3 = new PropertyToBuy(houseStub3, priceRangeStub3);

    PropertyToBuyTest() throws IllegalValueException {
        assertThrows(IllegalValueException.class, () -> new PropertyToBuy(houseStub1,
                new PriceRange(10, 9)));
    }

    @Test
    public void equalsTest() {
        assertTrue(toBuy1.equals(toBuy2));
        assertTrue(toBuy2.equals(toBuy1));

        assertFalse(toBuy3.equals(toBuy1));

    }
}
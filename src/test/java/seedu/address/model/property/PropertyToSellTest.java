package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.address.commons.exceptions.IllegalValueException;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PropertyToSellTest {

    House houseStub1 = new House(HouseType.APARTMENT, "Serangoon");
    PriceRange priceRangeStub1 = new PriceRange(0, 100);
    PropertyToSell toSell1 = new PropertyToSell(houseStub1, priceRangeStub1, "postal 533333");

    House houseStub2 = new House(HouseType.APARTMENT, "Serangoon");
    PriceRange priceRangeStub2 = new PriceRange(0, 100);
    PropertyToSell toSell2 = new PropertyToSell(houseStub2, priceRangeStub2, "postal 533333");

    PropertyToSellTest() throws IllegalValueException {
        assertThrows(IllegalValueException.class, () -> new PropertyToSell(houseStub1, new PriceRange(10, 9),
            "postal 533333"));
    }

    @Test
    public void equalsTest() {
        System.out.println(toSell1.getSellRange().equals(toSell2.getSellRange()));

        System.out.println(toSell1.getHouse().equals(toSell2.getHouse()));

        System.out.println(toSell2.getAddress().equals(toSell2.getAddress()));
        assertTrue(toSell1.equals(toSell2));
        assertTrue(toSell2.equals(toSell1));
    }
}
package seedu.address.model.house;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PriceRangeTest {

    private PriceRange pr1 = new PriceRange(0, 100);
    private PriceRange pr2 = new PriceRange(50, 100);

    @Test
    public void priceRangeToStringTest() {
        assertEquals("[0,100]", pr1.toString());
        assertEquals("[50,100]", pr2.toString());
    }

}

package seedu.address.model.house;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceRangeTest {

    PriceRange pr1 = new PriceRange(0, 100);
    PriceRange pr2 = new PriceRange(50, 100);

    @Test
    public void PriceRangeToStringTest() {
        assertEquals("[0,100]", pr1.toString());
        assertEquals("[50,100]", pr2.toString());
    }

}
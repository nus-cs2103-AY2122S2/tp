package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

class PriceRangeTest {

    private PriceRange editablePriceRange = new PriceRange(10, 20);

    PriceRangeTest() throws IllegalValueException {
        assertThrows(IllegalValueException.class, () -> new PriceRange(20, 10));
        assertThrows(IllegalValueException.class, () -> new PriceRange(-10, 50)); //negative values
        assertThrows(IllegalValueException.class, () -> new PriceRange(-100, -50)); //negative values
    }

    @Test
    public void priceRangeToStringTest() throws IllegalValueException {
        PriceRange pr1 = new PriceRange(0, 100);
        PriceRange pr2 = new PriceRange(50, 100);
        assertEquals("[0,100]", pr1.toString());
        assertEquals("[50,100]", pr2.toString());
    }

    @Test
    public void getLowerTest() throws IllegalValueException {
        PriceRange pr1 = new PriceRange(0, 100);
        PriceRange pr2 = new PriceRange(50, 100);
        assertEquals(0, pr1.getLower());
        assertEquals(50, pr2.getLower());
    }

    @Test
    public void getUpperTest() throws IllegalValueException {
        PriceRange pr1 = new PriceRange(0, 100);
        PriceRange pr2 = new PriceRange(50, 100);
        assertEquals(100, pr1.getUpper());
        assertEquals(100, pr2.getUpper());
    }

    @Test
    public void setLowerTest() throws IllegalValueException {
        assertThrows(IllegalValueException.class, () -> editablePriceRange.setLower(-1)); //negative
        assertThrows(IllegalValueException.class, () -> editablePriceRange.setLower(100)); //more than upper

        editablePriceRange.setLower(5);
        assertEquals(5, editablePriceRange.getLower());
    }

    @Test
    public void setUpperTest() throws IllegalValueException {
        assertThrows(IllegalValueException.class, () -> editablePriceRange.setUpper(-1)); //negative
        assertThrows(IllegalValueException.class, () -> editablePriceRange.setUpper(0)); //lower than lower

        editablePriceRange.setUpper(2000);
        assertEquals(2000, editablePriceRange.getUpper());
    }

    @Test
    public void updatePriceTest() throws IllegalValueException {
        PriceRange pr = new PriceRange(0, 100);

        //lower is not lower than upper
        assertThrows(IllegalValueException.class, () -> pr.updatePrice(100, 40));

        //value is negative
        assertThrows(IllegalValueException.class, () -> editablePriceRange.updatePrice(-1, 40));
        assertThrows(IllegalValueException.class, () -> editablePriceRange.updatePrice(-100, -50));

        editablePriceRange.updatePrice(20, 50);
        assertEquals(20, editablePriceRange.getLower());
        assertEquals(50, editablePriceRange.getUpper());
    }

    @Test
    public void isWithinRangeTest() throws IllegalValueException {
        PriceRange pr1 = new PriceRange(45, 100);
        PriceRange pr2 = new PriceRange(50, 150);
        PriceRange pr4 = new PriceRange(44, 46);

        //within range
        assertTrue(PriceRange.isWithinRange(90, pr1));
        assertTrue(PriceRange.isWithinRange(150, pr2)); //inclusive
        assertTrue(PriceRange.isWithinRange(50, pr2)); //inclusive
        assertTrue(PriceRange.isWithinRange(45, pr4));

        //outside range
        assertFalse(PriceRange.isWithinRange(30, pr1));
        assertFalse(PriceRange.isWithinRange(-5, pr1));
        assertFalse(PriceRange.isWithinRange(43, pr4));
        assertFalse(PriceRange.isWithinRange(47, pr4));
    }

    @Test
    public void canMatchPriceTest() throws IllegalValueException {
        PriceRange pr1 = new PriceRange(45, 100);
        PriceRange pr2 = new PriceRange(50, 150);
        PriceRange pr3 = new PriceRange(101, 150);
        PriceRange pr4 = new PriceRange(44, 46);

        assertTrue(PriceRange.canMatchPrice(pr1, pr2)); //values from 50-100 are valid matches
        assertTrue(PriceRange.canMatchPrice(pr2, pr1)); //other way should work also

        assertTrue(PriceRange.canMatchPrice(pr3, pr2));
        assertTrue(PriceRange.canMatchPrice(pr2, pr3));

        assertTrue(PriceRange.canMatchPrice(pr4, pr1));
        assertTrue(PriceRange.canMatchPrice(pr1, pr4)); // other way should be the same

        assertFalse(PriceRange.canMatchPrice(pr1, pr3));
        assertFalse(PriceRange.canMatchPrice(pr3, pr1));
    }

    @Test
    public void equalTest() throws IllegalValueException {
        PriceRange pr1 = new PriceRange(45, 100);
        PriceRange pr2 = new PriceRange(45, 100);
        PriceRange pr3 = new PriceRange(45, 99);

        assertTrue(pr1.equals(pr2));
        assertTrue(pr2.equals(pr1));

        assertFalse(pr1.equals(pr3));
    }
}

package seedu.ibook.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PriceTest {

    private static final String ZERO_PRICE = "0";
    private static final String MAX_PRICE = "999999.99";
    private static final String VALID_PRICE_WITH_LEADING_ZEROES = "00100";

    private static final String NEGATIVE_PRICE = "-1";
    private static final String LARGE_PRICE = "999999999999999";

    @Test
    void isValidPrice_boundaryPrice_returnsTrue() {
        assertTrue(Price.isValidPrice(ZERO_PRICE));
        assertTrue(Price.isValidPrice(MAX_PRICE));
        assertTrue(Price.isValidPrice(VALID_PRICE_WITH_LEADING_ZEROES));
    }

    @Test
    void isValidPrice_invalidPrice_returnsFalse() {
        assertFalse(Price.isValidPrice(NEGATIVE_PRICE));
        assertFalse(Price.isValidPrice(LARGE_PRICE));
    }

    @Test
    void isWithin_withinRange_returnsTrue() {
        Price price2 = new Price("2");
        PriceRange range1To10 = new PriceRange("1", "10");
        assertTrue(price2.isWithin(range1To10));
    }

    @Test
    void isWithin_outsideRange_returnsFalse() {
        Price price2 = new Price("2");
        PriceRange range3To10 = new PriceRange("3", "10");
        assertFalse(price2.isWithin(range3To10));
    }

    @Test
    void isLessThan_lessThan_returnTrue() {
        Price price1 = new Price("1");
        Price price2 = new Price("2");
        assertTrue(price1.isLessThan(price2));
    }

    @Test
    void isLessThan_equals_returnFalse() {
        Price price1 = new Price("1");
        Price price2 = new Price("2");
        assertFalse(price1.isLessThan(price1));
        assertFalse(price2.isLessThan(price2));
    }

    @Test
    void isLessThan_moreThan_returnFalse() {
        Price price1 = new Price("1");
        Price price2 = new Price("2");
        assertFalse(price2.isLessThan(price1));
    }

    @Test
    void isMoreThan_lessThan_returnFalse() {
        Price price1 = new Price("1");
        Price price2 = new Price("2");
        assertFalse(price1.isMoreThan(price2));
    }

    @Test
    void isMoreThan_equals_returnFalse() {
        Price price1 = new Price("1");
        Price price2 = new Price("2");
        assertFalse(price1.isMoreThan(price1));
        assertFalse(price2.isMoreThan(price2));
    }

    @Test
    void isMoreThan_moreThan_returnTrue() {
        Price price1 = new Price("1");
        Price price2 = new Price("2");
        assertTrue(price2.isMoreThan(price1));
    }
}

package seedu.ibook.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DiscountRateTest {

    private static final String EMPTY_DISCOUNT = "";
    private static final String ZERO_DISCOUNT = "0";
    private static final String MAX_DISCOUNT = "100";
    private static final String VALID_DISCOUNT_WITH_LEADING_ZEROES = "00010";

    private static final String INVALID_DISCOUNT_WITH_LEADING_ZEROES = "0001000";

    @Test
    void isValidDiscountRate_boundaryRates_returnsTrue() {
        assertTrue(DiscountRate.isValidDiscountRate(EMPTY_DISCOUNT));
        assertTrue(DiscountRate.isValidDiscountRate(ZERO_DISCOUNT));
        assertTrue(DiscountRate.isValidDiscountRate(MAX_DISCOUNT));
        assertTrue(DiscountRate.isValidDiscountRate(VALID_DISCOUNT_WITH_LEADING_ZEROES));
    }

    @Test
    void isValidDiscountRate_invalidRates_returnFalse() {
        assertFalse(DiscountRate.isValidDiscountRate(INVALID_DISCOUNT_WITH_LEADING_ZEROES));
    }
}

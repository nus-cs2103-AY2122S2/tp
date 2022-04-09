package seedu.ibook.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DiscountStartTest {

    private static final String EMPTY_DAYS = "";
    private static final String ZERO_DAYS = "0";
    private static final String MAX_DAYS = "999999";
    private static final String VALID_DAYS_WITH_LEADING_ZEROES = "00001";

    private static final String NEGATIVE_DAYS = "-1";
    private static final String INVALID_DAYS_WITH_LEADING_ZEROES = "000000010000000000000000";
    private static final String LARGE_DAYS = "9999999999999999999999";

    @Test
    void isValidDiscountStart_boundaryValues_returnTrue() {
        assertTrue(DiscountStart.isValidDiscountStart(EMPTY_DAYS));
        assertTrue(DiscountStart.isValidDiscountStart(ZERO_DAYS));
        assertTrue(DiscountStart.isValidDiscountStart(MAX_DAYS));
        assertTrue(DiscountStart.isValidDiscountStart(VALID_DAYS_WITH_LEADING_ZEROES));
    }

    @Test
    void isValidDiscountStart_invalidValues_returnFalse() {
        assertFalse(DiscountStart.isValidDiscountStart(NEGATIVE_DAYS));
        assertFalse(DiscountStart.isValidDiscountStart(INVALID_DAYS_WITH_LEADING_ZEROES));
        assertFalse(DiscountStart.isValidDiscountStart(LARGE_DAYS));
    }
}

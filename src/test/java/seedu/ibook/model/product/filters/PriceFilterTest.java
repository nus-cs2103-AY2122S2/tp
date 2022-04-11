package seedu.ibook.model.product.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;
import static seedu.ibook.testutil.TypicalProductFilters.PRICE_FILTER_0_0;
import static seedu.ibook.testutil.TypicalProductFilters.PRICE_FILTER_0_100;
import static seedu.ibook.testutil.TypicalProductFilters.PRICE_FILTER_A;
import static seedu.ibook.testutil.TypicalProductFilters.PRICE_FILTER_B;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;

import org.junit.jupiter.api.Test;

class PriceFilterTest {

    @Test
    void test_exactPrice_match() {
        assertTrue(PRICE_FILTER_A.test(PRODUCT_A));
        assertTrue(PRICE_FILTER_B.test(PRODUCT_B));
    }

    @Test
    void test_withinPriceRange_match() {
        assertTrue(PRICE_FILTER_0_100.test(PRODUCT_A));
        assertTrue(PRICE_FILTER_0_100.test(PRODUCT_B));
    }

    @Test
    void test_outsidePriceRange_noMatch() {
        assertFalse(PRICE_FILTER_0_0.test(PRODUCT_A));
        assertFalse(PRICE_FILTER_0_0.test(PRODUCT_B));
    }

    @Test
    void getValue_exactPrice_noDash() {
        assertEquals(VALID_PRICE_A, PRICE_FILTER_A.getValue());
        assertEquals(VALID_PRICE_B, PRICE_FILTER_B.getValue());
    }

    @Test
    void getValue_priceRange_withDash() {
        assertEquals("$0.00-$0.00", PRICE_FILTER_0_0.getValue());
        assertEquals("$0.00-$100.00", PRICE_FILTER_0_100.getValue());
    }

    @Test
    void testEquals() {
        assertEquals(PRICE_FILTER_A, PRICE_FILTER_A);
        assertEquals(PRICE_FILTER_B, PRICE_FILTER_B);
        assertNotEquals(PRICE_FILTER_A, PRICE_FILTER_B);
    }
}

package seedu.ibook.model.product.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.TypicalItems.getItemTenDays;
import static seedu.ibook.testutil.TypicalItems.getItemToday;
import static seedu.ibook.testutil.TypicalProductFilters.EXPIRING_FILTER_TEN_DAYS;
import static seedu.ibook.testutil.TypicalProductFilters.EXPIRING_FILTER_TODAY;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ProductBuilder;

public class ExpiringFilterTest {

    private final Product productA = new ProductBuilder(PRODUCT_A).buildWithItems(getItemToday());
    private final Product productB = new ProductBuilder(PRODUCT_B).buildWithItems(getItemTenDays());

    @Test
    void test_exactDate_match() {
        assertTrue(EXPIRING_FILTER_TODAY.test(productA));
        assertTrue(EXPIRING_FILTER_TEN_DAYS.test(productB));
    }

    @Test
    void test_dateBefore_match() {
        assertTrue(EXPIRING_FILTER_TEN_DAYS.test(productA));
    }

    @Test
    void test_dateAfter_noMatch() {
        assertFalse(EXPIRING_FILTER_TODAY.test(productB));
    }

    @Test
    void testEquals() {
        assertEquals(EXPIRING_FILTER_TODAY, EXPIRING_FILTER_TODAY);
        assertNotEquals(EXPIRING_FILTER_TODAY, EXPIRING_FILTER_TEN_DAYS);
    }
}

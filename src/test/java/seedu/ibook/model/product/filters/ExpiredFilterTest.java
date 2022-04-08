package seedu.ibook.model.product.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.TypicalItems.Q5_2020_01_01_KAYA;
import static seedu.ibook.testutil.TypicalItems.getItemTenDays;
import static seedu.ibook.testutil.TypicalItems.getItemToday;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.product.Product;
import seedu.ibook.testutil.ProductBuilder;

class ExpiredFilterTest {

    private final ExpiredFilter expiredFilter = new ExpiredFilter();

    private final Product productExpiredBefore = new ProductBuilder(PRODUCT_A)
        .buildWithItems(List.of(Q5_2020_01_01_KAYA));
    private final Product productExpireToday = new ProductBuilder(PRODUCT_A).buildWithItems(getItemToday());
    private final Product productExpireTenDays = new ProductBuilder(PRODUCT_A).buildWithItems(getItemTenDays());

    @Test
    void test_dateBefore_match() {
        assertTrue(expiredFilter.test(productExpiredBefore));
    }

    @Test
    void test_sameDate_noMatch() {
        assertFalse(expiredFilter.test(productExpireToday));
    }

    @Test
    void test_dateAfter_noMatch() {
        assertFalse(expiredFilter.test(productExpireTenDays));
    }

    @Test
    void testEquals() {
        ExpiredFilter clone = new ExpiredFilter();
        assertEquals(expiredFilter, expiredFilter);
        assertEquals(expiredFilter, clone);
    }
}

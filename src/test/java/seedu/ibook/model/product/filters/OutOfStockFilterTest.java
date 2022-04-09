package seedu.ibook.model.product.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD;
import static seedu.ibook.testutil.TypicalProducts.KAYA_BREAD_WITH_ITEMS;

import org.junit.jupiter.api.Test;

public class OutOfStockFilterTest {

    private final OutOfStockFilter outOfStockFilter = new OutOfStockFilter();

    @Test
    void test_noItems_match() {
        assertTrue(outOfStockFilter.test(KAYA_BREAD));
    }

    @Test
    void test_hasItems_noMatch() {
        assertFalse(outOfStockFilter.test(KAYA_BREAD_WITH_ITEMS));
    }

    @Test
    void testEquals() {
        OutOfStockFilter clone = new OutOfStockFilter();
        assertEquals(outOfStockFilter, outOfStockFilter);
        assertEquals(outOfStockFilter, clone);
    }
}

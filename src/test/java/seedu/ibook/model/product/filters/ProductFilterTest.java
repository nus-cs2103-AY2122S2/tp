package seedu.ibook.model.product.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.TypicalProductFilters.CATEGORY_FILTER_A;
import static seedu.ibook.testutil.TypicalProductFilters.DESCRIPTION_FILTER_A;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_A;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_B;
import static seedu.ibook.testutil.TypicalProductFilters.PRICE_FILTER_A;
import static seedu.ibook.testutil.TypicalProductFilters.getProductFilterA;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.product.exceptions.FilterNotFoundException;

class ProductFilterTest {

    private final ProductFilter productFilter =
        new ProductFilter();

    @Test
    void addFilter_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> productFilter.addFilter(null));
    }

    @Test
    void addFilter_validFilter_filterAdded() {
        productFilter.addFilter(NAME_FILTER_A);
        assertEquals(1, productFilter.getFilters().size());
    }

    @Test
    void removeFilter_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> productFilter.removeFilter(null));
    }

    @Test
    void removeFilter_invalidFilter_throwsFilterNotFoundException() {
        productFilter.addFilter(NAME_FILTER_A);
        assertThrows(FilterNotFoundException.class, () -> productFilter.removeFilter(NAME_FILTER_B));
    }

    @Test
    void removeFilter_existingFilter_filterRemoved() {
        productFilter.addFilter(NAME_FILTER_A);
        productFilter.removeFilter(NAME_FILTER_A);
        assertEquals(0, productFilter.getFilters().size());
    }

    @Test
    void clearFilters_containsFilters_filtersRemoved() {
        ProductFilter testFilter = getProductFilterA();
        testFilter.clearFilters();
        assertEquals(0, testFilter.getFilters().size());
    }

    @Test
    void getFilters() {
    }

    @Test
    void test_containsAttribute_matchProduct() {
        productFilter.addFilter(NAME_FILTER_A);
        assertTrue(productFilter.test(PRODUCT_A));
        productFilter.clearFilters();

        productFilter.addFilter(CATEGORY_FILTER_A);
        assertTrue(productFilter.test(PRODUCT_A));
        productFilter.clearFilters();

        productFilter.addFilter(DESCRIPTION_FILTER_A);
        assertTrue(productFilter.test(PRODUCT_A));
        productFilter.clearFilters();

        productFilter.addFilter(PRICE_FILTER_A);
        assertTrue(productFilter.test(PRODUCT_A));
        productFilter.clearFilters();
    }

    @Test
    void test_emptyFilter_allMatch() {
        assertTrue(productFilter.test(PRODUCT_A));
        assertTrue(productFilter.test(PRODUCT_B));
    }

    @Test
    void test_differentFilter_noMatch() {
        productFilter.addFilter(NAME_FILTER_A);
        assertFalse(productFilter.test(PRODUCT_B));
        productFilter.clearFilters();

        productFilter.addFilter(CATEGORY_FILTER_A);
        assertFalse(productFilter.test(PRODUCT_B));
        productFilter.clearFilters();

        productFilter.addFilter(DESCRIPTION_FILTER_A);
        assertFalse(productFilter.test(PRODUCT_B));
        productFilter.clearFilters();

        productFilter.addFilter(PRICE_FILTER_A);
        assertFalse(productFilter.test(PRODUCT_B));
        productFilter.clearFilters();
    }
}

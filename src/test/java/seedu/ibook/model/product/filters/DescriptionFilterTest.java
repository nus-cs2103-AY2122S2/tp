package seedu.ibook.model.product.filters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.testutil.TypicalProductFilters.DESCRIPTION_FILTER_A;
import static seedu.ibook.testutil.TypicalProductFilters.DESCRIPTION_FILTER_B;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;

import org.junit.jupiter.api.Test;
import seedu.ibook.model.product.Description;

class DescriptionFilterTest {

    @Test
    void test_exactDescription_match() {
        assertTrue(DESCRIPTION_FILTER_A.test(PRODUCT_A));
        assertTrue(DESCRIPTION_FILTER_B.test(PRODUCT_B));
    }

    @Test
    void test_partialName_match() {
        DescriptionFilter partialDescA = new DescriptionFilter(new Description("A"));
        assertTrue(partialDescA.test(PRODUCT_A));
    }

    @Test
    void test_differentName_noMatch() {
        assertFalse(DESCRIPTION_FILTER_A.test(PRODUCT_B));
        assertFalse(DESCRIPTION_FILTER_B.test(PRODUCT_A));
    }
}
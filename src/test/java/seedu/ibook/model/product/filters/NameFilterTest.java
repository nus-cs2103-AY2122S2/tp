package seedu.ibook.model.product.filters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_A;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_A;
import static seedu.ibook.testutil.TypicalProductFilters.NAME_FILTER_B;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.product.Name;

class NameFilterTest {

    @Test
    void test_exactName_match() {
        assertTrue(NAME_FILTER_A.test(PRODUCT_A));
        assertTrue(NAME_FILTER_B.test(PRODUCT_B));
    }

    @Test
    void test_partialName_match() {
        NameFilter partialNameA = new NameFilter(new Name("A"));
        assertTrue(partialNameA.test(PRODUCT_A));
    }

    @Test
    void test_caseInsensitiveExactName_match() {
        NameFilter lowerNameA = new NameFilter(new Name(VALID_NAME_A.toLowerCase()));
        NameFilter upperNameA = new NameFilter(new Name(VALID_NAME_A.toUpperCase()));
        assertTrue(lowerNameA.test(PRODUCT_A));
        assertTrue(upperNameA.test(PRODUCT_A));
    }

    @Test
    void test_caseInsensitivePartialName_match() {
        NameFilter upperPartial = new NameFilter(new Name("ITE"));
        NameFilter randomPartial = new NameFilter(new Name("iTeM"));
        assertTrue(upperPartial.test(PRODUCT_A));
        assertTrue(randomPartial.test(PRODUCT_A));
    }

    @Test
    void test_differentName_noMatch() {
        assertFalse(NAME_FILTER_A.test(PRODUCT_B));
        assertFalse(NAME_FILTER_B.test(PRODUCT_A));
    }
}

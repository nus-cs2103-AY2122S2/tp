package seedu.ibook.model.product.filters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_A;
import static seedu.ibook.testutil.TypicalProductFilters.CATEGORY_FILTER_A;
import static seedu.ibook.testutil.TypicalProductFilters.CATEGORY_FILTER_B;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.product.Category;

class CategoryFilterTest {

    @Test
    void test_exactCategory_match() {
        assertTrue(CATEGORY_FILTER_A.test(PRODUCT_A));
        assertTrue(CATEGORY_FILTER_B.test(PRODUCT_B));
    }

    @Test
    void test_partialName_match() {
        CategoryFilter partialCategoryA = new CategoryFilter(new Category("A"));
        assertTrue(partialCategoryA.test(PRODUCT_A));
    }

    @Test
    void test_caseInsensitiveExactCategory_match() {
        CategoryFilter lowerCategoryA = new CategoryFilter(new Category(VALID_CATEGORY_A.toLowerCase()));
        CategoryFilter upperCategoryA = new CategoryFilter(new Category(VALID_CATEGORY_A.toUpperCase()));
        assertTrue(lowerCategoryA.test(PRODUCT_A));
        assertTrue(upperCategoryA.test(PRODUCT_A));
    }

    @Test
    void test_caseInsensitivePartialCategory_match() {
        CategoryFilter upperPartial = new CategoryFilter(new Category("CAT"));
        CategoryFilter randomPartial = new CategoryFilter(new Category("caTeGO"));
        assertTrue(upperPartial.test(PRODUCT_A));
        assertTrue(randomPartial.test(PRODUCT_A));
    }

    @Test
    void test_differentName_noMatch() {
        assertFalse(CATEGORY_FILTER_A.test(PRODUCT_B));
        assertFalse(CATEGORY_FILTER_B.test(PRODUCT_A));
    }
}

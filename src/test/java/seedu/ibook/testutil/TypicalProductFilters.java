package seedu.ibook.testutil;

import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_A;
import static seedu.ibook.testutil.TypicalProducts.PRODUCT_B;

import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.filters.CategoryFilter;
import seedu.ibook.model.product.filters.DescriptionFilter;
import seedu.ibook.model.product.filters.NameFilter;
import seedu.ibook.model.product.filters.PriceFilter;
import seedu.ibook.model.product.filters.ProductFilter;

public class TypicalProductFilters {
    public static final NameFilter NAME_FILTER_KAYA = new NameFilter(new Name("Kaya Bread"));

    public static final NameFilter NAME_FILTER_A = new NameFilter(new Name(VALID_NAME_A));
    public static final NameFilter NAME_FILTER_B = new NameFilter(new Name(VALID_NAME_B));
    public static final CategoryFilter CATEGORY_FILTER_A = new CategoryFilter(new Category(VALID_CATEGORY_A));
    public static final CategoryFilter CATEGORY_FILTER_B = new CategoryFilter(new Category(VALID_CATEGORY_B));
    public static final DescriptionFilter DESCRIPTION_FILTER_A =
        new DescriptionFilter(new Description(VALID_DESCRIPTION_A));
    public static final DescriptionFilter DESCRIPTION_FILTER_B =
        new DescriptionFilter(new Description(VALID_DESCRIPTION_B));
    public static final PriceFilter PRICE_FILTER_A = new PriceFilter(new Price(VALID_PRICE_A));
    public static final PriceFilter PRICE_FILTER_B = new PriceFilter(new Price(VALID_PRICE_B));

    public static ProductFilter getProductFilterA() {
        return new ProductFilter(PRODUCT_A);
    }

    public static ProductFilter getProductFilterB() {
        return new ProductFilter(PRODUCT_B);
    }
}

package seedu.ibook.model.product.filters;

import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Product;

public class CategoryFilter extends AttributeFilter {
    private static final String TYPE = "category";

    private final Category category;
    private final String value;

    /**
     * Creates a predicate that checks if the product has the same parameters.
     * @param category
     */
    public CategoryFilter(Category category) {
        this.category = category;
        this.value = category.toString();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean test(Product otherProduct) {
        return this.category.equals(otherProduct.getCategory());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CategoryFilter)) {
            return false;
        }
        return this.category.equals(((CategoryFilter) other).category);
    }
}

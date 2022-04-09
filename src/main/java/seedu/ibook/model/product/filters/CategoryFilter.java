package seedu.ibook.model.product.filters;

import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Product;

public class CategoryFilter extends AttributeFilter {
    private static final String TYPE = "category";
    private final String value;

    private final Category category;

    /**
     * Creates a filter that checks if the product has a similar category.
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
        return otherProduct.getCategory().contains(category);
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

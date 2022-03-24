package seedu.ibook.model.product.filters;

import seedu.ibook.model.product.Product;

public class ExpiredFilter extends AttributeFilter {
    private static final String TYPE = "expired";

    private final String value = "";

    /**
     * Creates a predicate that checks if the product has expired.
     */
    public ExpiredFilter() {
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
        return otherProduct.hasExpiredItems();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof NameFilter;
    }
}

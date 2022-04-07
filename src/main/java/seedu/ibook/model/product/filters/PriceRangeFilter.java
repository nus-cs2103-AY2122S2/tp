package seedu.ibook.model.product.filters;

import seedu.ibook.model.product.PriceRange;
import seedu.ibook.model.product.Product;

public class PriceRangeFilter extends AttributeFilter {
    private static final String TYPE = "price";
    private final String value;

    private final PriceRange priceRange;

    /**
     * Creates a predicate that checks if the product falls within the price range.
     */
    public PriceRangeFilter(PriceRange priceRange) {
        this.priceRange = priceRange;
        this.value = priceRange.toString();
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
        return otherProduct.getPrice().isWithin(priceRange);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PriceRangeFilter)) {
            return false;
        }
        return this.priceRange.equals(((PriceRangeFilter) other).priceRange);
    }
}

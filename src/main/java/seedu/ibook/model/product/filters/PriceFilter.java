package seedu.ibook.model.product.filters;

import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;

public class PriceFilter extends AttributeFilter {
    private static final String TYPE = "price";
    private final String value;

    private final Price price;

    /**
     * Creates a predicate that checks if the product has the same parameters.
     */
    public PriceFilter(Price price) {
        this.price = price;
        this.value = price.toString();
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
        return price.equals(otherProduct.getPrice());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PriceFilter)) {
            return false;
        }

        return price.equals(((PriceFilter) other).price);
    }
}

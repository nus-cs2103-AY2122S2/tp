package seedu.ibook.model.product.filters;

import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;

public class PriceFilter extends AttributeFilter {
    private static final String TYPE = "price";

    private final Price startPrice;
    private final Price endPrice;
    private final String value;

    /**
     * Creates a predicate that checks if the product has the same parameters.
     */
    public PriceFilter(Price price) {
        this.startPrice = price;
        this.endPrice = price;
        this.value = price.toString();
    }

    /**
     * Creates a predicate that checks if the product falls within the price range.
     */
    public PriceFilter(Price startPrice, Price endPrice) {
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.value = String.format("%s-%s", startPrice, endPrice);
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
        return otherProduct.getPrice().isWithin(startPrice, endPrice);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PriceFilter)) {
            return false;
        }

        PriceFilter otherPriceFilter = (PriceFilter) other;
        return startPrice.equals(otherPriceFilter.startPrice) && endPrice.equals(otherPriceFilter.endPrice);
    }
}

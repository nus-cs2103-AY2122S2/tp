package seedu.ibook.model.product.filters;

import seedu.ibook.model.item.Quantity;
import seedu.ibook.model.product.Product;

/**
 * Filter that checks if the product has quantity of zero in all its items.
 */
public class OutOfStockFilter extends AttributeFilter {
    private static final String TYPE = "out-of-stock";
    private final String value = "";

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
        return otherProduct.getTotalQuantity().equals(new Quantity(0));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof OutOfStockFilter;
    }
}

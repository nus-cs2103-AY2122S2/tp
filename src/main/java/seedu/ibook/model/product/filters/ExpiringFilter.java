package seedu.ibook.model.product.filters;

import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.product.Product;

public class ExpiringFilter extends AttributeFilter {

    private static final String TYPE = "expiring";
    private final String value;

    private final ExpiryDate expiryDateToCheck;

    /**
     * Creates a predicate that checks if the product has expired.
     */
    public ExpiringFilter(ExpiryDate expiryDateToCheck) {
        this.expiryDateToCheck = expiryDateToCheck;
        value = expiryDateToCheck.toString();
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
    public boolean test(Product product) {
        return product.hasExpiringItems(expiryDateToCheck);
    }
}

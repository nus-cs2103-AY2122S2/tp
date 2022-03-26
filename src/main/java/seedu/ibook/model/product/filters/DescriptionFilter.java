package seedu.ibook.model.product.filters;

import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.Product;

public class DescriptionFilter extends AttributeFilter {
    private static final String TYPE = "description";

    private final Description description;
    private final String value;

    /**
     * Creates a predicate that checks if the product has the same parameters.
     * @param description
     */
    public DescriptionFilter(Description description) {
        this.description = description;
        this.value = description.toString();
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
        return otherProduct.getDescription().contains(description);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DescriptionFilter)) {
            return false;
        }
        return this.description.equals(((DescriptionFilter) other).description);
    }
}

package seedu.ibook.model.product.filters;

import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Product;

public class NameFilter extends AttributeFilter {

    private static final String TYPE = "name";
    private final String value;

    private final Name name;

    /**
     * Creates a predicate that checks if the product has the same parameters.
     * @param name
     */
    public NameFilter(Name name) {
        this.name = name;
        this.value = name.toString();
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
        return otherProduct.getName().contains(name);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof NameFilter)) {
            return false;
        }
        return this.name.equals(((NameFilter) other).name);
    }
}

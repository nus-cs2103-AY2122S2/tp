package seedu.ibook.model.product.filters;

import java.util.function.Predicate;

import seedu.ibook.model.product.Product;

public abstract class AttributeFilter implements Predicate<Product> {
    public abstract String getType();
    public abstract String getValue();
}

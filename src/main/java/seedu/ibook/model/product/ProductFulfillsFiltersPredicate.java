package seedu.ibook.model.product;

import java.util.function.Predicate;

public class ProductFulfillsFiltersPredicate implements Predicate<Product> {
    private final Product product;

    /**
     * Creates a predicate that checks if the product has the same parameters.
     * @param product
     */
    public ProductFulfillsFiltersPredicate(Product product) {
        this.product = product;
    }

    @Override
    public boolean test(Product product) {
        return this.product.equals(product);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ProductFulfillsFiltersPredicate)) {
            return false;
        }
        return this.product.equals(((ProductFulfillsFiltersPredicate) other).product);
    }
}

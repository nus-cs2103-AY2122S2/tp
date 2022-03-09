package seedu.ibook.model.product;

import java.util.function.Predicate;

public class ProductFulfillsFiltersPredicate implements Predicate<Product> {
    private final Name name;
    private final Category category;
    private final Price price;
    private final ExpiryDate expiryDate;
    private final Description description;

    /**
     * Creates a predicate that checks if it has the same values as the name, category, expiryDate, description, price
     * if they exist
     * @param name
     * @param category
     * @param expiryDate
     * @param description
     * @param price
     */
    public ProductFulfillsFiltersPredicate(Name name, Category category,
                                           ExpiryDate expiryDate, Description description, Price price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.expiryDate = expiryDate;
        this.description = description;
    }

    @Override
    public boolean test(Product product) {
        return equalsWithNull(product);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ProductFulfillsFiltersPredicate)) {
            return false;
        }

        ProductFulfillsFiltersPredicate otherProduct = (ProductFulfillsFiltersPredicate) other;
        return ((expiryDate == null && otherProduct.expiryDate == null || expiryDate.equals(otherProduct.expiryDate)))
                && (name == null && otherProduct.name == null || name.equals(otherProduct.name))
                && (category == null && otherProduct.category == null || category.equals(otherProduct.category))
                && ((description == null && otherProduct.description == null
                || description.equals(otherProduct.description)))
                && ((price == null && otherProduct.price == null || price.equals(otherProduct.price)));
    }

    /**
     * Checks if the otherProduct has the same parameters with the current product
     * provided that the parameters are not null
     *
     * @param otherProduct
     * @return true if it has exactly the same parameters with the current product
     */
    private boolean equalsWithNull(Product otherProduct) {
        return (name == null || name.equals(otherProduct.getName()))
                && (category == null || category.equals(otherProduct.getCategory()))
                && (expiryDate == null || expiryDate.equals(otherProduct.getExpiryDate()))
                && (description == null || description.equals(otherProduct.getDescription()))
                && (price == null || price.equals(otherProduct.getPrice()));
    }
}

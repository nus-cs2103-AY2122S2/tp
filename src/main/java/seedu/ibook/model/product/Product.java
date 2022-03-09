package seedu.ibook.model.product;

import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Product in the ibook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Product {

    // Identity fields
    private final Name name;
    private final Category category;
    private final ExpiryDate expiryDate;

    // Data fields
    private final Description description;
    private final Price price;

    /**
     * Every field must be present and not null.
     */
    public Product(Name name, Category category, ExpiryDate expiryDate, Description description, Price price) {
        requireAllNonNull(name, category, expiryDate, description, price);
        this.name = name;
        this.category = category;
        this.expiryDate = expiryDate;
        this.description = description;
        this.price = price;
    }

    public Name getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    public Description getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public boolean isExpired() {
        return expiryDate.isPast();
    }

    /**
     * Returns true if both products have the same name and expiry date.
     * This defines a weaker notion of equality between two products.
     */
    public boolean isSameProduct(Product otherProduct) {
        if (otherProduct == this) {
            return true;
        }

        return otherProduct != null
                && otherProduct.getName().equals(getName())
                && otherProduct.getCategory().equals(getCategory())
                && otherProduct.getExpiryDate().equals(getExpiryDate());
    }

    /**
     * Returns true if both products have the same identity and data fields.
     * This defines a stronger notion of equality between two products.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Product)) {
            return false;
        }

        Product otherProduct = (Product) other;
        return otherProduct.getName().equals(getName())
                && otherProduct.getCategory().equals(getCategory())
                && otherProduct.getExpiryDate().equals(getExpiryDate())
                && otherProduct.getDescription().equals(getDescription())
                && otherProduct.getPrice().equals(getPrice());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, category, expiryDate, description, price);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Category: ")
                .append(getCategory())
                .append("; ExpiryDate: ")
                .append(getExpiryDate())
                .append("; Description: ")
                .append(getDescription())
                .append("; Price: ")
                .append(getPrice());

        return builder.toString();
    }

}

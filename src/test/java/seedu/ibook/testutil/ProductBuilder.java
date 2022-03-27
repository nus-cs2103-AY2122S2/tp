package seedu.ibook.testutil;

import java.util.List;

import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;

/**
 * A utility class to help with building Product objects.
 */
public class ProductBuilder {

    public static final String DEFAULT_NAME = "Maggie Mee";
    public static final String DEFAULT_CATEGORY = "Noodles";
    public static final String DEFAULT_DESCRIPTION = "Delicious noodles";
    public static final String DEFAULT_PRICE = "1.99";

    private Name name;
    private Category category;
    private Description description;
    private Price price;

    /**
     * Creates a {@code ProductBuilder} with the default details.
     */
    public ProductBuilder() {
        name = new Name(DEFAULT_NAME);
        category = new Category(DEFAULT_CATEGORY);
        description = new Description(DEFAULT_DESCRIPTION);
        price = new Price(DEFAULT_PRICE);
    }

    /**
     * Initializes the ProductBuilder with the data of {@code productToCopy}.
     */
    public ProductBuilder(Product productToCopy) {
        name = productToCopy.getName();
        category = productToCopy.getCategory();
        description = productToCopy.getDescription();
        price = productToCopy.getPrice();
    }

    /**
     * Sets the {@code Name} of the {@code Product} that we are building.
     */
    public ProductBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Product} that we are building.
     */
    public ProductBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code Product} that we are building.
     */
    public ProductBuilder withCategory(String category) {
        this.category = new Category(category);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Product} that we are building.
     */
    public ProductBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    public Product build() {
        return new Product(name, category, description, price);
    }

    public Product buildWithItems(List<Item> items) {
        return new Product(name, category, description, price, items);
    }
}

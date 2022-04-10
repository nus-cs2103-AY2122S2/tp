package seedu.ibook.testutil;

import java.util.List;

import seedu.ibook.model.item.ItemDescriptor;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.DiscountRate;
import seedu.ibook.model.product.DiscountStart;
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
    public static final String DEFAULT_DISCOUNT_RATE = "25";
    public static final String DEFAULT_DISCOUNT_START = "2";

    private Name name;
    private Category category;
    private Description description;
    private Price price;
    private DiscountRate discountRate;
    private DiscountStart discountStart;

    /**
     * Creates a {@code ProductBuilder} with the default details.
     */
    public ProductBuilder() {
        name = new Name(DEFAULT_NAME);
        category = new Category(DEFAULT_CATEGORY);
        description = new Description(DEFAULT_DESCRIPTION);
        price = new Price(DEFAULT_PRICE);
        discountRate = new DiscountRate(DEFAULT_DISCOUNT_RATE);
        discountStart = new DiscountStart(DEFAULT_DISCOUNT_START);
    }

    /**
     * Initializes the ProductBuilder with the data of {@code productToCopy}.
     */
    public ProductBuilder(Product productToCopy) {
        name = productToCopy.getName();
        category = productToCopy.getCategory();
        description = productToCopy.getDescription();
        price = productToCopy.getPrice();
        discountRate = productToCopy.getDiscountRate();
        discountStart = productToCopy.getDiscountStart();
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

    /**
     * Sets the {@code DiscountRate} of the {@code Product} that we are building.
     */
    public ProductBuilder withDiscountRate(String discountRate) {
        this.discountRate = new DiscountRate(discountRate);
        return this;
    }

    /**
     * Sets the {@code DiscountStart} of the {@code Product} that we are building.
     */
    public ProductBuilder withDiscountStart(String discountStart) {
        this.discountStart = new DiscountStart(discountStart);
        return this;
    }

    public Product build() {
        return new Product(name, category, description, price, discountRate, discountStart);
    }

    public Product buildWithItems(List<? extends ItemDescriptor> items) {
        return new Product(name, category, description, price, discountRate, discountStart, items);
    }
}

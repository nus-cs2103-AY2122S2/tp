package seedu.ibook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.ibook.commons.exceptions.IllegalValueException;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.DiscountRate;
import seedu.ibook.model.product.DiscountStart;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;

/**
 * Jackson-friendly version of {@link Product}.
 */
class JsonAdaptedProduct {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Product's %s field is missing!";

    private final String name;
    private final String category;
    private final String description;
    private final String price;
    private final String discountRate;
    private final String discountStart;
    private final List<JsonAdaptedItem> items = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProduct} with the given product details.
     */
    @JsonCreator
    public JsonAdaptedProduct(@JsonProperty("name") String name,
                              @JsonProperty("category") String category,
                              @JsonProperty("description") String description,
                              @JsonProperty("price") String price,
                              @JsonProperty("discountRate") String discountRate,
                              @JsonProperty("discountStart") String discountStart,
                              @JsonProperty("items") List<JsonAdaptedItem> items) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.discountRate = discountRate;
        this.discountStart = discountStart;
        if (items != null) {
            this.items.addAll(items);
        }
    }

    /**
     * Converts a given {@code Product} into this class for Jackson use.
     */
    public JsonAdaptedProduct(Product source) {
        name = source.getName().fullName;
        category = source.getCategory().fullCategoryName;
        description = source.getDescription().fullDescription;
        price = source.getPrice().price.toString();
        discountRate = source.getDiscountRate().discountRate.toString();
        discountStart = source.getDiscountStart().discountStart.toString();
        items.addAll(StreamSupport.stream(source.getItems().spliterator(), false)
            .map(JsonAdaptedItem::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted product object into the model's {@code Product} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted product.
     */
    public Product toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (category == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Category.class.getSimpleName()));
        }
        if (!Category.isValidCategoryName(category)) {
            throw new IllegalValueException(Category.MESSAGE_CONSTRAINTS);
        }
        final Category modelCategory = new Category(category);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        if (discountRate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DiscountRate.class.getSimpleName()));
        }
        if (!DiscountRate.isValidDiscountRate(discountRate)) {
            throw new IllegalValueException(DiscountRate.MESSAGE_CONSTRAINTS);
        }
        final DiscountRate modelDiscountRate = new DiscountRate(discountRate);

        if (discountStart == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DiscountStart.class.getSimpleName()));
        }
        if (!DiscountStart.isValidDiscountStart(discountStart)) {
            throw new IllegalValueException(DiscountStart.MESSAGE_CONSTRAINTS);
        }
        final DiscountStart modelDiscountStart = new DiscountStart(discountStart);

        Product product = new Product(modelName, modelCategory, modelDescription, modelPrice,
                modelDiscountRate, modelDiscountStart);

        for (JsonAdaptedItem item : items) {
            product.addItem(item.toModelType());
        }

        return product;
    }

}

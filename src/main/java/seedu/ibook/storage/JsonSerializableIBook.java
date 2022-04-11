package seedu.ibook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.ibook.commons.exceptions.IllegalValueException;
import seedu.ibook.model.IBook;
import seedu.ibook.model.ReadOnlyIBook;
import seedu.ibook.model.product.Product;

/**
 * An Immutable IBook that is serializable to JSON format.
 */
@JsonRootName(value = "ibook")
class JsonSerializableIBook {

    public static final String MESSAGE_DUPLICATE_PRODUCT = "Products list contains duplicate product(s).";

    private final List<JsonAdaptedProduct> products = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableIBook} with the given products.
     */
    @JsonCreator
    public JsonSerializableIBook(@JsonProperty("products") List<JsonAdaptedProduct> products) {
        this.products.addAll(products);
    }

    /**
     * Converts a given {@code ReadOnlyIBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableIBook}.
     */
    public JsonSerializableIBook(ReadOnlyIBook source) {
        products.addAll(source.getProductList().stream().map(JsonAdaptedProduct::new).collect(Collectors.toList()));
    }

    /**
     * Converts this ibook into the model's {@code IBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public IBook toModelType() throws IllegalValueException {
        IBook iBook = new IBook();
        for (JsonAdaptedProduct jsonAdaptedProduct : products) {
            Product product = jsonAdaptedProduct.toModelType();
            if (iBook.hasProduct(product)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PRODUCT);
            }
            iBook.addProduct(product);
        }
        return iBook;
    }

}

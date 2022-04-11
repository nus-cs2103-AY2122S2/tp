package seedu.ibook.testutil;

import seedu.ibook.model.IBook;
import seedu.ibook.model.product.Product;

/**
 * A utility class to help with building IBook objects.
 * Example usage: <br>
 *     {@code IBook iBook = new IBookBuilder().withProduct("John", "Doe").build();}
 */
public class IBookBuilder {

    private IBook iBook;

    public IBookBuilder() {
        iBook = new IBook();
    }

    public IBookBuilder(IBook iBook) {
        this.iBook = iBook;
    }

    /**
     * Adds a new {@code Product} to the {@code IBook} that we are building.
     */
    public IBookBuilder withProduct(Product product) {
        iBook.addProduct(product);
        return this;
    }

    public IBook build() {
        return iBook;
    }
}

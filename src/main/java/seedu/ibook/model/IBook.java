package seedu.ibook.model;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.UniqueProductList;

/**
 * Wraps all data at the iBook level.
 * Duplicates are not allowed. (by .isSameProduct comparison)
 */
public class IBook implements ReadOnlyIBook {

    private final UniqueProductList products;

    /**
     * Default class constructor.
     */
    public IBook() {
        products = new UniqueProductList();
    }

    /**
     * Creates an IBook using the Products in the {@code toBeCopied}
     */
    public IBook(ReadOnlyIBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the product list with {@code products}.
     * {@code products} must not contain duplicate products.
     */
    private void setProducts(List<Product> products) {
        this.products.setProducts(products);
    }

    /**
     * Resets the existing data of this {@code IBook} with {@code newData}.
     */
    public void resetData(ReadOnlyIBook newData) {
        requireNonNull(newData);

        setProducts(newData.getProductList());
    }

    //// product-level operations

    /**
     * Returns true if a product with the same identity as {@code product} exists in the iBook.
     */
    public boolean hasProduct(Product product) {
        requireNonNull(product);
        return products.contains(product);
    }

    /**
     * Adds a product to the iBook.
     * The product must not already exist in the iBook.
     */
    public void addProduct(Product p) {
        products.add(p);
    }

    /**
     * Replaces the given product {@code target} in the list with {@code editedProduct}.
     * {@code target} must exist in the iBook.
     * The product identity of {@code updatedProduct} must not be the same as another existing product in the book.
     */
    public void setProduct(Product target, Product updatedProduct) {
        requireNonNull(updatedProduct);
        products.setProduct(target, updatedProduct);
    }

    /**
     * Removes {@code key} from this {@code IBook}.
     * {@code key} must exist in the iBook.
     */
    public void removeProduct(Product key) {
        products.remove(key);
    }

    //// item-level operations

    /**
     * Adds {@code item} to a {@code product} in the iBook.
     */
    public void addItem(Product product, Item item) {
        requireAllNonNull(product, item);
        product.addItem(item);
    }

    /**
     * Updates {@code targetItem} in {@code targetProduct}.
     * {@code targetProduct} must exist in the iBook and {@code targetItem} must exist in {@code targetProduct}.
     */
    public void setItem(Product targetProduct, Item targetItem, Item updatedItem) {
        requireAllNonNull(targetProduct, targetItem, updatedItem);
        targetProduct.setItem(targetItem, updatedItem);
    }

    /**
     * Removes {@code key} from {@code targetProduct}.
     * {@code targetProduct} must exist in the iBook and {@code key} must exist in {@code targetProduct}.
     */
    public void removeItem(Product product, Item key) {
        requireNonNull(key);
        product.removeItem(key);
    }

    //// util methods

    @Override
    public String toString() {
        return "IBook with " + products.asUnmodifiableObservableList().size() + " products";
    }

    @Override
    public ObservableList<Product> getProductList() {
        return products.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IBook // instanceof handles nulls
                && products.equals(((IBook) other).products));
    }

    @Override
    public int hashCode() {
        return products.hashCode();
    }
}

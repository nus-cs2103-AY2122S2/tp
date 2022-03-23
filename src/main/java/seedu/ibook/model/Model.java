package seedu.ibook.model;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.ibook.commons.core.GuiSettings;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.filters.AttributeFilter;
import seedu.ibook.model.product.filters.ProductFulfillsFiltersPredicate;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    ProductFulfillsFiltersPredicate PREDICATE_SHOW_ALL_PRODUCTS = new ProductFulfillsFiltersPredicate();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' iBook file path.
     */
    Path getIBookFilePath();

    /**
     * Sets the user prefs' iBook file path.
     */
    void setIBookFilePath(Path iBookFilePath);

    /**
     * Replaces iBook data with the data in {@code iBook}.
     */
    void setIBook(ReadOnlyIBook iBook);

    /** Returns the iBook */
    ReadOnlyIBook getIBook();

    /**
     * Returns true if a product with the same identity as {@code product} exists in the iBook.
     */
    boolean hasProduct(Product product);

    /**
     * Deletes the given product.
     * The product must exist in the iBook.
     */
    void deleteProduct(Product target);

    /**
     * Adds the given product.
     * {@code product} must not already exist in the iBook.
     */
    void addProduct(Product product);

    /**
     * Replaces the given product {@code target} with {@code editedProduct}.
     * {@code target} must exist in the iBook.
     * The product identity of {@code editedProduct} must not be the same as another existing product in the book.
     */
    void setProduct(Product target, Product editedProduct);

    /**
     * Deletes the given item from the product.
     * The product must exist in the iBook, and the item must exist in the product.
     */
    void deleteItem(Product targetProduct, Item target);

    /** Returns an unmodifiable view of the filtered product list */
    ObservableList<Product> getFilteredProductList();

    /**
     * Adds a filter to the product list.
     */
    void addProductFilter(AttributeFilter filter);

    /**
     * Removes a filter from the product list.
     */
    void removeProductFilter(AttributeFilter filter);

    /**
     * Clear all filters.
     */
    void clearProductFilters();

    /**
     * Updates the filter of the filtered product list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateProductFilters(ProductFulfillsFiltersPredicate predicate);

    /**
     * Gets the predicate of the current filter.
     */
    ObservableList<AttributeFilter> getProductFilters();
}

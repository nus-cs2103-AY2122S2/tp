package seedu.ibook.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.ibook.commons.core.GuiSettings;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.product.Product;
import seedu.ibook.model.product.filters.AttributeFilter;

/**
 * The API of the Model component.
 */
public interface Model {

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
     * Returns true if a product with the same identity as {@code product} exists in the IBook.
     */
    boolean hasProduct(Product product);

    /**
     * Adds the given product.
     * {@code product} must not already exist in the IBook.
     */
    void addProduct(Product product);

    /**
     * Deletes the given product.
     * The product must exist in the IBook.
     */
    void deleteProduct(Product target);

    /**
     * Replaces the given product {@code target} with {@code updatedProduct}.
     * {@code target} must exist in the Ibook.
     * The product identity of {@code updatedProduct} must not be the same as another existing product in the book.
     */
    void setProduct(Product target, Product updatedProduct);

    /**
     * Adds the given item to {@code product}.
     * {@code item} must not already exist in the IBook.
     */
    void addItem(Product product, Item item);

    /**
     * Deletes the given item from the product.
     * The product must exist in the iBook, and the item must exist in the product.
     */
    void deleteItem(Product targetProduct, Item target);

    /**
     * Updates the given item of a product.
     * The product must exist in the iBook, and the item must exist in the product.
     */
    void updateItem(Product targetProduct, Item targetItem, Item updatedItem);

    /** Returns an unmodifiable view of the filtered product list */
    ObservableList<Product> getFilteredProductList();

    /**
     * Gets the predicate of the current filter.
     */
    ObservableList<AttributeFilter> getProductFilters();

    /**
     * Adds a filter to the product list.
     */
    void addProductFilter(AttributeFilter filter);

    /**
     * Removes a filter from the product list.
     */
    void removeProductFilter(AttributeFilter filter);

    /**
     * Clears all filters.
     */
    void clearProductFilters();

    /**
     * Updates the filter of the filtered product list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateProductFilters(Predicate<Product> predicate);

    /**
     * Applies the filter to the filtered item list of every product in the model by the given {@code predicate}
     * @throws NullPointerException if {@code predicate} is null
     */
    void updateFilteredItemListForProducts(Predicate<Item> predicate);

    /**
     * Prepares the iBook for changes.
     */
    void prepareIBookForChanges();

    /**
     * Saves changes made to IBook.
     */
    void saveIBookChanges();

    /**
     * Checks if the current state of iBook can be undone.
     */
    boolean canUndoIBook();

    /**
     * Checks if there is any undone state of iBook that can be redone.
     */
    boolean canRedoIBook();

    /**
     * Reverts the iBook to one state older.
     */
    void undoIBook();

    /**
     * Restores the iBook to one state newer.
     */
    void redoIBook();
}

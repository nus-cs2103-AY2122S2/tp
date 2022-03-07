package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.product.Product;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Product> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getIBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setIBookFilePath(Path iBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setIBook(ReadOnlyIBook iBook);

    /** Returns the AddressBook */
    ReadOnlyIBook getIBook();

    /**
     * Returns true if a product with the same identity as {@code product} exists in the address book.
     */
    boolean hasProduct(Product product);

    /**
     * Deletes the given product.
     * The product must exist in the address book.
     */
    void deleteProduct(Product target);

    /**
     * Adds the given product.
     * {@code product} must not already exist in the address book.
     */
    void addProduct(Product product);

    /**
     * Replaces the given person {@code target} with {@code editedProduct}.
     * {@code target} must exist in the address book.
     * The product identity of {@code editedProduct} must not be the same as another existing person in the address book.
     */
    void setProduct(Product target, Product editedProduct);

    /** Returns an unmodifiable view of the filtered product list */
    ObservableList<Product> getFilteredProductList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProductList(Predicate<Product> predicate);
}

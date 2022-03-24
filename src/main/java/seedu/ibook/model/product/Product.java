package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.ibook.commons.core.Distinguishable;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.item.Quantity;
import seedu.ibook.model.item.UniqueItemList;

/**
 * Represents a Product in the ibook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Product implements Distinguishable<Product> {

    public static final Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    // Identity fields
    private final Name name;
    private final Category category;

    // Data fields
    private final Description description;
    private final Price price;
    private final UniqueItemList items = new UniqueItemList();
    private final FilteredList<Item> filteredItems;

    /**
     * Every field must be present and not null.
     */
    public Product(Name name, Category category, Description description, Price price) {
        this(name, category, description, price, new ArrayList<>()); // Any empty implementation of List<Item> would do.
    }

    /**
     * Every field must be present and not null.
     */
    public Product(Name name, Category category, Description description, Price price, List<Item> items) {
        requireAllNonNull(name, category, description, price, items);
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.items.setItems(items);
        filteredItems = new FilteredList<>(this.items.asUnmodifiableObservableList());
    }

    public Name getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Description getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public UniqueItemList getItems() {
        return items;
    }

    public ObservableList<Item> getFilteredItems() {
        return filteredItems;
    }


    public Integer getTotalQuantity() {
        return items.getTotalQuantity();
    }

    /**
     * Adds an item to the product.
     */
    public void addItem(Item i) {
        items.add(i);
    }

    /**
     * Returns true if the {@code Item} is in the current viewed list.
     */
    public boolean hasItem(Item i) {
        return filteredItems.contains(i);
    }

    /**
     * Removes {@code key} from this {@code items}.
     * {@code key} must exist in items.
     */
    public void removeItem(Item key) {
        items.remove(key);
    }

    /**
     * Updates {@code target} to {@code updatedItem}
     * {@code target} must exist in items.
     */
    public void setItem(Item target, Item updatedItem) {
        items.setItem(target, updatedItem);
    }

    /**
     * Sets the quantity of the specified item.
     */
    public void setItemCount(Item i, Quantity quantity) {
        items.setItemCount(i, quantity);
    }

    /**
     * Increase the quantity of the specified item.
     */
    public void incrementItemCount(Item i) {
        items.incrementItemCount(i);
    }

    /**
     * Decrease the quantity of the specified item.
     */
    public void decrementItemCount(Item i) {
        items.decrementItemCount(i);
    }

    /**
     * Returns true if both products have the same name and expiry date.
     * This defines a weaker notion of equality between two products.
     */
    public boolean isSame(Product otherProduct) {
        if (otherProduct == this) {
            return true;
        }

        return otherProduct != null
                && otherProduct.getName().equals(getName())
                && otherProduct.getCategory().equals(getCategory());
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
        return getName().equals(otherProduct.getName())
                && getCategory().equals(otherProduct.getCategory())
                && getDescription().equals(otherProduct.getDescription())
                && getPrice().equals(otherProduct.getPrice());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, category, description, price);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Category: ")
                .append(getCategory())
                .append("; Description: ")
                .append(getDescription())
                .append("; Price: ")
                .append(getPrice());

        return builder.toString();
    }

    /**
     * Checks if the Product has items that are expired
     *
     * @return true if the product contains items that are expired.
     */
    public boolean hasExpiredItems() {
        for (Item i : items) {
            if (i.isExpired()) {
                filteredItems.setPredicate(Item::isExpired);
                return true;
            }
        }

        return false;
    }

    /**
     * Updates the filter of the filtered product list to filter by the given {@code predicate}.
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    public void updateFilteredItemList (Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

}

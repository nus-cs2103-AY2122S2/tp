package seedu.ibook.model.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.ibook.commons.core.Distinguishable;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.FilteredItemList;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.item.ItemDescriptor;
import seedu.ibook.model.item.Quantity;
import seedu.ibook.model.item.UniqueItemList;

/**
 * Represents a Product in the iBook.
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
    private final DiscountRate discountRate;
    private final DiscountStart discountStart;
    private final UniqueItemList items = new UniqueItemList();
    private final FilteredItemList filteredItems;

    /**
     * Every field must be present and not null.
     */
    public Product(Name name, Category category, Description description,
                   Price price, DiscountRate discountRate, DiscountStart discountStart) {
        this(name, category, description, price,
                discountRate, discountStart, new ArrayList<>());
    }

    /**
     * Every field must be present and not null.
     */
    public Product(Name name, Category category, Description description, Price price,
                   DiscountRate discountRate, DiscountStart discountStart, List<? extends ItemDescriptor> items) {
        requireAllNonNull(name, category, description, price, discountRate, discountStart, items);
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.discountRate = discountRate;
        this.discountStart = discountStart;
        this.items.setItems(linkItemToProduct(items));
        filteredItems = new FilteredItemList(this.items);
    }

    private List<Item> linkItemToProduct(List<? extends ItemDescriptor> items) {
        return items.stream().map(x -> x.toItem(this)).collect(Collectors.toList());
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

    public DiscountRate getDiscountRate() {
        return discountRate;
    }

    public DiscountStart getDiscountStart() {
        return discountStart;
    }

    public UniqueItemList getItems() {
        return items;
    }

    public FilteredItemList getFilteredItems() {
        return filteredItems;
    }

    public Quantity getTotalQuantity() {
        return items.getTotalQuantity();
    }

    public Price getDiscountedPrice(ExpiryDate expiryDate) {
        long dayDiff = expiryDate.dayDifference(LocalDate.now());
        if (discountStart.hasDiscountStarted(dayDiff)) {
            return price.getDiscountedPrice(discountRate);
        }
        return price;
    }

    /**
     * Adds an item to the product.
     */
    public void addItem(ItemDescriptor i) {
        items.add(i.toItem(this));
    }

    /**
     * Returns true if the {@code Item} is in the current viewed list.
     */
    public boolean hasItem(Item i) {
        return items.contains(i);
    }

    /**
     * Checks if it is allowed to add a new item to current product.
     */
    public boolean canAddItem(Item i) {
        if (!hasItem(i)) {
            return true;
        }

        Item existingItem = items.getExisting(i);
        return existingItem.allowedToAdd(i);
    }

    /**
     * Returns the item in current item list that is similar to the specified item.
     */
    public Item getExistingItem(Item i) {
        return items.getExisting(i);
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
                && getPrice().equals(otherProduct.getPrice())
                && getDiscountRate().equals(otherProduct.getDiscountRate())
                && getDiscountStart().equals(otherProduct.getDiscountStart());
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
                .append(getPrice())
                .append("; Discount Rate: ")
                .append(getDiscountRate())
                .append("; Discount Start: ")
                .append(getDiscountStart());

        return builder.toString();
    }

    /**
     * Checks if the Product has items that are expired.
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
     * Checks if the Product has items that are expiring before but including the {@code expiryDateToCheck}
     * @param expiryDateToCheck
     * @return true if the product has items that are expiring before but including that date.
     */
    public boolean hasExpiringItems(ExpiryDate expiryDateToCheck) {
        for (Item i : items) {
            if (i.getExpiryDate().within(expiryDateToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the filter of the filtered item list with the given {@code predicate}.
     * @param predicate The filter to apply on the item list.
     * @throws NullPointerException if {@code predicate} is null.
     */
    public void updateFilteredItemList (Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

}

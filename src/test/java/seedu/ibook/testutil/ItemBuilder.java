package seedu.ibook.testutil;

import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.item.Quantity;

public class ItemBuilder {
    public static final String DEFAULT_EXPIRY_DATE = "2022-12-13";
    public static final String DEFAULT_QUANTITY = "10";

    private ExpiryDate expiryDate;
    private Quantity quantity;

    /**
     * Creates a {@code ItemBuilder} with the default details.
     */
    public ItemBuilder() {
        expiryDate = new ExpiryDate(DEFAULT_EXPIRY_DATE);
        quantity = new Quantity(DEFAULT_QUANTITY);
    }

    /**
     * Initializes the ItemBuilder with the data of {@code productToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        expiryDate = itemToCopy.getExpiryDate();
        quantity = itemToCopy.getQuantity();
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code Item} that we are building.
     */
    public ItemBuilder withExpiryDate(String expiryDate) {
        this.expiryDate = new ExpiryDate(expiryDate);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Item} that we are building.
     */
    public ItemBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    public Item build() {
        return new Item(expiryDate, quantity);
    }
}

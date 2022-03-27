package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.model.product.Product.PREDICATE_SHOW_ALL_ITEMS;

import java.util.List;
import java.util.Optional;

import seedu.ibook.commons.core.Messages;
import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.commons.util.CollectionUtil;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.Item;
import seedu.ibook.model.item.Quantity;
import seedu.ibook.model.product.Product;

/**
 * Represents a command that when executed updates an item of a specified product in the iBook.
 */
public class UpdateItemCommand extends Command {
    public static final String COMMAND_WORD = "update-item";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates the item identified by the index number used in the displayed list.\n"
            + "Parameters: INDEX (must be a positive integer pair separated by '-')\n"
            + "Example: " + COMMAND_WORD + " 2-1";

    public static final String MESSAGE_UPDATE_ITEM_SUCCESS = "Updated Item: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the iBook.";

    private final CompoundIndex targetIndex;
    private final UpdateItemDescriptor updateItemDescriptor;

    /**
     * Class constructor.
     * @param targetIndex of the item in the filtered product list to update.
     * @param updateItemDescriptor details to update the item with.
     */
    public UpdateItemCommand(CompoundIndex targetIndex, UpdateItemDescriptor updateItemDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(updateItemDescriptor);

        this.targetIndex = targetIndex;
        this.updateItemDescriptor = updateItemDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Product> lastShownList = model.getFilteredProductList();

        if (targetIndex.getZeroBasedFirst() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Product targetProduct = lastShownList.get(targetIndex.getZeroBasedFirst());
        List<Item> targetItemList = targetProduct.getItems().asUnmodifiableObservableList();

        if (targetIndex.getZeroBasedSecond() >= targetItemList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToUpdate = targetItemList.get(targetIndex.getZeroBasedSecond());
        Item updatedItem = createUpdatedItem(itemToUpdate, updateItemDescriptor);

        if (!itemToUpdate.isSame(updatedItem) && targetProduct.hasItem(updatedItem)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.updateItem(targetProduct, itemToUpdate, updatedItem);
        model.updateFilteredItemListForProducts(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(String.format(MESSAGE_UPDATE_ITEM_SUCCESS, updatedItem));
    }

    /**
     * Creates and returns an {@code Item} with the details of {@code itemToUpdate}
     * updated with {@code updateItemDescriptor}.
     */
    private static Item createUpdatedItem(
            Item itemToUpdate, UpdateItemDescriptor updateItemDescriptor) {

        assert itemToUpdate != null;

        ExpiryDate updatedExpiryDate = updateItemDescriptor.getExpiryDate().orElse(itemToUpdate.getExpiryDate());
        Quantity updatedQuantity = updateItemDescriptor.getQuantity().orElse(itemToUpdate.getQuantity());

        return new Item(updatedExpiryDate, updatedQuantity);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateItemCommand)) {
            return false;
        }

        // state check
        UpdateItemCommand e = (UpdateItemCommand) other;
        return targetIndex.equals(e.targetIndex)
                && updateItemDescriptor.equals(e.updateItemDescriptor);
    }

    /**
     * Stores the details to update the item with. Each non-empty field value will replace the
     * corresponding field value of the existing item.
     */
    public static class UpdateItemDescriptor {
        private ExpiryDate expiryDate;
        private Quantity quantity;

        public UpdateItemDescriptor() {}

        /**
         * Copy constructor.
         */
        public UpdateItemDescriptor(UpdateItemDescriptor toCopy) {
            setExpiryDate(toCopy.expiryDate);
            setQuantity(toCopy.quantity);
        }

        /**
         * Returns true if at least one field is updated.
         */
        public boolean isAnyFieldUpdated() {
            return CollectionUtil.isAnyNonNull(expiryDate, quantity);
        }

        public void setExpiryDate(ExpiryDate expiryDate) {
            this.expiryDate = expiryDate;
        }

        public Optional<ExpiryDate> getExpiryDate() {
            return Optional.ofNullable(expiryDate);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateItemDescriptor)) {
                return false;
            }

            // state check
            UpdateItemDescriptor e = (UpdateItemDescriptor) other;

            return getExpiryDate().equals(e.getExpiryDate())
                    && getQuantity().equals(e.getQuantity());
        }
    }
}

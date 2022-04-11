package seedu.ibook.logic.commands.item;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.Optional;

import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.commons.util.CollectionUtil;
import seedu.ibook.logic.commands.Command;
import seedu.ibook.logic.commands.CommandResult;
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
            + ": Updates the details of item identified by the index number used in the displayed list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX [TAG:NEW_VALUE ...]\n"
            + "INDEX must be a positive integer pair separated by '"
            + CompoundIndex.SEPARATOR + "' at most " + Integer.MAX_VALUE + "\n"
            + "Example: " + COMMAND_WORD + " 2" + CompoundIndex.SEPARATOR + "1 "
            + PREFIX_QUANTITY + "100";

    public static final String MESSAGE_UPDATE_ITEM_SUCCESS = "Updated Item in %1$s:\n%2$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_EXCESS_QUANTITY =
            "Total quantity of an item cannot be larger than " + Quantity.MAX_QUANTITY;

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

        Product targetProduct = model.getProduct(targetIndex.getFirst());
        Item itemToUpdate = model.getItem(targetIndex);
        Item updatedItem = createUpdatedItem(targetProduct, itemToUpdate, updateItemDescriptor);

        model.prepareIBookForChanges();

        if (!itemToUpdate.isSame(updatedItem) && targetProduct.hasItem(updatedItem)) {
            // There is an item in targetProduct which has same expiry date as the updatedItem.
            // Checks if merging them is allowed.
            if (!targetProduct.canAddItem(updatedItem)) {
                throw new CommandException(MESSAGE_EXCESS_QUANTITY);
            }

            model.deleteItem(targetProduct, itemToUpdate);
            model.addItem(targetProduct, updatedItem);
        } else {
            model.updateItem(targetProduct, itemToUpdate, updatedItem);
        }

        model.saveIBookChanges();
        model.clearProductFilters();

        return new CommandResult(
                String.format(MESSAGE_UPDATE_ITEM_SUCCESS, targetProduct.getName(), updatedItem));
    }

    /**
     * Creates and returns an {@code Item} with the details of {@code itemToUpdate}
     * updated with {@code updateItemDescriptor}.
     */
    private static Item createUpdatedItem(
            Product product,
            Item itemToUpdate, UpdateItemDescriptor updateItemDescriptor) {

        assert itemToUpdate != null;

        ExpiryDate updatedExpiryDate = updateItemDescriptor.getExpiryDate().orElse(itemToUpdate.getExpiryDate());
        Quantity updatedQuantity = updateItemDescriptor.getQuantity().orElse(itemToUpdate.getQuantity());

        return new Item(product, updatedExpiryDate, updatedQuantity);
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

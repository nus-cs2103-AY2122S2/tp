package seedu.ibook.logic.commands.product;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

import java.util.Optional;

import seedu.ibook.commons.core.index.Index;
import seedu.ibook.commons.util.CollectionUtil;
import seedu.ibook.logic.commands.Command;
import seedu.ibook.logic.commands.CommandResult;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.item.UniqueItemList;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.DiscountRate;
import seedu.ibook.model.product.DiscountStart;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.model.product.Product;

/**
 * Represents a command that when executed updates the details of an
 * existing product in the iBook.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the details of the product identified "
            + "by the index number (a positive integer at most " + Integer.MAX_VALUE + ")"
            + " used in the displayed product list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX [TAG:NEW_VALUE ...]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "potato bread "
            + PREFIX_PRICE + "12.70";

    public static final String MESSAGE_UPDATE_PRODUCT_SUCCESS = "Updated Product:\n%1$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "This product already exists in the ibook.";

    private final Index index;
    private final UpdateProductDescriptor updateProductDescriptor;

    /**
     * @param index of the product in the filtered product list to update.
     * @param updateProductDescriptor details to update the product with.
     */
    public UpdateCommand(Index index, UpdateProductDescriptor updateProductDescriptor) {
        requireNonNull(index);
        requireNonNull(updateProductDescriptor);

        this.index = index;
        this.updateProductDescriptor = new UpdateProductDescriptor(updateProductDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Product productToUpdate = model.getProduct(index);
        Product updatedProduct = createUpdatedProduct(productToUpdate, updateProductDescriptor);

        if (!productToUpdate.isSame(updatedProduct) && model.hasProduct(updatedProduct)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
        }

        model.prepareIBookForChanges();
        model.setProduct(productToUpdate, updatedProduct);
        model.saveIBookChanges();
        model.clearProductFilters();

        return new CommandResult(String.format(MESSAGE_UPDATE_PRODUCT_SUCCESS, updatedProduct));
    }

    /**
     * Creates and returns a {@code Product} with the details of {@code productToUpdate}
     * updated with {@code updateProductDescriptor}.
     */
    public static Product createUpdatedProduct(
            Product productToUpdate, UpdateProductDescriptor updateProductDescriptor) {
        assert productToUpdate != null;

        Name updatedName = updateProductDescriptor.getName().orElse(productToUpdate.getName());
        Category updatedCategory = updateProductDescriptor.getCategory().orElse(productToUpdate.getCategory());
        Description updatedDescription =
                updateProductDescriptor.getDescription().orElse(productToUpdate.getDescription());
        Price updatedPrice = updateProductDescriptor.getPrice().orElse(productToUpdate.getPrice());
        DiscountRate updatedDiscountRate =
                updateProductDescriptor.getDiscountRate().orElse(productToUpdate.getDiscountRate());
        DiscountStart updatedDiscountStart =
                updateProductDescriptor.getDiscountStart().orElse(productToUpdate.getDiscountStart());

        UniqueItemList items = updateProductDescriptor.getItems().orElse(productToUpdate.getItems());

        // Updates to items via this command is not available
        assert items.equals(productToUpdate.getItems());

        return new Product(updatedName, updatedCategory, updatedDescription, updatedPrice,
                updatedDiscountRate, updatedDiscountStart, items.asObservableList());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateCommand)) {
            return false;
        }

        // state check
        UpdateCommand e = (UpdateCommand) other;
        return index.equals(e.index)
                && updateProductDescriptor.equals(e.updateProductDescriptor);
    }

    /**
     * Stores the details to update the product with. Each non-empty field value will replace the
     * corresponding field value of the existing product.
     */
    public static class UpdateProductDescriptor {
        private Name name;
        private Category category;
        private Description description;
        private Price price;
        private DiscountRate discountRate;
        private DiscountStart discountStart;
        private UniqueItemList items;

        public UpdateProductDescriptor() {}

        /**
         * Copy constructor.
         */
        public UpdateProductDescriptor(UpdateProductDescriptor toCopy) {
            setName(toCopy.name);
            setCategory(toCopy.category);
            setDescription(toCopy.description);
            setPrice(toCopy.price);
            setDiscountRate(toCopy.discountRate);
            setDiscountStart(toCopy.discountStart);
            setItems(toCopy.items);
        }

        /**
         * Returns true if at least one field is updated.
         */
        public boolean isAnyFieldUpdated() {
            return CollectionUtil.isAnyNonNull(name, category, description, price, discountRate, discountStart);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Optional<Category> getCategory() {
            return Optional.ofNullable(category);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        public void setDiscountRate(DiscountRate discountRate) {
            this.discountRate = discountRate;
        }

        public Optional<DiscountRate> getDiscountRate() {
            return Optional.ofNullable(discountRate);
        }

        public void setDiscountStart(DiscountStart discountStart) {
            this.discountStart = discountStart;
        }

        public Optional<DiscountStart> getDiscountStart() {
            return Optional.ofNullable(discountStart);
        }

        public void setItems(UniqueItemList items) {
            this.items = items;
        }

        public Optional<UniqueItemList> getItems() {
            return Optional.ofNullable(items);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateProductDescriptor)) {
                return false;
            }

            // state check
            UpdateProductDescriptor e = (UpdateProductDescriptor) other;

            return getName().equals(e.getName())
                    && getCategory().equals(e.getCategory())
                    && getDescription().equals(e.getDescription())
                    && getPrice().equals(e.getPrice())
                    && getDiscountRate().equals(e.getDiscountRate())
                    && getDiscountStart().equals(e.getDiscountStart())
                    && getItems().equals(e.getItems());
        }
    }

}

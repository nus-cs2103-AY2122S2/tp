package seedu.ibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;

import java.util.ArrayList;
import java.util.List;

import seedu.ibook.logic.commands.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.logic.commands.exceptions.CommandException;
import seedu.ibook.model.Model;
import seedu.ibook.model.product.Product;

/**
 * Updates the details of all products shown in the displayed list.
 */
public class UpdateAllCommand extends Command {

    public static final String COMMAND_WORD = "update-all";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates all products in the displayed list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: [TAG:NEW_VALUE ...]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CATEGORY + "bread "
            + PREFIX_EXPIRY_DATE + "2022-02-14";

    public static final String MESSAGE_UPDATE_ALL_PRODUCT_SUCCESS = "All products updated.\n";
    public static final String MESSAGE_UPDATED_PRODUCT = "Updated Product: %1$s\n";
    public static final String MESSAGE_NO_PRODUCT_TO_UPDATE = "There is no products to update!";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_DUPLICATE_PRODUCT =
            "The result of this update will cause duplicate products in the ibook.";

    private final UpdateProductDescriptor updateProductDescriptor;

    /**
     * Class constructor.
     * @param updateProductDescriptor details to update the product with.
     */
    public UpdateAllCommand(UpdateProductDescriptor updateProductDescriptor) {
        requireNonNull(updateProductDescriptor);

        this.updateProductDescriptor = new UpdateProductDescriptor(updateProductDescriptor);
    }

    /**
     * Finds the number of occurrences of the {@code product} in {@code list}
     * based on the Product#isSame(Product) method.
     *
     * @param product the product in concern.
     * @param list a list of product.
     * @return number of occurrences of the specified product in the list.
     */
    private long numOfOccurrences(Product product, List<Product> list) {
        return list.stream().filter(p -> p.isSame(product)).count();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Product> lastShownList = model.getFilteredProductList();
        List<Product> productsToUpdate = new ArrayList<>(lastShownList);

        if (productsToUpdate.size() == 0) {
            throw new CommandException(MESSAGE_NO_PRODUCT_TO_UPDATE);
        }

        List<Product> updatedProducts = new ArrayList<>();
        for (Product productToUpdate : productsToUpdate) {
            Product updatedProduct = UpdateCommand.createUpdatedProduct(productToUpdate, updateProductDescriptor);
            updatedProducts.add(updatedProduct);
        }

        assert productsToUpdate.size() == updatedProducts.size();

        // sanity check to ensure no duplicate of products
        for (Product updatedProduct : updatedProducts) {
            if (model.hasProduct(updatedProduct) && numOfOccurrences(updatedProduct, productsToUpdate) == 0
                || numOfOccurrences(updatedProduct, updatedProducts) > 1) {
                throw new CommandException(MESSAGE_DUPLICATE_PRODUCT);
            }
        }

        StringBuilder updatedProductDescription = new StringBuilder();
        for (int i = 0; i < productsToUpdate.size(); i++) {
            Product productToUpdate = productsToUpdate.get(i);
            Product updatedProduct = updatedProducts.get(i);

            model.setProduct(productToUpdate, updatedProduct);
            updatedProductDescription.append(String.format(MESSAGE_UPDATED_PRODUCT, updatedProduct));
        }

        model.clearProductFilters();
        return new CommandResult(MESSAGE_UPDATE_ALL_PRODUCT_SUCCESS + updatedProductDescription);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateAllCommand)) {
            return false;
        }

        // state check
        UpdateAllCommand e = (UpdateAllCommand) other;
        return updateProductDescriptor.equals(e.updateProductDescriptor);
    }
}

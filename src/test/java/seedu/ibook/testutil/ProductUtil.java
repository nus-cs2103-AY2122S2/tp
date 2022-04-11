package seedu.ibook.testutil;

import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DISCOUNT_RATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DISCOUNT_START;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.ibook.logic.commands.product.AddCommand;
import seedu.ibook.logic.commands.product.UpdateCommand;
import seedu.ibook.model.product.Product;

/**
 * A utility class for Product.
 */
public class ProductUtil {
    /**
     * Returns an add command string for adding the {@code product}.
     */
    public static String getAddCommand(Product product) {
        return AddCommand.COMMAND_WORD + " " + getProductDetails(product);
    }

    /**
     * Returns the part of command string for the given {@code product}'s details.
     */
    public static String getProductDetails(Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + product.getName().toString() + " ");
        sb.append(PREFIX_CATEGORY + product.getCategory().toString() + " ");
        sb.append(PREFIX_DESCRIPTION + product.getDescription().toString() + " ");
        sb.append(PREFIX_PRICE + product.getPrice().toString() + " ");
        sb.append(PREFIX_DISCOUNT_RATE + product.getDiscountRate().toString() + " ");
        sb.append(PREFIX_DISCOUNT_START + product.getDiscountStart().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProductDescriptor}'s details.
     */
    public static String getUpdateProductDescriptorDetails(UpdateCommand.UpdateProductDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(
            name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getCategory().ifPresent(
            category -> sb.append(PREFIX_CATEGORY).append(category.fullCategoryName).append(" "));
        descriptor.getDescription().ifPresent(
            description -> sb.append(PREFIX_DESCRIPTION).append(description).append(" "));
        descriptor.getPrice().ifPresent(
            price -> sb.append(PREFIX_PRICE).append(price).append(" "));
        descriptor.getDiscountRate().ifPresent(
            discountRate -> sb.append(PREFIX_DISCOUNT_RATE).append(discountRate).append(" "));
        descriptor.getDiscountStart().ifPresent(
            discountStart -> sb.append(PREFIX_DISCOUNT_START).append(discountStart).append(" "));
        return sb.toString();
    }

}

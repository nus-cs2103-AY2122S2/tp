package seedu.ibook.testutil;

import static seedu.ibook.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.ibook.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.ibook.logic.commands.AddCommand;
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
        sb.append(PREFIX_EXPIRY_DATE + product.getExpiryDate().toString() + " ");
        sb.append(PREFIX_DESCRIPTION + product.getDescription().toString() + " ");
        sb.append(PREFIX_PRICE + product.getPrice().toString() + " ");
        return sb.toString();
    }
    /*
    /**
     * Returns the part of command string for the given {@code EditProductDescriptor}'s details.

    public static String getEditProductDescriptorDetails(EditProductDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
    */
}

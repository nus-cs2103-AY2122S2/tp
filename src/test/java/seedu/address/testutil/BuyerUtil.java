package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.EditBuyerCommand.EditBuyerDescriptor;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.tag.Tag;

/**
 * A utility class for client.
 */
public class BuyerUtil {

    /**
     * Returns an add command string for adding the {@code buyer}.
     */
    public static String getAddBuyerCommand(Buyer buyer) {
        return AddBuyerCommand.COMMAND_WORD + " " + getBuyerDetails(buyer);
    }

    /**
     * Returns the part of the command string for the given {@code buyer}'s PropertyToBuy details.
     */
    public static String getPropertyToBuyCommand(Buyer buyer) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_LOCATION + buyer.getPropertyToBuy().getHouse().getLocation().toString() + " ");
        sb.append(PREFIX_HOUSE_TYPE + buyer.getPropertyToBuy().getHouse().getHouseTypeToString() + " ");
        sb.append(PREFIX_PRICE_RANGE
                + buyer.getPropertyToBuy().getPriceRange().toString().split("]")[0].substring(1) + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code buyer}'s details.
     */
    public static String getBuyerDetails(Buyer buyer) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + buyer.getName().fullName + " ");
        sb.append(PREFIX_PHONE + buyer.getPhone().value + " ");
        buyer.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditclientDescriptor}'s details.
     */
    public static String getEditBuyerDescriptorDetails(EditBuyerDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
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
}

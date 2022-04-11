package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.NullPropertyToSell;
import seedu.address.model.property.PropertyToSell;
import seedu.address.model.seller.Seller;
import seedu.address.model.tag.Tag;

public class AddPropertyToSellCommand extends Command {

    public static final String COMMAND_WORD = "add-pts";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a new seller's property. "
            + "Parameters: "
            + "INDEX "
            + PREFIX_HOUSE_TYPE + "HOUSE TYPE "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_PRICE_RANGE + "PRICE RANGE "
            + PREFIX_LOCATION + "LOCATION"
            + "Must include: index h/ a/ pr/ l/ \n"
            + "Ensure INDEX is positive integer & less than or equal to size of displayed seller list \n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_HOUSE_TYPE + "HDB "
            + PREFIX_ADDRESS + "Blk 123, Serangoon Ave 1 #09-369 "
            + PREFIX_PRICE_RANGE + "400000,500000 "
            + PREFIX_LOCATION + "Serangoon";

    public static final String MESSAGE_SUCCESS = "New property added for seller: %1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "The seller already has a property, cannot add new one";

    private final Index index;
    private final PropertyToSell propertyToSell;

    /**
     * Creates an AddSellerCommand to add the specified {@code seller}
     */
    public AddPropertyToSellCommand(Index index, PropertyToSell propertyToSell) {
        requireNonNull(propertyToSell);
        requireNonNull(index);

        this.index = index;
        this.propertyToSell = propertyToSell;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Seller> lastShownList = model.getFilteredSellerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
        }

        Seller sellerToUpdate = lastShownList.get(index.getZeroBased());

        if (!sellerToUpdate.getPropertyToSell().equals(NullPropertyToSell.getNullPropertyToSell())) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }
        Seller sellerWithProperty = createPropertyForSeller(sellerToUpdate, propertyToSell);
        model.setSeller(sellerToUpdate, sellerWithProperty);
        model.updateFilteredSellerList(Model.PREDICATE_SHOW_ALL_SELLERS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, sellerWithProperty));
    }

    /**
     * Creates a Seller instance with the updated PropertyToSell.
     *
     * @param seller The original seller.
     * @param property The new PropertyToSell.
     * @return A new Seller.
     */
    public Seller createPropertyForSeller(Seller seller, PropertyToSell property) {
        assert seller != null;

        Name sellerName = seller.getName();
        Phone sellerPhone = seller.getPhone();
        Set<Tag> sellerTags = seller.getTags();
        Appointment sellerAppointment = seller.getAppointment();

        return new Seller(sellerName, sellerPhone, sellerAppointment, sellerTags, property);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPropertyToSellCommand // instanceof handles nulls
                && propertyToSell.equals(((AddPropertyToSellCommand) other).propertyToSell))
                && index.equals(((AddPropertyToSellCommand) other).index);
    }
}

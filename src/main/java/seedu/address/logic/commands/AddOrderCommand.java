package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLLECTION_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERYDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.CollectionType;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.Remark;

/**
 * Changes the remark of an existing person in ReadyBakey.
 */
public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to ReadyBakey. "
            + "Parameters: "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_DETAILS + "DETAILS "
            + PREFIX_DELIVERYDATETIME + "DELIVERYDATETIME "
            + PREFIX_COLLECTION_TYPE + "COLLECTION_TYPE "
            + "[" + PREFIX_REMARK + "REMARK]... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_DETAILS + "1: Jerry Favourite Cheese Cake "
            + PREFIX_DELIVERYDATETIME + "25-12-2022 15:30 "
            + PREFIX_COLLECTION_TYPE + "Delivery "
            + PREFIX_REMARK + "Add Cheese ";


    public static final String MESSAGE_ORDER_SUCCESS = "New order added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the address book";
    public static final String MESSAGE_NO_PERSON_FOUND = "No person found with the same phone number, "
            + "please enter a valid phone number.";

    private final List<Details> details;
    private final Phone phone;
    private final CollectionType collectionType;
    private final DeliveryDateTime deliveryDateTime;
    private final Remark remark;

    /**
     * Creates an AddOrderCommand to add the specified {@code Order}
     */
    public AddOrderCommand(Phone phone, Remark remark, List<Details> details, DeliveryDateTime deliveryDateTime,
                           CollectionType collectionType) {
        requireAllNonNull(phone, remark, details, deliveryDateTime, collectionType);
        this.phone = phone;
        this.remark = remark;
        this.details = details;
        this.deliveryDateTime = deliveryDateTime;
        this.collectionType = collectionType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Order toAdd = buildOrder(model);

        if (model.hasOrder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.addOrder(toAdd);
        return new CommandResult(String.format(MESSAGE_ORDER_SUCCESS, toAdd), true, false);
    }

    private Order buildOrder(Model model) throws CommandException {
        ArrayList<String> phoneKeywords = new ArrayList<String>();
        phoneKeywords.add(phone.value);
        FilteredList<Person> filteredPersons = model.getPersonList()
                .filtered(new PhoneContainsKeywordsPredicate(phoneKeywords));
        if (filteredPersons.isEmpty()) {
            throw new CommandException(MESSAGE_NO_PERSON_FOUND);
        }
        Person p = filteredPersons.get(0);
        UUID uuid = p.getUuid();
        Order toAdd = new Order(remark, details, deliveryDateTime, collectionType, uuid);
        return toAdd;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOrderCommand // instanceof handles nulls
                && details.equals(((AddOrderCommand) other).details)
                && phone.equals(((AddOrderCommand) other).phone)
                && remark.equals(((AddOrderCommand) other).remark)
                && deliveryDateTime.equals(((AddOrderCommand) other).deliveryDateTime)
                && collectionType.equals(((AddOrderCommand) other).collectionType));
    }
}

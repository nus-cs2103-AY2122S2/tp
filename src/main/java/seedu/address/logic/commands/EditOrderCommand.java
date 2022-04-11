package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLLECTION_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERYDATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.CollectionType;
import seedu.address.model.order.Complete;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;
import seedu.address.model.person.Remark;

/**
 * Edits the information of an existing order in the ReadyBakey.
 */
public class EditOrderCommand extends Command {

    public static final String COMMAND_WORD = "edito";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the information of the order identified "
            + "by the index number used in the displayed order list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DETAILS + "DETAILS "
            + PREFIX_REMARK + "REMARK "
            + PREFIX_DELIVERYDATETIME + "DELIVERYDATETIME "
            + PREFIX_COLLECTION_TYPE + "COLLECTION_TYPE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Add Cheese "
            + PREFIX_DETAILS + "1: Jerry Favourite Cheese Cake "
            + PREFIX_DETAILS + "2: Chocolate Cake "
            + PREFIX_DELIVERYDATETIME + "25-12-2022 15:30 "
            + PREFIX_COLLECTION_TYPE + "Delivery";

    public static final String MESSAGE_EDIT_ORDER_SUCCESS = "Edited Order: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditOrderDescriptor editOrderDescriptor;

    /**
     * @param index of the order in the filtered order list to edit
     * @param editOrderDescriptor details to edit the order with
     */
    public EditOrderCommand(Index index, EditOrderDescriptor editOrderDescriptor) {
        requireNonNull(index);
        requireNonNull(editOrderDescriptor);

        this.index = index;
        this.editOrderDescriptor = new EditOrderDescriptor(editOrderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());
        Order editedOrder = createEditedOrder(orderToEdit, editOrderDescriptor);

        model.setOrder(orderToEdit, editedOrder);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder), true, false);
    }

    /**
     * Creates and returns a {@code Order} with the details of {@code orderToEdit}
     * edited with {@code editOrderDescriptor}.
     */
    private static Order createEditedOrder(Order orderToEdit, EditOrderDescriptor editOrderDescriptor) {
        assert orderToEdit != null;

        List<Details> updatedDetails = editOrderDescriptor.getDetails().orElse(orderToEdit.getDetails());
        Complete complete = orderToEdit.getComplete();
        UUID uuid = orderToEdit.getUuid();
        Remark updatedRemarks = editOrderDescriptor.getRemark().orElse(orderToEdit.getRemark());
        DeliveryDateTime updatedDeliveryDateTime = editOrderDescriptor.getDeliveryDateTime()
                .orElse(orderToEdit.getDeliveryDateTime());
        CollectionType updatedCollectionType = editOrderDescriptor.getCollectionType()
                .orElse(orderToEdit.getCollectionType());

        return new Order(updatedRemarks, updatedDetails,
                updatedDeliveryDateTime, updatedCollectionType, complete, uuid);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditOrderCommand)) {
            return false;
        }

        // state check
        EditOrderCommand e = (EditOrderCommand) other;
        return index.equals(e.index)
                && editOrderDescriptor.equals(e.editOrderDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditOrderDescriptor {
        private List<Details> details;
        private Remark remark;
        private DeliveryDateTime deliveryDateTime;
        private CollectionType collectionType;

        public EditOrderDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditOrderDescriptor(EditOrderDescriptor toCopy) {
            setDetails(toCopy.details);
            setRemark(toCopy.remark);
            setDeliveryDateTime(toCopy.deliveryDateTime);
            setCollectionType(toCopy.collectionType);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(details, remark, deliveryDateTime, collectionType);
        }

        public void setDetails(List<Details> details) {
            this.details = details;
        }

        public Optional<List<Details>> getDetails() {
            return Optional.ofNullable(details);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setDeliveryDateTime(DeliveryDateTime deliveryDateTime) {
            this.deliveryDateTime = deliveryDateTime;
        }

        public Optional<DeliveryDateTime> getDeliveryDateTime() {
            return Optional.ofNullable(deliveryDateTime);
        }

        public void setCollectionType(CollectionType collectionType) {
            this.collectionType = collectionType;
        }

        public Optional<CollectionType> getCollectionType() {
            return Optional.ofNullable(collectionType);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditOrderDescriptor)) {
                return false;
            }

            // state check
            EditOrderDescriptor e = (EditOrderDescriptor) other;


            return getDetails().equals(e.getDetails())
                    && getRemark().equals(e.getRemark())
                    && getDeliveryDateTime().equals(e.getDeliveryDateTime())
                    && getCollectionType().equals(e.getCollectionType());
        }
    }
}

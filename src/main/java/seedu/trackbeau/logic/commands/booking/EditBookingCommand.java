package seedu.trackbeau.logic.commands.booking;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_CUSTOMERINDEX;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_FEEDBACK;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICEINDEX;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.trackbeau.logic.parser.booking.AddBookingCommandParser.EMPTY_FEEDBACK_TYPE;
import static seedu.trackbeau.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.commons.util.CollectionUtil;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.booking.BookingDateTime;
import seedu.trackbeau.model.booking.Feedback;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.service.Service;
import seedu.trackbeau.ui.Panel;


/**
 * Edits the details of an existing booking in trackBeau.
 */
public class EditBookingCommand extends Command {

    public static final String COMMAND_WORD = "editb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the booking identified "
            + "by the index number used in the displayed bookings list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CUSTOMERINDEX + "CUSTOMERINDEX (must be a positive integer)] "
            + "[" + PREFIX_SERVICEINDEX + "SERVICEINDEX (must be a positive integer)] "
            + "[" + PREFIX_STARTTIME + "APPOINTMENTTIME] "
            + "[" + PREFIX_FEEDBACK + "FEEDBACK] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CUSTOMERINDEX + "2 "
            + PREFIX_SERVICEINDEX + "2 "
            + PREFIX_STARTTIME + "11-11-2022 12:30 "
            + PREFIX_FEEDBACK + "Excellent customer service";

    public static final String MESSAGE_EDIT_BOOKING_SUCCESS = "Edited Booking: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking already exists in trackBeau.";

    private final Index index;
    private final EditBookingDescriptor editBookingDescriptor;

    /**
     * @param index of the booking in the bookings list to edit
     * @param editBookingDescriptor details to edit the booking with
     */
    public EditBookingCommand(Index index, EditBookingDescriptor editBookingDescriptor) {
        requireNonNull(index);
        requireNonNull(editBookingDescriptor);

        this.index = index;
        this.editBookingDescriptor = new EditBookingDescriptor(editBookingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownCustomerList = model.getFilteredCustomerList();
        List<Service> lastShownServiceList = model.getFilteredServiceList();
        List<Booking> lastShownBookingList = model.getFilteredBookingList();

        Index customerIndex = editBookingDescriptor.getCustomerIndex();
        Index serviceIndex = editBookingDescriptor.getServiceIndex();
        Optional<Feedback> feedback = editBookingDescriptor.getFeedback();

        Booking bookingToEdit = lastShownBookingList.get(index.getZeroBased());
        BookingDateTime updatedDateTime = editBookingDescriptor.getBookingDateTime()
                .orElse(bookingToEdit.getBookingDateTime());

        if (index.getZeroBased() >= lastShownBookingList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        if (customerIndex != null && customerIndex.getOneBased() >= lastShownCustomerList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        if (serviceIndex != null && serviceIndex.getOneBased() >= lastShownServiceList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX);
        }

        if ((LocalDateTime.now().compareTo(updatedDateTime.value) < 0)) {
            if (feedback.isPresent()) {
                throw new CommandException(Messages.MESSAGE_INVALID_FEEDBACK_TIME);
            } else {
                editBookingDescriptor.setFeedback(new Feedback(EMPTY_FEEDBACK_TYPE));
            }
        }

        Booking editedBooking = createEditedBooking(bookingToEdit, editBookingDescriptor,
                lastShownCustomerList, lastShownServiceList);

        if (!bookingToEdit.isSameItem(editedBooking) && model.hasBooking(editedBooking)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        model.setBooking(bookingToEdit, editedBooking);
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking), Panel.BOOKING_PANEL);
    }

    /**
     * Creates and returns a {@code Booking} with the details of {@code bookingToEdit}
     * edited with {@code editBookingDescriptor}.
     */
    private static Booking createEditedBooking(Booking bookingToEdit, EditBookingDescriptor editBookingDescriptor,
           List<Customer> lastShownCustomerList, List<Service> lastShownServiceList) {

        assert bookingToEdit != null;

        Customer updatedCustomer;
        Service updatedService;

        if (editBookingDescriptor.getCustomerIndex() != null) {
            updatedCustomer = lastShownCustomerList.get(editBookingDescriptor.getCustomerIndex().getZeroBased());
        } else {
            updatedCustomer = bookingToEdit.getCustomer();
        }

        if (editBookingDescriptor.getServiceIndex() != null) {
            updatedService = lastShownServiceList.get(editBookingDescriptor.getServiceIndex().getZeroBased());
        } else {
            updatedService = bookingToEdit.getService();
        }

        BookingDateTime updatedDateTime = editBookingDescriptor.getBookingDateTime()
                .orElse(bookingToEdit.getBookingDateTime());

        Feedback updatedFeedback = editBookingDescriptor.getFeedback()
                .orElse(bookingToEdit.getFeedback());

        return new Booking(updatedCustomer, updatedService, updatedDateTime, updatedFeedback);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBookingCommand)) {
            return false;
        }

        // state check
        EditBookingCommand e = (EditBookingCommand) other;
        return index.equals(e.index) && editBookingDescriptor.equals(e.editBookingDescriptor);
    }

    /**
     * Stores the details to edit the booking with. Each non-empty field value will replace the
     * corresponding field value of the booking.
     */
    public static class EditBookingDescriptor {
        private Index customerIndex;
        private Index serviceIndex;
        private BookingDateTime bookingDateTime;
        private Feedback bookingFeedback;

        public EditBookingDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code toCopy} is used internally.
         */
        public EditBookingDescriptor(EditBookingDescriptor toCopy) {
            setCustomerIndex(toCopy.customerIndex);
            setServiceIndex(toCopy.serviceIndex);
            setBookingDateTime(toCopy.bookingDateTime);
            setFeedback(toCopy.bookingFeedback);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(customerIndex, serviceIndex, bookingDateTime, bookingFeedback);
        }

        public void setCustomerIndex(Index customerIndex) {
            this.customerIndex = customerIndex;
        }

        public Index getCustomerIndex() {
            return customerIndex;
        }

        public void setServiceIndex(Index serviceIndex) {
            this.serviceIndex = serviceIndex;
        }

        public Index getServiceIndex() {
            return serviceIndex;
        }

        public void setBookingDateTime(BookingDateTime bookingDateTime) {
            this.bookingDateTime = bookingDateTime;
        }

        public Optional<BookingDateTime> getBookingDateTime() {
            return Optional.ofNullable(bookingDateTime);
        }

        public void setFeedback(Feedback bookingFeedback) {
            this.bookingFeedback = bookingFeedback;
        }

        public Optional<Feedback> getFeedback() {
            return Optional.ofNullable(bookingFeedback);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBookingDescriptor)) {
                return false;
            }

            // state check
            EditBookingDescriptor e = (EditBookingDescriptor) other;

            return getCustomerIndex().equals(e.getCustomerIndex())
                    && getServiceIndex().equals(e.getServiceIndex())
                    && getBookingDateTime().equals(e.getBookingDateTime())
                    && getFeedback().equals((e.getFeedback()));
        }
    }
}

package seedu.trackbeau.logic.commands.customer;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.commands.booking.DeleteBookingCommand.MESSAGE_DELETE_BOOKING_SUCCESS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.ui.Panel;

/**
 * Deletes a customer identified using it's displayed index from trackBeau.
 */
public class DeleteCustomerCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the customer identified by the index number used in the displayed customer list.\n"
            + "Parameters: INDEXES (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1,2";

    public static final String MESSAGE_DELETE_CUSTOMER_SUCCESS = "Deleted Customer(s):\n%1$s";

    private final ArrayList<Index> targetIndexes;

    public DeleteCustomerCommand(ArrayList<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    /**
     * Returns a list of bookings affected by the deletion of customers.
     */
    public ArrayList<Booking> bookingsToDelete(List<Customer> lastShownCustomersList,
                                               List<Booking> lastShownBookingsList) {
        ArrayList<Booking> bookingsToDelete = new ArrayList<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentTimeStr = localDateTime.format(formatter);
        LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);

        for (Booking booking : lastShownBookingsList) {
            for (Index targetIndex : targetIndexes) {
                if (booking.getCustomer().equals(lastShownCustomersList.get(targetIndex.getZeroBased()))
                        && booking.getBookingDateTime().value.isAfter(currentTime)) {
                    bookingsToDelete.add(booking);
                    break;
                }
            }
        }

        return bookingsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownCustomersList = model.getFilteredCustomerList();
        List<Booking> lastShownBookingsList = model.getFilteredBookingList();

        ArrayList<Customer> customersToDelete = new ArrayList<>();

        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownCustomersList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
            }
            Customer customerToDelete = lastShownCustomersList.get(targetIndex.getZeroBased());
            if (customersToDelete.contains(customerToDelete)) {
                throw new CommandException(Messages.MESSAGE_DUPLICATED_INDEX);
            }
            customersToDelete.add(customerToDelete);
        }
        ArrayList<Booking> bookingsToDelete = bookingsToDelete(lastShownCustomersList, lastShownBookingsList);

        StringBuilder sbCustomers = new StringBuilder();
        StringBuilder sbBookings = new StringBuilder();

        for (Customer customerToDelete : customersToDelete) {
            model.deleteCustomer(customerToDelete);
            sbCustomers.append(customerToDelete).append("\n");
        }

        for (Booking bookingToDelete : bookingsToDelete) {
            model.deleteBooking(bookingToDelete);
            sbBookings.append(bookingToDelete).append("\n");
        }

        String successMessage = String.format(MESSAGE_DELETE_CUSTOMER_SUCCESS, sbCustomers);

        if (sbBookings.length() > 0) {
            successMessage += String.format(MESSAGE_DELETE_BOOKING_SUCCESS, sbBookings);
        }

        return new CommandResult(successMessage, Panel.CUSTOMER_PANEL);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCustomerCommand // instanceof handles nulls
                && targetIndexes.containsAll(((DeleteCustomerCommand) other).targetIndexes)); // state check
    }
}

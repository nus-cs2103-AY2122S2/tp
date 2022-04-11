package seedu.trackbeau.logic.commands.service;

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
import seedu.trackbeau.model.service.Service;
import seedu.trackbeau.ui.Panel;

/**
 * Deletes service(s) identified using it's displayed index from trackBeau.
 */
public class DeleteServiceCommand extends Command {

    public static final String COMMAND_WORD = "deletes";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the service(s) identified by the index number(s) used in the displayed service list.\n"
        + "Parameters: INDEXES (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1,2";

    public static final String MESSAGE_DELETE_SERVICE_SUCCESS = "Deleted Service(s):\n%1$s";

    private final ArrayList<Index> targetIndexes;

    public DeleteServiceCommand(ArrayList<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    /**
     * Returns a list of bookings affected by the deletion of Services.
     */
    public ArrayList<Booking> bookingsToDelete(List<Service> lastShownServicesList,
                                               List<Booking> lastShownBookingsList) {
        ArrayList<Booking> bookingsToDelete = new ArrayList<>();

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentTimeStr = localDateTime.format(formatter);
        LocalDateTime currentTime = LocalDateTime.parse(currentTimeStr, formatter);

        for (Booking booking : lastShownBookingsList) {
            for (Index targetIndex : targetIndexes) {
                if (booking.getService().equals(lastShownServicesList.get(targetIndex.getZeroBased()))
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
        List<Service> lastShownServiceList = model.getFilteredServiceList();
        List<Booking> lastShownBookingList = model.getFilteredBookingList();

        ArrayList<Service> servicesToDelete = new ArrayList<>();

        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownServiceList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX);
            }
            Service serviceToDelete = lastShownServiceList.get(targetIndex.getZeroBased());
            if (servicesToDelete.contains(serviceToDelete)) {
                throw new CommandException(Messages.MESSAGE_DUPLICATED_INDEX);
            }
            servicesToDelete.add(serviceToDelete);
        }

        ArrayList<Booking> bookingsToDelete = bookingsToDelete(lastShownServiceList, lastShownBookingList);

        StringBuilder sbServices = new StringBuilder();
        StringBuilder sbBookings = new StringBuilder();

        for (Service serviceToDelete : servicesToDelete) {
            model.deleteService(serviceToDelete);
            sbServices.append(serviceToDelete).append("\n");
        }

        for (Booking bookingToDelete : bookingsToDelete) {
            model.deleteBooking(bookingToDelete);
            sbBookings.append(bookingToDelete).append("\n");
        }

        String successMessage = String.format(MESSAGE_DELETE_SERVICE_SUCCESS, sbServices);

        if (sbBookings.length() > 0) {
            successMessage += String.format(MESSAGE_DELETE_BOOKING_SUCCESS, sbBookings);
        }

        return new CommandResult(successMessage, Panel.SERVICE_PANEL);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteServiceCommand // instanceof handles nulls
            && targetIndexes.containsAll(((DeleteServiceCommand) other).targetIndexes)); // state check
    }
}

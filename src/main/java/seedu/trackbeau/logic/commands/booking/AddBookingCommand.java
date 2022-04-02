package seedu.trackbeau.logic.commands.booking;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_CUSTOMERINDEX;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICEINDEX;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.trackbeau.logic.parser.booking.AddBookingCommandParser.EMPTY_FEEDBACK_TYPE;

import java.util.List;

import seedu.trackbeau.commons.core.Messages;
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
 * Adds a booking to trackBeau.
 */
public class AddBookingCommand extends Command {

    public static final String COMMAND_WORD = "addb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Booking to TrackBeau. "
            + "Parameters: "
            + PREFIX_CUSTOMERINDEX + "CUSTOMERINDEX (must be a positive integer) "
            + PREFIX_SERVICEINDEX + "SERVICEINDEX (must be a positive integer) "
            + PREFIX_STARTTIME + "APPOINTMENTTIME (In format dd-MM-yyyy HH:mm) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CUSTOMERINDEX + "1 "
            + PREFIX_SERVICEINDEX + "1 "
            + PREFIX_STARTTIME + "10-10-2022 10:30";

    public static final String MESSAGE_SUCCESS = "New Booking added: \n%1$s";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking already exists in TrackBeau";

    private final Integer customerIndex;
    private final Integer serviceIndex;
    private final BookingDateTime bookingDateTime;
    private final Feedback feedback = new Feedback(EMPTY_FEEDBACK_TYPE);

    /**
     * Creates an AddBookingCommand to add the specified booking
     */
    public AddBookingCommand(Integer customerIndex, Integer serviceIndex, BookingDateTime bookingDateTime) {
        requireNonNull(customerIndex);
        requireNonNull(serviceIndex);
        requireNonNull(bookingDateTime);

        this.customerIndex = customerIndex;
        this.serviceIndex = serviceIndex;
        this.bookingDateTime = bookingDateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> customerList = model.getFilteredCustomerList();
        List<Service> serviceList = model.getFilteredServiceList();

        if (customerIndex >= customerList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        if (serviceIndex >= serviceList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX);
        }

        Customer customer = customerList.get(customerIndex);
        Service service = serviceList.get(serviceIndex);
        requireAllNonNull(customer, service);

        Booking booking = new Booking(customer, service, bookingDateTime, feedback);

        if (model.hasBooking(booking)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        model.addBooking(booking);
        return new CommandResult(String.format(MESSAGE_SUCCESS, booking), Panel.BOOKING_PANEL);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBookingCommand // instanceof handles nulls
                && customerIndex.equals(((AddBookingCommand) other).customerIndex)
                && serviceIndex.equals(((AddBookingCommand) other).serviceIndex)
                && bookingDateTime.equals(((AddBookingCommand) other).bookingDateTime));
    }
}

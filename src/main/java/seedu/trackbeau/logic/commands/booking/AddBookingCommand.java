package seedu.trackbeau.logic.commands.booking;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;

import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.booking.Booking;

/**
 * Adds a booking to trackBeau.
 */
public class AddBookingCommand extends Command {

    public static final String COMMAND_WORD = "addb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Booking to TrackBeau. "
            + "Parameters: "
            + PREFIX_NAME + "CUSTOMERNAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_SERVICE + "SERVICE "
            + PREFIX_STARTTIME + "APPOINTMENTTIME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_SERVICE + "Acne treatment "
            + PREFIX_STARTTIME + "10-10-2022 10:30";

    public static final String MESSAGE_SUCCESS = "New Booking added: \n%1$s";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking already exists in TrackBeau";

    private final Booking toAdd;

    /**
     * Creates an AddBookingCommand to add the specified booking
     */
    public AddBookingCommand(Booking booking) {
        requireNonNull(booking);
        toAdd = booking;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBooking(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        model.addBooking(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddBookingCommand // instanceof handles nulls
                && toAdd.equals(((AddBookingCommand) other).toAdd));
    }
}

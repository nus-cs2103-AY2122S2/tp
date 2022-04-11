package seedu.trackbeau.logic.commands.booking;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;

import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;


/**
 * Lists all bookings in trackBeau to the user.
 */
public class ListBookingsCommand extends Command {

    public static final String COMMAND_WORD = "listb";

    public static final String MESSAGE_SUCCESS = "Listed all bookings";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
        return new CommandResult(MESSAGE_SUCCESS, Panel.BOOKING_PANEL);
    }
}

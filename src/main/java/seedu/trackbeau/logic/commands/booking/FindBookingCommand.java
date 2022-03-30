package seedu.trackbeau.logic.commands.booking;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.booking.BookingSearchContainsKeywordsPredicate;

/**
 * Finds and lists all bookings in trackBeau whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindBookingCommand extends Command {

    public static final String COMMAND_WORD = "findb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all booking whose details match "
            + "Parameters: "
            + "[" + PREFIX_NAME + "CUSTOMERNAME/SERVICENAME] "
            + "[" + PREFIX_STARTTIME + "APPOINTMENTTIME] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "ALICE "
            + PREFIX_STARTTIME + "10-10-2022 10:30";

    private final BookingSearchContainsKeywordsPredicate predicate;

    public FindBookingCommand(BookingSearchContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookingList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKING_LISTED_OVERVIEW, model.getFilteredBookingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindBookingCommand // instanceof handles nulls
                && predicate.equals(((FindBookingCommand) other).predicate)); // state check
    }
}

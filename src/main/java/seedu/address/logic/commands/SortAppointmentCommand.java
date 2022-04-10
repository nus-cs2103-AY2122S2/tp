package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Sorts all appointments in appointment book in ascending or descending order in terms of appointment time
 */
public class SortAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String SORT_ASCENDING = "a";
    public static final String SORT_DESCENDING = "d";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all appointment in address book in ascending/descending order in terms of appointment time.\n"
            + "Example: " + COMMAND_WORD + " -a a "
            + "('a' for sort in ascending order, 'd' for sort in descending order)";
    private final String sortOption;

    public SortAppointmentCommand(String sortOption) {
        this.sortOption = sortOption;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Comparator<Appointment> comparator;
        String message = "";
        switch (sortOption) {
        case SORT_ASCENDING:
            comparator = Comparator.comparing(Appointment::getLocalDateTime);
            message = String.format(Messages.MESSAGE_APPOINTMENT_SORTED_ASCENDING,
                    model.getFilteredAppointmentList().size());
            break;
        case SORT_DESCENDING:
            comparator = (Appointment a1, Appointment a2) -> a2.getLocalDateTime().compareTo(a1.getLocalDateTime());
            message = String.format(Messages.MESSAGE_APPOINTMENT_SORTED_DESCENDING,
                    model.getFilteredAppointmentList().size());
            break;
        default:
            // A comparator that does nothing
            comparator = (Appointment a1, Appointment a2) -> 0;
        }
        model.sortAppointmentBook(comparator);
        return new CommandResult(message, false, false, true,
                false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortAppointmentCommand // instanceof handles nulls
                && sortOption.equals(((SortAppointmentCommand) other).sortOption)); // state check
    }
}

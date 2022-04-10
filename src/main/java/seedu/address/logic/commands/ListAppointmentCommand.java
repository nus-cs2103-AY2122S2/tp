package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Lists all appointments in the appointment book to the user.
 */
public class ListAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all unexpired appointments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Appointment> predicate = a -> a.getLocalDateTime().isAfter(LocalDateTime.now());
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

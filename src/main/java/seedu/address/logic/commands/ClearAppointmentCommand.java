package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;

/**
 * Clears the appointment list.
 */
public class ClearAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Appointment list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAppointmentBook(new AppointmentBook());
        model.getAppointmentHistoryBook().getAppointmentHistoryList().clear();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

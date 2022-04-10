package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Deletes an appointment identified using it's displayed index from the appointment book.
 */
public class DeleteAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Appointment deleted: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " -a "
            + ": Deletes an appointment from the appointment book. "
            + "Parameters: "
            + "APPOINTMENT INDEX"
            + "Example: " + COMMAND_WORD + " -a " + " 1";
    private final Index targetIndex;

    /**
     * Creates an DeleteAppointmentCommand to delete the specified {@code Appointment}
     */
    public DeleteAppointmentCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteAppointment(appointmentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, appointmentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAppointmentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteAppointmentCommand) other).targetIndex));
    }
}

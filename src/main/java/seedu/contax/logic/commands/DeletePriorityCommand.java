package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.appointment.Appointment;



/**
 * Deletes a priority identified using it's displayed index from the address book.
 */
public class DeletePriorityCommand extends Command {
    public static final String COMMAND_WORD = "deletepriority";
    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD
            + "` : **Deletes the priority identified by appointment index.**\n"
            + "Parameters: *INDEX (must be a positive integer)*\n"
            + "Example: `" + COMMAND_WORD + " 1`";

    public static final String MESSAGE_DELETE_PRIORITY_SUCCESS = "Deleted Priority of Appointment at Index : %d";

    private final Index targetIndex;

    public DeletePriorityCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Appointment> appointmentList = model.getFilteredAppointmentList();

        if (targetIndex.getZeroBased() >= appointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointment = appointmentList.get(targetIndex.getZeroBased());
        Appointment editedAppointment = appointment.setPriority(null);

        model.setAppointment(appointment, editedAppointment);

        return new CommandResult(String.format(MESSAGE_DELETE_PRIORITY_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof DeletePriorityCommand)) {
            return false;
        }

        return ((DeletePriorityCommand) o).targetIndex.equals(targetIndex);
    }
}

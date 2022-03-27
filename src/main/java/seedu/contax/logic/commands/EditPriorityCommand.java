package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Priority;


/**
 * Edits the level of an existing priority in the appointment list with level low, medium or high
 */
public class EditPriorityCommand extends Command {
    public static final String COMMAND_WORD = "priority";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the level of priority.\n"
            + "Parameters: INDEX pri/PRIORITY\n"
            + "Example: " + COMMAND_WORD + " 1 " + "pri/high";

    public static final String MESSAGE_EDIT_PRIORITY_SUCCESS = "Edited Priority of Appointment at index %d: %s";
    private final Index index;
    private final Priority priority;

    /**
     *
     * @param index The specified index to update the priority
     * @param priority Details to edit the priority with
     */
    public EditPriorityCommand(Index index, Priority priority) {
        requireNonNull(index);

        this.index = index;
        this.priority = priority;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Appointment> appointmentList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= appointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointment = appointmentList.get(index.getZeroBased());
        Appointment editedAppointment = appointment.withPriority(priority);

        String priorityFeedbackMessage = "None";

        if (priority != null) {
            priorityFeedbackMessage = priority.toString();
        }

        model.setAppointment(appointment, editedAppointment);
        return new CommandResult(
                String.format(MESSAGE_EDIT_PRIORITY_SUCCESS, index.getOneBased(), priorityFeedbackMessage));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof EditPriorityCommand)) {
            return false;
        }

        EditPriorityCommand e = ((EditPriorityCommand) o);

        Priority thisPriority = (priority != null) ? priority : Priority.NONE;
        Priority otherPriority = (e.priority != null) ? e.priority : Priority.NONE;

        return e.index.equals(index) && thisPriority.equals(otherPriority);
    }
}

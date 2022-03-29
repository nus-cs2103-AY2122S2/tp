package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Priority;


/**
 * Edits the priority level (low, medium or high) of an existing appointment in the appointment list.
 */
public class EditPriorityCommand extends Command {
    public static final String COMMAND_WORD = "prioritizeappt";

    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD
            + "`: **Edits the level of priority of an existing appointment in the appointment list.**\n"
            + "Parameters: *INDEX pri/PRIORITY*\n"
            + "Example: `" + COMMAND_WORD + " 1 " + "pri/high`";

    public static final String MESSAGE_EDIT_PRIORITY_SUCCESS = "Edited Priority of Appointment at index %d: %s";
    public static final String MESSAGE_REMOVE_PRIORITY_SUCCESS = "Removed Priority of Appointment at index %d";
    public static final String MESSAGE_PRIORITY_NOT_EDITED =
            "The priority level you provided is same as existing priority.";

    private final Index index;
    private final EditPriorityDescriptor editPriorityDescriptor;

    /**
     * Creates an {@code EditPriorityCommand} object.
     *
     * @param index The specified index to update the priority.
     * @param editPriorityDescriptor Details to edit the priority with.
     */
    public EditPriorityCommand(Index index, EditPriorityDescriptor editPriorityDescriptor) {
        requireNonNull(index);

        this.index = index;
        this.editPriorityDescriptor = editPriorityDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Appointment> appointmentList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= appointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointment = appointmentList.get(index.getZeroBased());

        if (editPriorityDescriptor.getPriority().isPresent()) {
            Priority priority = editPriorityDescriptor.getPriority().get();

            if (Objects.equals(appointment.getPriority(), priority)) {
                throw new CommandException(MESSAGE_PRIORITY_NOT_EDITED);
            }

            Appointment editedAppointment = appointment.withPriority(priority);
            model.setAppointment(appointment, editedAppointment);

            return new CommandResult(
                    String.format(MESSAGE_EDIT_PRIORITY_SUCCESS, index.getOneBased(), priority));
        } else {
            if (appointment.getPriority() == null) {
                throw new CommandException(MESSAGE_PRIORITY_NOT_EDITED);
            }

            Appointment editedAppointment = appointment.withPriority(null);
            model.setAppointment(appointment, editedAppointment);

            return new CommandResult(
                    String.format(MESSAGE_REMOVE_PRIORITY_SUCCESS, index.getOneBased()));
        }
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

        return e.index.equals(index) && editPriorityDescriptor.equals(e.editPriorityDescriptor);
    }

    /**
     * Stores the details to edit the priority.
     */
    public static class EditPriorityDescriptor {
        private Priority priority;

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof EditPriorityCommand.EditPriorityDescriptor)) {
                return false;
            }

            EditPriorityCommand.EditPriorityDescriptor e = (EditPriorityCommand.EditPriorityDescriptor) o;

            return e.getPriority().equals(getPriority());
        }
    }
}

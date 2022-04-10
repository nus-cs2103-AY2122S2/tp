package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_DESCRIPTION;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Description;

/**
 * Edits the details of an existing appointment in the address book.
 */
public class EditAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -a : Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_APPT_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_APPT_DATETIME + "DATETIME] \n"
            + "Example: " + COMMAND_WORD + " -a 1 "
            + PREFIX_APPT_DESCRIPTION + "Meet James at Bugis Junction "
            + PREFIX_APPT_DATETIME + "01-04-2022 15:00";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
            "An appointment at the same time already exists in the address book.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index                of the appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    /**
     * Creates and returns an {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                  EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        Description updatedDescription = editAppointmentDescriptor.getDescription()
                .orElse(appointmentToEdit.getDescription());
        DateTime updatedDateTime = editAppointmentDescriptor.getDateTime()
                .orElse(appointmentToEdit.getDateTime());
        return new Appointment(updatedDescription, updatedDateTime);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!appointmentToEdit.isSameAppointment(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        Predicate<Appointment> predicate = a -> a.getLocalDateTime().isAfter(LocalDateTime.now());
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        // state check
        EditAppointmentCommand e = (EditAppointmentCommand) other;
        return index.equals(e.index)
                && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private Description description;
        private DateTime dateTime;
        public EditAppointmentDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setDescription(toCopy.description);
            setDateTime(toCopy.dateTime);
        }
        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, dateTime);
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }
        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<DateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }
        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }
        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;
            return getDescription().equals(e.getDescription()) && getDateTime().equals(e.getDateTime());
        }
    }
}

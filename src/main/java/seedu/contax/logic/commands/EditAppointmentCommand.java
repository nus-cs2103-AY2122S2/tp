package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.commons.util.CollectionUtil;
import seedu.contax.commons.util.DateUtil;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.Name;
import seedu.contax.model.appointment.Priority;
import seedu.contax.model.appointment.StartDateTime;
import seedu.contax.model.appointment.exceptions.OverlappingAppointmentException;
import seedu.contax.model.person.Person;

/**
 * Edits the details of an existing appointment in the schedule.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "editappointment";

    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "`: **Edits the details of the appointment "
            + "identified by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.**\n"
            + "Parameters: *INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "[" + PREFIX_DURATION + "DURATION] "
            + "[" + PREFIX_PERSON + "PERSON]*\n"
            + "Example: `" + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Product Meeting with John "
            + PREFIX_DURATION + "60 "
            + PREFIX_PERSON + "2`";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "No fields supplied, nothing was changed.";
    public static final String MESSAGE_OVERLAPPING_APPOINTMENT = "The new appointment will overlap with"
            + " another appointment in the schedule!";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * Creates an {@code EditAppointmentCommand} object.
     *
     * @param index of the appointment in the list of appointments to edit.
     * @param editAppointmentDescriptor details to edit the appointment with.
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> appointmentsList = model.getFilteredAppointmentList();
        List<Person> personsList = model.getFilteredPersonList();

        if (index.getZeroBased() >= appointmentsList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            return new CommandResult(MESSAGE_NOT_EDITED);
        }

        boolean isPersonIndexInvalid = editAppointmentDescriptor.getPersonIndex()
                .map(index -> index.getZeroBased() >= personsList.size()).orElse(false);
        if (isPersonIndexInvalid) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = appointmentsList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit,
                model.getFilteredPersonList(), editAppointmentDescriptor);

        try {
            model.setAppointment(appointmentToEdit, editedAppointment);
        } catch (OverlappingAppointmentException ex) {
            throw new CommandException(MESSAGE_OVERLAPPING_APPOINTMENT);
        }

        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns an {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     * Relies on the execute method to check that editAppointmentDescriptor's person index is valid.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                  List<Person> personList,
                                                  EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        Name updatedName = editAppointmentDescriptor.getName().orElse(appointmentToEdit.getName());
        Duration updatedDuration = editAppointmentDescriptor.getDuration()
                .orElse(appointmentToEdit.getDuration());

        StartDateTime updatedStartDateTime = appointmentToEdit.getStartDateTimeObject();
        if (editAppointmentDescriptor.isDateTimeUpdated()) {
            // Final modifier is required for lambda expressions
            final LocalDateTime originalDateTime = appointmentToEdit.getStartDateTime();
            final LocalDateTime updatedDate = editAppointmentDescriptor.getStartDate()
                    .map(date -> DateUtil.updateDate(originalDateTime, date)).orElse(originalDateTime);
            LocalDateTime updatedDateTime = editAppointmentDescriptor.getStartTime()
                    .map(time -> DateUtil.updateTime(updatedDate, time)).orElse(updatedDate);
            updatedStartDateTime = new StartDateTime(updatedDateTime);
        }

        Person updatedPerson = appointmentToEdit.getPerson();
        if (editAppointmentDescriptor.isPersonModified()) {
            updatedPerson = editAppointmentDescriptor.getPersonIndex()
                    .map(index -> personList.get(index.getZeroBased())).orElse(null);
        }

        Priority originalPriority = appointmentToEdit.getPriority();

        return new Appointment(updatedName, updatedStartDateTime, updatedDuration, updatedPerson, originalPriority);
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
        private Name name;
        private LocalDate startDate;
        private LocalTime startTime;
        private Duration duration;
        private Index personIndex;
        private boolean isPersonModified;

        public EditAppointmentDescriptor() {
            this.isPersonModified = false;
        }

        /**
         * Copy constructor.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setName(toCopy.name);
            setStartDate(toCopy.startDate);
            setStartTime(toCopy.startTime);
            setDuration(toCopy.duration);

            // Does not use setPersonIndex to prevent incorrectly setting the isPersonModified flag
            this.personIndex = toCopy.personIndex;
            this.isPersonModified = toCopy.isPersonModified;
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, startDate, startTime, duration) || isPersonModified;
        }

        /**
         * Returns true if either {@code startDate} or {@code startTime} is to be modified.
         *
         * @return true if either startDate or startTime is to be modified;
         */
        public boolean isDateTimeUpdated() {
            return startDate != null || startTime != null;
        }

        /**
         * Returns true if {@code setPersonIndex} has been called after this object was created.
         *
         * @return true is setPersonIndex has been called.
         */
        public boolean isPersonModified() {
            return isPersonModified;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public Optional<LocalDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public Optional<LocalTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }

        public void setPersonIndex(Index person) {
            this.personIndex = person;
            this.isPersonModified = true;
        }

        public Optional<Index> getPersonIndex() {
            return Optional.ofNullable(personIndex);
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

            return getName().equals(e.getName())
                    && getStartDate().equals(e.getStartDate())
                    && getStartTime().equals(e.getStartTime())
                    && getDuration().equals(e.getDuration())
                    && getPersonIndex().equals(e.getPersonIndex())
                    && (isPersonModified == e.isPersonModified);
        }
    }
}

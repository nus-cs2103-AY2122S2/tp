package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * The application should list person.
     */
    private final boolean person;

    /**
     * The application should list insurance.
     */
    private final boolean insurance;

    /**
     * The application should list appointment.
     */
    private final boolean appointment;


    /**
     * The application should list record.
     */
    private final boolean record;

    /**
     * The application should list appointment history.
     */
    private final boolean appointmentHistory;

    /**
     * The application should list expired records.
     */
    private final boolean expiredRecord;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean person, boolean insurance, boolean appointment, boolean record,
                         boolean appointmentHistory, boolean expiredRecord, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.person = person;
        this.insurance = insurance;
        this.appointment = appointment;
        this.record = record;
        this.appointmentHistory = appointmentHistory;
        this.expiredRecord = expiredRecord;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false,
                false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isListPerson() {
        return person;
    }

    public boolean isListInsurance() {
        return insurance;
    }

    public boolean isListAppointment() {
        return appointment;
    }

    public boolean isListRecord() {
        return record;
    }

    public boolean isListAppointmentHistory() {
        return appointmentHistory;
    }

    public boolean isListExpiredRecord() {
        return expiredRecord;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && (person == otherCommandResult.person
                || insurance == otherCommandResult.insurance
                || appointment == otherCommandResult.appointment
                || record == otherCommandResult.record
                || appointmentHistory == otherCommandResult.appointmentHistory
                || expiredRecord == otherCommandResult.expiredRecord)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, person, insurance, appointment,
                record, appointmentHistory, expiredRecord, showHelp, exit);
    }

}

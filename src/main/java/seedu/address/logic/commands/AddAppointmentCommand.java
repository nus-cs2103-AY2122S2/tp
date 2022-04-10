package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_DESCRIPTION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Adds an appointment to Mr. Agent.
 */
public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " -a "
            + ": Adds an appointment to the appointment book. "
            + "Parameters: "
            + PREFIX_APPT_DESCRIPTION + "DESCRIPTION "
            + PREFIX_APPT_DATETIME + "DATETIME \n"
            + "Example: " + COMMAND_WORD + " -a "
            + PREFIX_APPT_DESCRIPTION + "Meet James at UTown "
            + PREFIX_APPT_DATETIME + "20-03-2022 18:00 ";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
            "A appointment at the same time already exists in the appointment book";

    private final Appointment toAdd;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Appointment}
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && toAdd.equals(((AddAppointmentCommand) other).toAdd));
    }
}

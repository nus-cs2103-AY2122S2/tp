package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;
import java.util.Objects;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.person.Person;

/**
 * Adds an appointment to the schedule.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "addappt";

    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "`: **Adds an appointment to the schedule.** "
            + "\nParameters: *"
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_DURATION + "DURATION "
            + "[" + PREFIX_PERSON + "PERSON] *\n"
            + "Example: `" + COMMAND_WORD + " "
            + PREFIX_NAME + "Contract Signing With Charlie "
            + PREFIX_DATE + "22-10-2022 "
            + PREFIX_TIME + "16:30 "
            + PREFIX_DURATION + "300 "
            + PREFIX_PERSON + "1`";

    public static final String MESSAGE_SUCCESS = "New appointment added:\n %1$s";

    private final Appointment toAdd;
    private final Index personIndex;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Appointment}.
     */
    public AddAppointmentCommand(Appointment appointment, Index personIndex) {
        requireNonNull(appointment);

        this.toAdd = appointment;
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownPersonList = model.getFilteredPersonList();
        Appointment appointmentWithPerson = toAdd;

        if (personIndex != null) {
            if (personIndex.getZeroBased() >= lastShownPersonList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person person = lastShownPersonList.get(personIndex.getZeroBased());
            appointmentWithPerson = toAdd.withPerson(person);
        }

        if (model.hasOverlappingAppointment(appointmentWithPerson)) {
            throw new CommandException(Messages.MESSAGE_APPOINTMENTS_OVERLAPPING);
        }

        model.addAppointment(appointmentWithPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointmentWithPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && toAdd.equals(((AddAppointmentCommand) other).toAdd)
                && Objects.equals(personIndex, ((AddAppointmentCommand) other).personIndex));
    }
}

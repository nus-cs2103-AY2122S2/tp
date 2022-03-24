package seedu.contax.testutil;

import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.format.DateTimeFormatter;

import seedu.contax.logic.commands.AddAppointmentCommand;
import seedu.contax.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.contax.model.AddressBook;
import seedu.contax.model.appointment.Appointment;

/**
 * A utility class for Appointment.
 */
public class AppointmentUtil {

    /**
     * Returns an add command string for adding the {@code appointment}.
     */
    public static String getAddCommand(Appointment appointment, AddressBook addressBook) {
        return AddAppointmentCommand.COMMAND_WORD + " " + getAppointmentDetails(appointment, addressBook);
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment, AddressBook addressBook) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        StringBuilder sb = new StringBuilder();

        sb.append(PREFIX_NAME + appointment.getName().name + " ");
        sb.append(PREFIX_DATE + appointment.getStartDateTime().format(dateFormatter) + " ");
        sb.append(PREFIX_TIME + appointment.getStartDateTime().format(timeFormatter) + " ");
        sb.append(PREFIX_DURATION + String.valueOf(appointment.getDuration().value) + " ");
        if (appointment.getPerson() != null) {
            sb.append(PREFIX_PERSON
                    + String.valueOf(addressBook.getPersonList().indexOf(appointment.getPerson())) + " ");
        }

        return sb.toString();
    }

    /**
     * Returns the string parameter inputs for a given {@code EditAppointmentDescriptor}.
     */
    public static String getAppointmentEditDescriptorDetails(EditAppointmentDescriptor descriptor) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        StringBuilder sb = new StringBuilder();

        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME + name.name + " "));
        descriptor.getStartDate()
                .ifPresent(date -> sb.append(PREFIX_DATE + date.format(dateFormatter) + " "));
        descriptor.getStartTime()
                .ifPresent(time -> sb.append(PREFIX_TIME + time.format(timeFormatter) + " "));
        descriptor.getDuration()
                .ifPresent(duration -> sb.append(PREFIX_DURATION + String.valueOf(duration.value) + " "));
        if (descriptor.isPersonModified()) {
            descriptor.getPersonIndex()
                    .ifPresent(index -> sb.append(PREFIX_PERSON + String.valueOf(index.getOneBased()) + " "));
        }

        return sb.toString();
    }
}

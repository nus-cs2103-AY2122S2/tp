package seedu.address.logic.commands.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.contact.AddContactCommand.MESSAGE_MISSING_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.ConsultationWithPredicates;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;

/**
 * Lists all consultations in the address book to the user.
 */
public class ViewConsultationCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final CommandType COMMAND_TYPE = CommandType.CONSULTATION;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_TYPE + "consultation "
            + ": Shows consultations that "
            + "matches the specified owner NRIC, and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_TYPE + "TYPE "
            + PREFIX_NRIC + "NRIC \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TYPE + "consultation "
            + PREFIX_NRIC + "S1234567L";

    private final Nric patientNric;

    /**
     * Creates an ViewConsultationCommand to view the specified {@code Patient}
     */
    public ViewConsultationCommand(Nric patientNric) {
        requireNonNull(patientNric);
        this.patientNric = patientNric;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredConsultationList(new ConsultationWithPredicates(patientNric));

        if (!model.hasNric(patientNric)) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        ObservableList<Patient> patientList = model.getPatientList();
        String nameAndNric = "";

        for (Patient patient : patientList) {
            if (patient.getNric().equals(patientNric)) {
                nameAndNric = patient.getName().toString() + ", " + patientNric;
            }
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_CONSULTATION_LISTED_OVERVIEW,
                        model.getFilteredConsultationList().size(), nameAndNric),
                COMMAND_TYPE);
    }
}


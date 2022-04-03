package seedu.address.logic.commands.prescription;

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
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.PrescriptionWithNricPredicate;

public class ViewPrescriptionCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final CommandType COMMAND_TYPE = CommandType.PRESCRIPTION;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_TYPE + "prescription "
            + ": Lists all prescription who is prescribed to"
            + "the specified owner NRIC and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_TYPE + "prescription "
            + PREFIX_NRIC + "NRIC\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_TYPE + "prescription "
            + PREFIX_NRIC + "S1234567L";

    private final Nric nric;

    /**
     * Creates an ViewContactCommand to view the specified {@code Patient}
     */
    public ViewPrescriptionCommand(Nric nric) {
        requireNonNull(nric);
        this.nric = nric;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPrescriptionList(new PrescriptionWithNricPredicate(nric));

        if (!model.hasNric(nric)) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        ObservableList<Patient> patientList = model.getPatientList();
        String nameAndNric = "";

        for (Patient patient : patientList) {
            if (patient.getNric().equals(nric)) {
                nameAndNric = patient.getName().toString() + ", " + nric;
            }
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_PRESCRIPTIONS_LISTED_OVERVIEW,
                        model.getFilteredPrescriptionList().size(), nameAndNric),
                COMMAND_TYPE);
    }
}

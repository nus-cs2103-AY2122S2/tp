package seedu.address.logic.commands.medical;

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
import seedu.address.model.medical.MedicalWithNricPredicate;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;

public class ViewMedicalCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final CommandType COMMAND_TYPE = CommandType.MEDICAL;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_TYPE + "medical"
            + ": Lists all medical information whose owner's name contain any of "
            + "the specified owner NRIC and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_TYPE + "medical "
            + PREFIX_NRIC + "NRIC \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TYPE + "medical "
            + PREFIX_NRIC + "S1234567G";

    private final Nric nric;

    /**
     * Creates an ViewMedicalCommand to view the medical information of specified {@code Patient}
     */
    public ViewMedicalCommand(Nric nric) {
        this.nric = nric;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPatientList(Model.PREDICATE_SHOW_ALL_PATIENTS);


        if (nric == null) { // No nric specified, display all medical information
            model.updateFilteredMedicalList(Model.PREDICATE_SHOW_ALL_MEDICALS);
            return new CommandResult(
                    String.format(Messages.MESSAGE_MEDICALS_LISTED_NO_NRIC,
                            model.getFilteredMedicalList().size()),
                    COMMAND_TYPE);
        } else { // Nric specified, find and display medical details for patient with specifed nric
            model.updateFilteredMedicalList(new MedicalWithNricPredicate(nric));
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
                    String.format(Messages.MESSAGE_MEDICALS_LISTED_OVERVIEW,
                            model.getFilteredMedicalList().size(), nameAndNric),
                    COMMAND_TYPE);
        }
    }
}

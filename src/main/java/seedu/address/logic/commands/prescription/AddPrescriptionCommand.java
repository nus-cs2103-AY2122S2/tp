package seedu.address.logic.commands.prescription;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.NricPredicate;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionWithNricPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddPrescriptionCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final CommandType COMMAND_TYPE = CommandType.PRESCRIPTION;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a medical prescription of a patient in Medbook. "
            + "Parameters: "
            + PREFIX_TYPE + "PRESCRIPTION_TYPE "
            + PREFIX_NRIC + "OWNER_NRIC "
            + PREFIX_NAME + "DRUG_NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_INSTRUCTION + "INSTRUCTION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TYPE + "prescription "
            + PREFIX_NRIC + "S12345678P "
            + PREFIX_NAME + "Acetaminophen "
            + PREFIX_DATE + "2022-02-22 "
            + PREFIX_INSTRUCTION + "2 tablets after meal everyday ";

    public static final String MESSAGE_SUCCESS = "New medical prescription added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This medical prescription already exist";
    public static final String MESSAGE_MISSING_PATIENT = "This patient does not exists in Medbook";

    private final Nric nric;

    private final Prescription toAdd;

    public AddPrescriptionCommand(Nric nric, Prescription prescription) {
        requireNonNull(nric);
        requireNonNull(prescription);
        toAdd = prescription;
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(new NricPredicate(nric))) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        if (model.hasPrescription(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        model.addPrescription(toAdd);
        model.updateFilteredPrescriptionList(new PrescriptionWithNricPredicate(nric));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }
}

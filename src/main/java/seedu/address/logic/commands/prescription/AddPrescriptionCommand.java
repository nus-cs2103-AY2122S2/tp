package seedu.address.logic.commands.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Nric;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionWithNricPredicate;

public class AddPrescriptionCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final CommandType COMMAND_TYPE = CommandType.PRESCRIPTION;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_TYPE + "prescription "
            + ": Adds a medical prescription of a patient in Medbook. \n"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_NAME + "DRUG_NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_INSTRUCTION + "INSTRUCTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TYPE + "prescription "
            + PREFIX_NRIC + "S1234567L "
            + PREFIX_NAME + "Acetaminophen "
            + PREFIX_DATE + "2022-02-22 "
            + PREFIX_INSTRUCTION + "2 tablets after meal everyday ";

    public static final String MESSAGE_SUCCESS = "New medical prescription added: %1$s";
    public static final String MESSAGE_DUPLICATE_PRESCRIPTION =
            "This medical prescription already exist in this patient's prescription list";
    public static final String MESSAGE_MISSING_PATIENT = "This patient does not exists in MedBook";

    private final Nric nric;

    private final Prescription toAdd;

    /**
     * Creates an AddPrescriptionCommand to add the specified {@code Patient}
     */
    public AddPrescriptionCommand(Nric nric, Prescription prescription) {
        requireNonNull(nric);
        requireNonNull(prescription);
        toAdd = prescription;
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasNric(nric)) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        if (model.hasPrescription(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRESCRIPTION);
        }

        model.addPrescription(toAdd);
        model.updateFilteredPrescriptionList(new PrescriptionWithNricPredicate(nric));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }
}

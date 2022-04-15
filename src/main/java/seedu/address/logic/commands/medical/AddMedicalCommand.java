package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ETHNICITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY_HISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ILLNESSES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMMUNIZATION_HISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURGERIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medical.Medical;
import seedu.address.model.medical.MedicalWithNricPredicate;
import seedu.address.model.patient.Nric;

public class AddMedicalCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final CommandType COMMAND_TYPE = CommandType.MEDICAL;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_TYPE + "medical "
            + ": Adds medical information of a patient to MedBook. \n"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_AGE + "AGE "
            + PREFIX_BLOODTYPE + "BLOODTYPE "
            + PREFIX_MEDICATION + "MEDICATION "
            + PREFIX_HEIGHT + "HEIGHT "
            + PREFIX_WEIGHT + "WEIGHT "
            + PREFIX_ILLNESSES + "ILLNESSES "
            + PREFIX_SURGERIES + "SURGERIES "
            + PREFIX_FAMILY_HISTORY + "FAMILY_HISTORY "
            + PREFIX_IMMUNIZATION_HISTORY + "IMMUNIZATION_HISTORY "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_ETHNICITY + "ETHNICITY \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TYPE + "medical "
            + PREFIX_NRIC + "S1234567L "
            + PREFIX_AGE + "22 "
            + PREFIX_BLOODTYPE + "O "
            + PREFIX_MEDICATION + "Paracetamol 500mg twice a day; Atarvastatin 20mg once a day "
            + PREFIX_HEIGHT + "185 cm "
            + PREFIX_WEIGHT + "70 kg "
            + PREFIX_ILLNESSES + "Mild fever; High cholesterol "
            + PREFIX_SURGERIES + "Appendectomy "
            + PREFIX_FAMILY_HISTORY + "Has family history of high blood pressure "
            + PREFIX_IMMUNIZATION_HISTORY + "MMR; 6 in 1; Hepatitis B "
            + PREFIX_GENDER + "Male "
            + PREFIX_ETHNICITY + "Chinese ";

    public static final String MESSAGE_SUCCESS = "New medical information added: %1$s";
    public static final String MESSAGE_MISSING_PATIENT = "This patient does not exist in MedBook";
    public static final String MESSAGE_DUPLICATE_MEDICAL = "Medical information for this patient already exists.";

    // Identifier
    private final Nric patientNric;

    private final Medical toAdd;

    /**
     * Creates an AddMedicalCommand to add medical information to the specified {@code Patient}
     */
    public AddMedicalCommand(Nric patientNric, Medical medical) {
        requireNonNull(patientNric);
        requireNonNull(medical);
        toAdd = medical;
        this.patientNric = patientNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasNric(patientNric)) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        if (model.hasMedical(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEDICAL);
        }

        model.addMedical(toAdd);
        model.updateFilteredMedicalList(new MedicalWithNricPredicate(patientNric));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }
}

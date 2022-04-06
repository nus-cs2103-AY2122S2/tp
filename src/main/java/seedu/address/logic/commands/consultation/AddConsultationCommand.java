package seedu.address.logic.commands.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationWithPredicates;
import seedu.address.model.patient.Nric;

public class AddConsultationCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final CommandType COMMAND_TYPE = CommandType.CONSULTATION;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_TYPE + "consultation "
            + ": Adds a consultation of a patient in MedBook. \n"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_DIAGNOSIS + "DIAGNOSIS "
            + PREFIX_FEE + "FEE ["
            + PREFIX_NOTES + "NOTES] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TYPE + "consultation "
            + PREFIX_NRIC + "S1234567L "
            + PREFIX_DATE + "2020-09-19 "
            + PREFIX_TIME + "19-00 "
            + PREFIX_DIAGNOSIS + "Upper respiratory infection. Patient was having cough, sneezing fits. "
            + PREFIX_FEE + "54.00 ";

    public static final String MESSAGE_SUCCESS = "New consultation added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_CONSULTATION =
            "This consultation already exists in this patient's consultation list";
    public static final String MESSAGE_MISSING_PATIENT = "This patient does not exists in MedBook";

    // Identifier
    private final Nric patientNric;

    private final Consultation toAdd;

    /**
     * Creates an AddConsultationCommand to add the specified {@code Consultation}
     */
    public AddConsultationCommand(Nric patientNric, Consultation consultation) {
        requireNonNull(patientNric);
        requireNonNull(consultation);
        toAdd = consultation;
        this.patientNric = patientNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasNric(patientNric)) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        if (model.hasConsultation(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        model.addConsultation(toAdd);
        model.updateFilteredConsultationList(new ConsultationWithPredicates(patientNric));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }
}


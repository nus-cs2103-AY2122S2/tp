package seedu.address.logic.commands.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.*;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationWithPredicates;
import seedu.address.model.person.Nric;
import seedu.address.model.patient.NricPredicate;

public class AddConsultationCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final CommandType COMMAND_TYPE = CommandType.CONTACT;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a consultation of patient in Medbook. "
            + "Parameters: "
            + PREFIX_TYPE + "consultation "
            + PREFIX_NRIC + "OWNER_NRIC "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_NOTES + "NOTES "
            + PREFIX_PRESCRIPTION + "PRESCRIPTION "
            + PREFIX_TESTS_TAKEN_AND_RESULTS + "TESTS TAKEN AND RESULTS ";

    public static final String MESSAGE_SUCCESS = "New consultation added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "This consultation already exists in patient consultation list";
    public static final String MESSAGE_MISSING_PATIENT = "This patient does not exists in Medbook";

    // Identifier
    private final Nric ownerNric;

    private final Consultation toAdd;

    /**
     * Creates an AddContactCommand to add the specified {@code Patient}
     */
    public AddConsultationCommand(Nric ownerNric, Consultation consultation) {
        requireNonNull(ownerNric);
        requireNonNull(consultation);
        toAdd = consultation;
        this.ownerNric = ownerNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(new NricPredicate(ownerNric))) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        if (model.hasConsultation(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        model.addConsultation(toAdd);
        model.updateFilteredConsultationList(new ConsultationWithPredicates(ownerNric));

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), COMMAND_TYPE);
    }
}


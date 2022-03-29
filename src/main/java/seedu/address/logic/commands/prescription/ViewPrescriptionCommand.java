package seedu.address.logic.commands.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.contact.AddContactCommand.MESSAGE_MISSING_PATIENT;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.NricPredicate;
import seedu.address.model.prescription.PrescriptionWithNricPredicate;

public class ViewPrescriptionCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final CommandType COMMAND_TYPE = CommandType.PRESCRIPTION;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all cotnact whose names contain any of "
            + "the specified owner NRIC and displays them as a list with index numbers.\n"
            + "Parameters: OWNER NRIC\n"
            + "Example: " + COMMAND_WORD + " S1234567L";

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

        if (!model.hasPatient(new NricPredicate(nric))) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_PRESCRIPTIONS_LISTED_OVERVIEW,
                        model.getFilteredPrescriptionList().size(), nric),
                COMMAND_TYPE);
    }
}

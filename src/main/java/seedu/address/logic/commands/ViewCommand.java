package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.commons.core.Messages;
import static seedu.address.commons.core.Messages.MESSAGE_SUMMARY_SHOWN;
import static seedu.address.logic.commands.contact.AddContactCommand.MESSAGE_MISSING_PATIENT;
import seedu.address.logic.commands.exceptions.CommandException;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.consultation.ConsultationWithPredicates;
import seedu.address.model.contact.ContactWithNricPredicate;
import seedu.address.model.medical.MedicalWithNricPredicate;
import seedu.address.model.patient.Nric;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final CommandType SUMMARY_COMMAND_TYPE = CommandType.SUMMARY;

    public static final String MESSAGE_SUCCESS = "Listed all patients";

    private final Nric nric;

    /**
     * Creates an ViewCommand with NRIC to view the summary of specified {@code Patient}
     */
    public ViewCommand(Nric nric) {
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (nric == null) { // No nric specified, display all patients
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            CommandType.setViewCommandType(CommandType.DEFAULT);
            return new CommandResult(MESSAGE_SUCCESS);
        } else { // Nric specified, display summary of patient with given NRIC

            model.updateSummary(nric);
            return new CommandResult(String.format(Messages.MESSAGE_SUMMARY_SHOWN, nric),
                    SUMMARY_COMMAND_TYPE);
        }
    }
}

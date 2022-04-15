package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.contact.AddContactCommand.MESSAGE_MISSING_PATIENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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

        if (nric != null && !model.hasNric(nric)) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        if (nric == null) { // No nric specified, display all patients
            model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
            CommandManager.setCurrentViewType(CommandType.DEFAULT);
            ViewedNric.setViewedNric(null);
            return new CommandResult(MESSAGE_SUCCESS);
        } else { // Nric specified, display summary of patient with given NRIC
            ViewedNric.setViewedNric(nric);
            model.updateSummary(nric);
            return new CommandResult(String.format(Messages.MESSAGE_SUMMARY_SHOWN, nric),
                    SUMMARY_COMMAND_TYPE);
        }
    }
}

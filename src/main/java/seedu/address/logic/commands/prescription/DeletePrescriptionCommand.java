package seedu.address.logic.commands.prescription;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandManager;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.prescription.Prescription;



public class DeletePrescriptionCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final CommandType COMMAND_TYPE = CommandType.PRESCRIPTION;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the prescription identified by the index number used in the displayed prescription list.\n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PRESCRIPTION_SUCCESS = "Deleted Prescription: %1$s";

    private final Index targetIndex;

    public DeletePrescriptionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Prescription> lastShownList = model.getFilteredPrescriptionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX);
        }

        Prescription prescriptionToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePrescription(prescriptionToDelete);
        if (lastShownList.isEmpty()) {
            CommandManager.setCurrentViewType(CommandType.DEFAULT);
            model.updateFilteredPatientList(Model.PREDICATE_SHOW_ALL_PATIENTS);
            return new CommandResult(String.format(MESSAGE_DELETE_PRESCRIPTION_SUCCESS, prescriptionToDelete),
                    CommandType.DEFAULT);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PRESCRIPTION_SUCCESS, prescriptionToDelete),
                COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePrescriptionCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePrescriptionCommand) other).targetIndex)); // state check
    }
}

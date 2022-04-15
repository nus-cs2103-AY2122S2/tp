package seedu.address.logic.commands.medical;

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
import seedu.address.model.medical.Medical;

/**
 * Deletes medical information identified using its displayed index from MedBook.
 */
public class DeleteMedicalCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final CommandType COMMAND_TYPE = CommandType.MEDICAL;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the medical information identified by the "
            + "index number used in the displayed medical information list.\n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_MEDICAL_SUCCESS = "Deleted Medical Information: %1$s";

    private final Index targetIndex;

    public DeleteMedicalCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Medical> lastShownList = model.getFilteredMedicalList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEDICAL_INFORMATION_INDEX);
        }

        Medical medicalToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteMedical(medicalToDelete);
        if (lastShownList.isEmpty()) {
            CommandManager.setCurrentViewType(CommandType.DEFAULT);
            model.updateFilteredPatientList(Model.PREDICATE_SHOW_ALL_PATIENTS);
            return new CommandResult(String.format(MESSAGE_DELETE_MEDICAL_SUCCESS, medicalToDelete),
                    CommandType.DEFAULT);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_MEDICAL_SUCCESS, medicalToDelete), COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMedicalCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteMedicalCommand) other).targetIndex)); // state check
    }
}

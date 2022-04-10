package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.insurance.Insurance;

/**
 * Deletes an insurance identified using it's displayed index from the insurance book.
 */
public class DeleteInsuranceCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " -i : Deletes the insurance identified by the index number used in the displayed insurance list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_INSURANCE + " 1";

    public static final String MESSAGE_DELETE_INSURANCE_SUCCESS = "Deleted Insurance: %1$s";

    private final Index targetIndex;

    public DeleteInsuranceCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Insurance> lastShownList = model.getFilteredInsuranceList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INSURANCE_DISPLAYED_INDEX);
        }

        Insurance insuranceToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInsurance(insuranceToDelete);

        System.out.println(model.getInsuranceBook().toString());
        return new CommandResult(String.format(MESSAGE_DELETE_INSURANCE_SUCCESS, insuranceToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInsuranceCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteInsuranceCommand) other).targetIndex)); // state check
    }
}

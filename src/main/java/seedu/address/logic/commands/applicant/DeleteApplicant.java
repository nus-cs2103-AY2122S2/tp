package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

/**
 * Constructs a delete applicant command.
 */
public class DeleteApplicant extends Command {
    public static final String COMMAND_WORD = "deleteapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the applicant identified by the index number used in the displayed applicant list.\n"
            + "Parameters: *[INDEX] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPLICANT_SUCCESS = "Deleted Applicant: %1$s";

    private final Index targetIndex;

    public DeleteApplicant(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Execute the detele applicant command.
     * @param model {@code Model} which the command should operate on.
     * @return the command result.
     * @throws CommandException thrown when the targetIndex is invalid.
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        int zeroBasedIndex = targetIndex.getZeroBased();

        if (zeroBasedIndex >= lastShownList.size() || zeroBasedIndex < 0) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX,
                    DeleteApplicant.MESSAGE_USAGE));
        }

        Applicant applicantToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteApplicant(applicantToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete), true,
                false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteApplicant // instanceof handles nulls
                && targetIndex.equals(((DeleteApplicant) other).targetIndex)); // state check
    }
}

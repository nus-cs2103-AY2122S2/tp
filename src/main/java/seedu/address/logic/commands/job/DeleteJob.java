package seedu.address.logic.commands.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.Job;

public class DeleteJob extends Command {
    public static final String COMMAND_WORD = "deletejob";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the job identified by the index number used in the displayed job list.\n"
            + "Parameters: *[INDEX] (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_JOB_SUCCESS = "Deleted job: %1$s";

    private final Index targetIndex;

    public DeleteJob(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Execute the detele job command.
     * @param model {@code Model} which the command should operate on.
     * @return the command result.
     * @throws CommandException thrown when the targetIndex is invalid.
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();
        int zeroBasedIndex = targetIndex.getZeroBased();
        if (zeroBasedIndex >= lastShownList.size() || zeroBasedIndex < 0) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX,
                    DeleteJob.MESSAGE_USAGE));
        }

        Job jobToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteJob(jobToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_JOB_SUCCESS, jobToDelete), false, true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteJob // instanceof handles nulls
                && targetIndex.equals(((DeleteJob) other).targetIndex)); // state check
    }
}

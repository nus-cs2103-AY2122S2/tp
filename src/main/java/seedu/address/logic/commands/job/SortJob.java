//@author e0543517
package seedu.address.logic.commands.job;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.Job;

public class SortJob extends Command {
    public static final String COMMAND_WORD = "sortjob";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the job list by the job status.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SORT_JOB_SUCCESS = "Job list has been sorted by Job Status";

    /**
     * Execute the sort job command.
     * @param model {@code Model} which the command should operate on.
     * @return the command result.
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();
        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_EMPTY_JOB_LIST);
        }
        model.sortJob();
        return new CommandResult(String.format(MESSAGE_SORT_JOB_SUCCESS));
    }
}


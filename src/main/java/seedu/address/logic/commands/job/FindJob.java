package seedu.address.logic.commands.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.Job;

/**
 * Finds and lists all jobs in address book whose name contains any of the argument keywords or matches give id.
 * Keyword matching is case insensitive.
 */
public class FindJob extends Command {

    public static final String COMMAND_WORD = "findjob";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all job whose job title contains "
            + "the specified keywords (case-insensitive) or finds a job matching the id given. "
            + "findjob command will displays them as a list with index numbers.\n"
            + "Parameters (search via jobtitle keyword): " + PREFIX_JOBTITLE + "SEARCH_TYPE KEYWORD [KEYWORD]\n"
            + "Parameters (search via id): " + PREFIX_JOBID + "JOB ID\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_JOBTITLE + "designer\n"
            + COMMAND_WORD + " " + PREFIX_JOBID + "1234";


    private final Predicate<Job> predicate;

    /**
     * Constructs FindJob
     */
    public FindJob (Predicate<Job> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredJobList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_JOBS_LISTED_OVERVIEW, model.getFilteredJobList().size()), false, true,
                false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindJob // instanceof handles nulls
                && predicate.equals(((FindJob) other).predicate)); // state check
    }
}

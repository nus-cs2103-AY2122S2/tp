package seedu.address.model.job;

import java.util.function.Predicate;

/**
 * Predicate to filter out jobs in the FilteredJobList with the same ID number. Checking of argument to be
 * int is done in the parser.
 */
public class JobIdSamePredicate implements Predicate<Job> {
    private final Integer idToCheck;

    public JobIdSamePredicate(String id) {
        idToCheck = Integer.parseInt(id);
    }

    @Override
    public boolean test(Job job) {
        return idToCheck.equals(job.getJobId().jobId);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobIdSamePredicate // instanceof handles nulls
                && idToCheck.equals(((JobIdSamePredicate) other).idToCheck)); // state check
    }
}

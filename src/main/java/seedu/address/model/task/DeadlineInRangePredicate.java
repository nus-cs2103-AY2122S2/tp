package seedu.address.model.task;

import static seedu.address.model.task.Deadline.MAX_DEADLINE;
import static seedu.address.model.task.Deadline.MIN_DEADLINE;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Deadline} is within the range given.
 */
public class DeadlineInRangePredicate implements Predicate<Task> {

    public static final String INVALID_RANGE_MESSAGE = "The start date cannot be later than the end date!";

    private final Optional<Deadline> startDate;
    private final Optional<Deadline> endDate;

    /**
     * Constructor for DeadlineInRangePredicate.
     *
     * @param startDate The lower bound of the range of deadline (inclusive).
     *                  If the given value is null, it means that there is no lower bound.
     * @param endDate the upper bound of the range of deadline (inclusive)
     *                If the given value is null, it means that there is no upper bound.
     */
    public DeadlineInRangePredicate(Deadline startDate, Deadline endDate) {
        this.startDate = Optional.ofNullable(startDate);
        this.endDate = Optional.ofNullable(endDate);
    }

    /**
     * Checks whether a range of deadline is valid.
     *
     * @param startDate the lower bound of deadline
     * @param endDate the upper bound of deadline
     * @return true if {@code startDate} is not later than {@code endDate}
     */
    public static boolean isValidRange(Deadline startDate, Deadline endDate) {
        if (startDate == null || endDate == null) {
            // If one of the deadline is missing, then it is always valid.
            return true;
        }
        return startDate.compareTo(endDate) <= 0;
    }

    @Override
    public boolean test(Task task) {
        Deadline deadline = task.getDeadline();
        return deadline.compareTo(startDate.orElse(MIN_DEADLINE)) >= 0
                && deadline.compareTo(endDate.orElse(MAX_DEADLINE)) <= 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineInRangePredicate // instanceof handles nulls
                && startDate.equals(((DeadlineInRangePredicate) other).startDate)
                && endDate.equals(((DeadlineInRangePredicate) other).endDate)); // state check
    }
}

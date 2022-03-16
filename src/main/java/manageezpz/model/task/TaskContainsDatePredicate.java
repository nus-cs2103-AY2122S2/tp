package manageezpz.model.task;

import java.util.function.Predicate;

/**
 * A predicate to filter tasks with the given date.
 */
public class TaskContainsDatePredicate implements Predicate<Task> {
    private final Date date;

    /**
     * Constructor for TaskContainsDatePredicate.
     * @param date The date as the search term
     */
    public TaskContainsDatePredicate(Date date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Task task) {
        if (task instanceof Deadline || task instanceof Event) {
            Date taskDate = getDateFromTask(task);
            return taskDate.equals(date);
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return obj == this // short circuit if same object
                || (obj instanceof TaskContainsDatePredicate // instanceof handles nulls
                && date.equals(((TaskContainsDatePredicate) obj).date)); // state check
    }

    /**
     * Gets the date from either Deadline or Event.
     * @param task Either Deadline or Event
     * @return The Date of the task
     */
    private Date getDateFromTask(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getDate();
        } else if (task instanceof Event) {
            return ((Event) task).getDate();
        } else {
            return null;
        }
    }
}

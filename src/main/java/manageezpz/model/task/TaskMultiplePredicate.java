package manageezpz.model.task;

import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;

import java.util.List;
import java.util.function.Predicate;

import manageezpz.commons.util.StringUtil;
import manageezpz.logic.parser.Prefix;

/**
 * The predicate to search tasks based on the properties given.
 */
public class TaskMultiplePredicate implements Predicate<Task> {
    private final Prefix taskType;
    private final List<String> descriptions;
    private final Date date;
    private final Priority priority;
    private final String assignee;
    private final Boolean isMarked;

    /**
     * The constructor for predicate.
     * @param taskType The task type to search
     * @param descriptions The description to search
     * @param date The date of either the deadline or event
     * @param priority The priority of the task
     * @param assignee The employees assigned to the tasks
     * @param isMarked Whether the task is marked
     */
    public TaskMultiplePredicate(Prefix taskType, List<String> descriptions, Date date, Priority priority,
                                 String assignee, Boolean isMarked) {
        this.taskType = taskType;
        this.descriptions = descriptions;
        this.date = date;
        this.priority = priority;
        this.assignee = assignee;
        this.isMarked = isMarked;

        boolean isAtLeastOneNotNull = (this.taskType != null) || (this.descriptions != null) || (this.date != null)
                || (this.priority != null) || (this.assignee != null) || (this.isMarked != null);
        assert isAtLeastOneNotNull : "At least one search option should be specified";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Task task) {
        // Checks if the specific search term is specified in the parameter, then check on the task provided.
        // Defaults to true if not specified.
        boolean hasTaskType = taskType != null ? checkIfHasSpecificTaskType(task) : true;
        boolean hasKeyword = descriptions != null ? checkIfHasKeywords(task) : true;
        boolean hasDate = date != null ? checkIfHasDate(task) : true;
        boolean hasPriority = priority != null ? checkIfHasPriority(task) : true;
        boolean hasAssignee = assignee != null ? checkIfHasAssignee(task) : true;
        boolean hasIsMarked = isMarked != null ? checkedIfIsMarked(task) : true;

        return hasTaskType && hasKeyword && hasDate && hasPriority && hasAssignee && hasIsMarked;
    }

    private boolean checkIfHasSpecificTaskType(Task task) {
        boolean isTodo = taskType.equals(PREFIX_TODO) && task instanceof Todo;
        boolean isDeadline = taskType.equals(PREFIX_DEADLINE) && task instanceof Deadline;
        boolean isEvent = taskType.equals(PREFIX_EVENT) && task instanceof Event;

        return isTodo || isDeadline || isEvent;
    }

    private boolean checkIfHasKeywords(Task task) {
        String otherTaskDescription = task.getDescription().toString();
        return descriptions.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(otherTaskDescription, keyword));
    }

    private boolean checkIfHasDate(Task task) {
        if (task instanceof Todo) {
            return false;
        } else {
            Date taskDate = getDateFromTask(task);
            return taskDate.equals(date);
        }
    }

    private Date getDateFromTask(Task task) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getDate();
        } else if (task instanceof Event) {
            return ((Event) task).getDate();
        } else {
            assert false : "checkIfHasDate() did not filter out the todo";
            return null;
        }
    }

    private boolean checkIfHasPriority(Task task) {
        return task.getPriority().equals(priority);
    }

    private boolean checkIfHasAssignee(Task task) {
        return task.haveAssignees(assignee);
    }

    private boolean checkedIfIsMarked(Task task) {
        return task.isDone == isMarked.booleanValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof TaskMultiplePredicate) {
            TaskMultiplePredicate pre = (TaskMultiplePredicate) obj;
            boolean isSameTaskType = isSameOption(taskType, pre.taskType);
            boolean isSameDescription = isSameOption(descriptions, pre.descriptions);
            boolean isSameDate = isSameOption(date, pre.date);
            boolean isSamePriority = isSameOption(priority, pre.priority);
            boolean isSameAssignee = isSameOption(assignee, pre.assignee);
            boolean isSameIsMarked = isSameOption(isMarked, pre.isMarked);

            return isSameTaskType
                    && isSameDescription && isSameDate && isSamePriority && isSameAssignee && isSameIsMarked;
        }
        return false;
    }

    private boolean isSameOption(Object currentObj, Object otherObj) {
        if (otherObj != null) {
            return otherObj.equals(currentObj);
        }
        return currentObj == null;
    }
}

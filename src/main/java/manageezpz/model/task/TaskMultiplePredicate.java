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
    private final List<String> description;
    private final Date date;
    private final Priority priority;
    private final String assignee;
    private final Boolean isMarked;

    /**
     * The constructor for predicate.
     * @param taskType The task type to search
     * @param description The description to search
     * @param date The date of either the deadline or event
     * @param priority The priority of the task
     * @param assignee The employees assigned to the tasks
     * @param isMarked Whether the task is marked
     */
    public TaskMultiplePredicate(Prefix taskType, List<String> description, Date date, Priority priority,
                                 String assignee, Boolean isMarked) {
        this.taskType = taskType;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.assignee = assignee;
        this.isMarked = isMarked;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Task task) {
        // Checks if the specific search term is specified in the parameter, then check on the task provided.
        // Defaults to true if not specified.
        boolean hasTaskType = taskType != null ? checkIfHasSpecificTaskType(task) : true;
        boolean hasKeyword = description != null ? checkIfHasKeywords(task) : true;
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
        return description.stream()
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
            boolean isSameTaskType = isSameTaskType(pre.taskType);
            boolean isSameDescription = isSameDescription(pre.description);
            boolean isSameDate = isSameDate(pre.date);
            boolean isSamePriority = isSamePriority(pre.priority);
            boolean isSameAssignee = isSameAssignee(pre.assignee);
            boolean isSameIsMarked = isSameIsMarked(pre.isMarked);

            return isSameTaskType
                    && isSameDescription && isSameDate && isSamePriority && isSameAssignee && isSameIsMarked;
        }
        return false;
    }

    private boolean isSameTaskType(Prefix taskType) {
        if (taskType != null) {
            return taskType.equals(this.taskType);
        }
        return this.taskType == null;
    }

    private boolean isSameDescription(List<String> description) {
        if (description != null) {
            return description.equals(this.description);
        }
        return this.description == null;
    }

    private boolean isSameDate(Date date) {
        if (date != null) {
            return date.equals(this.date);
        }
        return this.date == null;
    }

    private boolean isSamePriority(Priority priority) {
        if (priority != null) {
            return priority.equals(this.priority);
        }
        return this.priority == null;
    }

    private boolean isSameAssignee(String assignee) {
        if (assignee != null) {
            return assignee.equals(this.assignee);
        }
        return this.assignee == null;
    }

    private boolean isSameIsMarked(Boolean isMarked) {
        if (isMarked != null) {
            return isMarked.equals(this.isMarked);
        }
        return this.isMarked == null;
    }
}

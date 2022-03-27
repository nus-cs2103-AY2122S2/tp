package manageezpz.model.task;

import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;
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
    private final Task.Priority priority;
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
    public TaskMultiplePredicate(
            Prefix taskType, List<String> description, Date date, Task.Priority priority, String assignee,
            Boolean isMarked) {
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
        boolean hasTaskType = !taskType.equals(PREFIX_TASK) ? checkIfHasSpecificTaskType(task, taskType) : true;
        boolean hasKeyword = description != null ? checkIfHasKeywords(task, description) : true;
        boolean hasDate = date != null ? checkIfHasDate(task, date) : true;
        boolean hasPriority = priority != null ? checkIfHasPriority(task, priority) : true;
        boolean hasAssignee = assignee != null ? checkIfHasAssignee(task, assignee) : true;
        boolean hasIsMarked = isMarked != null ? checkedIfIsMarked(task, isMarked.booleanValue()) : true;

        return hasTaskType && hasKeyword && hasDate && hasPriority && hasAssignee && hasIsMarked;
    }

    private boolean checkIfHasSpecificTaskType(Task task, Prefix taskType) {
        boolean isTaskTodo = taskType.equals(PREFIX_TODO) && (task instanceof Todo);
        boolean isTaskDeadline = taskType.equals(PREFIX_DEADLINE) && (task instanceof Deadline);
        boolean isTaskEvent = taskType.equals(PREFIX_EVENT) && (task instanceof Event);
        return isTaskTodo || isTaskDeadline || isTaskEvent;
    }

    private boolean checkIfHasKeywords(Task task, List<String> description) {
        return description.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getDescription().toString(), keyword));
    }

    private boolean checkIfHasDate(Task task, Date date) {
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
            return null;
        }
    }

    private boolean checkIfHasPriority(Task task, Task.Priority priority) {
        return task.getPriority().equals(priority);
    }

    private boolean checkIfHasAssignee(Task task, String assignees) {
        return task.haveAssignees(assignee);
    }

    private boolean checkedIfIsMarked(Task task, boolean booleanValue) {
        return task.isDone == booleanValue;
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
            boolean isSameTaskType = pre.taskType.equals(taskType);
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

    private boolean isSamePriority(Task.Priority priority) {
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

package seedu.address.model.person;

import java.util.ArrayList;

/**
 * Maintains the list of tasks assigned to a student.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Constructs a {@code Task}.
     */
    TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to list of tasks.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task at {@code index} from the list of tasks.
     *
     * @param index the index of the task to be removed.
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }

    /**
     * Marks the task at {@code index} from the list of tasks as complete.
     *
     * @param index the index of the task to be marked complete.
     */
    public void markTaskAsComplete(int index) {
        taskList.get(index).markComplete();
    }

    /**
     * Marks the task at {@code index} from the list of tasks as not complete.
     *
     * @param index the index of the task to be marked not complete.
     */
    public void markTaskAsNotComplete(int index) {
        taskList.get(index).markNotComplete();
    }

    /**
     * Checks if the {@code task} is already present in the list of tasks.
     *
     * @param task the task name to be checked.
     * @return true if the task is already present ;false otherwise.
     */
    public boolean isTaskAlreadyPresent(Task task) {
        return taskList.stream().map(x -> x.isTaskNameEqual(task.getTaskName())).anyMatch(y -> y == true);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks.
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }

    /**
     * Checks whether there are any tasks in the list.
     *
     * @return true if the there are no tasks; false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    @Override
    public String toString() {
        String taskStr = "";

        int idxNum = 1;
        String period = ".";

        for (Task task: taskList) {
            taskStr += idxNum + period + task + "\n";
            idxNum++;
        }

        return taskStr;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && taskList.equals(((TaskList) other).taskList));
    }
}

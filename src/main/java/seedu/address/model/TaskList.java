package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

public class TaskList implements ReadOnlyTaskList {
    private final ArrayList<Task> taskList;
    private final UniqueTaskList uniqueTaskList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        taskList = new ArrayList<>();
        uniqueTaskList = new UniqueTaskList();
    }

    public TaskList() {}

    /**
     * Creates a TaskList using the Tasks in the {@code toBeCopied}
     */
    public TaskList(ReadOnlyTaskList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code TaskList} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskList newData) {
        requireNonNull(newData);

        setTaskList(newData.getObservableTaskList());
    }

    public void setTaskList(List<Task> tasks) {
        requireNonNull(tasks);
        this.taskList.addAll(tasks);
        this.uniqueTaskList.addAll(tasks);
    }

    /**
     * Add a task to the taskList.
     *
     * @param task the Task to be added which must not be null
     */
    public void addTask(Task task) {
        requireNonNull(task);
        this.taskList.add(task);
        this.uniqueTaskList.addTask(task);
    }

    /**
     *  Add a task to the taskList at a specific index.
     *
     * @param task the Task to be added which must not be null
     * @param taskId the location to be added in which must not be null
     */
    public void addTask(Task task, Integer taskId) throws CommandException {
        uniqueTaskList.addTask(task, taskId); //throws an exception if adding duplicate task
        requireNonNull(task);
        requireNonNull(taskId);
        this.taskList.add(taskId - 1, task); // to convert to zero-based indexing
    }

    /**
     * Returns the taskList.
     *
     * @return the taskList with all the tasks contained
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int size() {
        return this.taskList.size();
    }

    /**
     * Deletes a task from the tasklist in the specified index.
     *
     * @param taskNumber the index of the task to be deleted
     */
    public void deleteTask(Integer taskNumber) {
        taskList.remove(taskNumber - 1); // to convert to zero-based indexing
        uniqueTaskList.deleteTask(taskNumber);
    }

    /**
     * Returns an ArrayList of matching tasks based on keyword.
     *
     * @param input the keyword input by user
     * @return the list of ordered tasks that contain the keyword
     */
    public String findTask(String input) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task curr = this.taskList.get(i);
            // Solution below adapted from
            // https://stackoverflow.com/questions/25483114/regex-to-find-whole-word-in-text-but-case-insensitive
            String pattern = "(?i)(?<=|^|\\.)"
                    + Pattern.quote(input) + "(?=\\s|$|\\.)"; //bypass case sensitivity with regex
            if (Pattern.compile(pattern).matcher(curr.toString()).find()) {
                matchingTasks.add(curr);
            }
        }
        String orderedList = getOutput(matchingTasks);
        return orderedList;
    }

    /**
     * Returns a String of all tasks.
     *
     * @return the list of ordered tasks
     */
    public String viewTask() {
        String orderedList = getOutput(taskList);
        return orderedList;
    }

    /**
     * Provides an ordered list of tasks that contains keyword.
     *
     * @param list - the list of tasks that matches keyword
     * @return String of ordered list of tasks.
     */
    private String getOutput(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            int order = i + 1;
            if (i == list.size() - 1) {
                sb.append(order).append(". ").append(list.get(i).toString());
            } else {
                sb.append(order).append(". ").append(list.get(i).toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Provides the task in the corresponding index
     * @param taskId task id of the task to be retrieved
     * @return task with the matching ID in task list
     */
    public Task getTask(Integer taskId) {
        return taskList.get(taskId - 1); // to convert to zero-based
    }

    /**
     * Retrieves the observable list of tasks
     * @return observable list of tasks
     */
    @Override
    public ObservableList<Task> getObservableTaskList() {
        return uniqueTaskList.asUnmodifiableObservableList();

    }
}



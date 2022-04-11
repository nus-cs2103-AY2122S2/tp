package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

public class TypicalTask {
    public static final Task DUMMY_TASK = new Task("dummy", "01/01/2023");

    private TypicalTask() {} // prevent instantiation

    public static TaskList getTypicalTaskList() {
        TaskList taskList = new TaskList();
        for (Task task : getTypicalTask()) {
            taskList.addTask(task);
        }
        return taskList;
    }

    public static List<Task> getTypicalTask() {
        return new ArrayList<>(Arrays.asList(DUMMY_TASK));
    }
}

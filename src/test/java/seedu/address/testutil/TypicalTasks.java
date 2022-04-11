package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ASSIGNMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_HOMEWORK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.model.tasks.Task;

public class TypicalTasks {
    public static final Task HOMEWORK =
            new TaskBuilder().withDesc(VALID_DESCRIPTION_HOMEWORK).withDeadline(VALID_DEADLINE_HOMEWORK).build();
    public static final Task ASSIGNMENT =
            new TaskBuilder().withDesc(VALID_DESCRIPTION_ASSIGNMENT).withDeadline(VALID_DEADLINE_ASSIGNMENT).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code ContactList} with all the typical persons.
     */
    public static PriorityTaskList getTypicalTaskList() {
        PriorityTaskList tl = new PriorityTaskList();
        for (Task task : getTypicalTasks()) {
            tl.add(task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(HOMEWORK, ASSIGNMENT));
    }
}

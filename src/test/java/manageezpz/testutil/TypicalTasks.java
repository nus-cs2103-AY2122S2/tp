package manageezpz.testutil;

import static manageezpz.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import manageezpz.model.AddressBook;
import manageezpz.model.task.Task;

public class TypicalTasks {

    public static final Task READ_BOOK = new TaskBuilder().withDescription("Read Book").build();
    public static final Task RETURN_BOOK = new TaskBuilder().withDescription("Return Book").build();
    public static final Task GO_FOR_RUN = new TaskBuilder().withDescription("Go for run").build();
    public static final Task GET_HAIRCUT = new TaskBuilder().withDescription("Get Haircut").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task GET_A_DRINK = new TaskBuilder().withDescription(VALID_TASK_DESCRIPTION).build();


    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTask()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTask() {
        return new ArrayList<>(Arrays.asList(READ_BOOK, RETURN_BOOK, GO_FOR_RUN, GET_HAIRCUT));
    }
}

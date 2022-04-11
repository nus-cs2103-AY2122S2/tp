package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code group} objects with {@code tasks}to be used in tests.
 */
public class TypicalTasks {
    public static final Group NUS_FINTECH_SOCIETY = new GroupBuilder().withGroupName("NUS Fintech Society").build();
    public static final Group NUS_DATA_SCIENCE_SOCIETY =
            new GroupBuilder().withGroupName("NUS Data Science Society").build();

    public static final Task MEETING = new TaskBuilder().withTaskName("Meeting").build();
    public static final Task PRESENTATION = new TaskBuilder().withTaskName("Presentation").build();
    public static final Task NON_EXISTENT_TASK = new TaskBuilder().withTaskName("Non existent").build();

    /**
     * Returns an {@code AddressBook} with all the typical groups with the tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            for (Task task: getTypicalTasks()) {
                group.addTask(task);
            }
            ab.addGroup(group);
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(NUS_FINTECH_SOCIETY, NUS_DATA_SCIENCE_SOCIETY));
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(MEETING, PRESENTATION));
    }
}

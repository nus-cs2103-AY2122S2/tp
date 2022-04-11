package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskList;
import seedu.address.model.task.Task;


public class TypicalTasks {

    public static final Task BRUSH_TEETH = new TaskBuilder().withTaskName("Brush my teeth")
                    .withDateTime(LocalDateTime.of(2050, 12, 15, 21, 0))
                    .withTags("Chores").withNoLink().build();

    public static final Task LAUNDRY = new TaskBuilder().withTaskName("Do the laundry")
            .withDateTime(LocalDateTime.of(2050, 12, 15, 21, 0))
            .withTags("Chores").withNoLink().build();

    public static final Task CONSULTATION = new TaskBuilder().withTaskName("Consultation with students")
            .withDateTime(LocalDateTime.of(2050, 8, 3, 14, 0))
            .withTags("Consult")
            .withLink("https:google.com")
            .build();

    public static final Task INVIGILATOR_MEETING = new TaskBuilder().withTaskName("Meeting with exam invigilators")
            .withDateTime(LocalDateTime.of(2050, 2, 5, 14, 30))
            .withTags("Meeting")
            .withLink("https:apple.com")
            .build();

    public static final Task EXAM_INVIGILATION = new TaskBuilder().withTaskName("ZOOM INVIGILATING")
            .withDateTime(LocalDateTime.of(2050, 2, 5, 14, 30))
            .withTags("Meeting")
            .withLink("https:zoom.com")
            .build();

    public static final Task CONSULATION_CS2103 = new TaskBuilder().withTaskName("CS2103 CONSULTATION")
            .withDateTime(LocalDateTime.of(2050, 2, 5, 14, 30))
            .withTags("Meeting")
            .withLink("https:zoom.com")
            .build();

    /**
     * Returns array of 4 test tasks.
     *
     * @return Array of 4 test tasks.
     */
    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(BRUSH_TEETH, LAUNDRY, CONSULTATION, INVIGILATOR_MEETING));
    }

    /**
     * Returns a {@code TaskList} with all the typical tasks.
     */
    public static TaskList getTypicalTaskList() {
        TaskList tl = new TaskList();
        for (Task task : getTypicalTasks()) {
            tl.addTask(task);
        }
        return tl;
    }

}

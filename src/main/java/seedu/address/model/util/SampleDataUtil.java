package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code TaskList} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Name("Complete Tutorial"), new Description("Hand draw UML diagrams"),
                new CompletionStatus("true"), new Deadline("2022-03-10"), Priority.LOW, getTagSet("CS2103T")),
            new Task(new Name("Review individual projects"),
                new Description("To review two ips that are sent to my email, for 4 participation points"),
                new CompletionStatus("true"), new Deadline("2022-03-12"), Priority.LOW,
                getTagSet("CS2103T", "participation")),
            new Task(new Name("Complete Assignment"),
                new Description("Name submission file as assignment1_name and upload to Luminus folder"),
                new CompletionStatus("false"), new Deadline("2022-04-01"), Priority.LOW, getTagSet("CS2105")),
            new Task(new Name("Complete CS3240 Milestone G1"),
                new Description("Interview users, analyse responses and create wireframes"),
                new CompletionStatus("true"), new Deadline("2022-11-22"), Priority.LOW, getTagSet("CS3240"))
        };
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        TaskList sampleTl = new TaskList();
        for (Task sampleTask : getSampleTasks()) {
            sampleTl.addTask(sampleTask);
        }
        return sampleTl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}

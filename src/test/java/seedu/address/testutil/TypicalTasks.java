package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MIDTERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_LOW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2102;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TEST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TagList;
import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task CS2105_MIDTERM = new TaskBuilder().withName("CS2105 Midterm")
            .withCompletionStatus("false").withDeadline("2022-03-03")
            .withDescription("Lecture 1-5, Tutorial 1-4")
            .withPriority("low")
            .withTags("CS2105", "exam").build();

    /** Task with Completion Status set to true **/
    public static final Task CS2106_FINALS = new TaskBuilder().withName("CS2106 Finals")
            .withCompletionStatus("true")
            .withDeadline("2022-11-11").withDescription("All lectures and tutorials")
            .withPriority("low").withTags("CS2106", "exam").build();

    public static final Task CS2107_TUTORIAL = new TaskBuilder().withName("CS2107 Tutorial")
            .withDescription("Week 9 Tutorial").withDeadline("2022-03-16").withCompletionStatus("false")
            .withPriority("low").withTags("CS2107").build();

    public static final Task CS2103T_PROJECT = new TaskBuilder().withName("CS2103T Project")
            .withDescription("V1.2b completion").withDeadline("2022-03-13").withCompletionStatus("false")
            .withPriority("low").withTags("CS2103T").build();

    public static final Task MEET_ALICE = new TaskBuilder().withName("Meet Alice")
            .withPriority("low").withDescription("Meeting Alice at NUS").withDeadline("2022-04-01")
            .withCompletionStatus("false").build();

    public static final Task BUY_ART_SUPPLIES = new TaskBuilder().withName("Buy Art Supplies")
            .withDescription("Buy Art supplies for art project").withDeadline("2022-03-21")
            .withPriority("low").withCompletionStatus("false").build();

    public static final Task APPLY_INTERNSHIP = new TaskBuilder().withName("Apply Internship")
            .withDescription("Apply for opening at Shopee").withDeadline("2022-03-24")
            .withPriority("low").withCompletionStatus("false").build();

    public static final Task CS2107_ASSIGNMENT = new TaskBuilder().withName("CS2107 Assignment")
            .withDescription("Assignment 2").withDeadline("2022-03-20")
            .withPriority("low").withCompletionStatus("false").build();

    public static final Task CS2102_MEETING = new TaskBuilder().withName("CS2102 Meeting")
            .withDescription("Meeting").withDeadline("2022-04-06")
            .withPriority("medium").withCompletionStatus("true").build();


    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task CS2103T_TUTORIAL = new TaskBuilder().withName(VALID_NAME_TUTORIAL)
            .withDescription(VALID_DESCRIPTION_TUTORIAL).withDeadline(VALID_DEADLINE_TUTORIAL)
            .withCompletionStatus(VALID_COMPLETION_STATUS_TRUE).withPriority(VALID_PRIORITY_LOW)
            .withTags(VALID_TAG_CS2103T).build();
    public static final Task CS2102_MIDTERM = new TaskBuilder().withName(VALID_NAME_MIDTERM)
            .withDescription(VALID_DESCRIPTION_MIDTERM).withDeadline(VALID_DEADLINE_MIDTERM)
            .withCompletionStatus(VALID_COMPLETION_STATUS_FALSE).withPriority(VALID_PRIORITY_LOW)
            .withTags(VALID_TAG_CS2102, VALID_TAG_TEST).build();

    // Used in {@code ComparatorFactoryTest} and {@code SortCommandTest}
    public static final Task SAMPLE_TASK_ONE = new TaskBuilder().withName("AAA KEYWORD")
            .withDescription("TEST DESCRIPTION ONE").withDeadline("2022-02-21")
            .withPriority("high").withCompletionStatus("false").build();
    public static final Task SAMPLE_TASK_TWO = new TaskBuilder().withName("BBB KEYWORD")
            .withDescription("TEST DESCRIPTION TWO").withDeadline("2022-03-24")
            .withPriority("low").withCompletionStatus("false").build();
    public static final Task SAMPLE_TASK_THREE = new TaskBuilder().withName("CCC")
            .withDescription("TEST DESCRIPTION THREE").withDeadline("2022-01-20")
            .withPriority("medium").withCompletionStatus("false").build();
    public static final Task SAMPLE_TASK_FOUR = new TaskBuilder().withName("DDD KEYWORD")
            .withDescription("TEST DESCRIPTION THREE").withDeadline("2022-02-15")
            .withPriority("medium").withCompletionStatus("true").build();


    public static final String KEYWORD_MATCHING_NUS = "NUS"; // A keyword that matches NUS

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns a {@code TaskList} with all the typical Tasks.
     */
    public static TaskList getTypicalTaskList() {
        TaskList tl = new TaskList();
        for (Task task : getTypicalTasks()) {
            tl.addTask(task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(CS2103T_PROJECT, CS2106_FINALS, CS2105_MIDTERM,
                CS2107_TUTORIAL, MEET_ALICE, CS2102_MEETING));
    }

    /**
     * Returns a {@code TaskList} with all the typical Tasks optimized for Sort tests.
     */
    public static TaskList getTypicalTaskListForSort() {
        TaskList tl = new TaskList();
        for (Task task : getTypicalTasksForSort()) {
            tl.addTask(task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasksForSort() {
        return new ArrayList<>(Arrays.asList(SAMPLE_TASK_ONE, SAMPLE_TASK_TWO,
                SAMPLE_TASK_THREE, SAMPLE_TASK_FOUR));
    }

    /**
     * Returns a {@code TaskList} with all the typical Tasks which have no tags.
     */
    public static TaskList getTypicalTaskListWithNoTags() {
        TaskList tl = new TaskList();
        for (Task task : getTypicalTasksWithNoTags()) {
            tl.addTask(task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasksWithNoTags() {
        return new ArrayList<>(Arrays.asList(MEET_ALICE, BUY_ART_SUPPLIES, APPLY_INTERNSHIP, CS2107_ASSIGNMENT));
    }

    /**
     * Returns a {@code TagList} with all the tags of typical Tasks.
     */
    public static TagList getTypicalTagList() {
        TagList tagList = new TagList(new TaskList());
        for (Task task : getTypicalTasks()) {
            tagList.addTagsOfTask(task);
        }
        return tagList;
    }

}
